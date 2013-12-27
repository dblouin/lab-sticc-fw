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
package fr.labsticc.framework.core.transformation;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.transformation.IModel;

/**
 * Interface declaring model injection operations.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface IModelInjector {

	/**
	 * Injects a source model content into the content of a target model. 
	 * @param p_source The source model whose content will be injected.
	 * @param p_target The target model that will receive the content of the source model.
	 * @throws ResourceAccessException
	 */
	void inject( 	IModel p_source,
					IModel p_target )
	throws ResourceAccessException;
}
