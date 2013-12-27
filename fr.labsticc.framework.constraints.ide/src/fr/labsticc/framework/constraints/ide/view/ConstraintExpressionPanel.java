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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Composite;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.constraints.ide.ConstraintsDialogExceptionHandler;
import fr.labsticc.framework.constraints.ide.ConstraintsIDEPlugin;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.view.ConstraintPanel;
import fr.labsticc.framework.core.exception.IExceptionHandler;

/**
 * A panel used to edit a given formal language expression as dcefined by the constraint language meta-model.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class ConstraintExpressionPanel extends ConstraintPanel {
	
	/**
	 * Creates a constraint expression panel.
	 * @param p_parent A <code>SWT</code> <code>Composite</code> element that is the parent of this component.
	 * @param pi_style The style as defined for <code>SWT</code> <code>Composite</code> components.
	 * @param p_expression A <code>FormalLanguageExpression</code> that this panel edits.
	 * @throws CoreException
	 */
	public ConstraintExpressionPanel(	final Composite p_parent,
										final int pi_style,
										final FormalLanguageExpression p_expression )
	throws CoreException {
		this( 	p_parent, 
				pi_style, 
				new ConstraintsDialogExceptionHandler( LoggerFactory.getLogger( ConstraintExpressionPanel.class ) ),
				p_expression );
	}

	/**
	 * Creates a constraint expression panel.
	 * @param p_parent A <code>SWT</code> <code>Composite</code> element that is the parent of this component.
	 * @param pi_style The style as defined for <code>SWT</code> <code>Composite</code> components.
	 * @param p_exceptionHandler A <code>IExceptionHandler</code> object to handle exceptions that may occur. This should
	 * handle exceptions throw by language interpretation services such as those related to syntactic errors.
	 * @param p_expression A <code>FormalLanguageExpression</code> that this panel edits.
	 * @throws CoreException
	 */
	public ConstraintExpressionPanel(	final Composite p_parent,
										final int pi_style,
										final IExceptionHandler p_exceptionHandler,
										final FormalLanguageExpression p_expression )
	throws CoreException {
		this( 	p_parent, 
				pi_style, 
				new BasicConstraintPanelActionHandler( p_expression, p_exceptionHandler ), 
				p_exceptionHandler,
				p_expression );
	}

	/**
	 * Creates a constraint expression panel.
	 * @param p_parent A <code>SWT</code> <code>Composite</code> element that is the parent of this component.
	 * @param pi_style The style as defined for <code>SWT</code> <code>Composite</code> components.
	 * @param p_actionHandler A <code>IContextualConstraintPanelActionHandler</code> used to handle the UI actions of this panel.
	 * @param p_exceptionHandler A <code>IExceptionHandler</code> object to handle exceptions that may occur. This should
	 * handle exceptions throw by language interpretation services such as those related to syntactic errors.
	 */
	public ConstraintExpressionPanel(	final Composite p_parent,
										final int pi_style,
										final IConstraintExpressionPanelActionHandler p_actionHandler,
										final IExceptionHandler p_exceptionHandler,
										final FormalLanguageExpression p_expression ) {
		super( 	p_parent, 
				pi_style, 
				p_actionHandler, 
				ConstraintsIDEPlugin.getDefault().getViewersConfiguration(),
				p_exceptionHandler );
		
		// Display the current expression data into the panel UI components.
		displayData( p_expression );
	}

	/**
	 * Display the given expression data into the panel UI components.
	 * @param p_expression A <code>FormalLanguageExpression</code> object that is to be displayed by the panel.
	 */
	public void displayData( final FormalLanguageExpression p_expression ) {

		// Set the proper viewer configuration for the language and select the proper language in the language choice combo. 
		configure( p_expression.getLanguage().getId() );
		
		// Display the expression
		displayData( p_expression.getText(), null, getActionHandler().libraryNames() );
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.constraints.view.ConstraintPanel#getActionHandler()
	 */
	@Override
	public IConstraintExpressionPanelActionHandler getActionHandler() {
		return (IConstraintExpressionPanelActionHandler) super.getActionHandler();
	}
}
