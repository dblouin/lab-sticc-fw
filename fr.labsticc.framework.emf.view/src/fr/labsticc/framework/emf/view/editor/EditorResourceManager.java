package fr.labsticc.framework.emf.view.editor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.IExceptionHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.emf.core.resource.ResourceDeltaVisitor;
import fr.labsticc.framework.ide.action.ExceptionWorkspaceModifyOperation;

/**
 * Resource manager for the EMF based editors.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class EditorResourceManager implements IEditorResourceManager  {

	private final Logger trace = LoggerFactory.getLogger( getClass() );
	
	private final List<IEditorListener> editorListeners = new ArrayList<IEditorListener>(); 
	
	private final Collection<Resource> changedResources;

	private final Collection<Resource> savedResources;

	private final Collection<Resource> removedResources;

	private final Map<Resource, Diagnostic> resourceToDiagnosticMap;

	private final EditingDomain editingDomain;

	private boolean shouldListenForChange;
	
	private final ResourceSet resourceSet;
	
	private final EClass rootClass;

	private final String editorId;
	
	private final String resProblemMessage;
	
	private final IExceptionHandler exceptionHandler;
	
	private Set<String> ignoredResourceExtensions;
	
	public EditorResourceManager( 	final EditingDomain p_editingDomain,
									final EClass p_rootClass,
									final String p_editorId,
									final String p_resProblemMessage,
									final IExceptionHandler p_exceptionHandler ) {
		this( p_editingDomain.getResourceSet(), p_rootClass, p_editorId, p_resProblemMessage, p_exceptionHandler );
		
	}
	
	public EditorResourceManager( 	final ResourceSet p_resourceSet,
									final EClass p_rootClass,
									final String p_editorId,
									final String p_resProblemMessage,
									final IExceptionHandler p_exceptionHandler ) {
		this( null, p_resourceSet, p_rootClass, p_editorId, p_resProblemMessage, p_exceptionHandler );
	}
	
	private EditorResourceManager( 	final EditingDomain p_editingDomain,
									final ResourceSet p_resourceSet,
									final EClass p_rootClass,
									final String p_editorId,
									final String p_resProblemMessage,
									final IExceptionHandler p_exceptionHandler ) {
		if ( p_exceptionHandler == null ) {
			throw new IllegalArgumentException( "Exception handler cannot be null." );
		}

		editingDomain = p_editingDomain;
		resourceSet = p_resourceSet;
		rootClass = p_rootClass;
		editorId = p_editorId;
		resProblemMessage = p_resProblemMessage;
		changedResources = new ArrayList<Resource>();
		savedResources = new ArrayList<Resource>();
		removedResources = new ArrayList<Resource>();
		resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();
		shouldListenForChange = true;
		exceptionHandler = p_exceptionHandler;
		ignoredResourceExtensions = new HashSet<String>();
	}
	
	@Override
	public boolean addEditorListener( final IEditorListener p_listener ) {
		return editorListeners.add( p_listener );
	}
	
	@Override
	public boolean removeEditorListener( final IEditorListener p_listener ) {
		return editorListeners.remove( p_listener );
	}

	@Override
	public Resource loadOrCreateResource( final URI p_resURI ) {
		if ( resourceSet.getURIConverter().exists( p_resURI, null ) ) {
			return resourceSet.getResource( p_resURI, true );
		}

		return resourceSet.createResource( p_resURI, null );
	}

	@Override
	public Resource loadResource( final URI p_resURI ) {
		Resource resource = null;
		Throwable exception = null;
		inhibitChangeListening();
		
		try {
			fireResourceLoading( p_resURI );
			
			resource = resourceSet.getResource( p_resURI, true );

			fireResourceLoaded( resource );
		}
		catch ( final Throwable p_th ) {
			trace.error( this.toString(), p_th );
			
			exception = p_th;
		}

		analyzeResourceProblems( resource, exception );
		activateChangeListening();
		
		return resource;
	}
	
	@Override
	public void saveResource( 	final Shell p_shell,
								final Resource p_resource )
	throws ResourceAccessException, InterruptedException {
		final ExceptionWorkspaceModifyOperation operation = new ExceptionWorkspaceModifyOperation() {
		
			// This is the method that gets invoked when the operation runs.
			@Override
			protected void execute( final IProgressMonitor p_monitor ) {
				try {
					p_monitor.beginTask( "Saving QAML Models", 1 );
					
					saveResource( p_resource, true );
					p_monitor.worked( 1 );
				}
				catch ( final Throwable p_ex ) {
					exception = p_ex;
				}
				finally {
					p_monitor.done();
				}
			}
		};

		// This runs the options, and shows progress.
		try {
			new ProgressMonitorDialog( p_shell ).run( true, false, operation );
			
			if ( operation.getException() instanceof ResourceAccessException ) {
				throw (ResourceAccessException) operation.getException();
			}
			
			if ( operation.getException() != null ) {
				throw new ResourceAccessException( operation.getException() );
			}
		} 
		catch ( final InvocationTargetException p_ex ) {
			throw new ResourceAccessException( p_ex.getTargetException() );
		}
	}

	private void saveResource( 	final Resource p_resource, 
								final boolean pb_commit )
	throws ResourceAccessException {
		try {
			p_resource.save( null );
			
			if ( pb_commit ) {
				refreshCommandStack();
				getSavedResources().add( p_resource );
			}
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	/**
	 * Returns a diagnostic describing the errors and warnings listed in the resource
	 * and the specified exception (if any).
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	private Diagnostic analyzeResourceProblems( 	final Resource p_resource, 
													final Throwable p_exception ) {
		final Diagnostic diagnostic;
		
		if ( 	p_resource != null && 
				( !p_resource.getErrors().isEmpty() || !p_resource.getWarnings().isEmpty() ) ) {
			diagnostic = new BasicDiagnostic( 	Diagnostic.ERROR,
												editorId,
												0,
												resProblemMessage,
												new Object [] { p_exception == null ? (Object) p_resource : p_exception } );
			( (BasicDiagnostic) diagnostic ).merge(EcoreUtil.computeDiagnostic( p_resource, true));
		}
		else if ( p_exception != null ) {
			diagnostic = new BasicDiagnostic( 	Diagnostic.ERROR,
												editorId,
												 0,
												 resProblemMessage,
												 new Object[] { p_exception } );
		}
		else {
			diagnostic = Diagnostic.OK_INSTANCE;
		}

		if ( diagnostic.getSeverity() != Diagnostic.OK ) {
			resourceToDiagnosticMap.put( p_resource, diagnostic );
		}
		else {
			resourceToDiagnosticMap.remove( p_resource );
		}
		
		return diagnostic;
	}

	protected Collection<Resource> getChangedResources() {
		return changedResources;
	}

	protected Collection<Resource> getSavedResources() {
		return savedResources;
	}

	protected Collection<Resource> getRemovedResources() {
		return removedResources;
	}

	@Override
	public void dispose() {
		clearAllResources();
		fireEditorClosed();
		editorListeners.clear();
	}

	@Override
	public void clearAllResources() {
		clearChangedResources();
		clearRemovedResources();
		clearSavedResources();
	}

	@Override
	public void clearChangedResources() {
		getChangedResources().clear();
	}

	@Override
	public void clearRemovedResources() {
		getRemovedResources().clear();
	}

	@Override
	public void clearSavedResources() {
		getSavedResources().clear();
	}

	@Override
	public Collection<Diagnostic> getAllDiagnosticsKO() {
		final Collection<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		
		for ( final Diagnostic diagnostic : resourceToDiagnosticMap.values() ) {
			if ( diagnostic.getSeverity() != Diagnostic.OK) {
				diagnostics.add( diagnostic );
			}
		}
		
		return diagnostics;
	}

	@Override
	public boolean hasChangedResources() {
		return !getChangedResources().isEmpty();
	}

	@Override
	public boolean hasRemovedResources() {
		return !getRemovedResources().isEmpty();
	}

	@Override
	public void removeSavedResourcesFromChangedResources() {
		getChangedResources().removeAll( getSavedResources() );
	}

	@Override
	public void saveResources( final Shell p_shell )
	throws ResourceAccessException, InterruptedException {
		final ExceptionWorkspaceModifyOperation operation =
			new ExceptionWorkspaceModifyOperation() {
				// This is the method that gets invoked when the operation runs.
				//
				@Override
				protected void execute( final IProgressMonitor p_monitor ) {
					for ( final Resource resource : resourceSet.getResources()) {
						if ( !resource.getContents().isEmpty() && !isReadOnly( resource ) ) {
							
							try {
								
								// Disable resource change listener for that resource
								saveResource( resource, false );
								savedResources.add( resource );
							}
							catch ( Throwable p_th ) {
								analyzeResourceProblems( resource, p_th );
								exception = p_th;
							}
							finally {
								p_monitor.done();
							}
						}
					}
				}
			};

		// This runs the options, and shows progress.
		//
		try {
			new ProgressMonitorDialog( p_shell ).run( true, false, operation );
			
			if ( operation.getException() != null ) {
				throw new ResourceAccessException( operation.getException() );
			}
				
			refreshCommandStack();
		} 
		catch ( final InvocationTargetException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	private void refreshCommandStack() {
		final BasicCommandStack commandStack = getBasicCommandStack();  
		
		if ( commandStack != null ) {

			// Refresh the necessary state.
			commandStack.saveIsDone();
		}
	}
	
	private BasicCommandStack getBasicCommandStack() {
		if ( getEditingDomain() == null ) {
			return null;
		}
		
		final CommandStack commandStack = getEditingDomain().getCommandStack();  
			
		if ( commandStack instanceof BasicCommandStack ) {
			return (BasicCommandStack) commandStack;
		}
		
		return null;
	}
	
	private boolean isReadOnly( final Resource p_resource ) {
		return getEditingDomain() == null ? false : getEditingDomain().isReadOnly( p_resource );
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	@Override
	public void activateChangeListening() {
		shouldListenForChange = true;
	}

	@Override
	public void inhibitChangeListening() {
		shouldListenForChange = false;
	}

	@Override
	public boolean isListeningForChangeEvents() {
		return shouldListenForChange;
	}

	private void fireResourcesChanged() {
		for ( final IEditorListener listener : editorListeners ) {
			listener.editorResourcesChanged( this );
		}
	}

	private void fireResourceLoading( final URI p_resourceURI ) {
		for ( final IEditorListener listener : editorListeners ) {
			listener.editorResourceLoading( this , p_resourceURI );
		}
	}

	private void fireResourceLoaded( final Resource p_resource ) {
		for ( final IEditorListener listener : editorListeners ) {
			listener.editorResourceLoaded( this , p_resource );
		}
	}

	private void fireEditorClosed() {
		for ( final IEditorListener listener : editorListeners ) {
			listener.editorClosed( this );
		}
	}

	@Override
	public Iterator<Resource> changedResources() {
		return getChangedResources().iterator();
	}

	@Override
	public Iterator<Resource> removedResources() {
		return getRemovedResources().iterator();
	}
	
	/**
	 * Handles activation of the editor or it's associated views.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean handleActivate() {
		boolean shouldRefreshEditor = false;
		
		if ( editingDomain instanceof AdapterFactoryEditingDomain ) {
			// Recompute the read only state.
			//
			final AdapterFactoryEditingDomain adaptEdDomain = (AdapterFactoryEditingDomain) editingDomain;
			
			if ( adaptEdDomain.getResourceToReadOnlyMap() != null ) {
				adaptEdDomain.getResourceToReadOnlyMap().clear();
	
				// Refresh any actions that may become enabled or disabled.
				shouldRefreshEditor = true;
			}
		}

		if ( removedResources.isEmpty() && !changedResources.isEmpty() ) {
			changedResources.removeAll( savedResources );
			handleChangedResources();
			changedResources.clear();
			savedResources.clear();
		}

		clearAllResources();
		
		return shouldRefreshEditor;
	}

	/**
	 * Handles what to do with changed resources on activation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void handleChangedResources() {
		if ( !changedResources.isEmpty() && !isDirty() ) {
			if (isDirty()) {
				changedResources.addAll( resourceSet.getResources());
			}
			
			if ( editingDomain != null ) {
				editingDomain.getCommandStack().flush();
			}

			//updateProblemIndication = false;
			
			for ( final Resource resource : changedResources ) {
				if ( 	!ignoredResourceExtensions.contains( resource.getURI().fileExtension() ) && 
						resource.isLoaded() ) {
					resource.unload();
					
					try {
						resource.load( Collections.EMPTY_MAP );
					}
					catch ( final IOException p_exception ) {
						if ( !resourceToDiagnosticMap.containsKey( resource ) ) {
							resourceToDiagnosticMap.put(resource, analyzeResourceProblems( resource, p_exception ) );
						}
					}
				}
			}
		}
	}

	public boolean isDirty() {
		final BasicCommandStack commandStack = getBasicCommandStack();
		
		return commandStack == null ? false : commandStack.isSaveNeeded();
	}
	
	@Override
	public void resourceChanged( final IResourceChangeEvent p_event ) {
		if ( isListeningForChangeEvents() ) {
			try {
				final IResourceDelta delta = p_event.getDelta();
				final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor( 	resourceSet,
																				getSavedResources(),
																				null,
																				rootClass );
				
				delta.accept( visitor );
				
				getRemovedResources().addAll( visitor.getRemovedResources() );
				getChangedResources().addAll( visitor.getChangedResources() );
				
				if ( !visitor.getChangedResources().isEmpty() ) {
					 handleActivate();
				}
				
				fireResourcesChanged();
			}
			catch ( final CoreException p_exception ) {
				exceptionHandler.handleException( p_exception, null );
			}
		}
	}

	@Override
	public void addIgnoredResourceExtension( final String p_extension ) {
		ignoredResourceExtensions.add( p_extension );
	}
	
	@Override
	public boolean removeIgnoredResourceExtension( final String p_extension ) {
		return ignoredResourceExtensions.remove( p_extension );
	}
}