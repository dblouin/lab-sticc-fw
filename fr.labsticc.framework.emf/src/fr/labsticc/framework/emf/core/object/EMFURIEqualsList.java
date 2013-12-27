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
package fr.labsticc.framework.emf.core.object;

import java.util.Collection;
import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;

import fr.labsticc.framework.core.object.CustomComparatorList;
import fr.labsticc.framework.emf.core.util.EMFUtil;

public class EMFURIEqualsList<E extends EObject> extends CustomComparatorList<E> {

	private static final long serialVersionUID = -7965453612275518848L;
	
	private static Comparator<EObject> comparator = new Comparator<EObject>() {

		@Override
		public int compare(	final EObject p_object1,
							final EObject p_object2 ) {
			if ( EMFUtil.equalsByURI( p_object1, p_object2 ) ) {
				return 0;
			}
			
			return -1;
		}
	};
	
	/**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the list
     * @exception IllegalArgumentException if the specified initial capacity
     *            is negative
     */
    @SuppressWarnings("unchecked")
	public EMFURIEqualsList( final int pi_initialCapacity ) {
    	super( pi_initialCapacity, (Comparator<E>) comparator );
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    @SuppressWarnings("unchecked")
	public EMFURIEqualsList() {
    	super( (Comparator<E>) comparator );
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    @SuppressWarnings("unchecked")
	public EMFURIEqualsList( final Collection<? extends E> p_elements ) {
    	super( p_elements, (Comparator<E>) comparator );
    }
}
