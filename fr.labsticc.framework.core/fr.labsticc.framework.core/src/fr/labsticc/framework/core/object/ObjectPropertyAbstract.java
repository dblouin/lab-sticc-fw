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

import fr.labsticc.framework.core.util.ReflectUtil;

public abstract class ObjectPropertyAbstract<T extends ISmartObject<?, ?>> implements IObjectProperty<T> {

	private final Class<T> parentObjectClass;
	
	protected final String parentObjectClassName;
	
	private final Class<?> propertyType;
	
	private final boolean changeable;

	protected ObjectPropertyAbstract( 	final Class<T> p_parentObjectClass,
										final Class<?> p_propertyType,
										final boolean pb_changeable ) {
		parentObjectClass = p_parentObjectClass;
		parentObjectClassName = ReflectUtil.decapitalizePropertyName( parentObjectClass.getSimpleName() ); 
		propertyType = p_propertyType;
		changeable = pb_changeable;
	}

	@Override
	public Class<T> getParentObjectClass() {
		return parentObjectClass;
	}

	@Override
	public Class<?> getPropertyType() {
		return propertyType;
	}
	
	@Override
	public boolean isChangeable() {
		return changeable;
	}

	@Override
	public void setPropertyValue( 	final T p_data, 
									final Object p_value ) {
		if ( p_data != null && isChangeable() ) {
			p_data.setPropertyValue( getRootPropertyName(), p_value );
		}
	}

	@Override
	public Object getPropertyValue( final T p_data ) {
		if ( p_data == null ) {
			return null;
		}
		
		return p_data.getPropertyValue( getRootPropertyName() );
	}

	@Override
	public String toString() {
		return "Class " + parentObjectClassName;
	}
}
