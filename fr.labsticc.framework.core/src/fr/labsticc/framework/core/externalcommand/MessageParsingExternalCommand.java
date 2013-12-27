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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.message.IMessageParser;
import fr.labsticc.framework.core.util.SystemUtil;

public class MessageParsingExternalCommand extends ExternalCommand {
	
	private List<IMessage<?, ?>> messages = new ArrayList<IMessage<?, ?>>();

	private final IMessageParser[] messageParsers;

	public MessageParsingExternalCommand( 	final String p_command,
											final String p_commandArgs,
											final String p_workingDir,
											final String p_symbolicName,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, p_workingDir, LoggerFactory.getLogger( p_symbolicName ), p_messageParsers );
	}

	public MessageParsingExternalCommand( 	final String p_command,
											final String p_commandArgs,
											final String p_workingDir,
											final String p_symbolicName,
											final Level p_logLevel,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, null, p_workingDir, LoggerFactory.getLogger( p_symbolicName ), p_logLevel, p_messageParsers );
	}

	public MessageParsingExternalCommand( 	final String p_command,
						  					final String p_commandArgs,
						  					final Logger p_logger,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, (String) null, p_logger, p_messageParsers );
	}
  
	public MessageParsingExternalCommand( 	final String p_command, 
											final String p_commandArgs,
						  					final String p_workingDir,
						  					final Logger p_logger,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, null, p_workingDir, p_logger, logLevelDefault, p_messageParsers );
	}

	public MessageParsingExternalCommand( 	final String p_command, 
											final String p_commandArgs,
						  					final String[] p_envParams,
						  					final Logger p_logger,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, p_envParams, null, p_logger, p_messageParsers );
	}

	public MessageParsingExternalCommand( 	final String p_command, 
											final String p_commandArgs,
											final String[] p_envParams, 
											final String p_workingDir,
											final Logger p_logger,
											final IMessageParser[] p_messageParsers ) {
		this( p_command, p_commandArgs, p_envParams, p_workingDir, p_logger, logLevelDefault, p_messageParsers );
	}
  
	public MessageParsingExternalCommand( 	final String p_command, 
											final String p_commandArgs,
											final String[] p_envParams,
											final String p_workingDir,
											final Logger p_logger,
											final Level p_logLevel,
											final IMessageParser[] p_messageParsers ) {
		super( p_command, p_commandArgs, p_envParams, p_workingDir, p_logger, p_logLevel );
		
		messageParsers = p_messageParsers;
	}

	/**
	 * @param p_waitToFinish True if the thread must wait for the external process
	 * to finish before returning, False otherwise
	 * @return status returned from the external process or 0(successful)
	 */
	@Override
	public List<IMessage<?, ?>> execute( boolean pb_waitToFinish ) 
	throws ExternalCommandException, InterruptedException {
		super.execute( pb_waitToFinish );
		
		return getMessages();
	}

	@Override
	protected IExternalCommandExceptionHandler createExceptionHandler() {
		return new MessageExtCommandExceptionHandler( 0, SystemUtil.getPermissionErrorCode() );
	}
	
	@Override
	protected void handleReturnCode( final int pi_retCode ) 
	throws ExternalCommandException {
		getExceptionHandler().handleReturnCode( pi_retCode, getFullCommand(), getMessages() );
	}
	
	@Override
	protected void log( final Level p_level,
						final String p_line ) {
		super.log( p_level, p_line );
		
		if ( messageParsers != null ) {
			for ( final IMessageParser parser : messageParsers ) {
				final IMessage<?, ?> parsedMessage = parser.parseLine( p_line );
				
				if ( parsedMessage != null  && !getMessages().contains( parsedMessage ) ) {
					getMessages().add( parsedMessage );
					
					// Assume a line can be parsed by only one parser. Perfs.
					break;
				}
			}
		}
	}

	@Override
	public IMessageExtCommandExceptionHandler getExceptionHandler() {
		return (IMessageExtCommandExceptionHandler) super.getExceptionHandler();
	}

  	public List<IMessage<?, ?>> getMessages() {
		return messages;
	}
}
