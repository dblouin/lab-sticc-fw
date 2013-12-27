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

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import fr.labsticc.framework.constraints.ide.view.ConstraintsLanguagePreferencePage;

public class ConstraintPreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		final IPreferenceStore store = getPreferenceStore();

		// DB: Added this preference page for constraints library directory.
		ConstraintsLanguagePreferencePage.initDefaults( store );
	}

	protected IPreferenceStore getPreferenceStore() {
		return ConstraintsIDEPlugin.getDefault().getPreferenceStore();
	}
}
