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

public interface IExceptionHandler {
	
	void handleException( 	Throwable p_th,
							Object p_info );

	void handleException( 	SystemException p_ex,
							Object p_info );

	void handleException( 	FunctionalException p_ex,
							Object p_info );

	void handleFunctionalException( String p_message,
									Object p_info );
	
	Object handleExceptionWithReturn( 	Throwable p_th,
										Object p_info );
}
