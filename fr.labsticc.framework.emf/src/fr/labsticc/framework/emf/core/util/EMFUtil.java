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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
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

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.ClassHierarchyComparator;

public class EMFUtil {

	private static final ClassHierarchyComparator classHierarchyComp = new ClassHierarchyComparator();

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
		if ( isEMFResource( p_resource.getFileExtension() ) ) {
			return true;
		}
		
		try {
			return convertToEMFResource( p_resource ) != null;
		}
		catch ( final WrappedException p_ex ) {
			return false;
		}
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
	
	public static <T extends EObject> void fillContentOfTypes( 	final Resource p_resource,
																final Collection<? extends EClassifier> p_supportedTypes,
																final Collection<T> p_elements ) {
		fillContentOfTypes( p_resource.getAllContents(), p_supportedTypes, p_elements );
	}

	public static <T extends EObject> void fillContentOfTypes( 	final EObject p_object,
																final boolean pb_resolve,
																final Collection<? extends EClassifier> p_supportedTypes,
																final Collection<T> p_elements,
																final boolean includeRoot ) {
		final Iterator<EObject> iterator = EcoreUtil.getAllContents( p_object, pb_resolve );
		fillContentOfTypes( iterator, p_supportedTypes, p_elements );
		
		if ( includeRoot ) {
			fillContentOfTypes( Collections.singletonList( p_object ).iterator(), p_supportedTypes, p_elements );
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends EObject> void fillContentOfTypes( 	final Iterator<EObject> p_content,
																final Collection<? extends EClassifier> p_supportedTypes,
																final Collection<T> p_elements ) {
		while ( p_content.hasNext() ) {
			final EObject eObject = p_content.next();
			
			if ( p_supportedTypes == null ) {
				p_elements.add( (T) eObject );
			}
			else {
				for ( final EClassifier classifier : p_supportedTypes ) {
					if ( classifier.isInstance( eObject ) ) {
						p_elements.add( (T) eObject );
						
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
	public static Resource convertToEMFResource( final IResource p_file ) {
		return convertToEMFResource( p_file, null );
	}

	public static Resource convertToEMFResource( 	final IResource p_file,
													ResourceSet p_resourceSet ) {
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
	
	public static Set<Resource> allReferencedExtents( final Resource resourceExtent ) {
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
	
	public static boolean isReadOnly( final URI p_uri ) {
		return isReadOnly( p_uri, URIConverter.INSTANCE/*new ExtensibleURIConverterImpl()*/ );
	}
	
	public static boolean isReadOnly( 	final URI p_uri,
										final URIConverter p_uriConverter ) {
		final Map<String, ? extends Object> resAtt = p_uriConverter.getAttributes( p_uri, null );
		
		return Boolean.TRUE.equals( resAtt.get( URIConverter.ATTRIBUTE_READ_ONLY ) );
	}
	
	public static boolean isReadOnly( final Resource p_resource ) {
		final ResourceSet resSet = p_resource.getResourceSet();
		final URIConverter converter = resSet == null ? URIConverter.INSTANCE/*new ExtensibleURIConverterImpl()*/ : resSet.getURIConverter();
		
		return isReadOnly( p_resource.getURI(), converter );
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
				
				// If the resource was already loaded, it will not be loaded with the new content so
				// unload it first.
				p_targetResource.unload();
				p_targetResource.load( inputStr, p_targetResource.getResourceSet().getLoadOptions() );
			}
			catch( final IOException p_ex ) {
				throw new RuntimeException( p_ex );
			}
		}
	}
	
	public static final boolean setIntoParent( 	final EObject p_parent,
												final Object p_child,
												final EStructuralFeature p_abstractFeature ) {
		final EStructuralFeature featureToSet = getConcreteFeature( p_parent, p_abstractFeature, p_child );
		
		if ( featureToSet == null ) {
			return false;
		}

		
		if ( featureToSet.isMany() ) {
			@SuppressWarnings("unchecked")
			final EList<Object> targetList = (EList<Object>) p_parent.eGet( featureToSet );
			targetList.add( p_child ); 
		}
		else {
			p_parent.eSet( featureToSet, p_child );
		}
		
		return true;
	}
	
	public static EStructuralFeature getConcreteFeature( 	final Object p_sourceInstanceObject,
															final EStructuralFeature p_feature,
															Object p_targetObject ) {
		EStructuralFeature closestFeature = p_feature;
		Class<?> closestFeatureClass = closestFeature.getEType().getInstanceClass();

		if ( closestFeature.isDerived() ) {
			if ( p_targetObject == null ) {
				if ( p_feature.isMany() ) {
					throw new IllegalStateException( "Features with multiplicity greater than 1 not handled." );
				}
				
				final Object actualObject = ( (EObject) p_sourceInstanceObject ).eGet( p_feature );
				
				if ( actualObject == p_targetObject ) {
					// Return a null feature meaning that nothing needs to be set.
					return null;
				}
				
				p_targetObject = actualObject;
			}

			final EClass sourceObjectClass = ( (EObject) p_sourceInstanceObject ).eClass();
			
			for ( final EStructuralFeature feature : sourceObjectClass.getEAllStructuralFeatures() ) {
				if ( feature.isChangeable() && !feature.isDerived() && feature.getEType().isInstance( p_targetObject ) ) {
					final Class<?> currentFeatureClass = feature.getEType().getInstanceClass();
					final int comparison = classHierarchyComp.compare( closestFeatureClass, currentFeatureClass );
					
					// If the classes are not related (equals), use the current class because it ensure the starting feature is replaced with
					// one that is changeable.
					if ( comparison >= 0 ) {
						closestFeature = feature;
						closestFeatureClass = closestFeature.getEType().getInstanceClass();
					}
				}
			}
		}

		return closestFeature;
	}
	
	public static boolean dependsOn( 	final Resource p_resource1,
										final Resource p_resource2 ) {
		return ResourceDepComparator.dependsOn( p_resource1, p_resource2 );
	}

	/**
	 * Return a collection of objects not contained in the specified resource and referred by the
	 * specified object, either directly or through references to other objects contained in the
	 * specified resource.
	 * @param p_containingRes
	 * @param p_object
	 * @return
	 * @throws ResourceAccessException
	 */
	public static Collection<EObject> externalReferences(	final Resource p_containingRes,
															final EObject p_object )
	throws ResourceAccessException {
		return externalReferences( p_containingRes, p_object, new HashSet<EObject>() );
	}

	/**
	 * Return a collection of objects not contained in the specified resource and referred by the
	 * specified object, either directly or through references to other objects contained in the
	 * specified resource.
	 * @param p_parentRefRes
	 * @param p_object
	 * @param p_travObjects
	 * @return
	 * @throws ResourceAccessException
	 */
	private static Collection<EObject> externalReferences(	final Resource p_parentResource,
															final EObject p_object,
															final Set<EObject> p_travObjects )
	throws ResourceAccessException {
		final Collection<EObject> refs = new HashSet<EObject>();
		
		if ( !p_travObjects.contains( p_object ) ) {
			p_travObjects.add( p_object );
			final Resource crossRes = p_object.eResource();
			
			if ( crossRes == null ) {
				throw new ResourceAccessException( 	"Unresolved resource for object " + EcoreUtil.getURI( p_object ) );
			}
	
			// The object is outside so we need it
			if ( crossRes != p_parentResource ) {
				refs.add( p_object );
			}
			else {
				// The object is inside so we need to see if any of its cross references has a reference to the outside
				final EObject container = p_object.eContainer();
				
				for ( final EObject object : p_object.eCrossReferences() ) {
					if ( object != container ) {
						refs.addAll( externalReferences( p_parentResource, object, p_travObjects ) );
					}
				}
			}
		}
		
		return refs;
	}

	public static void unloadResource( 	final Resource p_resource,
										final boolean pb_unloadDepRes ) {
		if ( pb_unloadDepRes ) {
			// Remove all depending resources
			for ( final Resource cachedRes : new ArrayList<Resource>( p_resource.getResourceSet().getResources() ) ) {
				if ( cachedRes != p_resource && dependsOn( cachedRes, p_resource ) ) {
					unloadResource( cachedRes );
				}
			}
		}
		
		unloadResource( p_resource );
	}

	public static void unloadResource( final Resource p_resource ) {
		if ( p_resource.isLoaded() ) {
			p_resource.unload();
		}
		
		p_resource.getResourceSet().getResources().remove( p_resource );
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFirstObjectOfType(	final Resource p_resource,
												final EClassifier p_type ) {
		final Iterator<EObject> contentIt = p_resource.getAllContents();
		
		while ( contentIt.hasNext() ) {
			final EObject element = contentIt.next();

			if ( p_type.isInstance( element ) ) {
		        return (T) element;
		     }
		}
		
		return null;
	}
}