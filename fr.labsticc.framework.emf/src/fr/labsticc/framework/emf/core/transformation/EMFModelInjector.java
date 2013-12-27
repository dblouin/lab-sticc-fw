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

import java.io.IOException;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.transformation.IModel;
import fr.labsticc.framework.core.transformation.IModelInjector;
import fr.labsticc.framework.emf.core.util.EMFUtil;

/**
 * This class is a model injector for the EMF framework. Injection is performed using the EMF resources
 * save and load API with input streams from the models.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class EMFModelInjector implements IModelInjector {

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModelInjector#inject(fr.labsticc.framework.core.transformation.IModel, fr.labsticc.framework.core.transformation.IModel)
	 */
	@Override
	public void inject( final IModel p_source, 
						final IModel p_target )
	throws ResourceAccessException {
		try {
			( (EMFModel) p_target ).getResource().load( p_source.toInputStream(), EMFUtil.getDefaultOptions() );
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
}
