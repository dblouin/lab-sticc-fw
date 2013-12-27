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

public class ResourceAccessException extends SystemException {

	private static final long serialVersionUID = 8945813710804571579L;

	public ResourceAccessException() {
	}

	public ResourceAccessException(String message) {
		super(message);
	}

	public ResourceAccessException( final Throwable cause) {
		super(cause);
	}

	public ResourceAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}
