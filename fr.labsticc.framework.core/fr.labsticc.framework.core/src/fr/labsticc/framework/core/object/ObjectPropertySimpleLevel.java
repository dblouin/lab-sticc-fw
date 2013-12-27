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


public class ObjectPropertySimpleLevel<T extends ISmartObject<?, ?>> extends ObjectPropertyAbstract<T> implements IObjectPropertySimpleLevel<T> {

	private final String propertyName;
	
	public ObjectPropertySimpleLevel( 	final Class<T> p_parentObjectClass,
										final String p_propertyName,
										final boolean pb_derived ) {
		this( p_parentObjectClass, null, p_propertyName, pb_derived );
	}

	public ObjectPropertySimpleLevel( 	final Class<T> p_parentObjectClass,
										final Class<?> p_propertyType,
										final String p_propertyName,
										final boolean pb_derived ) {
		super( p_parentObjectClass, p_propertyType, pb_derived );
		
		propertyName = p_propertyName;
	}
	
	@Override
	public String getKey() {
		return parentObjectClassName + "." + getRootPropertyName();
	}

	@Override
	public String getRootPropertyName() {
		return propertyName;
	}

	@Override
	public String toString() {
		return super.toString() + " property " + getRootPropertyName();
	}
}
