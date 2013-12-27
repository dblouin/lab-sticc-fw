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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * This interface represents a transformation and meta-modeling framework agnostic model. A model contains a set of model 
 * elements representing domain objects. Various implementations can be provided for various modeling and transformation
 * frameworks such as EMF and ATL.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface IModel {

	/**
	 * Returns the name of this model.
	 * @return A string representing the name of this model.
	 */
	String getName();
	
	/**
	 * Sets the name of this model.
	 * @param p_name A string representing the name of this model.
	 */
	void setName( String p_name );

	/**
	 * Returns a unique resource identifier (URI) for this model.
	 * @return A string representing the URI of this model.
	 */
	String getURI();
	
	/**
	 * Return the meta-model of this model.
	 * @return An <code>IMetaModel</code> object representing the meta-model of this model.
	 */
	IMetaModel getMetaModel();
	
	/**
	 * Sets the meta-model of this model.
	 * @param p_value An <code>IMetaModel</code> object representing the meta-model of this model.
	 */
	void setMetaModel( IMetaModel p_value );
	
	/**
	 * Return the root model elements of this model.
	 * @return A <code>List</code> containing the root elements of this model.
	 */
	List<?> getRootElements();
	
	/**
	 * Convert this model content to an input stream.
	 * @return An <code>InputStream</code> object representing the content of this model.
	 * @throws IOException
	 */
	InputStream toInputStream()
	throws IOException;
}
