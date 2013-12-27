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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * This map is used by the Ecore OCL evaluation environment so that calls to allInstances method in OCL will return all 
 * objects of the type that are contained for the scope provided for the language of the context through the model interface
 * API.
 *   
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class MultiResourceLazyExtentMap implements Map<EClass, Set<EObject>> {
	
	private final Map<EClass, Set<EObject>> delegate = new HashMap<EClass, Set<EObject>>();
	
	private final Collection<EObject> scope;

	/**
	 * Initializes me with the context element of an OCL
	 * expression evaluation.  I discover the scope of the model from this
     * element.
	 * 
	 * @param context my context element
	 */
	public MultiResourceLazyExtentMap( 	final EObject p_context,
										final Collection<EObject> p_scope ) {
		super();
		
		scope = p_scope;
//		final EObject rootElement = EcoreUtil.getRootContainer( p_context );

//		if ( rootElement.eResource() != null ) {
//			// TODO: This is the general implementation
////			roots = EMFUtil.getAllRootReferredObjects( rootElement );
//			
//			roots = getAllRootElements( rootElement );
//		} 
//		else {
//			// can only search this object tree
//			roots = Collections.singleton( p_context );
//		}
	}
	
//	
//	private Set<EObject> getAllRootElements( final EObject rootElement ) {
//		final Set<EObject> roots = new HashSet<EObject>();
//		final ResourceSet resSet = rootElement.eResource().getResourceSet();
//
//		if ( resSet != null ) {
//			for ( final Resource resource : resSet.getResources() ) {
//				roots.addAll( resource.getContents() );
//			}
//		}
//		
//		return roots;
//	}
	
	/**
	 * Lazily computes the extent of the specified class <code>key</code>.
	 * 
	 * @param key a class in the model
	 */
	@Override
	public Set<EObject> get( final Object p_key ) {
		// TODO: Optimize by parsing ahead of time to find all EClasses that we will query
		Set<EObject> result = delegate.get( p_key );
		
		if ( result == null ) {
			final EClass cls = (EClass) p_key;
			
			result = new HashSet<EObject>();
			delegate.put(cls, result);
			
			for ( final Iterator<Notifier> iter = EcoreUtil.getAllContents( scope ); iter.hasNext(); ) {
				final EObject nextObject = (EObject) iter.next();
				
				if ( isInstance( cls, nextObject ) ) {
					result.add( nextObject );
				}
			}
		}
		
		return result;
	}

	protected boolean isInstance( 	final EClass p_class, 
									final EObject p_element ) {
		return p_class.isInstance( p_element );
	}
	
	//
	// Strictly delegating methods
	//
	@Override
	public void clear() {
		delegate.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return delegate.containsValue(value);
	}

	@Override
	public Set<Map.Entry<EClass, Set<EObject>>> entrySet() {
		return delegate.entrySet();
	}
	
	@Override
    public boolean equals( Object obj ) {
		return delegate.equals(obj);
	}
	@Override
    public int hashCode() {
		return delegate.hashCode();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public Set<EClass> keySet() {
		return delegate.keySet();
	}

	@Override
	public Set<EObject> put(EClass key, Set<EObject> value) {
		return delegate.put(key, value);
	}

	@Override
	public void putAll(Map<? extends EClass, ? extends Set<EObject>> t) {
		delegate.putAll(t);
	}

	@Override
	public Set<EObject> remove(Object key) {
		return delegate.remove(key);
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
    public String toString() {
		return delegate.toString();
	}

	@Override
	public Collection<Set<EObject>> values() {
		return delegate.values();
	}
}
