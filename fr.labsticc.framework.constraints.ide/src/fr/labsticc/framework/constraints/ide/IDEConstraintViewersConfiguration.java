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
package fr.labsticc.framework.constraints.ide;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor;
import fr.labsticc.framework.constraints.view.BlankConstraintViewerConfig;
import fr.labsticc.framework.constraints.view.IConstraintLanguageViewerConfig;
import fr.labsticc.framework.constraints.view.IConstraintViewersConfiguration;

public class IDEConstraintViewersConfiguration implements IConstraintViewersConfiguration {
	
	IDEConstraintViewersConfiguration() {
	}

	@Override
	public Map<String, IConstraintLanguageViewerConfig> viewerConfigurations() {
		final Map<String, IConstraintLanguageViewerConfig> configs = new HashMap<String, IConstraintLanguageViewerConfig>();
		
		for ( final ConstraintLanguage language : ConstraintsIDEPlugin.getDefault().getDefinedConstraintLanguages() ) {
			final String languageId = language.getId();
			
			try {
				configs.put( languageId, viewerConfig( language ) );
			}
			catch ( final Exception p_ex ) {
				throw new RuntimeException( p_ex );
			}
		}
		
		return configs;
	}
	
	private IConstraintLanguageViewerConfig viewerConfig( final ConstraintLanguage p_language ) 
	throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		if ( p_language instanceof FormalConstraintLanguage ) {
			final FormalConstraintLanguage formalLanguage = (FormalConstraintLanguage) p_language;
			final ServiceDescriptor descr = formalLanguage.getViewer();
			
			if ( descr == null ) {
				return new BlankConstraintViewerConfig( p_language.getId(), p_language.getName(), true );
			}

			final Bundle bundle = Platform.getBundle( descr.getProviderId() );
			
			if ( bundle == null ) {
				throw new IllegalAccessException( "Bundle " + descr.getProviderId() + " not found." );
			}
			
			final IConstraintLanguageViewerConfig viewerConfig = (IConstraintLanguageViewerConfig) bundle.loadClass( descr.getServiceClass() ).newInstance();
			viewerConfig.setLanguageId( p_language.getId() );
			viewerConfig.setLanguageName( p_language.getName() );
			viewerConfig.setLanguageFormal( true );

			return viewerConfig;
		}
		
		return new BlankConstraintViewerConfig( p_language.getId(), p_language.getName(), false );
	}
}
