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
package fr.labsticc.framework.core.externalcommand;


public class ErrorCodeExternalCommandException extends ExternalCommandException {
	
	private static final long serialVersionUID = 9212871555075428649L;

	private final Integer errorCode;
	
	public ErrorCodeExternalCommandException() {
		this( (String) null );
	}
	
	public ErrorCodeExternalCommandException( final Integer p_errorCode ) {
		this( p_errorCode, null );
	}

	public ErrorCodeExternalCommandException( 	final Integer p_errorCode,
												final String p_message ) {
		super( p_message );
		
		errorCode = p_errorCode;
	}

	public ErrorCodeExternalCommandException( final String p_message ) {
		this( null, p_message );
	}

	public Integer getErrorCode() {
		return errorCode;
	}
}
