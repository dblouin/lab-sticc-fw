package fr.labsticc.framework.emf.view;
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


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

import fr.labsticc.framework.emf.core.resource.IEMFResourceHandler;
import fr.labsticc.framework.ide.util.PluginUtil;

public class EmfFwIdePlugin extends Plugin {

	private static final String RESOURCE_HANDLER_EXT_ID = "resourceHandlers";
	private static final String RESOURCE_HANDLER_EXT_HANDLER_ELEM = "handler";
	private static final String RESOURCE_HANDLER_EXT_HANDLER_ELEM_CLASS_ATT = "class";

	// The shared instance
	private static EmfFwIdePlugin plugin;

	private final Collection<IEMFResourceHandler> resourceHandlers;
	
	public EmfFwIdePlugin() {
		resourceHandlers = new ArrayList<IEMFResourceHandler>();
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static EmfFwIdePlugin getDefault() {
		return plugin;
	}

	@Override
	public void start(BundleContext context)
	throws Exception {
		super.start(context);

		plugin = this;
		initResourceHandlers();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		resourceHandlers.clear();
		
		super.stop(context);
	}
	
	private void initResourceHandlers() 
	throws CoreException {
		for( final IExtension extension : PluginUtil.getExtensionPointExtensions( getBundle().getSymbolicName(), RESOURCE_HANDLER_EXT_ID ) ) {
			final IConfigurationElement[] elements = extension.getConfigurationElements();
		
			for( final IConfigurationElement element : elements ) {
				if ( RESOURCE_HANDLER_EXT_HANDLER_ELEM.equals( element.getName() ) ) {
					resourceHandlers.add( (IEMFResourceHandler) element.createExecutableExtension( RESOURCE_HANDLER_EXT_HANDLER_ELEM_CLASS_ATT ) );
				}
			}
		}
	}

	public Collection<IEMFResourceHandler> getResourceHandlers() {
		return resourceHandlers;
	}
}
