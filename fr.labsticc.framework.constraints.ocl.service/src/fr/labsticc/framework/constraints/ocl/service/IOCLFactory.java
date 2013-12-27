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
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: IOCLFactory.java,v 1.2 2007/10/15 22:19:22 cdamus Exp $
 */

package fr.labsticc.framework.constraints.ocl.service;

import java.util.Collection;
import java.util.Map;

import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;

import fr.labsticc.framework.settings.model.settings.ScopeProvider;

/**
 * Interface for a provider of an {@link OCL} facade.
 */
public interface IOCLFactory<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E, LT, R> {

    /**
     * Creates a new OCL instance for my target meta-model.
     * 
     * @param modeling level the active modeling level
     * 
     * @return an OCL facade
     */
    OCL<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E> createOCL(	E p_context,
														    		Collection<ScopeProvider> p_scopeProviders,
																	Map<String, LT> p_libraries/*,
    																ModelingLevel p_level*/ )
    throws ParserException;
    
    /**
     * Creates a new OCL instance for my target metamodel, persisted in the
     * specified resource.
     * 
     * @param modeling level the active modeling level
     * 
     * @param res a resource in which to persist the OCL environment
     * 
     * @return an OCL facade
     */
    OCL<PK, C, O, P, EL, PM, S, COA, SSA, CT, CLS, E> createOCL(	E p_context,
														    		Collection<ScopeProvider> p_modelInterfaces,
																	Map<String, LT> p_libraries,
																//	ModelingLevel p_level,
    																R p_res )
    throws ParserException;
    
    /**
     * Obtains the metamodel-specific representation of the context classifier
     * of the specified model element.
     * 
     * @param object an element in the model
     * 
     * @return its classifier, in the metamodel that I target
     */
    C getContextClassifier( E p_object );
    
    /**
     * Obtains the name of an element of my metamodel (an instance of a
     * metaclass).
     * 
     * @param p_modelElement a named model element
     * 
     * @return the model element's name
     */
    String getName( E p_modelElement );
    
    void refreshLibrary( String p_libraryId );
    
    void refreshLibraries( Collection<String> p_libraryIds );
}
