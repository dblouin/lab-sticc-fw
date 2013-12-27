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
import java.util.Collection;
import java.util.List;

import fr.labsticc.framework.core.message.IMessage;

public class MessageExtCommandExceptionHandler extends PermissionErrorCommandExceptionHandler implements IMessageExtCommandExceptionHandler {

	private boolean checkingErrorMessages = true;

	public MessageExtCommandExceptionHandler( 	final int pi_okRetCode,
												final int pi_permissionErrorCode ) {
		super( pi_okRetCode, pi_permissionErrorCode );
	}
	
	@Override
	public void handleReturnCode( 	final int pi_returnCode,
									final String p_command,
									final List<IMessage<? ,?>> p_messages ) 
	throws ExternalCommandException {
		// Check output messages first
		if ( isCheckingErrorMessages() && p_messages != null ) {
			final Collection<IMessage<?, ?>> errorMessages = new ArrayList<IMessage<?, ?>>();
			
			for ( final IMessage<?, ?> message : p_messages ) {
				assert message.getSeverity() != null : "Message severity should not be null.";

				switch ( message.getSeverity() ) {
					case ERROR:
					case FATAL:
					case WARNING:
						errorMessages.add( message );
						break;
				}
			}

			if 	( !errorMessages.isEmpty() ) {
				throw new MessagesExternalCommandException( "Error messages where output while executing external command " + p_command + ".", errorMessages );
			}
			
			// Checks the return code if no fatal error messages
			handleReturnCode( pi_returnCode, p_command );
		}
		else {
			handleReturnCode( pi_returnCode, p_command );
		}
	}

	public boolean isCheckingErrorMessages() {
		return checkingErrorMessages;
	}

	public void setCheckingErrorMessages( final boolean pb_checkingErrorMessages ) {
		checkingErrorMessages = pb_checkingErrorMessages;
	}
}
