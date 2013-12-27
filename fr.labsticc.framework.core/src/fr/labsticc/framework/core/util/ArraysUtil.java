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

import java.util.Arrays;

public class ArraysUtil {

	public static String[] toStringArray( Object[] p_array ) {
		final String[] strArray = new String[ p_array.length ];
		int index = 0;
		
		for ( final Object element : p_array ) {
			strArray[ index++ ] = element == null ? null : element.toString();
		}
		
		return strArray;
	}
	
	public static int indexOf( 	final String[] p_elements,
								final String p_key ) {
		if ( p_elements == null ) {
			return -1;
		}
		
		return Arrays.asList( p_elements ).indexOf( p_key );
	}
}
