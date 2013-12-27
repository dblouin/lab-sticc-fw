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
package fr.labsticc.framework.core.util;

import java.io.Serializable;

import java.util.Comparator;

public class DefaultComparator implements Comparator<Object>, Serializable {

	private static final long serialVersionUID = -4028210384476993301L;

	private static final DefaultComparator instance = new DefaultComparator();

	private DefaultComparator() {
	}

	public static DefaultComparator getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare( final Object p_object1, 
						final Object p_object2 ) {
		if (p_object1 == null && p_object2 == null) {
			return 0;
		}

		if (p_object1 == null) {
			return -1;
		}

		if (p_object2 == null) {
			return 1;
		}

		if (p_object1 instanceof Comparable && p_object2 instanceof Comparable) {
			// Mist be parameterized with "Object" otherwise (if ? is used instead) this compare method is called!!
			return compare((Comparable<Object>) p_object1, (Comparable<Object>) p_object2);
		}

		return p_object1.equals(p_object2) ? 0 : -1;
	}

	public static int compare( 	final Comparable<Object> p_comp1, 
								final Comparable<Object> p_comp2 ) {

		if (p_comp1 == null && p_comp2 == null) {
			return 0;
		}

		if (p_comp1 == null) {
			return -1;
		}

		if (p_comp2 == null) {
			return 1;
		}

		int li_result1 = p_comp1.compareTo( p_comp2 );
		int li_result2 = p_comp2.compareTo( p_comp1 );

		// enforce comparable contract
		if (li_result1 == 0 && li_result2 == 0) {
			return 0;
		}

		if (li_result1 < 0 && li_result2 > 0) {
			return li_result1;
		}

		if (li_result1 > 0 && li_result2 < 0) {
			return li_result1;
		}

		// results inconsistent
		throw new IllegalStateException( "Inconsistent comparison result for comparables!" );
	}
}
