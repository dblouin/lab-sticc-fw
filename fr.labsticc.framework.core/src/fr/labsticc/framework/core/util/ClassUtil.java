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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ClassUtil {

	private static final Comparator<Class<?>> comparator = new ClassHierarchyComparator();

	/**
	 * Returns the max of these classes with respect to class hierarchy. A class is said to be 
	 * higher in hierarchy if is the farthest from the leaf in the class tree.
	 * @param <T>
	 * @param p_class1
	 * @param p_class2
	 * @return
	 */
	public static <T> Class<? extends T> max( 	final Class<? extends T> p_class1, 
												final Class<? extends T> p_class2 ) {
		final int res = comparator.compare( p_class1, p_class2 );
		
		// Modif so that max and min return the same result when classes are equals
		return res > 0 ? p_class1 : p_class2;
//		return res >= 0 ? p_class1 : p_class2;
	}
	
	public static <T> Class<? extends T> min( 	final Class<? extends T> p_class1, 
												final Class<? extends T> p_class2 ) {
		final int res = comparator.compare( p_class1, p_class2 );
		
		return res < 0 ? p_class1 : p_class2;
	}
	
	/**
	 * Returns a boolean indicating if the first specified class is greater than the second 
	 * class with respect to class hierarchy. A class is said to be higher in hierarchy if 
	 * it is the farthest from the leaf in the class tree.
	 * @param <T>
	 * @param p_class1
	 * @param p_class2
	 * @return
	 */
	public static <T> boolean isGreaterThan( 	final Class<? extends T> p_class1, 
												final Class<? extends T> p_class2 ) {
		return max( p_class1, p_class2 ) == p_class2 ? false : true;
	}
	
	public static <T> Class<? extends T> max( final Collection<Class<? extends T>> p_classes ) {
		Class<? extends T> maxClass = null;
		
		for ( final Class<? extends T> actualClass : p_classes ) {
			maxClass = max( maxClass, actualClass );
		}
		
		return maxClass;
	}
	
	public static <T> Class<? extends T> min( final Collection<Class<? extends T>> p_classes ) {
		Class<? extends T> minClass = null;
		
		for ( final Class<? extends T> actualClass : p_classes ) {
			minClass = min( minClass, actualClass );
		}
		
		return minClass;
	}

	public static Class<?> findMinCommonInterface(	final Class<?> p_class1, 
													final Class<?> p_class2 ) {
		final List<Class<?>> superInterfaces2 = getAllInterfaces( p_class2 );
		final List<Class<?>> commonInterfaces = new ArrayList<Class<?>>();
		
		for ( final Class<?> interf1 : getAllInterfaces( p_class1 ) ) {
			if ( superInterfaces2.contains( interf1 ) ) {
				commonInterfaces.add( interf1 );
			}
		}
		
		return min( commonInterfaces );
	}
	
	public static Class<?> findMinInterface( final Class<?> p_class ) {
		return min( getAllInterfaces( p_class ) );
	}
	
	public static List<Class<?>> getAllInterfaces( final Class<?> p_class ) {
		final List<Class<?>> interfaces = new ArrayList<Class<?>>();
		
		if ( p_class != null ) {
			if ( p_class.isInterface() ) {
				interfaces.add( p_class );
			}
			
			interfaces.addAll(  Arrays.asList( p_class.getInterfaces() ) );
			
			for ( final Class<?> superInt : p_class.getInterfaces() ) {
				interfaces.addAll( getAllInterfaces( superInt ) );
			}
			
			if ( p_class.getSuperclass() != null ) {
				interfaces.addAll( getAllInterfaces( p_class.getSuperclass() ) );
			}
		}
		
		return interfaces;
	}
	
	public static boolean isInstance( 	final Object p_object,
										final Class<?>... p_classes ) {
		if ( p_classes == null ) {
			return false;
		}
		
		for ( final Class<?> clazz : p_classes ) {
			if ( clazz.isInstance( p_object ) ) {
				return true;
			}
		}
		
		return false;
	}
}
