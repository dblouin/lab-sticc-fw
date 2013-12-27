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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.SendSignalAction;

import fr.labsticc.framework.constraints.core.DelegatingPackageRegistry;
import fr.labsticc.framework.settings.model.settings.ScopeProvider;

public class ECoreOCLFactory implements IOCLFactory<EPackage, 
													EClassifier, 
													EOperation, 
													EStructuralFeature,
													EEnumLiteral, 
													EParameter, 
													EObject,
													CallOperationAction, 
													SendSignalAction, 
													Constraint,
													EClass, 
													EObject, 
													String, 
													Resource> {
	
	private final Map<Collection<String>, OCL<	EPackage, 
												EClassifier, 
												EOperation, 
												EStructuralFeature,
												EEnumLiteral, 
												EParameter, 
												EObject,
												CallOperationAction, 
												SendSignalAction, 
												Constraint,
												EClass, 
												EObject>> oclCache;

	public ECoreOCLFactory() {
		oclCache = new Hashtable<Collection<String>, OCL<	EPackage, 
															EClassifier, 
															EOperation, 
															EStructuralFeature,
															EEnumLiteral, 
															EParameter, 
															EObject,
															CallOperationAction, 
															SendSignalAction, 
															Constraint,
															EClass, 
															EObject>>();
	}

	@Override
    public OCL<	EPackage, 
				EClassifier, 
				EOperation, 
				EStructuralFeature,
				EEnumLiteral, 
				EParameter, 
				EObject,
				CallOperationAction, 
				SendSignalAction, 
				Constraint,
				EClass, 
				EObject> createOCL( final EObject p_context,
									final Collection<ScopeProvider> p_scopeProviders,
									final Map<String, String> p_libraries ) 
	throws ParserException {
		final Collection<String> key = key( p_libraries );
        OCL<EPackage, 
			EClassifier, 
			EOperation, 
			EStructuralFeature,
			EEnumLiteral, 
			EParameter, 
			EObject,
			CallOperationAction, 
			SendSignalAction, 
			Constraint,
			EClass, 
			EObject> ocl = oclCache.get( key );
        
        if ( ocl == null ) {
			ocl = new OCLWithLibraries( createEnvironmentFactory( p_context, p_scopeProviders ), p_libraries );
			oclCache.put( key, ocl );
        }
        
        return ocl;
    }
	
	private static Collection<String> key( final Map<String, String> p_libraries ) {
		return p_libraries.keySet();
	}
    
	@Override
    public OCL<	EPackage, 
				EClassifier, 
				EOperation, 
				EStructuralFeature,
				EEnumLiteral, 
				EParameter, 
				EObject,
				CallOperationAction, 
				SendSignalAction, 
				Constraint,
				EClass, 
				EObject> createOCL(	final EObject p_context,
									final Collection<ScopeProvider> p_scopeProviders,
									final Map<String, String> p_libraries,
								//	final ModelingLevel p_level,
            						final Resource p_res ) 
   throws ParserException {
		// Do not cache the OCL because it may depend on the context resource.
		return new OCLWithLibraries( createEnvironmentFactory( p_context, p_scopeProviders ), p_libraries, p_res );
    }
	
	private static EcoreEnvironmentFactory createEnvironmentFactory( 	final EObject p_context,
			 															final Collection<ScopeProvider> p_scopeProviders ) {
		if ( p_context == null ) {
			return MultiResourceEcoreEnvFactory.INSTANCE;
		}
		
		return new MultiResourceEcoreEnvFactory( 	new DelegatingPackageRegistry(	p_context.eResource().getResourceSet().getPackageRegistry(),
																					EPackage.Registry.INSTANCE ),
													p_scopeProviders );
	}
    
    @Override
    public EClassifier getContextClassifier( final EObject p_object ) {
    	if ( p_object == null ) {
    		return null;
    	}
    	
        return p_object.eClass();
    }
    
    @Override
    public String getName( final EObject p_modelElement ) {
        return ( (ENamedElement) p_modelElement ).getName();
    }


	@Override
	public void refreshLibrary( final String p_libraryId ) {
		if ( p_libraryId == null ) {
			oclCache.clear();
		}
		else {
			final Collection<Collection<String>> libIdsToRemove = new ArrayList<Collection<String>>();
			
			for ( final Collection<String> key : oclCache.keySet() ) {
				if ( key.contains( p_libraryId ) ) {
					libIdsToRemove.add( key );
				}
			}
			
			for ( final Collection<String> key : libIdsToRemove ) {
				oclCache.remove( key );
			}
		}
	}
    
	@Override
	public void refreshLibraries( final Collection<String> p_libraryIds ) {
		for ( final String libraryId : p_libraryIds ) {
			refreshLibrary( libraryId );
		}
	}
}
