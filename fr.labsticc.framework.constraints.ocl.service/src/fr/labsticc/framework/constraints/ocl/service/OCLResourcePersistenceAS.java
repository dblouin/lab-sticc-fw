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
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: OCLResource.java,v 1.14 2007/10/15 22:19:22 cdamus Exp $
 */

package fr.labsticc.framework.constraints.ocl.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ocl.expressions.OCLExpression;

import fr.labsticc.framework.constraints.service.IConstraintPersistenceAS;
import fr.labsticc.framework.core.exception.ResourceAccessException;


public class OCLResourcePersistenceAS implements IConstraintPersistenceAS {

    private static Map<String, Object> saveOptions = new java.util.HashMap<String, Object>();
    
    static {
        saveOptions.put( XMLResource.OPTION_SAVE_TYPE_INFORMATION, true );
    }
    
	/**
	 * Initializes me.
	 */
	public OCLResourcePersistenceAS() {
	}
	
	/**
	 * Loads an OCL expression from the specified <code>path</code>.  The
	 * OCL expression is converted to a string using a custom AST visitor that
	 * renders the string representation.
	 * 
	 * @param path the absolute path of the XMI file to load
	 * @return the string representation of the OCL expression, if found
	 * @throws IOException if anything goes wrong in loading the XMI file
	 */
	@Override
	public OCLExpression<Object> readEntity( final String p_entityId ) 
	throws ResourceAccessException {
		final ResourceSet resourceSet = new ResourceSetImpl();
		
		// create and load the resource
		final OCLResource resource = new OCLResource( URI.createFileURI( p_entityId ) );
		resourceSet.getResources().add( resource );
		
		try {
			resource.load( Collections.EMPTY_MAP );
		
			return resource.getOCLExpression();
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	/**
	 * Saves the specified OCL expression to an XMI file.
	 * 
	 * @param path the fully-qualified path of the XMI file to save
	 * @param document the current OCL document
	 * @param expression the expression to save
	 * 
	 * @throws RuntimeException if anything goes wrong in parsing
	 * @throws IOException if anything goes wrong in saving
	 * @throws OCLParsingException if anything goes wrong in parsing
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OCLExpression<Object> persistEntity( final String p_entityId, 
												final EObject p_expression )
	throws ResourceAccessException {
		final OCLResource resource = new OCLResource( URI.createFileURI( p_entityId ) );
		
		final OCLExpression<Object> oclExpr = (OCLExpression<Object>) p_expression;
		
		// add the AST to the resource and save it
		resource.setOCLExpression( oclExpr );
		
		try {
			resource.save( saveOptions );
			
			return oclExpr;
		}
		catch ( final IOException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
}
