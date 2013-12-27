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
package fr.labsticc.framework.ide.log;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.message.IMessageLogger;
import fr.labsticc.framework.core.object.IObjectResourceHandler;

public abstract class MarkerMessageLogger implements IMessageLogger {
	
	private IObjectResourceHandler resourceHandler;

	protected MarkerMessageLogger( ) {
		this( null );
	}

	protected MarkerMessageLogger( final IObjectResourceHandler p_resourceHandler ) {
		setResourceHandler( p_resourceHandler );
	}

	@Override
	public void logMessages( 	final Object p_object,
								final Collection<? extends IMessage<?, ?>> p_messages ) 
	throws ResourceAccessException {
		for ( final IMessage<?, ?> message : p_messages ) {
			logMessage( p_object, message );
		}
	}

	protected IMarker createMarker( final IResource p_resource,
									final IMessage<?, ?> p_message ) 
	throws ResourceAccessException {
		try {
			final IMarker marker = createMarker( p_resource );	
			marker.setAttribute( IMarker.MESSAGE, p_message.getLabel() );
			marker.setAttribute( IMarker.SEVERITY, mapSeverity( p_message ) );
			
			return marker;
		}
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	protected int mapSeverity( final IMessage<?, ?> p_message ) {
		final IMessage.Severity severity = p_message.getSeverity();
		
		if ( severity == null ) {
			return IMarker.SEVERITY_INFO;
		}

		switch ( severity ) {
			case FATAL:
			case ERROR:
				return IMarker.SEVERITY_ERROR;

			case WARNING:
				return IMarker.SEVERITY_WARNING;
				
			default:
				return IMarker.SEVERITY_INFO;
		}
	}
	
	protected IMarker createMarker( final IResource p_resource ) 
	throws ResourceAccessException {
		try {
			return p_resource.createMarker( IMarker.PROBLEM );
		}
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	public IObjectResourceHandler getResourceHandler() {
		return resourceHandler;
	}

	@Override
	public void setResourceHandler( final IObjectResourceHandler p_resourceHandler ) {
		resourceHandler = p_resourceHandler;
	}
}
