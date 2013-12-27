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
package fr.labsticc.framework.constraints.core;

public interface ICompletionProposal {

	/**
	 * Obtains my name, suitable for display in a UI list.
	 * 
	 * @return my name
	 */
	String getName();

	/**
	 * Obtains my description, suitable for display in a UI tool tip or pop-up
	 * help window.
	 * 
	 * @return my description
	 */
	String getDescription();

	/**
	 * Obtains the model element (property, operation, type, etc.) that is
	 * the choice in question.
	 * 
	 * @return the element that I represent
	 */
	Object getElement();

	CompletionKind getCompletionKind();
}
