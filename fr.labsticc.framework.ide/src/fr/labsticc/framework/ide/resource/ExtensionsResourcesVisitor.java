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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;

public class ExtensionsResourcesVisitor implements IResourceVisitor {

	private final Collection<String> supportedExtensions;
	private final Collection<String> supportedNatures;
	private final Collection<IResource> foundResources;

	public ExtensionsResourcesVisitor( final Collection<String> p_supportedExtensions ) {
		this( p_supportedExtensions, null );
	}

	public ExtensionsResourcesVisitor( 	final Collection<String> p_supportedExtensions,
										final Collection<String> p_supportedNatures ) {
		supportedExtensions = p_supportedExtensions;
		supportedNatures = p_supportedNatures;
		foundResources = new ArrayList<IResource>();
	}

	@Override
	public boolean visit( final IResource p_resource )
	throws CoreException {
		if ( !p_resource.isAccessible() ) {
			return false;
		}
		
		if ( supportedNatures != null && p_resource instanceof IProject ) {
			final IProject project = (IProject) p_resource;
			
			for ( final String natureId : supportedNatures ) {
				if ( project.hasNature( natureId ) ) {
					return true;
				}
			}
			
			// Do not visit contents
			return false;
		}
		
		if ( supportedExtensions == null || supportedExtensions.contains( p_resource.getFileExtension() ) ) {
			foundResources.add( p_resource );
		}
		
		return true;
	}

	public Collection<IResource> getFoundResources() {
		return foundResources;
	}
}
