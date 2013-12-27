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
 * This interface represents a transformation and meta-modeling framework agnostic model transformation factory. A transformation 
 * factory is used to create models used by a transformation.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public interface ITransformationFactory extends IModelInjector {

	/**
	 * Creates a model of the given name from the given resource for the meta-modeling and transformation framework represented
	 * by this factory.
	 * @param p_name A string representing the name of the model to create.
	 * @param p_resource A meta-modeling and transformation framework dependent object representing the resource providing 
	 * the model elements of the new model.
	 * @param p_metaModel An <code>IMetaModel</code> object representing the meta-model of the object
	 * @return An <code>IModel</code> object containing the elements from the given resource for the meta-modeling and
	 * transformation framework represented by this factory. 
	 * @throws TransformationException
	 */
	IModel createModel( String p_name,
						Object p_resource,
						IMetaModel p_metaModel )
	throws TransformationException;

	/**
	 * Creates an empty model of the given name for the meta-modeling and transformation framework represented by this
	 * factory. An empty model is typically created to represent target models.
	 * @param p_name A string representing the name of the model to create.
	 * @param p_metaModel An <code>IMetaModel</code> object representing the meta-model of the object
	 * @return An empty <code>IModel</code> object for the meta-modeling and transformation framework represented by this
	 * factory. 
	 * @throws TransformationException
	 */
	IModel createEmptyModel( 	String p_name,
								IMetaModel p_metaModel )
	throws TransformationException;

	/**
	 * Creates a meta-model of the given name from the given resource for the meta-modeling and transformation framework
	 * represented by this factory.
	 * @param p_name A string representing the name of the meta-model to create.
	 * @param p_resource A meta-modeling and transformation framework dependent object representing the resource providing the
	 * model elements of the new meta-model.
	 * @return An <code>IMetaModel</code> object for the meta-modeling and transformation framework represented by this factory. 
	 * framework.
	 * @throws TransformationException
	 */
	IMetaModel createMetaModel( final String p_name,
								final Object p_resource ) 
	throws TransformationException;

	/**
	 * Creates a transformation from the given information object for the meta-modeling and transformation framework represented
	 * by this factory.
	 * @param p_info An object carrying all info needed to create the transformation.
	 * @return An <code>ITransformation</code> object for the meta-modeling and transformation framework represented by this
	 * factory. 
	 * @throws TransformationException
	 */
	ITransformation createTransformation( Object p_info )
	throws TransformationException;
}
