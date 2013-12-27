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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PatternMessageParser implements IMessageParser {

	//private final Pattern pattern;
	
	private final Matcher matcher;

	private final IMessage.Severity severity;
	
	public PatternMessageParser( 	final String p_regExp,
									final IMessage.Severity p_severity ) {
		severity = p_severity;
		matcher = createPattern( p_regExp ).matcher( "" );
	}
	
	protected Pattern createPattern( final String p_regExp ) {
		return Pattern.compile( p_regExp );
	}
	
	protected IMessage<?, ?> createMessage( final Matcher p_matcher ) {
  		final Message<?> message = new Message<Object>( parseLabel( p_matcher ) );
	  	message.setSeverity( getSeverity() );

	  	return message;
	}

	protected abstract String parseLabel( final Matcher p_matcher );
	
	@Override
	public IMessage<?, ?> parseLine( final String p_line ) {
		matcher.reset( p_line );

		if ( matcher.find() ) {
			return createMessage( matcher );
		}
		
		return null;
	}

	protected IMessage.Severity getSeverity() {
		return severity;
	}
}
