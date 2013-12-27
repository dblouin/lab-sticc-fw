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

import java.util.List;

import fr.labsticc.framework.core.message.IMessage;

public class FunctionalException extends BaseException {

	private static final long serialVersionUID = -1263662608090995111L;

	private final Object diagnostic;
	
	private IMessage<?, ?> info = null;
	
	private List<IMessage<?, ?>> messages = null;

	public FunctionalException() {
		diagnostic = null;
	}

	public FunctionalException( final IMessage<?, ?> p_info ) {
		this( p_info, null );
	}
	
	public FunctionalException( final IMessage<?, ?> p_info,
								final List<IMessage<?, ?>> p_messages ) {
		setInfo( p_info );
		
		messages = p_messages;
		diagnostic = null;
	}

	public FunctionalException( final String p_message ) {
		this( null, p_message, null );
	}

	public FunctionalException( final String p_message,
								final List<IMessage<?, ?>> p_messages ) {
		super( p_message );
		
		messages = p_messages;
		diagnostic = null;
	}

	public FunctionalException( final Object p_diagnostic,
								final String p_message,
								final Object dummy) {
		super( p_message );
		
		diagnostic = p_diagnostic;
	}

	public IMessage<?,?> getInfo() {
		return info;
	}

	public void setInfo( final IMessage<?,?> p_info) {
		info = p_info;
	}
	
	@Override
	public String toString() {
		return getInfo() == null ? super.toString() : getInfo().toString();
	}
	
	public IMessage.Severity getSeverity() {
		return getInfo() == null ? IMessage.Severity.FATAL : getInfo().getSeverity();
	}
	
	public boolean isFatal() {
		return getSeverity() == null ? true : IMessage.Severity.FATAL.equals( getSeverity() ); 
	}
	
	@Override
	public String getMessage() {
		return getInfo() == null ? super.getMessage() : getInfo().getLabel();
	}

	public List<IMessage<?, ?>> getMessages() {
		return messages;
	}

	public void setMessages( final List<IMessage<?, ?>> p_messages ) {
		messages = p_messages;
	}

	public Object getDiagnostic() {
		return diagnostic;
	}
}
