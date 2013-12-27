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

import org.eclipse.core.runtime.CoreException;

import fr.labsticc.framework.constraints.service.DefaultConstraintServicesFacade;

public class IDEConstraintsServicesDelegate extends	DefaultConstraintServicesFacade {

	public IDEConstraintsServicesDelegate() 
	throws CoreException {
		super( 	ConstraintsIDEPlugin.getDefault().getConstraintInterpretationServicesByLanguageId(),
				ConstraintsIDEPlugin.getDefault().getConstraintPersistenceServicesByLanguageId() );
	}
}
