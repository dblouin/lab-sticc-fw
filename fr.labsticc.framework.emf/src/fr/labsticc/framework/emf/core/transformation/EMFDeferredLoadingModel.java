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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.labsticc.framework.core.transformation.AbstractModel;
import fr.labsticc.framework.core.transformation.IDeferredLoadingModel;
import fr.labsticc.framework.emf.core.util.EMFUtil;

/**
 * This class is a basic implementation of an EMF model with deferred loading of the content resource. It stores the resource URI
 * and only loads the content when needed.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class EMFDeferredLoadingModel extends AbstractModel implements IEMFModel, IDeferredLoadingModel {

	private Resource resource;
	
	private final String resURI;

	public EMFDeferredLoadingModel( final String p_name, 
									final String p_resURI,
									final IEMFMetaModel p_metaModel ) {
		super( p_name, p_metaModel );
		
		resURI = p_resURI;
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getRootElements()
	 */
	@Override
	public List<EObject> getRootElements() {
		if ( getResource() == null ) {
			throw new IllegalStateException( "Resource is not loaded." );
		}

		return getResource().getContents();
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#getURI()
	 */
	@Override
	public String getURI() {
		return resURI;
	}

	/* (non-Javadoc)
	 * @see fr.labsticc.framework.core.transformation.IModel#toInputStream()
	 */
	@Override
	public InputStream toInputStream() 
	throws IOException {
		if ( getResource() == null ) {
			throw new IllegalStateException( "Resource is not loaded." );
		}
		
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		getResource().save( outputStream, EMFUtil.getDefaultOptions() );
		
		final InputStream inputStr = new ByteArrayInputStream( outputStream.toByteArray() );
		outputStream.close();
		
		return inputStr;
	}
	
	/* (non-Javadoc)
	 * @see fr.labsticc.framework.emf.core.transformation.IEMFModel#getResource()
	 */
	@Override
	public Resource getResource() {
		if ( resource == null ) {
			final ResourceSet resourceSet = new ResourceSetImpl();
			resource = resourceSet.getResource( URI.createURI( resURI ), true );
		}
		
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
