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
package fr.labsticc.framework.constraints.examples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.labsticc.framework.ide.wizard.AbstractExampleWizard;

/**
 * Create the constraints example project.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class ConstraintsExampleWizard extends AbstractExampleWizard {
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see AbstractExampleWizard#getProjectDescriptors()
	 */
	@Override
	protected Collection<ProjectDescriptor> getProjectDescriptors() {
		final List<ProjectDescriptor> projects = new ArrayList<ProjectDescriptor>( 1 );
		projects.add( new ProjectDescriptor( 	Activator.PLUGIN_ID, 
												"examples/fr.labsticc.framework.constraints.examples.ide.zip", 
												"fr.labsticc.framework.constraints.examples.ide" ) );
		return projects;
	}
}
