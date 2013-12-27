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
package fr.labsticc.framework.emf.view.ide;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.emf.core.util.EMFUtil;
import fr.labsticc.framework.ide.log.MarkerMessageLogger;

public class EMFMarkerMessageLogger extends MarkerMessageLogger {
	
	protected EMFMarkerMessageLogger() {
	}
	
	@Override
	public void logMessage( final Object p_object,
							final IMessage<?, ?> p_message ) 
	throws ResourceAccessException {
		if ( p_message != null ) {
			final EObject eObject = (EObject) p_object;
			final IResource resource = EMFUtil.convertToIDEResource( eObject.eResource() );
			createMarker( resource, eObject, p_message );
		}	
	}
	
	protected IMarker createMarker( final IResource p_resource,
									final EObject p_eObject,
									final IMessage<?,?> p_message ) 
	throws ResourceAccessException {
		try {
			final IMarker marker = super.createMarker( p_resource, p_message );	
			marker.setAttribute( EValidator.URI_ATTRIBUTE, EcoreUtil.getURI( p_eObject ).toString() );
			
			return marker;
		}
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}

	@Override
	public void clearMessages( final Object p_object ) 
	throws ResourceAccessException {
		try {
			final EObject eObject = (EObject) p_object;
			final IResource resource = EMFUtil.convertToIDEResource( eObject.eResource() );
			resource.deleteMarkers( IMarker.PROBLEM, true, IResource.DEPTH_INFINITE );
		} 
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
}
