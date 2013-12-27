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

/**
 * This class is a basic implementation of an EMF meta-model with deferred loading of the content resource. It stores the 
 * resource URI and only loads the content when needed.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class EMFDeferredLoadingMetaModel extends EMFDeferredLoadingModel implements IEMFMetaModel {

	public EMFDeferredLoadingMetaModel( final String p_name, 
										final String p_resURI ) {
		super( p_name, p_resURI, null );
	}
}
