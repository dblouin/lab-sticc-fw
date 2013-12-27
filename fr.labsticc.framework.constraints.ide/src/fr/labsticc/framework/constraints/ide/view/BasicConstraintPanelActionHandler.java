/*******************************************************************************
 * Copyright (c) 2011 Lab-STICC Universite de Bretagne Sud, Lorient.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the CeCILL-B license available
 * at :
 * en : http://www.cecill.info/licences/Licence_CeCILL-B_V1-en.html
 * fr : http://www.cecill.info/licences/Licence_CeCILL-B_V1-fr.html
 * 
 * Contributors:
 * Dominique BLOUIN (Lab-STICC UBS), dominique.blouin@univ-ubs.fr
 ******************************************************************************/
package fr.labsticc.framework.constraints.ide.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.ide.ConstraintsIDEPlugin;
import fr.labsticc.framework.constraints.ide.IDEConstraintExpressionServicesDelegate;
import fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;
import fr.labsticc.framework.constraints.model.constraints.provider.ConstraintsItemProviderAdapterFactory;
import fr.labsticc.framework.constraints.model.service.IConstraintExpressionServicesFacade;
import fr.labsticc.framework.core.IResourceHandler;
import fr.labsticc.framework.core.exception.IExceptionHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;
import fr.labsticc.framework.emf.core.resource.BundleEMFResourceHandler;
import fr.labsticc.framework.ide.util.EditorUtil;

