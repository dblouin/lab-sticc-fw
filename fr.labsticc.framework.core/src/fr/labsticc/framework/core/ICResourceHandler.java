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

import java.util.List;
import java.util.Map;
import java.util.Properties;

import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * The interface of a resource handler specialized for C code resources. It is able to retrieve information
 * for C resources needed for its compilation.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface ICResourceHandler extends IResourceHandler {

	Properties getEnvironmentVariables( String p_resourceId )
	throws ResourceAccessException;
	
	List<String> getIncludePaths( String p_resourceId )
	throws ResourceAccessException;
	
	String getCompilingOptions( String p_resourceId )
	throws ResourceAccessException;

	Map<String, String> getDefinedSymbols( String p_resourceId )
	throws ResourceAccessException;
	
	boolean isExcludedFromBuild( String p_resourceId )
	throws ResourceAccessException;
}
