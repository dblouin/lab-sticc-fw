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

import java.util.List;
import java.util.ListIterator;

public class ObjectPropertyMultiLevel<T extends ISmartObject<?, ?>> extends ObjectPropertyAbstract<T> implements IObjectPropertyMultiLevel<T> {

	private final List<IObjectProperty<ISmartObject<?, ?>>> properties;
	
	private final String key;

	public ObjectPropertyMultiLevel( 	final Class<T> p_parentDataClass,
										final Class<?> p_propertyType,
										final List<IObjectProperty<ISmartObject<?, ?>>>  p_properties,
										final boolean pb_derived ) {
		super( p_parentDataClass, p_propertyType, pb_derived );
		
		properties = p_properties;
		final StringBuffer keyBuf = new StringBuffer( parentObjectClassName );
		
		for ( IObjectProperty<?> property : properties ) {
			keyBuf.append( property.getKey() );
		}
		
		key = keyBuf.toString();
	}
	
	@Override
	public List<IObjectProperty<ISmartObject<?, ?>>> getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IObjectProperty<T> getRootProperty() {
		return (IObjectProperty<T>) properties.get( 0  );
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getPropertyValue( final T p_data ) {
		Object value = p_data;
		int index = 0;
		
		for ( IObjectProperty<ISmartObject<?,?>> property : getProperties() ) {
			if ( value instanceof ISmartObject<?,?> ) {
				value = property.getPropertyValue( (ISmartObject<?,?>) value );
			}
			else {
				if ( index != getProperties().size() - 1 ) {
					throw new IllegalArgumentException( p_data.toString() );
				}
			}
			
			index++;
		}
		
		return value;
	}

	@Override
	public void setPropertyValue( 	final T p_data, 
									final Object p_value ) {
		Object value = p_data;
		int index = 0;
		
		for ( IObjectProperty<ISmartObject<?,?>> property : getProperties() ) {
			if ( index == getProperties().size() - 1 ) {
				if ( value instanceof ISmartObject<?,?> ) {
					property.setPropertyValue( (ISmartObject<?,?>) value, p_value );
				}
				else {
					throw new IllegalArgumentException( p_data.toString() );
				}
			}
			else {
				value = property.getPropertyValue( (ISmartObject<?,?>) value );
			}
			
			index++;
		}
	}

	@Override
	public String getRootPropertyName() {
		return getRootProperty().getRootPropertyName();
	}

	@Override
	public Class<?> getPropertyType() {
		Class<?> type = super.getPropertyType();
		
		if ( type == null ) {
			type = getPropertyType( getParentObjectClass(), getProperties().listIterator() );
		}
		
		return type;
	}

	public static Class<?> getPropertyType( final Class<?> p_data,
											final ListIterator<IObjectProperty<ISmartObject<?, ?>>> p_properties ) {
		if ( p_properties.hasNext() ) {
			return getPropertyType( 	p_properties.next().getPropertyType(), 
										p_properties );
		}
		
		return p_properties.previous().getPropertyType();
	}
}