/**
 * A basic event handler to be used with a constraint panel to define its UI actions. This handler maintains the context and
 * the formal language expression object used to store the data entered via the constraint panel.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class BasicConstraintPanelActionHandler implements IConstraintExpressionPanelActionHandler {
	
	private static final String[] ALL_FILES = new String[ 0 ];
	
	/**
	 * An array of model elements on which the defined expression is to be evaluated.
	 */
	private Object[] context;
	
	/**
	 * The expression model object to be edited with the constraint panel.
	 */
	private FormalLanguageExpression expression;

	/**
	 * A facade for dispatching the evaluation of the expression to the proper interpretation service defined for the
	 * expression's language. 
	 */
	private final IConstraintExpressionServicesFacade cstExprServicesFacade;

	/**
	 * An object used to handle exceptions (functional or system) when they occur.
	 */
	private final IExceptionHandler exceptionHandler;
	
	/**
	 * An object used to handle resources for various APIs (EMF, Eclipse IDE, Java).
	 */
	private final IResourceHandler resourcesHandler;
	
	/**
	 * Used by the library selection dialog to provide labels to for library elements of the choice of libraries 
	 * used by the expression. 
	 */
	private final ILabelProvider labelProvider;
	
	public BasicConstraintPanelActionHandler( 	final FormalLanguageExpression p_expression,
												final IExceptionHandler p_exceptionHandler ) 
	throws CoreException {
		this( p_expression, new IDEConstraintExpressionServicesDelegate(), p_exceptionHandler );
	}
	
	public BasicConstraintPanelActionHandler( 	final FormalLanguageExpression p_expression,
												final IConstraintExpressionServicesFacade p_constraintDelegate,
												final IExceptionHandler p_exceptionHandler ) 
	throws CoreException {
		expression = p_expression;
		exceptionHandler = p_exceptionHandler;
		
		// Using the Eclipse IDE integrated facade. 
		cstExprServicesFacade = p_constraintDelegate;

		// The label provider used by the library selection dialog
		labelProvider = new AdapterFactoryLabelProvider( new ConstraintsItemProviderAdapterFactory() );
		
		// Also handles the EMF resource protocols.
		resourcesHandler = new BundleEMFResourceHandler();
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#constraintTextChanged(java.lang.String, java.lang.String)
	 */
	@Override
	public void constraintTextChanged( 	final String p_languageId,
										final String p_newConstraintText ) {
		// Set the text of the expression
		expression.setText( p_newConstraintText );
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#constraintLoadingRequested(java.lang.String)
	 */
	@Override
	public String constraintLoadingRequested( final String p_languageId ) {
		// Prompt the user to chose the file from which to import the expression.
		final FileDialog fileDialog = new FileDialog( getShell(), SWT.OPEN );

		try {
			fileDialog.setFilterExtensions( languageFileExtensions( p_languageId ) );
			fileDialog.setText( "Load Constraint Expression from File" );
			final String resourceId = fileDialog.open();

			if ( resourceId != null ) {
				return FileUtil.getFileContent( resourceId );
			} 
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, null );
		}
		
		// An exception occurred or the action was canceled.
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#constraintSavingRequested(java.lang.String, java.lang.String)
	 */
	@Override
	public void constraintSavingRequested(	final String p_languageId,
											final String p_constraintText ) {
		final FileDialog fileDialog = new FileDialog( getShell(), SWT.SAVE );

		try {
			fileDialog.setFilterExtensions( languageFileExtensions( p_languageId ) );
			fileDialog.setText( "Save Constraint Expression to File" );
			final String resourceId = fileDialog.open();

			if ( resourceId != null ) {
				FileUtil.writeToFile( resourceId, p_constraintText );
			} 
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, null );
		}
	}
	
	/**
	 * Return the default file extension for the given language. It is computed from the name of the language converted to
	 * lower case.
	 * @param p_languageId A string identifying the language.
	 * @return A string array containing one element for the file extension.
	 */
	private String[] languageFileExtensions( final String p_languageId ) {
		if ( p_languageId == null ) {
			return ALL_FILES;
		}
		
		final ConstraintLanguage language = ConstraintsIDEPlugin.getDefault().getSpecification().languageById( p_languageId );
		
		return new String[] { "*." + language.getName().toLowerCase() };
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#constraintLanguageChanged(java.lang.String)
	 */
	@Override
	public Collection<String> constraintLanguageChanged( final String p_newLanguageId ) {
		// Erase the current expression text.
		expression.setText( null );
		
		// Sets the new chosen language to the expression.
		final FormalConstraintLanguage language = (FormalConstraintLanguage) ConstraintsIDEPlugin.getDefault().getSpecification().languageById( p_newLanguageId );
		expression.setLanguage( language );
		
		// Libraries
		expression.getUsedLibraries().clear();

		for( final FormalLanguageLibrary langLib : language.getLibraries() ) {
			if ( langLib.isDefault() ) {
				expression.getUsedLibraries().add( langLib );
			}
		}
		
		return expression.getUsedLibraryNames();
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#addLibrariesRequested(java.lang.String, java.util.Collection)
	 */
	@Override
	public Collection<String> addLibrariesRequested( 	final String p_languageId,
														final Collection<String> p_currentLibraryNames ) {
		// Compute the library choice to present to the user by removing the current libraries from all libraries defined for the
		// language.
		final FormalConstraintLanguage language = (FormalConstraintLanguage) ConstraintsIDEPlugin.getDefault().getSpecification().languageById( p_languageId );
		final List<FormalLanguageLibrary> libraryChoice = new ArrayList<FormalLanguageLibrary>();
		
		for ( final FormalLanguageLibrary library : language.getLibraries() ) {
			if ( !p_currentLibraryNames.contains( library.getName() ) ) {
				libraryChoice.add( library );
			}
		}
		
		try {
			final UsedLibrarySelectionDialog dialog = new UsedLibrarySelectionDialog( 	getShell(), 
																						labelProvider, 
																						expression,
																						libraryChoice );
			if ( dialog.open() == Window.OK ) {
				
				// Update the libraries used by the expression
				expression.getUsedLibraries().clear();
				expression.getUsedLibraries().addAll( dialog.getResult() );
				
				return expression.getUsedLibraryNames();
			}
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th , p_languageId );
		}
		
		// An exception occurred or the action was canceled.
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#openLibrariesRequested(java.util.Collection)
	 */
	@Override
	public void openLibrariesRequested( final Collection<String> p_libraryNames ) {
		try {
			EditorUtil.openEditors( libraryIds( p_libraryNames ), resourcesHandler );
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, p_libraryNames );
		}
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#librariesRefreshRequested(java.util.Collection)
	 */
	@Override
	public void librariesRefreshRequested( final Collection<String> p_libraryNames ) {
		for ( final String libName : p_libraryNames ) {
			try {
				final FormalConstraintLanguage language = (FormalConstraintLanguage) expression.getLanguage();
				final FormalLanguageLibrary library = language.libraryByName( libName );
				assert library != null : "Library should be found for name " + libName;

				final String libId = library.getId();
				library.getExpression().setText( libraryContent( libId ) );
				
				// Notify interpretation service in case it is caching the library.
				cstExprServicesFacade.libraryContentChanged( language.getId(), libId );
			}
			catch ( final Throwable p_th ) {
				exceptionHandler.handleException( p_th, libName );
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#librariesDeleted(java.util.Collection)
	 */
	@Override
	public void librariesDeleted( final Collection<String> p_libraryNames ) {
		for ( final String libName : p_libraryNames ) {
			final FormalLanguageLibrary library = ( (FormalConstraintLanguage) expression.getLanguage() ).libraryByName( libName );
			
			assert library != null : "Library " + libName + " to remove is not used.";
			
			// Update the libraries used by the expression
			expression.getUsedLibraries().remove( library );
		}
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#completionProposalRequested(java.lang.String, java.lang.String, java.util.Collection, fr.labsticc.framework.constraints.core.ModelingLevel)
	 */
	@Override
	public List<ICompletionProposal> completionProposalRequested(	final String p_languageId,
																	final String p_constraintText,
																	final Collection<String> p_libraryNames ) {
		try  {
			// Convert the library names displayed in the panel into their ids.
			final Collection<String> libraryIds = libraryIds( p_libraryNames );
			
			// Create a map of all library texts.
			final Map<String, String> libraryContents = libraryContent( libraryIds );
			
			// Use the facade to dispatch the call to the proper interpretation service.
			return cstExprServicesFacade.computeCompletionProposals( 	p_languageId,
																		p_constraintText,
																		libraryContents,
																		//	p_modelingLevel,
																		context );
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, context );
		}
		
		// An exception occurred or the action was canceled.
		return null;
	}
	
	/**
	 * Return a collection of library identifiers from a collection of library names.
	 * @param p_libraryNames A <code>Collection</code> of string identifiers.
	 * @return
	 */
	private Collection<String> libraryIds( final Collection<String> p_libraryNames ) {
		final Collection<String> libIds = new ArrayList<String>();
		final FormalConstraintLanguage language = (FormalConstraintLanguage) expression.getLanguage();
		
		for ( final String libName : p_libraryNames ) {
			final FormalLanguageLibrary library = language.libraryByName( libName );
			
			if ( library != null ) {
				libIds.add( library.getId() );
			}
		}
		
		return libIds;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IConstraintPanelActionHandler#constraintEvaluationRequested(java.lang.String, java.lang.String, java.util.Collection, fr.labsticc.framework.constraints.core.ModelingLevel)
	 */
	@Override
	public String constraintEvaluationRequested(	final String p_languageId,
													final String p_constraintText, 
													final Collection<String> p_libraryNames ) {
		try {
			// Convert the library names displayed in the panel into their ids.
			final Collection<String> libraryIds = libraryIds( p_libraryNames );
			
			// Create a map of all library texts.
			final Map<String, String> libraryContents = libraryContent( libraryIds );

			final Object result = cstExprServicesFacade.evaluateConstraint(	p_languageId, 
																			p_constraintText,
																			libraryContents,
																					//p_modelingLevel,
																			true,
																			context );
			// Convert an eventual list of strings into a string.
			return String.valueOf( result );
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, context );
		}
		
		// An exception occurred or the action was canceled.
		return null;
	}
    
    /**
     * Gets the current display's shell.
     * @return The <code>Shell</code> of the active display. 
     */
    private Shell getShell() {
    	return Display.getCurrent().getActiveShell();
    }

    /* (non-Javadoc)
     * @see fr.labsticc.framework.constraints.view.IContextualConstraintPanelActionHandler#getContext()
     */
    @Override
	public Object[] getContext() {
		return context;
	}

    /* (non-Javadoc)
     * @see fr.labsticc.framework.constraints.view.IContextualConstraintPanelActionHandler#setContext(java.lang.Object[])
     */
    @Override
	public void setContext( final Object... p_context ) {
		context = p_context;
	}
	
	/**
	 * Creates an ordered map whose keys are the library ids and values the library contents.
	 * @param p_libraryIds A <code>Collection</code> of string elements representing the library identifiers.
	 * @return An ordered  <code>Map</code> whose keys are the library ids and values the library contents.
	 * @throws IOException
	 * @throws ResourceAccessException
	 */
	private Map<String, String> libraryContent( final Collection<String> p_libraryIds ) 
	throws IOException, ResourceAccessException {
											// We must maintain the order!
		final Map<String, String> libraries = new LinkedHashMap<String, String>( p_libraryIds.size() );

		for ( final String libraryFileId : p_libraryIds ) {
			libraries.put( libraryFileId, libraryContent( libraryFileId ) );
		}
		
		return libraries;
	}
	
	/**
	 * Return the content text of the library identified by the given identifier.
	 * @param p_libraryId A string identifying the library.
	 * @return A string representing the text of this library.
	 * @throws IOException
	 * @throws ResourceAccessException
	 */
	private String libraryContent( final String p_libraryId ) 
	throws IOException, ResourceAccessException {
		// First see if the id is written in terms of EMF protocols.
		String location = resourcesHandler.locateResource( p_libraryId );
		
		if ( location == null ) {
			location = p_libraryId;
		}
		
		return FileUtil.getFileContent( location );
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.IContextualConstraintPanelActionHandler#libraryNames()
	 */
	@Override
	public Collection<String> libraryNames() {
		// This handler displays the names in the library table of the constraint panel.
		return expression == null ? null : expression.getUsedLibraryNames();
	}

	@Override
	public Object evaluateExpression() {
		try {
			return cstExprServicesFacade.evaluateExpression( expression, getContext() );
		}
		catch ( final Throwable p_th ) {
			exceptionHandler.handleException( p_th, context );
			
			return null;
		}
	}

	public FormalLanguageExpression getExpression() {
		return expression;
	}

	public void setExpression(FormalLanguageExpression expression) {
		this.expression = expression;
	}

	public IConstraintExpressionServicesFacade getCstExprServicesFacade() {
		return cstExprServicesFacade;
	}
}
