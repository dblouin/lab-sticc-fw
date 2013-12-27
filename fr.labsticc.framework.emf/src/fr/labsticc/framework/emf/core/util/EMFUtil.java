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
package fr.labsticc.framework.emf.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.PackageNotFoundException;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.xml.sax.SAXParseException;

public class EMFUtil {

	public static EFactory getEFactory( final Class<?> p_dataType ) {
		final EPackage emfPackage = getEPackage( p_dataType );
		
		return emfPackage.getEFactoryInstance();
	}

	public static EPackage getEPackage( final Class<?> p_dataType ) {
		final EClassifier eClassifier = getEClassifier( p_dataType );
		
		if ( eClassifier == null ) {
			return null;
		}
		
		return eClassifier.getEPackage();
	}
	
	public static EClassifier getEClassifier( final Class<?> p_dataType ) {
		final Class<?> interfaceClass = findInterface( p_dataType );
		final Registry packageRegistry = EPackage.Registry.INSTANCE;

		// Do not use the key set directly. getEPackage adds packages created from the
		// descriptors so would get concurrent modification.
		final Collection<String> uris = new ArrayList<String>(); 
		uris.addAll( packageRegistry.keySet() );
		
		for ( final String packURI : uris ) {
			final EPackage ePackage = (EPackage) packageRegistry.getEPackage( packURI );
			
			for ( final EClassifier eClassifier : ePackage.getEClassifiers() ) {
				if ( interfaceClass == eClassifier.getInstanceClass() ) {
					return eClassifier;
				}
			}
		}
		
		return null;
	}

	public static Class<?> findInterface( final Class<?> p_dataType ) {
		if ( p_dataType.isInterface() ) {
			return p_dataType;
		}
		
		for ( Class<?> upInterface : p_dataType.getInterfaces() ) {
			if ( EObject.class.isAssignableFrom( upInterface ) ) {
				return upInterface;
			}
		}
		
		return null;
	}
	
	public static Class<?> getFeatureType( 	final EClassifier p_featureContainerClass,
											final EStructuralFeature p_feature ) {
        EClassifier classifier = getFeatureClassifier( p_featureContainerClass, p_feature);
        
       	return classifier.getInstanceClass();
	}

	public static EClassifier getFeatureClassifier( final EClassifier p_featureContainerClass,
													final EStructuralFeature p_feature ) {
        EClassifier classifier = p_feature.getEType();
        final EGenericType genType = p_feature.getEGenericType();

        if ( genType != null && genType.getETypeParameter() != null ) {
        	classifier = getFeatureClassifier( p_featureContainerClass, p_feature, genType );
        }
        
       	return classifier;
	}

	public static EClassifier getFeatureClassifier( final EClassifier p_featureContainerClass,
													final EStructuralFeature p_feature,
													final EGenericType p_genType ) {
		for ( EGenericType superType : ( (EClass)p_featureContainerClass ).getEGenericSuperTypes() ) {
			final EClassifier classifier = superType.getEClassifier();
			final List<ETypeParameter> itClassTypeParms = classifier.getETypeParameters();
			
			for ( int index = 0; index < itClassTypeParms.size(); index++ ) {
				ETypeParameter typParm = itClassTypeParms.get( index );
	
				if ( typParm.getName().equals( p_genType.getETypeParameter().getName() ) ) {
					return superType.getETypeArguments().get( index ).getEClassifier();
				}
			}
			
			final EClassifier foundClass = getFeatureClassifier( classifier, p_feature );
			
			if ( foundClass != null ) {
				return foundClass;
			}
		}
       	
       	return null;
	}
	
	public static boolean isEMFResource( final IResource p_resource ) {
		return isEMFResource( p_resource.getFileExtension() );
	}
	
	public static boolean isEMFResource( final String p_fileExtension ) {
		return Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().get( p_fileExtension ) != null;
	}

	public static EObject getRootObject( 	final EObject p_object,
											final EClass p_objectClass ) {
		EObject container = p_object;
		EObject root = null;
		
		while ( container != null ) {
			if ( p_objectClass == null || container.eClass().equals( p_objectClass ) ) {
				root = container;
			}
			
			container = container.eContainer();
		}
		
		return root;
	}
	
	public static void fillContentOfTypes( 	final Resource p_resource,
											final Collection<? extends EClassifier> p_supportedTypes,
											final Collection<EObject> p_elements ) {
		fillContentOfTypes( p_resource.getAllContents(), p_supportedTypes, p_elements );
	}

	public static void fillContentOfTypes( 	final EObject p_object,
											final boolean pb_resolve,
											final Collection<? extends EClassifier> p_supportedTypes,
											final Collection<EObject> p_elements,
											final boolean includeRoot ) {
		final Iterator<EObject> iterator = EcoreUtil.getAllContents( p_object, pb_resolve );
		fillContentOfTypes( iterator, p_supportedTypes, p_elements );
		
		if ( includeRoot ) {
			fillContentOfTypes( Collections.singletonList( p_object ).iterator(), p_supportedTypes, p_elements );
		}
	}
	
