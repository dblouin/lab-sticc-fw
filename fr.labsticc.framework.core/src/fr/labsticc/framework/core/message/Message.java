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

import fr.labsticc.framework.core.object.IntrospectionSOLocalized;

public class Message<ID> extends IntrospectionSOLocalized<ID, Message<ID>> implements IMessage<ID, Message<ID>> {
	
	private static final long serialVersionUID = -1288455450806598678L;
	
	private Message<ID> info = null;

	protected String label = null;
	
	protected Severity severity = null;
	
	protected Throwable cause = null;
	
	public Message() {
	}

	public Message( final String p_label ) {
		setLabel(p_label);
	}
	
	public Message( final String p_label,
					final Severity p_severity ) {
		setLabel( p_label );
		setSeverity( p_severity );
	}
	
	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	@Override
	public void setLabel(String p_label) {
		label = p_label;
	}

	@Override
	public void setSeverity(Severity p_severity) {
		severity = p_severity;
	}

	@Override
	public void setCause(Throwable p_cause) {
		cause = p_cause;
	}

	public Message<ID> getInfo() {
		return info;
	}

	public void setInfo( final Message<ID> info) {
		this.info = info;
	}
}
