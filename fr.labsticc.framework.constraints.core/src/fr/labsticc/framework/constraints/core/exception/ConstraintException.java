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
package fr.labsticc.framework.constraints.core.exception;

import org.eclipse.emf.ecore.EObject;

import fr.labsticc.framework.core.exception.FunctionalException;

public class ConstraintException extends FunctionalException {

	private static final long serialVersionUID = 4160448095884889740L;
	
	private EObject[] context;

	public ConstraintException() {
		this( null );
	}

	public ConstraintException( final String p_message ) {
		this( null, p_message );
	}

	public ConstraintException( final Object p_diagnostic ) {
		this( p_diagnostic, null );
	}

	public ConstraintException( final Object p_diagnostic,
								final String p_message ) {
		super( p_diagnostic, p_message, null );
		
		context = null;
	}

	public EObject[] getContext() {
		return context;
	}

	public void setContext(EObject[] context) {
		this.context = context;
	}
}
