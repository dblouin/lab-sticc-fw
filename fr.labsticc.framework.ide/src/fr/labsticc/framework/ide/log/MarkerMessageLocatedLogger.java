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

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.message.IMessageLocated;
import fr.labsticc.framework.core.object.IObjectResourceHandler;

public abstract class MarkerMessageLocatedLogger extends MarkerMessageLogger {

	protected MarkerMessageLocatedLogger() {
		super();
	}

	protected MarkerMessageLocatedLogger( final IObjectResourceHandler p_resourceHandler ) {
		super( p_resourceHandler );
	}
	
	@Override
	public void logMessage( final Object p_object,
							final IMessage<?, ?> p_message ) 
	throws ResourceAccessException {
		if ( p_message instanceof IMessageLocated<?, ?> ) {
			final IMessageLocated<?, ?> locMessage = (IMessageLocated<?, ?>) p_message;
			final String resId = locMessage.getResourceId();
			assert resId != null : "Resource ID cannot be null.";
			
			final IResource resource = getResource( p_object, resId );
			
			if ( resource != null ) {
				createMarker( resource, locMessage );
			}
		}
		else {
			logMessage( p_object, p_message );
		}
	}
	
	protected IResource getResource( 	final Object p_object,
										final String p_resId ) 
	throws ResourceAccessException {
		final String fullPath = getResourceHandler() == null ? p_resId : getResourceHandler().locateResource( p_object, p_resId );
		assert fullPath != null : "Message resource path should not be null.";
		
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( new Path( fullPath ) );
	}

	protected IMarker createMarker( final IResource p_resource,
									final IMessageLocated<?, ?> p_message ) 
	throws ResourceAccessException {
		try {
			final IMarker marker = super.createMarker( p_resource, p_message );	
			
			final IMessageLocated<?, ?> locatedMess = (IMessageLocated<?, ?>) p_message;
			
			// Override the message label with the description
			marker.setAttribute( IMarker.MESSAGE, locatedMess.getDescription() );
			marker.setAttribute( IMarker.LOCATION, locatedMess.getLocation() );
			marker.setAttribute( IMarker.LINE_NUMBER, locatedMess.getLocation() );

			return marker;
		}
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
}
