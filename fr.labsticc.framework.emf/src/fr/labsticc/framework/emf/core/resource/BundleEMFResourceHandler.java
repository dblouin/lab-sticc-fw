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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.internal.boot.PlatformURLConnection;
import org.eclipse.core.internal.boot.PlatformURLHandler;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.exception.ResourceNotFoundException;
import fr.labsticc.framework.ide.resource.PlatformResourceHandler;
import fr.labsticc.framework.ide.util.PluginUtil;


@SuppressWarnings("restriction")
public class BundleEMFResourceHandler extends PlatformResourceHandler {

	@Override
	public String locateResource( final String p_resourceId ) 
	throws ResourceAccessException {
		return locateResource( null, p_resourceId, URI.createURI( p_resourceId ) );
	}

	public String locateResource( 	final EObject p_object,
									final String p_objectId,
									final URI p_resUri )
	throws ResourceAccessException {
		if ( p_resUri == null ) {
			return null;
		}
		
		if ( p_resUri.isPlatform() ) {
			if ( p_resUri.isPlatformResource() ) { 
				final String projectName = getProjectName( p_resUri );
				final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject( projectName );
	
				if ( project == null || project.getLocation() == null ) {
					throw new IllegalArgumentException( "Resource project \"" + projectName + "\" not found in resource \"" + p_resUri + "\". Please check resource name." );
				}
				
				return project.getLocation() + p_resUri.toPlatformString( true ).replaceFirst( projectName, "" ); 
			}
			
			if ( p_resUri.isPlatformPlugin() ) {
				final String bundleId = p_resUri.segment( 1 );
				
				try {
					final StringBuffer endSegments = new StringBuffer();
					
					for ( int index = 2; index < p_resUri.segmentCount(); index++ ) {
						endSegments.append( p_resUri.segment( index ) );
						
						if ( index != p_resUri.segmentCount() - 1 ) {
							endSegments.append( File.separator );
						}
					}
					
					return PluginUtil.getPluginCanonicalEntryPath( bundleId, endSegments.toString() );
				}
				catch ( final IOException p_ex ) {
					throw new ResourceAccessException( p_ex );
				}
			}
			
			try {
				final PlatformURLHandler urlHandler = new PlatformURLHandler();
				final PlatformURLConnection connection = (PlatformURLConnection) urlHandler.openConnection( new URL( p_resUri.toString() ) ); 
					
				return connection.getResolvedURL().getPath();
			}
			catch ( final MalformedURLException p_ex ) {
				throw new ResourceAccessException( p_ex );
			}
			catch ( final IOException p_ex ) {
				throw new ResourceAccessException( p_ex );
			}
		}
		else if ( p_resUri.isFile() ) {
			if ( 	p_resUri.scheme() == null && 
					p_object != null &&
					p_object.eResource() != null ) {// Assume platform if no scheme
				final URI projectURI = p_object.eResource().getURI();
				final String projectName;
				
				if ( projectURI != null && projectURI.segmentCount() != 0 ) {
					projectName = getProjectName( projectURI );
				}
				else {
					if ( p_objectId == null ) {
						projectName = null;
					}
					else {
						projectName = p_objectId.substring( 0, p_objectId.substring( 1 ).indexOf( SEGMENT_SEPARATOR ) + 1 );
					}
				}
				
				if ( projectName == null ) {
					return p_resUri.toString();
				}
				
				return locateResource( 	p_object, 
										p_objectId,
										URI.createPlatformResourceURI( projectName + p_resUri.toString(), true ) );
			}

			return p_resUri.toFileString();
		}
		
		return p_resUri.toString();
	}
	
	protected String getProjectName( final URI p_uri ) {
		if ( p_uri == null || !p_uri.isPlatform() ) {
			throw new IllegalArgumentException( "URI cannot be null and its scheme must be platform." );
		}
		
		return SEGMENT_SEPARATOR + p_uri.segment( 1 );
	}
	
	public Resource loadFileResource( 	final String p_fullPath,
										final ResourceSet p_resSet ) 
	throws ResourceAccessException {
		final URI sourceURI = URI.createFileURI( p_fullPath );
		
		try {
			return p_resSet.getResource( sourceURI, true );
		}
		catch ( final RuntimeException p_ex ) {
			if ( p_ex.getMessage().contains( "does not exist" ) ) {
				throw new ResourceNotFoundException( p_ex );
			}
		}
		
		return null;
	}
}
