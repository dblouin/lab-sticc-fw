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
package fr.labsticc.framework.ide.resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;

import fr.labsticc.framework.core.IResourceHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;

public class PlatformResourceHandler implements IResourceHandler {

	protected static final String SEGMENT_SEPARATOR = "/";
	protected static final String DOUBLE_QUOTES = "\"";

	@Override
	public String locateResource( final String p_resource ) 
	throws ResourceAccessException {
		return locateResource( p_resource, null );
	}
	
	@Override
	public String locateResource( 	final String p_resource,
									final ClassLoader p_loader ) 
	throws ResourceAccessException {
		final String cleanedResName;
		final boolean quoted = p_resource.startsWith( DOUBLE_QUOTES ) && p_resource.endsWith( DOUBLE_QUOTES );
			
		if ( quoted ) {
			cleanedResName = p_resource.substring( 1, p_resource.length() - 1 );
		}
		else {
			cleanedResName = p_resource;
		}
		
		final ClassLoader loader = p_loader == null ? getClass().getClassLoader() : p_loader;
		
		final URL resUrl = loader.getResource( cleanedResName );
		
		if ( resUrl == null ) {
			throw new ResourceAccessException( "Resource " + cleanedResName + " not found." );
		}

		try {
			final File file = new File( FileLocator.toFileURL( resUrl ).getPath() );
			String foundRes = file.getPath();
			
			if ( file.isDirectory() && p_resource.endsWith( SEGMENT_SEPARATOR ) ) {
				foundRes += SEGMENT_SEPARATOR;
			}
			
			if ( quoted ) {
				foundRes = DOUBLE_QUOTES + foundRes + DOUBLE_QUOTES;
			}
			
			return foundRes;
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}

	@Override
	public void updateResource( final String p_resourceId, 
								byte[] p_resourceContent )
	throws ResourceAccessException {
		try {
			FileUtil.writeToFile( p_resourceId, p_resourceContent );
			findResourceInWorkspace( p_resourceId ).refreshLocal( IResource.DEPTH_ZERO, null );
		} 
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
		catch ( final CoreException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	protected IResource findResourceInWorkspace( final String p_resourceId ) {
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( new Path( p_resourceId ) );
	}
	
	protected IResource getResource( final String p_resourceId ) {
		return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( new Path( p_resourceId ) );
	}
}
