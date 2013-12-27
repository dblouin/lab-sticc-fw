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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.ocl.expressions.OCLExpression;


/**
 * A resource for loading and saving OCL expressions.
 */
public class OCLResource extends XMIResourceImpl {

    private static Map<String, Object> saveOptions = new java.util.HashMap<String, Object>();
    
    static {
        saveOptions.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, true);
    }
    
	/**
	 * Initializes me with my URI.
	 * 
	 * @param uri my URI
	 */
	public OCLResource(URI uri) {
		super(uri);
	}
	
	@Override
	protected boolean useIDs() {
	    return true;
	}
	
	@Override
	protected boolean useUUIDs() {
	    return true;
	}
	
	/**
	 * Sets my contents to the specified OCL expression.
	 * 
	 * @param expr an OCL expression
	 */
	public void setOCLExpression(OCLExpression<Object> expr) {
		// add my expression as the first root, because I already contain
		//    variables and EPackages defining dynamically-generated types
		getContents().add( 0, expr );
	}
	
	/**
	 * Obtains the OCL expression that I store.
	 * 
	 * @return my OCL expression
	 */
	@SuppressWarnings("unchecked")
	public OCLExpression<Object> getOCLExpression() {
		for ( final EObject content : getContents() ) {
			if ( content instanceof OCLExpression<?> ) {
				return (OCLExpression<Object>) content;
			}
		}
		
		return null;
	}
}
