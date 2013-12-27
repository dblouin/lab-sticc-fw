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
package fr.labsticc.framework.emf.core.transformation;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * This class is a basic implementation of an EMF meta-model. It uses an EMF resource to hold the model elements of the
 * meta-model.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class EMFMetaModel extends EMFModel implements IEMFMetaModel {

	public EMFMetaModel( 	final String p_name, 
							final Resource p_resource ) {
		super( p_name, p_resource, null );
	}
}
