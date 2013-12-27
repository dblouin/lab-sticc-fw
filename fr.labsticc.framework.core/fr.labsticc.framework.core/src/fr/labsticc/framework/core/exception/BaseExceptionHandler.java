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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseExceptionHandler implements IExceptionHandler {

	private final transient Logger logger;

	public BaseExceptionHandler( final Logger p_logger ) {
		logger = p_logger == null ? LoggerFactory.getLogger( BaseExceptionHandler.class ) : p_logger;
	}
	 
	@Override
	public void handleException( 	final Throwable p_th, 
									final Object p_info) {
		if ( p_th instanceof SystemException ) {
			handleException( (SystemException) p_th, p_info );
		}
		else if ( p_th instanceof FunctionalException ) {
			handleException( (FunctionalException) p_th, p_info );
		}
		else if ( p_th.getCause() != null ) {
			handleException( p_th.getCause(), p_info );
		}
		else {
			getLogger().error( String.valueOf( p_info ), p_th );
		}
	}

	@Override
	public void handleException( 	final SystemException p_ex, 
									final Object p_info ) {
		getLogger().error( String.valueOf( p_info ), p_ex );
	}

	@Override
	public void handleException(	final FunctionalException p_ex, 
									final Object p_info ) {
		getLogger().error( String.valueOf( p_info ), p_ex );
	}

	@Override
	public void handleFunctionalException(	String p_message, 
											Object p_info) {
		handleException( new FunctionalException( p_message ), p_info );
	}
	
	protected Logger getLogger() {
		return logger;
	}

	@Override
	public Object handleExceptionWithReturn(	final Throwable p_th,
												final Object p_info ) {
		handleException( p_th, p_info );
		
		return p_th.getLocalizedMessage();
	}
}
