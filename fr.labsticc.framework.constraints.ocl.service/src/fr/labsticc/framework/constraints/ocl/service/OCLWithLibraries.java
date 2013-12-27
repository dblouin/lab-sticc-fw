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
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.SendSignalAction;

public class OCLWithLibraries extends OCL<	EPackage, 
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
											EObject> {

	private static final String LINE_SEPARATOR = "\n";
	
	private final Collection<String> libraryIds;

	protected OCLWithLibraries(	final EcoreEnvironmentFactory p_envFactory,
								final Map<String, String> p_libraries ) 
	throws ParserException {
		super( p_envFactory );
		
		libraryIds = initLibraries( p_libraries );
	}

	protected OCLWithLibraries(	final EcoreEnvironmentFactory p_envFactory,
								final Map<String, String> p_libraries,
								final Resource p_resource ) 
	throws ParserException {
		super( p_envFactory, p_resource );
		
		libraryIds = initLibraries( p_libraries );
	}
	
	private Collection<String> initLibraries( final Map<String, String> p_libraries )
	throws ParserException {
		final Collection<String> libIds = new ArrayList<String>();
		libIds.addAll( p_libraries.keySet() );
		parseLibraries( p_libraries.values() );
		
		return libIds;
	}
	
	protected Collection<String> getLibraryIds() {
		return libraryIds;
	}
	
	public Object getId() {
		return getLibraryIds();
	}
	
	private void parseLibraries( final Collection<String> p_libraryTexts ) 
	throws ParserException {
        for ( final String libraryText : p_libraryTexts ) {
        	final String parsedText;
        	
        	if ( libraryText.startsWith( "MainModel : " ) ) {
        		final int endLineIndex = libraryText.indexOf( LINE_SEPARATOR );
        		
        		if ( endLineIndex > 0 ) {
        			parsedText = libraryText.substring( endLineIndex + LINE_SEPARATOR.length() + 1 );
        		}
        		else {
        			parsedText = libraryText;
        		}
        	}
        	else {
    			parsedText = libraryText;
        	}
        	
        	parse( new OCLInput( parsedText.toString() ) );
        }
	}
}
