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
package fr.labsticc.framework.constraints.view;

import java.util.List;

import fr.labsticc.framework.constraints.core.ICompletionProposal;

/**
 * This interface defines a call back API used by the Lab-STICC constraints framework constraint console panel (a sub panel of the 
 * constraint definition panel) to handle user interface actions.
 * @see ConstraintConsolePanel
 * @author <a href=mailto:dominique.blouin@univ-ubs.fr>Dominique BLOUIN</a>
 */
public interface IConstraintConsolePanelActionHandler {

	/**
	 * Return a list of code completion proposals to help the user define a formal language query expression. Called when the
	 * user request code completion in the expression editing area (typically by hitting <code>ctrl-space</code>.
	 * @param p_languageId A string identifying the language used to write the query.
	 * @param p_constraintText A string representing the text of the expression up to the cursor position.
	 * @return A <code>List</code> of <code>ICompletionProposal</code> objects.
	 */
	List<ICompletionProposal> completionProposalRequested( 	String p_languageId,
															String p_constraintText/*,
															ModelingLevel p_modelingLevel*/ );
	
	/**
	 * Return a string representing the result of evaluating the given constraint text. Called when the user hit the evaluation button
	 * on the constraint panel.
	 * @param p_languageId A string identifying the language used to write the query.
	 * @param p_constraintText A string representing the text of the expression.
	 * @return A string representation of the result of evaluation. A <code>null</code> value means that the action did not terminate 
	 * normally.
	 */
	String constraintEvaluationRequested( 	String p_languageId,
											String p_constraintText/*,
											ModelingLevel p_modelingLevel*/ );

	/**
	 * Request the actual constraint text to be saved. The handler decides on the persistence means. Called when the user hit the 
	 * save button on the constraint panel.
	 * @param p_languageId A string identifying the language used to define the query. Used to infer the file extension.
	 * @param p_constraintText A string representing the text of the expression.
	 */
	void constraintSavingRequested( String p_languageId,
									String p_constraintText );

	/**
	 * Return a string representing an expression loaded from a persistence store defined by this handler. Called when the user
	 * hits the load button on the constraint panel.
	 * @param p_languageId A string identifying the language used to define the query. Used to infer the file extension.
	 * @return A string representing the loaded expression. A <code>null</code> value means that the action did not
	 * finish normally.
	 */
	String constraintLoadingRequested( String p_languageId );
	
	/**
	 * Notify this handler that the user has changed the language use to define the expression in the constraint panel. Called when
	 * the user selects a new element in the language combo box.
	 * @param p_newLanguageId A string identifying the new language used to define the query.
	 */
	void constraintLanguageChanged( String p_newLanguageId );
	
	/**
	 * Notify this handler that the user has changed the expression text in the constraint panel. Called when the user edits
	 * the expression text in the editor.
	 * @param p_languageId A string identifying the language used to define the query.
	 * @param p_newConstraintText A string representing the new text of the constraint expression.
	 */
	void constraintTextChanged( String p_languageId,
								String p_constraintText );
}
