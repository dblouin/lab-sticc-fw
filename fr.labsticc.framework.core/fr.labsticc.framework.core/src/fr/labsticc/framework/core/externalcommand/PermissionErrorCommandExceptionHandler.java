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


public class PermissionErrorCommandExceptionHandler extends ExternalCommandExceptionHandler {

	private final int permissionErrorCode;// = 126;
	
	public PermissionErrorCommandExceptionHandler( 	final int pi_okReturnCode,
													final int pi_permissionErrorCode ) {
		super( pi_okReturnCode );
		
		permissionErrorCode = pi_permissionErrorCode;
	}
	
	@Override
	public void handleReturnCode( 	final int pi_returnCode,
									final String p_command ) 
	throws ExternalCommandException {
		if ( pi_returnCode == permissionErrorCode ) {
	    	throw new PermissionException( pi_returnCode, null );
		}
			
	    super.handleReturnCode( pi_returnCode, p_command );
	}
}
