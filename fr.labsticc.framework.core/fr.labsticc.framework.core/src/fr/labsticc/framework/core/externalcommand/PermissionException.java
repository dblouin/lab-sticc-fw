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

public class PermissionException extends ErrorCodeExternalCommandException {

	private static final long serialVersionUID = 4279751517219623356L;

	public PermissionException() {
		super();
	}

	public PermissionException( final Integer p_errorCode,
								final String p_message ) {
		super( p_errorCode, p_message );
	}

	public PermissionException( final String p_message ) {
		super( p_message );
	}
}
