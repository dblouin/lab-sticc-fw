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

import java.util.HashMap;

public class ClassKeyMap<K, V> extends HashMap<Class<? extends K>, V> {

	private static final long serialVersionUID = 614630657288155569L;
	
	@SuppressWarnings("unchecked")
	@Override
    public V get(Object p_key) {
		final Class<? extends K> classKey = (Class<? extends K>) p_key;
		V value = super.get( classKey );
		
		if ( value != null ) {
			return value;
		}
		
		Class<? extends K> minClass = null;

		for ( Class<? extends K> key : keySet() ) {
			if ( key.isAssignableFrom( classKey ) ) {
				minClass = ClassUtil.min( minClass, key );
				
				if ( containsKey( minClass ) ) {
					value = super.get( minClass );
				}
			}
		}
		
		return value;
	}
}
