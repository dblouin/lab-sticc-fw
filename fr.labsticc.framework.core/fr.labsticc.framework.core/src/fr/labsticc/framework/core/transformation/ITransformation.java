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

/**
 * This interface represents a transformation and meta-modeling framework agnostic model transformation. A transformation may use
 * a <code>ITransformationFactory</code> object to create the models it will use.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface ITransformation {
	
	/**
	 * Return the transformation factory used by this transformation. This factory creates models conformed to the modeling 
	 * and transformation frameworks of this transformation. 
	 * @return A <code>ITransformationFactory</code> representing the transformation factory used by this transformation.
	 */
	ITransformationFactory getFactory();
}
