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
/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.labsticc.framework.emf.view.editor;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import fr.labsticc.framework.core.exception.IExceptionHandler;

public abstract class RuntimeRegistryItemProvider extends ItemProviderAdapter {
	
	private final IExceptionHandler exceptionHandler;

	public RuntimeRegistryItemProvider( final AdapterFactory p_adapterFactory ) {
		this( p_adapterFactory, null );
	}

	public RuntimeRegistryItemProvider( final AdapterFactory p_adapterFactory,
										final IExceptionHandler p_exceptionHandler ) {
		super( p_adapterFactory );
		
		exceptionHandler = p_exceptionHandler;
	}

	/**
	 * DB: Override this so that runtime registry packages elements can be used in EMF object tree editors.
	 */
	@Override
	protected ItemPropertyDescriptor createItemPropertyDescriptor( 	final AdapterFactory adapterFactory,
																	final ResourceLocator resourceLocator,
																	final String displayName,
																	final String description,
																	final EStructuralFeature feature,
																	final boolean isSettable,
																	final boolean multiLine,
																	final boolean sortChoices,
																	final Object staticImage,
																	final String category,
																	final String[] filterFlags ) {
	    return new ItemPropertyDescriptor(	adapterFactory,
										 	resourceLocator,
										    displayName,
										    description,
										    feature,
										    isSettable,
										    multiLine,
										    sortChoices,
										    staticImage,
										    category,
										    filterFlags ) {

	        @Override
	        public Collection<?> getChoiceOfValues(Object object) {
	          Collection<?> result = super.getChoiceOfValues(object);
	         
	          if (feature instanceof EReference && object instanceof EObject) {
	            @SuppressWarnings("unchecked")
	            List<EObject> eObjects = (List<EObject>)(List<?>)new LinkedList<Object>(result);
	            Resource resource = ((EObject)object).eResource();
	            if (resource != null)
	            {
	              ResourceSet resourceSet = resource.getResourceSet();
	              if (resourceSet != null)
	              {
	                Collection<EObject> visited = new HashSet<EObject>(eObjects);
	                Registry packageRegistry = resourceSet.getPackageRegistry();
	                for (String nsURI : packageRegistry.keySet())
	                {
	                  collectReachableObjectsOfType(visited, eObjects, packageRegistry.getEPackage(nsURI), feature.getEType());
	                }
	              }
	            }
	            result = eObjects;
	          }
	          return result;
	        }
	        
	        @Override
	        protected Object getValue(	final EObject p_object,
	        							final EStructuralFeature p_feature ) {
	        	try {
	        		return p_object.eGet( p_feature );
	        	}
	        	catch ( final Throwable p_exception ) {
	        		if ( exceptionHandler != null ) {
	        			return exceptionHandler.handleExceptionWithReturn( p_exception, p_object );
	        		}
	        		
	        		return null;
	        	}
	        }
	    };
	}
}
