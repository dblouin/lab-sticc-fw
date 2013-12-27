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

import java.util.Collection;
import java.util.List;

import fr.labsticc.framework.constraints.core.ICompletionProposal;

/**
 * This interface defines a call back API used by the Lab-STICC constraints framework constraint definition panel to handle 
 * user interface actions.
 * @see ConstraintPanel
 * @author <a href=mailto:dominique.blouin@univ-ubs.fr>Dominique BLOUIN</a>
 */
public interface IConstraintPanelActionHandler {

	/**
	 * Return a list of code completion proposals to help the user define a formal language query expression. Called when the
	 * user request code completion in the expression editing area (typically by hitting <code>ctrl-space</code>.
	 * @param p_languageId A string identifying the language used to write the query.
	 * @param p_constraintText A string representing the text of the expression up to the cursor position.
	 * @param p_libraryNames A <code>Collection</code> of strings identifying the libraries used in the definition of this expression.
	 * @param p_modelingLevel A <code>ModelingLevel</code> object defining if the expression concerns the M1 (model) or M2 (meta-model) level.
	 * @return A <code>List</code> of <code>ICompletionProposal</code> objects.
	 */
	List<ICompletionProposal> completionProposalRequested( 	String p_languageId,
															String p_constraintText,
															Collection<String> p_libraryNames );

	/**
	 * Return a string representing the result of evaluating the given constraint text. Called when the user hit the evaluation button
	 * on the constraint panel.
	 * @param p_languageId A string identifying the language used to write the query.
	 * @param p_constraintText A string representing the text of the expression.
	 * @param p_libraryNames A <code>Collection</code> of strings identifying the libraries used in the definition of this expression.
	 * @param p_modelingLevel A <code>ModelingLevel</code> object defining if the expression concerns the M1 (model) or M2 (meta-model) level.
	 * @return A string representation of the result of evaluation. A <code>null</code> value means that the action did not terminate 
	 * normally.
	 */
	String constraintEvaluationRequested( 	String p_languageId,
											String p_constraintText,
											Collection<String> p_libraryNames );

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
	 * Return a collection of library identifiers to be used to define an expression in the constraint expression panel. Called 
	 * when the user hits the add library button on the constraint panel library tab.
	 * @param p_languageId A string identifying the language used to define the query.
	 * @param p_currentLibraryNames A <code>Collection</code> of library identifiers representing the library currently being 
	 * used by expression.
	 * @return A string <code>Collection</code> of names of the libraries to be used to define the expression of the
	 * constraint expression panel. Null means that the action did not finish normally. An empty collection means no libraries.
	 */
	Collection<String> addLibrariesRequested( 	String p_languageId,
												Collection<String> p_currentLibraryNames );

	/**
	 * Request this handler to open the given libraries. Called when the user selects a collection of libraries in the library table of 
	 * the constraint panel and click the open button. Also called when a row element is doubled-clicked.
	 * @param p_libraryNames A <code>Collection</code> of strings identifying the libraries to be opened.
	 */
	void openLibrariesRequested( Collection<String> p_libraryNames );

	/**
	 * Notify this handler that a collection of libraries have been modified and needs to be reloaded in the constraint languages
	 * model. Called when the user selects a collection of libraries in the library table of the constraint panel and click the
	 * refresh button.
	 * @param p_libraryNames A <code>Collection</code> of strings identifying the libraries to be refreshed.
	 */
	void librariesRefreshRequested( Collection<String> p_libraryNames );

	/**
	 * Notify this handler that a collection of libraries have been deleted from the library table and should not be used by the
	 * expression. Called when the user selects a collection of libraries in the library table of the constraint panel and click
	 * the delete button.
	 * @param p_libraryNames A <code>Collection</code> of strings identifying the libraries to be removed from the expression's
	 * used libraries.
	 */
	void librariesDeleted( Collection<String> p_libraryNames );

	/**
	 * Notify this handler that the user has changed the language use to define the expression in the constraint panel. Called when
	 * the user selects a new element in the language combo box.
	 * @param p_newLanguageId A string identifying the new language used to define the query.
	 */
	Collection<String> constraintLanguageChanged( String p_newLanguageId );

	/**
	 * Notify this handler that the user has changed the expression text in the constraint panel. Called when the user edits
	 * the expression text in the editor.
	 * @param p_languageId A string identifying the language used to define the query.
	 * @param p_newConstraintText A string representing the new text of the constraint expression.
	 */
	void constraintTextChanged( String p_languageId,
								String p_newConstraintText );
}
