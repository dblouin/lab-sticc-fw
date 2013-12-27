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

public abstract class PatternMessageLocatedParser extends PatternMessageParser {
	
	public PatternMessageLocatedParser( final String p_regExp,
										final IMessage.Severity p_severity ) {
		super( p_regExp, p_severity );
		
	}

	@Override
	protected IMessage<?, ?> createMessage( final Matcher p_matcher ) {
  		final Message<Integer> message = new MessageLineLocated( 	parseLabel( p_matcher ),
  																	parseResourceId( p_matcher ), 
  																	parseLineNumber( p_matcher ),
  																	parseDescription( p_matcher ) );
	  	message.setSeverity( getSeverity() );

	  	return message;
	}
	
	protected abstract Integer parseLineNumber( final Matcher p_matcher );

	protected abstract String parseResourceId( final Matcher p_matcher );

	protected abstract String parseDescription( final Matcher p_matcher );
}
