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
package fr.labsticc.framework.core.message;

import java.util.Collection;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.object.IObjectResourceHandler;

public interface IMessageLogger {
	
	void logMessages( 	Object p_object,
						Collection<? extends IMessage<?, ?>> p_messages )
	throws ResourceAccessException;

	void logMessage( 	Object p_object,
						IMessage<?, ?> p_message )
	throws ResourceAccessException;

	void clearMessages( Object p_object )
	throws ResourceAccessException;
	
	void setResourceHandler( IObjectResourceHandler p_handler );
}
