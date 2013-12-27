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
 * A constraint panel action handler that maintains a context to be used to evaluate expressions.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface IContextualConstraintPanelActionHandler extends IConstraintPanelActionHandler {

	/**
	 * Returns the context used to evaluate constraints expressions.
	 * @return An array of model elements representing the context of the expression.
	 */
	Object[] getContext();

	/**
	 * Sets the context to be used to evaluate the query expressions.
	 * @param p_context An array containing all model elements on which the expression shall be evaluated.
	 */
	void setContext( Object... p_context );
	
	/**
	 * Return the library names to be displayed in the used libraries table.
	 * @return A <code>Collection</code> of strings to display to the user.
	 */
	Collection<String> libraryNames();
}
