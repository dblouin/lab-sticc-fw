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


public class EnumUtil {
	public static <T extends Enum<T>> T max(	final T p_enum1,
												final T p_enum2,
												final T[] enumValues ) {
		if ( p_enum1 == null ) {
			return p_enum2;
		}
		
		if ( p_enum2 == null ) {
			return p_enum1;
		}

		return enumValues[ Math.max( p_enum1.ordinal(), p_enum2.ordinal() ) ]; 
	}
}
