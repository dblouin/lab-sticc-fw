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

public class CompletionProposal implements ICompletionProposal {

	private final String name;

	private final String description;
	
	private final Object element;
	
	private final CompletionKind completionKind;

	public CompletionProposal( 	final String p_name,
								final String p_description,
								final Object p_element,
								final CompletionKind p_completionKind ) {
		name = p_name;
		description = p_description;
		element = p_element;
		completionKind = p_completionKind;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Object getElement() {
		return element;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public CompletionKind getCompletionKind() {
		return completionKind;
	}
}
