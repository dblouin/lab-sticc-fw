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
 * This is an abstract implementation of the model interface.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public abstract class AbstractModel implements IModel {

	/**
	 * A reference to this model's meta-model.
	 */
	private IMetaModel metaModel;

	/**
	 * The name of this model.
	 */
	private String name;
	
	/**
	 * Creates a model from a given name and a given meta-model.
	 * @param p_name A string representing the name of this model.
	 * @param p_metaModel A <code>IMetaModel</code> object representing this model's meta-model.
	 */
	protected AbstractModel( 	final String p_name,
								final IMetaModel p_metaModel ) {
		setName( p_name );
		setMetaModel( p_metaModel );
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getMetaModel()
	 */
	@Override
	public IMetaModel getMetaModel() {
		return metaModel;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#setMetaModel(fr.labsticc.framework.core.transformation.IMetaModel)
	 */
	@Override
	public void setMetaModel( IMetaModel p_value ) {
		metaModel = p_value;
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#setName(java.lang.String)
	 */
	@Override
	public void setName( final String p_name ) {
		name = p_name;
	}
	
	/**
	 * Returns a string representing the URI of this model. It is composed as the concatenation of this modem's name with
	 * the <code>:</code> symbol and the <code>URI</code>.
	 * @return A string representing this model.
	 */
	@Override
	public String toString() {
		return getName() + ":" + getURI();
	}
}
