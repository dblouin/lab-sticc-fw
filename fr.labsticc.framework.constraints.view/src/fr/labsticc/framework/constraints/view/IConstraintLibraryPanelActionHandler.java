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

/**
 * This interface defines a call back API used by the Lab-STICC constraints framework constraint library panel (a sub panel of
 * the constraint definition panel) to handle user interface actions.
 * @see ConstraintLibraryPanel
 * @author <a href=mailto:dominique.blouin@univ-ubs.fr>Dominique BLOUIN</a>
 */
public interface IConstraintLibraryPanelActionHandler {

	/**
	 * Return a collection of library identifiers to be added to the library table of the library tab of the contraint panel.
	 * Called when the user hits the add library button on the constraint panel library tab.
	 * @param p_currentLibraryNames A <code>Collection</code> of library identifiers representing the library currently being 
	 * used by expression.
	 * @return A string <code>Collection</code> of names of the libraries to be used to define the expression of the
	 * constraint expression panel. Null means that the action did not finish normally. An empty collection means no libraries.
	 */
	Collection<String> addLibrariesRequested( Collection<String> p_currentLibraryNames );

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
}
