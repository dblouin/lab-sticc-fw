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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.labsticc.framework.core.transformation.AbstractModel;
import fr.labsticc.framework.emf.core.util.EMFUtil;

/**
 * This class is a basic implementation of an EMF model. It uses an EMF resource to hold the model elements of the model.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class EMFModel extends AbstractModel implements IEMFModel {
	
	private final Resource resource;

	/**
	 * Construct a model for the EMF from an EMF resource and an EMF meta-model.
	 * @param p_name A string representing the name of this model.
	 * @param p_resource A loaded EMF <code>Resource</code> containing the elements of this model.
	 * @param p_metaModel An <code>IEMFMetaModel</code> object representing the meta-model of this model.
	 */
	public EMFModel( 	final String p_name,
						final Resource p_resource,
						final IEMFMetaModel p_metaModel ) {
		super( p_name, p_metaModel );
		
		if ( p_resource == null ) {
			throw new IllegalArgumentException( "EMF model resource cannot be null." );
		}
		
		resource = p_resource;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getRootElements()
	 */
	@Override
	public List<EObject> getRootElements() {
		return resource.getContents();
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#toInputStream()
	 */
	@Override
	public InputStream toInputStream() 
	throws IOException {
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		resource.save( outputStream, EMFUtil.getDefaultOptions() );
		
		final InputStream inputStr = new ByteArrayInputStream( outputStream.toByteArray() );
		outputStream.close();
		
		return inputStr;
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getURI()
	 */
	@Override
	public String getURI() {
		return resource == null ? null : resource.getURI().toString();
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.emf.core.transformation.IEMFModel#getResource()
	 */
	@Override
	public Resource getResource() {
		return resource;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.AbstractModel#getMetaModel()
	 */
	@Override
	public IEMFMetaModel getMetaModel() {
		return (IEMFMetaModel) super.getMetaModel();
	}
}
