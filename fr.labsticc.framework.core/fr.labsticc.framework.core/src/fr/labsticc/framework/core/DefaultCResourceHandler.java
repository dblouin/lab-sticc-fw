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

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;

public class DefaultCResourceHandler implements ICResourceHandler {
	
	private final Properties environmentVariables;
	private final Map<String, String> definedSymbols;
	private final List<String> includePaths;
	
	public DefaultCResourceHandler( final Properties p_environmentVariables,
									final Map<String, String> p_definedSymbols,
									final List<String> p_includePaths ) {
		environmentVariables = p_environmentVariables;
		definedSymbols = p_definedSymbols;
		includePaths = p_includePaths;
	}
	
	@Override
	public Map<String, String> getDefinedSymbols( final String p_resourceId ) {
		return definedSymbols;
	}

	@Override
	public List<String> getIncludePaths( final String p_resourceId ) {
		return includePaths;
	}

	@Override
	public boolean isExcludedFromBuild( final String p_resourceId ) {
		return false;
	}

	@Override
	public String locateResource( final String p_resourceId ) {
		return p_resourceId;
	}

	@Override
	public String locateResource(	final String p_resourceId, 
									final ClassLoader p_loader ) {
		return p_resourceId;
	}

	@Override
	public void updateResource(	final String p_resourceId, 
								final byte[] p_resourceContent )
	throws ResourceAccessException {
		try {
			FileUtil.writeToFile( p_resourceId, p_resourceContent );
		} 
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}

	@Override
	public Properties getEnvironmentVariables( final String p_resourceId ) {
		return environmentVariables;
	}

	@Override
	public String getCompilingOptions( final String p_resourceId ) {
		return "";
	}
}
