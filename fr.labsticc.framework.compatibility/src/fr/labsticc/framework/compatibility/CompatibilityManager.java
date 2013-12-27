package fr.labsticc.framework.compatibility;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.SystemUtil;
import fr.labsticc.framework.ide.resource.ExtensionsResourcesVisitor;

public class CompatibilityManager {
	
	private static final String UPDATE_DIALOG_TITLE = "Compatibility Update";

	private final List<ICompatibilityUpdater> updaters;
	
	private final Shell shell;
	
	private final IPreferenceStore prefStore;
	
	public CompatibilityManager( 	final List<ICompatibilityUpdater> p_updaters,
									final Shell p_shell,
									final IPreferenceStore p_prefStore ) {
		updaters = p_updaters;
		shell = p_shell;
		prefStore = p_prefStore;
	}

	public void applyCompatibilityPatchs() 
	throws Exception {
		if ( !updaters.isEmpty() ) {
			final Map<IFile, IUpdatedData> updatesToBePerformed = computeUpdates( updaters );

			if ( confirmUpdates( updatesToBePerformed ) ) {
				performUpdates( updatesToBePerformed );
			}
		}
	}
	
	private boolean confirmUpdates( final Map<IFile, IUpdatedData> p_updatesToBePerformed ) {
		final StringBuilder completeUpdatesInfo = new StringBuilder( "The following updates are required by the current configuration." );
		boolean hasChanges = false;
		
		for ( final Map.Entry<IFile, IUpdatedData> entry : p_updatesToBePerformed.entrySet() ) {
			final IUpdatedData updateData = entry.getValue();
			
			if ( updateData.hasChanges() ) {
				hasChanges = true;
				final ICompatibilityUpdater updater = updateData.getUpdater();
				assert updater != null : "Updater should not be null.";
				completeUpdatesInfo.append( SystemUtil.getLineSeparator() + SystemUtil.getLineSeparator() + " File " + entry.getKey() + " needs to be updated;" );
				completeUpdatesInfo.append( SystemUtil.getLineSeparator() + updater.getDescription() );
				final List<String> updateInfo = updateData.getUpdateInfo();

				for ( final String detail : updateInfo ) {
					completeUpdatesInfo.append( SystemUtil.getLineSeparator() + SystemUtil.getLineSeparator() + detail );
				}
			}
		}
		
		if ( hasChanges ) {
			return Boolean.TRUE.equals( MessageDialog.openConfirm( shell , UPDATE_DIALOG_TITLE, completeUpdatesInfo.toString() ) );
		}
		
		return false;
	}
	
	private Map<IFile, IUpdatedData> computeUpdates( final List<ICompatibilityUpdater> p_updaters ) 
	throws ResourceAccessException, CoreException {
		final Map<IFile, IUpdatedData> updatesToBePerformed = new HashMap<IFile, IUpdatedData>();
		
		for ( final IFile file : allFilesInWorkspace() ) {
			for ( final ICompatibilityUpdater updater : p_updaters ) {
				final String key = getKey( updater, file );
				
				// File has not been updated
				if ( !prefStore.getBoolean( key ) ) {
					final IUpdatedData updatedContent = updater.update( file );
					
					if ( updatedContent != null ) {
						updatesToBePerformed.put( file, updatedContent );
					}
				}
			}
		}
		
		return updatesToBePerformed;
	}
	
	private Collection<IFile> allFilesInWorkspace() 
	throws CoreException, ResourceAccessException {
		final ExtensionsResourcesVisitor visitor = new ExtensionsResourcesVisitor( null );
		ResourcesPlugin.getWorkspace().getRoot().accept( visitor );

		final Collection<IFile> files = new ArrayList<IFile>();
		
		for ( final IResource res : visitor.getFoundResources() ) {
			if ( res instanceof IFile ) {
				files.add( (IFile) res );
			}
		}
		
		return files;
	}
	
	private void performUpdates( final Map<IFile, IUpdatedData> p_updatesToBePerformed ) 
	throws Exception {
		final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

			@Override
			protected void execute( final IProgressMonitor p_monitor )
			throws CoreException, InvocationTargetException, InterruptedException {
				if ( p_monitor != null ) {
					p_monitor.beginTask( "Updating " + p_updatesToBePerformed.size() + " files.",  p_updatesToBePerformed.size() );
				}
				
				try {
					for ( final Map.Entry<IFile, IUpdatedData> entry : p_updatesToBePerformed.entrySet() ) {
						final IUpdatedData updateData = entry.getValue();
						final IFile file = entry.getKey();
						
						if ( updateData.hasChanges() ) {
							file.setContents( new ByteArrayInputStream( updateData.getUpdatedContent() ), IResource.FORCE, null );
						}
						
						prefStore.setValue( getKey( updateData.getUpdater(), file ),  true );
						
						if ( p_monitor != null ) {
							p_monitor.worked( 1 );
						}
					}
				}
				finally {
					if ( p_monitor != null ) {
						p_monitor.done();
					}
				}
			}
		};
		
		//operation.run( null );
		try {
			new ProgressMonitorDialog( shell ).run( true, false, operation );
		}
		catch( final InterruptedException p_ex ) {
			// Nothing to do.
		}
	}
	
	private String getKey( 	final ICompatibilityUpdater p_updater,
							final IFile p_file ) {
		return "Updated_" + p_file.getFullPath() + "_" + p_updater.getUpdateId();
	}
}