	public static void fillContentOfTypes( 	final Iterator<EObject> p_content,
											final Collection<? extends EClassifier> p_supportedTypes,
											final Collection<EObject> p_elements ) {
		while ( p_content.hasNext() ) {
			final EObject eObject = p_content.next();
			
			if ( p_supportedTypes == null ) {
				p_elements.add( eObject );
			}
			else {
				for ( final EClassifier classifier : p_supportedTypes ) {
					if ( classifier.isInstance( eObject ) ) {
						p_elements.add( eObject );
						
						break;
					}
				}
			}
		}
	}
	
	public static EReference getEReference( final EClass p_class, 
											final String p_refName ) {
		for ( EReference reference : p_class.getEAllReferences() ) {
			if ( reference.getName().equals( p_refName ) ) {
				return reference;
			}
		}
		
		return null;
	}
	
	public static Map<?,?> getDefaultOptions() {
		final Map<Object, Object> options = new HashMap<Object, Object>();
		options.put( XMLResource.OPTION_ENCODING, "ISO-8859-1" );
		
		return options;
	}
	
	public static IResource convertToIDEResource( final Resource p_emfResource ) {
		return p_emfResource == null ? null : convertToIDEResource( p_emfResource.getURI() );
	}
	
	public static IResource convertToIDEResource( final URI p_resUri ) {
		assert p_resUri != null : "EMF resource URI cannot be null.";

		final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		
		if ( p_resUri.isPlatform() ) {
			return workspaceRoot.getFile( new Path( p_resUri.toPlatformString( true ) ) );
		}

		if ( p_resUri.isFile() ) {
			return workspaceRoot.getFileForLocation( new Path( p_resUri.toFileString() ) );
		}
		
		throw new IllegalArgumentException( "Resource scheme not managed: " + p_resUri.scheme() );
	}

	/**
	 * Tries to obtain the EMF resource from the eclipse IDE resource.
	 * 
	 * @param p_resource The eclipse IDE resource.
	 * @return The corresponding EMF resource if the specified resource is in xmi format.
	 */
	public static Resource convertToEMFResource( final IResource p_file )
	throws CoreException {
		return convertToEMFResource( p_file, null );
	}

	public static Resource convertToEMFResource( 	final IResource p_file,
													ResourceSet p_resourceSet )
	throws CoreException {
		assert p_file != null : "IDE file cannot be null.";
		final IPath path = p_file.getFullPath();
		final URI uri = URI.createPlatformResourceURI( path.toString(), false );
		p_resourceSet =  p_resourceSet == null ? new ResourceSetImpl() : p_resourceSet;

		try {
			if ( p_resourceSet.getURIConverter().exists( uri, null ) ) {
				return p_resourceSet.getResource( uri, true );
			}
			
			return null;
		}
		catch ( final RuntimeException p_ex ) {
			if ( p_ex.getCause() instanceof SAXParseException || p_ex.getCause() instanceof PackageNotFoundException ) {
				return null;
			}
				
			throw p_ex;
		}
	}
	
	public static boolean equalsByURI( 	final EObject p_object1,
										final EObject p_object2 ) {
		if ( p_object1 == null ) {
			return p_object2 == null;
		}

		if ( p_object2 == null ) {
			return p_object1 == null;
		}

		return EcoreUtil.getURI( p_object1 ).equals( EcoreUtil.getURI( p_object2 ) );
	}
	
