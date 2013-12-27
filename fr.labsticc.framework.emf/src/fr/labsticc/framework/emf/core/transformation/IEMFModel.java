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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.labsticc.framework.core.transformation.IModel;

/**
 * This interface represents a model for the Eclipse Modeling Framework (EMF).
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface IEMFModel extends IModel {
	
	@Override
	List<EObject> getRootElements();

	/**
	 * Returns the EMF resource containing the model elements of this EMF model.
	 * @return a <code>Resource</code> object containing the model elements of this EMF model.
	 */
	Resource getResource();
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getMetaModel()
	 */
	@Override
	IEMFMetaModel getMetaModel();
}
