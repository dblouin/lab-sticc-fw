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
package fr.labsticc.framework.constraints.examples.ide.views;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.constraints.ide.ConstraintsDialogExceptionHandler;
import fr.labsticc.framework.constraints.ide.ConstraintsIDEPlugin;
import fr.labsticc.framework.constraints.ide.view.ConstraintExpressionPanel;
import fr.labsticc.framework.constraints.model.constraints.ConstraintsFactory;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;
import fr.labsticc.framework.core.exception.IExceptionHandler;

/**
 * This sample class demonstrates how to use a Lab-STICC framework constraint panel in a workbench view.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class ExamplesConstraintConsoleView extends ViewPart implements ISelectionListener {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "fr.labsticc.framework.constraints.examples.ide.views.ConstraintsExamplesView";

	/**
	 * The GUI panel used to define the formal language expression.
	 */
	private ConstraintExpressionPanel constraintPanel;
	
	/**
	 * The exception handler used by both the view and the constraint panel.
	 */
	private final IExceptionHandler exceptionHandler;
	
	/**
	 * The constructor.
	 */
	public ExamplesConstraintConsoleView() {
		constraintPanel = null;
		
		// This exception handler handles the constraint language exception such as those due to syntactic error.
		exceptionHandler = new ConstraintsDialogExceptionHandler( LoggerFactory.getLogger( getClass() ) );
	}
	
	/**
	 * Initiate an expression to be edited by the constraint panel.
	 * @return An initialized OCL <code>FormalLanguageExpression</code>.
	 */
	private static FormalLanguageExpression initExpression() {
		final FormalLanguageExpression expression = ConstraintsFactory.eINSTANCE.createFormalLanguageExpression();
		expression.setName( "Test OCL expression" );
		expression.setDescription( "This expression is used for the Lab-STICC constraint definition panel example." );
		expression.setText( "self" );
		final FormalConstraintLanguage oclLanguage = (FormalConstraintLanguage) ConstraintsIDEPlugin.getDefault().getSpecification().languageByName( "OCL" );
		
		assert oclLanguage != null : "OCL language not defined in constraints language model" + ConstraintsIDEPlugin.getDefault().getSpecification().getName();
		
		expression.setLanguage( oclLanguage );
		
		for ( final FormalLanguageLibrary library : oclLanguage.getLibraries() ) {
			if ( library.isDefault() ) {
				expression.getUsedLibraries().add( library );
			}
		}
		
		return expression;
	}

	/**
	 * This is a callback that will allow us to create the constraint expression panel.
	 */
	@Override
	public void createPartControl( final Composite p_parent ) {
		try {
			// Instantiate a dummy expression to be edited by the constraint panel.
			final FormalLanguageExpression expression = initExpression();

			// Instantiate a constraint expression panel used to edit our formal language expression.
			constraintPanel = new ConstraintExpressionPanel( 	p_parent, 
																SWT.NULL,
																exceptionHandler,
																expression );

	
			// Adds this view as a selection listener to define the context of the expression to be the selected EObject 
			// in the current EMF editor.
			getSite().getPage().addPostSelectionListener( this );
		}
		catch ( final CoreException p_ex ) {
			exceptionHandler.handleException( p_ex, null );
		}
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		constraintPanel.setFocus();
	}

    /*
     *  As the selection changes, we see if the selection contains an EObject and update the context of the constraint panel
     *  with this object.
     */
    @Override
    public void selectionChanged( 	final IWorkbenchPart p_part, 
    								final ISelection p_selection ) {
    	// Reset the context for evaluating the entered constraint.
    	EObject context = null;

		if ( p_selection instanceof IStructuredSelection ) {
			final IStructuredSelection structSel = (IStructuredSelection) p_selection;
			
			if ( structSel.size() == 1 ) {
				final Object element = AdapterFactoryEditingDomain.unwrap( ( (IStructuredSelection) p_selection ).getFirstElement() );

				// Set the context as the selected EObject if any.
				if ( element instanceof EObject ) {
					context = (EObject) element;
				}
				else if ( element instanceof IAdaptable ) {
					context = (EObject) ( (IAdaptable) element ).getAdapter( EObject.class );
				}
			}
		}
    	
    	constraintPanel.getActionHandler().setContext( context );
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		// Dispose the constraint panel
		constraintPanel.dispose();
		
		// Remove the selection listener
		getSite().getPage().removePostSelectionListener( this );
	}
}
