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


public class ConstraintValidationException extends ConstraintException {

	private static final long serialVersionUID = -863859729987923949L;

	public ConstraintValidationException() {
	}

	public ConstraintValidationException( final String p_message ) {
		super( p_message );
	}
	
	public ConstraintValidationException( final Object p_diagnostic ) {
		super( p_diagnostic );
	}

	public ConstraintValidationException( 	final Object p_diagnostic,
											final String p_message ) {
		super( p_diagnostic, p_message );
	}
}
