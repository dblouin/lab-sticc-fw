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
package fr.labsticc.framework.emf.core.resource;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.emf.core.util.EMFUtil;
import fr.labsticc.framework.ide.resource.ExtensionsResourcesVisitor;

public class EMFResourcesVisitor extends ExtensionsResourcesVisitor {

	private static final Logger logger = LoggerFactory.getLogger( EMFResourcesVisitor.class );
	
	private final Collection<IEMFResourceHandler> resourceHandlers;
	
	private final ResourceSet resourceSet;

	public EMFResourcesVisitor( final Collection<String> p_supportedExtensions,
								final Collection<IEMFResourceHandler> p_resourceHandlers ) {
		this( p_supportedExtensions, null, p_resourceHandlers, new ResourceSetImpl() );
	}

	public EMFResourcesVisitor( final Collection<String> p_supportedExtensions,
								final Collection<String> p_supportedNatures,
								final Collection<IEMFResourceHandler> p_resourceHandlers,
								final ResourceSet p_resourceSet ) {
		super( p_supportedExtensions, p_supportedNatures );
		
		resourceHandlers = p_resourceHandlers;
		
		if ( p_resourceSet == null ) {
			throw new IllegalArgumentException( "Resource set cannot be null." );
		}

		resourceSet = p_resourceSet;
	}

	public Collection<Resource> getFoundEMFResources() 
	throws ResourceAccessException {
		final Collection<Resource> resources = new ArrayList<Resource>();
		
		for ( final IResource resource : getFoundResources() ) {
			if ( resource instanceof IFile && EMFUtil.isEMFResource( resource ) ) {
				final Resource emfResource;
				final IEMFResourceHandler resHandler = findResourceHandler( resource );
				
				try {
					if ( resHandler == null ) {
						final IPath path = resource.getFullPath();
						final URI uri = URI.createPlatformResourceURI( path.toString(), false );
						emfResource = resourceSet.getResource( uri, true );
					}
					else {
						emfResource = resHandler.loadResource( resource );
					}
				
					resources.add( emfResource );
				}
				catch ( final RuntimeException p_ex ) {
					logger.error( "Unable to load resource " + resource.getName(), p_ex );
				}
			}
		}
		
		return resources;
	}
	
	private IEMFResourceHandler findResourceHandler( final IResource p_resource ) {
		if ( resourceHandlers != null ) {
			for ( final IEMFResourceHandler resHandler : resourceHandlers ) {
				if ( resHandler.handlesResource( p_resource ) ) {
					return resHandler;
				}
			}
		}
		
		return null;
	}
}
