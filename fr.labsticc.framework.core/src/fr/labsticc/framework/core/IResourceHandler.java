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
package fr.labsticc.framework.core;

import fr.labsticc.framework.core.exception.ResourceAccessException;

public interface IResourceHandler {
	
	/**
	 * Finds the absolute location of this resource in the file system. EMF URIs are managed.
	 * @param p_resourceId The resource id conforming to the EMF URI.
	 * @return The absolute location in the file system.
	 * @throws ResourceAccessException
	 */
	String locateResource( 	String p_resourceId )
	throws ResourceAccessException;

	/**
	 * Finds the absolute location of this resource in the file system. EMF URIs are managed.
	 * @param p_resourceId The resource id conforming to the EMF URI.
	 * @param p_loader The class loader to use to find the resource.
	 * @return The absolute location in the file system.
	 * @throws ResourceAccessException
	 */
	String locateResource( 	String p_resourceId,
							ClassLoader p_loader )
	throws ResourceAccessException;

	/**
	 * Write the specified content to the resource identified by the id.
	 * @param p_resourceId
	 * @param p_resourceContent
	 * @throws ResourceAccessException
	 */
	void updateResource( 	String p_resourceId,
							byte[] p_resourceContent )
	throws ResourceAccessException;
}
