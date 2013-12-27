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

import fr.labsticc.framework.core.exception.BaseExceptionHandler;

public class ExternalCommandExceptionHandler extends BaseExceptionHandler implements IExternalCommandExceptionHandler {

	private final int okRetCode;

	public ExternalCommandExceptionHandler( final int pi_okRetCode ) {
		super( null );
		
		okRetCode = pi_okRetCode;
	}
	
	@Override
	public void handleReturnCode( 	final int pi_returnCode,
									final String p_command ) 
	throws ExternalCommandException {
		if ( pi_returnCode != getOkRetCode() ) {
			final StringBuffer errorMessage = new StringBuffer( "Error executing external command " + p_command + "." );
			errorMessage.append( " Return code : " + pi_returnCode );
				
			throw new ErrorCodeExternalCommandException( pi_returnCode, errorMessage.toString() );
		}
	}
	
	@Override
	public void handleException( 	final Throwable p_th,
									final String p_command ) 
	throws ExternalCommandException {
	      if ( !( p_th instanceof ExternalCommandException ) ) {
	    	  throw new ExternalCommandException( "Error executing command " + p_command, p_th );
	      } 
	      
	      throw (ExternalCommandException) p_th;
	}

	protected int getOkRetCode() {
		return okRetCode;
	}
}
