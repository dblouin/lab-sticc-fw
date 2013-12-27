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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ResourceDeltaVisitor implements IResourceDeltaVisitor {
	private final ResourceSet resourceSet;
	private final Collection<Resource> savedResources;
	private final Collection<Resource> changedResources;
	private final Collection<Resource> removedResources;
	private EClass rootObjectClass;
	private EObject rootObject;
	private final Resource disabledResource;
	
	public ResourceDeltaVisitor( 	final ResourceSet p_resourceSet,
									final Collection<Resource> p_savedResources,
									final Resource p_disabledResource,
									final EClass p_rootObjectClass ) {
		resourceSet = p_resourceSet;
		savedResources = p_savedResources;
		disabledResource = p_disabledResource;
		changedResources = new ArrayList<Resource>();
		removedResources = new ArrayList<Resource>();
		rootObject = null;
		rootObjectClass = p_rootObjectClass;
	}

	@Override
	public boolean visit( final IResourceDelta delta ) {
		if ( 	delta.getFlags() != IResourceDelta.MARKERS &&
				delta.getResource().getType() == IResource.FILE) {
			if ( (delta.getKind() & (IResourceDelta.CHANGED | IResourceDelta.REMOVED)) != 0 ) {
				final String resPath = delta.getFullPath().toString();
				
				// Important: do not use URI.createURI() because resource listening will not work when the file name contains space characters
				final Resource resource = resourceSet.getResource( URI.createPlatformResourceURI( resPath, true ), false );

				if ( resource != null && resource != disabledResource ) {
					if ( 	!resource.getContents().isEmpty() && 
							rootObjectClass.isInstance( resource.getContents().get( 0 ) ) ) {
						rootObject = resource.getContents().get( 0 );
					}
					
					if ((delta.getKind() & IResourceDelta.REMOVED) != 0) {
						removedResources.add(resource);
					}
					else if ( !savedResources.remove( resource ) ) {
						changedResources.add( resource );
					}
				}
			}
		}

		return true;
	}

	public Collection<Resource> getChangedResources() {
		return changedResources;
	}

	public Collection<Resource> getRemovedResources() {
		return removedResources;
	}

	public EObject getRootObject() {
		return rootObject;
	}
}
