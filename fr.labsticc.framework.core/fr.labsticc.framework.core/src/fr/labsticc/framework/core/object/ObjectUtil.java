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
package fr.labsticc.framework.core.object;


public class ObjectUtil {

	@SuppressWarnings("unchecked")
	public static Number extractNumberValue( final Object p_value ) {
		if ( p_value instanceof Number ) {
			return (Number) p_value;
		}
		
		if ( p_value instanceof IValueUnit ) {
			return ( (IValueUnit<Number, ?, ?, ?>) p_value ).getValue();
		}

		if ( p_value instanceof IObjectRange ) {
			return extractNumberValue( ( (IObjectRange<?, ?, ?>) p_value ).getValue() );
		}
		
		return null;
	}
	
	public static String getCollectionElementLabel( final ISmartObject<?, ?> p_parentObject,
													final IObjectProperty<?> p_parentCollectionProperty,
													final IObjectProperty<?> p_elementProperty,
													final String p_defaultLabel ) {
		final ISmartObjectLocalized<?,?> localizedObject;
		final String propertyName;
	
		if ( p_parentObject instanceof ISmartObjectLocalized<?,?> ) {
			localizedObject = (ISmartObjectLocalized<?,?>) p_parentObject;
			propertyName = p_parentCollectionProperty.getRootPropertyName() + "." + p_elementProperty.getRootPropertyName();
		}
		else {
			localizedObject = null;
			propertyName = null;
		}

		if ( localizedObject == null ) {
			return p_defaultLabel;
		}	

		final String label = localizedObject.getPropertyLabel( propertyName );
		
		return label == null ? p_defaultLabel : label;
	}
}
