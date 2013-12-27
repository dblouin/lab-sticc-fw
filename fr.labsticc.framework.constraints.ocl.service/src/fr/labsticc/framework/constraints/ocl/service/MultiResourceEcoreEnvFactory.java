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
package fr.labsticc.framework.constraints.ocl.service;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;

import fr.labsticc.framework.settings.model.settings.ScopeProvider;

public class MultiResourceEcoreEnvFactory extends EcoreEnvironmentFactory {

	/**
	 * A convenient shared instance of the environment factory, that creates
	 * environments using the global package registry.
	 */
	@SuppressWarnings("unchecked")
	public static MultiResourceEcoreEnvFactory INSTANCE = new MultiResourceEcoreEnvFactory( Collections.EMPTY_SET );
	
	private final Collection<ScopeProvider> scopeProviders;

	/**
	 * Initializes me. Environments that I create will use the global package registry to look up packages.
	 */
	public MultiResourceEcoreEnvFactory( final Collection<ScopeProvider> p_scopeProviders ) {
		this( EPackage.Registry.INSTANCE, p_scopeProviders );
	}

	/**
	 * Initializes me with an <code>EPackage.Registry</code> that the
	 * environments I create will use to look up packages.
	 * 
	 * @param reg my package registry (must not be <code>null</code>)
	 */
	public MultiResourceEcoreEnvFactory( 	final EPackage.Registry p_registry,
											final Collection<ScopeProvider> p_scopeProviders ) {
		super( p_registry );
		
		scopeProviders = p_scopeProviders;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ocl.ecore.EcoreEnvironmentFactory#createEvaluationEnvironment()
	 */
	@Override
	public MultiResourceEcoreEvaluationEnvironment createEvaluationEnvironment() {
		return new MultiResourceEcoreEvaluationEnvironment( new EcoreEvaluationEnvironment(), scopeProviders );
	}

	@Override
	public MultiResourceEcoreEvaluationEnvironment createEvaluationEnvironment( final EvaluationEnvironment<EClassifier, 
																											EOperation, 
																											EStructuralFeature, 
																											EClass, 
																											EObject> p_parent ) {
		return new MultiResourceEcoreEvaluationEnvironment( p_parent, scopeProviders );
	}
}
