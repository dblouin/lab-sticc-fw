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

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dominique Blouin
 *
 * Utility class for introspecting the properties of a class.
 */
public class ReflectUtil {
	
	@SuppressWarnings("unused")
	private static final Logger trace = LoggerFactory.getLogger( ReflectUtil.class );
	
	private static final String GET = "get";

	private static final Map<Class<?>, Map<String, PropertyDescriptor>> ALL_PROPERTY_DESCRIPTORS = new HashMap<Class<?>, Map<String, PropertyDescriptor>>();

	/**
	 * Returns a map of property descriptors for the specified class. A descriptor will be generated for each 
	 * getter method even if there is no actual property in the class. The results are cached.
	 * 
	 * @param p_class The class for which the property descriptors are required.
	 * @param p_exceptions The property names that should not be included.
	 * @return The property descriptors.
	 * @throws IntrospectionException
	 */
	public static Map<String, PropertyDescriptor> getPropertyDescriptors( 	final Class<?> p_class,
																			final String[] p_exceptions )
	throws IntrospectionException {
		Map<String, PropertyDescriptor> descriptors = ALL_PROPERTY_DESCRIPTORS.get( p_class );

		if ( descriptors == null ) {
			descriptors = new HashMap<String, PropertyDescriptor>();
			final List<String> propExc = Arrays.asList( p_exceptions );
			
			for ( Method method : p_class.getMethods() ) {
				final String methodName = method.getName();

				if ( methodName.startsWith( GET ) && method.getParameterTypes().length == 0 ) {
					final String propertyName = decapitalizePropertyName( methodName.replaceFirst( GET, "" ) );
				
					if ( !propExc.contains( propertyName ) && descriptors.get( propertyName ) == null ) {
						descriptors.put( propertyName, createPropertyDescriptor( p_class,  method ) );
					}
				}
			}
			
			ALL_PROPERTY_DESCRIPTORS.put( p_class, descriptors );
		}
	
		return descriptors;
	}
	
	/**
	 * Generates a property descriptor for a class and for a specified getter method. The getter will
	 * be the one declared by the class if it exists. Otherwise, it will be the one having the highest 
	 * return type in the class hierarchy.
	 * @param p_class The class from which the specified getter was obtained.
	 * @param p_getter The actual getter.
	 * @return A property descriptor of the finest getter return type.
	 * @throws IntrospectionException
	 */
	private static PropertyDescriptor createPropertyDescriptor( final Class<?> p_class,
																final Method p_getter ) 
	throws IntrospectionException {
		Method finestGetter = findFinestGetter( p_getter, p_class );
		
		Method setter = null;
		String capPropertyName = p_getter.getName().replaceFirst( GET, "" );
		
		try {
			setter = p_class.getMethod( "set" + capPropertyName, 
										new Class<?>[] { finestGetter.getReturnType() } );
		}
		catch ( NoSuchMethodException p_ex ) {
		}
		
		return new PropertyDescriptor( 	decapitalizePropertyName( capPropertyName ), 
										finestGetter, 
										setter );
	}
	
	private static Method findFinestGetter( final Method p_getter,
											final Class<?> p_class ) {
		final String getterName = p_getter.getName();
		
		try {
			return p_class.getDeclaredMethod( 	getterName, 
												new Class<?>[ 0 ] );
		}
		catch ( NoSuchMethodException p_ex ) {
			//trace.debug( "No declared method for read method " + getterName + " in " + p_class );
			Method finestGetter = p_getter;
			
			// Ensures that the getter having the most refined return type is used
			for( Method getter : p_class.getMethods() ) {
				if ( 	getterName.equals( getter.getName() ) &&  getter.getParameterTypes().length == 0 &&
						ClassUtil.isGreaterThan( finestGetter.getReturnType(), getter.getReturnType() ) ) {
					finestGetter = getter;
				}
			}
			
			return finestGetter;
		}
	}

	public static PropertyDescriptor getPropertyDescriptor( final Class<?> p_class,
															final String p_propertyName,
															final String[] p_exceptions )
	throws IntrospectionException {
		return getPropertyDescriptors( p_class, p_exceptions ).get( p_propertyName );
	}
	
	public static String capitalizePropertyName( final String p_propertyName ) {
		if ( p_propertyName == null || p_propertyName.length() == 0 ) { 
		    return p_propertyName; 
	    }
		
		return p_propertyName.substring(0, 1).toUpperCase( Locale.ENGLISH ) + p_propertyName.substring(1);
	}

	public static String decapitalizePropertyName( String p_propertyName ) {
		return Introspector.decapitalize( p_propertyName );
	}
	
	public static List<Field> getAllInterfaceFields( final Class<?> p_class ) {
		List<Field> fields = new ArrayList<Field>();
		addAllInterfaceFields( p_class, fields );
		
		return fields;
	}
	
	private static void addAllInterfaceFields( 	final Class<?> p_class,
												final List<Field> p_fields ) {
		if ( p_class != null ) { 
			if ( p_class.isInterface() ) {
				p_fields.addAll( Arrays.asList( p_class.getDeclaredFields() ) );
			}
			
			for ( Class<?> interfaceClass : p_class.getInterfaces() ) {
				addAllInterfaceFields( interfaceClass, p_fields );
			}
		}
	}
}
