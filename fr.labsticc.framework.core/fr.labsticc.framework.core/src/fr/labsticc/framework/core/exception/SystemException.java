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
package fr.labsticc.framework.core.exception;

public class SystemException extends BaseException {

	private static final long serialVersionUID = 6860407032847817471L;

	public SystemException() {
	}

	public SystemException( final String p_message ) {
		super( p_message );
	}

	public SystemException( final Throwable p_cause ) {
		super( p_cause );
	}

	public SystemException(	String p_message,
							Throwable p_cause ) {
		super( p_message, p_cause );
	}
}