	/**
	 * @deprecated. Use <code>selectedObject(	final ISelection p_selection,
	 *											final Class<T> p_class )</code>
	 * @param <T>
	 * @param p_part
	 * @param p_selection
	 * @param p_class
	 * @return
	 */
	@Deprecated
	public static <T> T selectedObject(	final IWorkbenchPart p_part,
										final ISelection p_selection,
										final Class<T> p_class ) {
		return selectedObject( p_selection, p_class );
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T selectedObject(	final ISelection p_selection,
										final Class<T> p_class ) {
		T newObject = null;

		if ( p_selection instanceof IStructuredSelection ) {
			final IStructuredSelection structSel = (IStructuredSelection) p_selection;
			
			if ( structSel.size() == 1 ) {
				final Object element = AdapterFactoryEditingDomain.unwrap( ( (IStructuredSelection) p_selection ).getFirstElement() );

				if ( p_class.isInstance( element ) ) {
					newObject = (T) element;
				}
				else if ( element instanceof IAdaptable ) {
					newObject = (T) ( (IAdaptable) element ).getAdapter( p_class );
				}
			}
		}
		
		return newObject;
	}
	
    @SuppressWarnings("unchecked")
	public static <T> Collection<T> selectedObjects(	final ISelection p_selection,
														final Class<T> p_class ) {
		Collection<T> objects = new LinkedHashSet<T>();

		if ( p_selection instanceof IStructuredSelection ) {
			for ( final Object selElement : ( (IStructuredSelection) p_selection ).toList() ) {
				final Object element = AdapterFactoryEditingDomain.unwrap( selElement );

				if ( p_class.isInstance( element ) ) {
					objects.add( (T) element );
				}
				else if ( element instanceof IAdaptable ) {
					final T adaptedElement = (T) ( (IAdaptable) element ).getAdapter( p_class );
					
					if ( adaptedElement != null ) {
						objects.add( (T) adaptedElement );
					}
				}
			}
		}
		
		return objects;
	}

    public static Set<EObject> getAllRootReferredObjects( final EObject p_object ) {
    	final Set<EObject> objects = new HashSet<EObject>();
		final EObject rootElement = EcoreUtil.getRootContainer( p_object );
		
		if ( rootElement.eResource() != null ) {
			for ( final Resource resource : allReferencedExtents( rootElement.eResource() ) ) {
				objects.addAll( resource.getContents() );
			}
		}
		
		return objects;
    }
	
	private static Set<Resource> allReferencedExtents( final Resource resourceExtent ) {
		final Set<Resource> referencedExtents = new HashSet<Resource>();
		
		final Iterator<EObject> contents = resourceExtent.getAllContents();
		
		while ( contents.hasNext() ) {
			referencedExtents.addAll( allReferencedExtents( contents.next(), new HashSet<EObject>() ) );
		}

		return referencedExtents;
	}

	private static Set<Resource> allReferencedExtents( 	final EObject p_object,
														final Set<EObject> p_traversedObjects ) {
		final Set<Resource> referencedExtents = new HashSet<Resource>();

		if ( p_object != null && !p_traversedObjects.contains( p_object ) ) {
			p_traversedObjects.add( p_object );
			
			if ( p_object.eResource() != null ) {
				referencedExtents.add( p_object.eResource() );
			}

			for ( final EObject object : p_object.eCrossReferences() ) {
				referencedExtents.addAll( allReferencedExtents( object, p_traversedObjects ) );
			}
		}
		
		return referencedExtents;
	}
	
	/**
	 * Check that the object is an instance of any of the specified classes. Uses the EMF API.
	 * @param p_object
	 * @param p_classes
	 * @return
	 */
	public static boolean isInstance( 	final EObject p_object,
										final Collection<EClass> p_classes ) {
		for ( final EClass eClass : p_classes ) {
			if ( eClass.isInstance( p_object ) ) {
				return true;
			}
		}
		
		return false;
	}
	
	public static <T extends EObject> T findEObjectById( 	final Object p_id,
															final EAttribute p_attribute,
															final EList<T> p_extendingList ) {
		for ( final T object : p_extendingList ) {
			if ( p_id.equals( object.eGet( p_attribute ) ) ) {
				return object;
			}
		}
		
		return null;
	}
	
	public static boolean isReadOnly( final Resource p_resource ) {
		final ResourceSet resSet = p_resource.getResourceSet();
		final URIConverter converter = resSet.getURIConverter();
		final Map<String, ? extends Object> resAtt = converter.getAttributes( p_resource.getURI(), null );
		
		return Boolean.TRUE.equals( resAtt.get( URIConverter.ATTRIBUTE_READ_ONLY ) );
	}
	
	public static void setReadOnly( final Resource p_resource ) 
	throws IOException {
		final ResourceSet resSet = p_resource.getResourceSet();
		final URIConverter converter = resSet.getURIConverter();
		@SuppressWarnings("unchecked")
		final Map<String, Object> resAtt = (Map<String, Object>) converter.getAttributes( p_resource.getURI(), null );
		
		resAtt.put( URIConverter.ATTRIBUTE_READ_ONLY, Boolean.TRUE );
		converter.setAttributes( p_resource.getURI(), resAtt, null );
	}
	
	public static void copyResourceContent( final Resource p_sourceResource,
											final Resource p_targetResource,
											final Map<?, ?> p_options ) {
		if ( p_sourceResource.getContents().isEmpty() ) {
			p_targetResource.getContents().clear();
		}
		else {
			final ByteArrayOutputStream outStr = new ByteArrayOutputStream();
			
			try {
				p_sourceResource.save( outStr, p_options );
				final ByteArrayInputStream inputStr = new ByteArrayInputStream( outStr.toByteArray() );
				p_targetResource.load( inputStr, null );
			}
			catch( final IOException p_ex ) {
				throw new RuntimeException( p_ex );
			}
		}
	}
}
