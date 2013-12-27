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

import fr.labsticc.framework.core.exception.BaseException;

public class ExternalCommandException extends BaseException {

	private static final long serialVersionUID = 8653466824403695562L;

	public ExternalCommandException() {
		this( (String) null );
	}

	public ExternalCommandException( final String p_message ) {
		this( p_message, (Throwable) null );
	}

	public ExternalCommandException( final Throwable p_cause ) {
		this( null, p_cause );
	}

	public ExternalCommandException(	final String p_message, 
										final Throwable p_cause ) {
		super( p_message, p_cause );
	}
}
