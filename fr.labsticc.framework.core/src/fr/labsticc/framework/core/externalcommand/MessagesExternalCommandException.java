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

import java.util.Collection;

import fr.labsticc.framework.core.message.IMessage;

public class MessagesExternalCommandException extends ExternalCommandException {

	private static final long serialVersionUID = -8777253612074233507L;

	private final Collection<IMessage<?, ?>> messages;

	public MessagesExternalCommandException( 	final String p_message,
												final Collection<IMessage<?, ?>> p_messages ) {
		super( p_message );
		
		messages = p_messages;
	}
	
	public Collection<IMessage<?, ?>> getMessages() {
		return messages;
	}
}
