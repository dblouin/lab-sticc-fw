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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.util.ReflectUtil;

public abstract class IntrospectionSmartDataObject<ID, M extends IMessage<?, ?>> extends AbstractSmartObject<ID, M> {
	
	private static final String[] exceptions = new String[] { "propertyValue", "propertyValues", "propertyType", "propertyNames", "class", "messages", "currentInDent", "stringPropertyValue", "integerPropertyValue", "intPropertyValue", "doublePropertyValue", "doubPropertyValue", "longPropertyValue", "datePropertyValue" };	
	
	private static final long serialVersionUID = -9003396733875101196L;
	
	@Override
	public Class<?> getPropertyType( String p_propertyName ) {
		if ( p_propertyName == null ) {
			throw new IllegalArgumentException( "Property name cannot be null." );
		}

		try {
			return getReadMethod( p_propertyName ).getReturnType();
		} 
		catch ( RuntimeException p_ex ) {
			throw p_ex;
		}
		catch ( Throwable p_th ) {
			throw new RuntimeException( "Unable to get property type for property name " + p_propertyName + " in " + getClass(), p_th );
		}
	}

	@Override
	public Object getPropertyValue( String p_propertyName ) {
		if ( p_propertyName == null ) {
			throw new IllegalArgumentException( "Property name cannot be null." );
		}
		
		try {
			return getReadMethod( p_propertyName ).invoke( this, (Object[]) null );
		} 
		catch ( RuntimeException p_ex ) {
			throw p_ex;
		}
		catch ( Throwable p_th ) {
			throw new RuntimeException( "Unable to get property value for property name " + p_propertyName + " in " + getClass(), p_th );
		}
	}

	@Override
	public void setPropertyValue( 	String p_propertyName, 
									Object p_propertyValue) {
		if ( p_propertyName == null ) {
			throw new IllegalArgumentException( "Property name cannot be null." );
		}

		try {
			Method writeMethod = getWriteMethod( p_propertyName );
			
			if ( writeMethod != null ) {
				writeMethod.invoke( this, new Object[] { p_propertyValue } );
			}
		} 
		catch ( RuntimeException p_ex ) {
			throw p_ex;
		}
		catch ( Throwable p_th ) {
			throw new RuntimeException( "Unable to set property value " + String.valueOf( p_propertyValue ) + " for property name " + p_propertyName + " in " + getClass(), p_th );
		}
	}

	@Override
	@XmlTransient
	public Collection<String> getPropertyNames() {
		try {
			Map<String, PropertyDescriptor> descr = ReflectUtil.getPropertyDescriptors( getClass(), exceptions );
			
			return descr.keySet();
		} 
		catch ( IntrospectionException p_ex ) {
			throw new RuntimeException( p_ex );
		}
	}
	
	@Override
	public boolean hasProperty( final String p_propertyName ) {
		return getPropertyNames().contains( p_propertyName );
	}

	protected Method getReadMethod( String p_propertyName ) 
	throws IntrospectionException, NoSuchMethodException {
		if ( p_propertyName == null ) {
			throw new IllegalArgumentException( "Property name cannot be null." );
		}

		PropertyDescriptor descr = getPropertyDescriptor( p_propertyName );
		Method readMethod = descr.getReadMethod();
		
		if ( readMethod == null ) {
			throw new NoSuchMethodException( getClass() + " does not have a read method for property " + p_propertyName );
		}
		
		
		return readMethod;
	}

	protected Method getWriteMethod( String p_propertyName ) 
	throws IntrospectionException {
		PropertyDescriptor descr = getPropertyDescriptor( p_propertyName );
		
		return descr.getWriteMethod();
	}

	private PropertyDescriptor getPropertyDescriptor( String p_propertyName ) 
	throws IntrospectionException {
		return ReflectUtil.getPropertyDescriptor( getClass(), p_propertyName, exceptions );
	}

	@Override
	public List<ISmartObject<?, ?>> getChildren() {
		final List<ISmartObject<?, ?>> children = new ArrayList<ISmartObject<?,?>>();
		
		for ( String propertyName : getPropertyNames() ) {
			if ( !"children".equals( propertyName ) ) {
				final Object propertyValue = getPropertyValue( propertyName );
			
				if ( propertyValue instanceof ISmartObject<?, ?> ) {
					children.add( (ISmartObject<?, ?>) propertyValue );
				}
			}
		}

		return children;
	}

	@Override
	public boolean isPropertyDerived( final String p_propertyName ) {
		// TODO:
		return false;
	}

	@Override
	public boolean isPropertyPrivatelyOwned( final String p_propertyName ) {
		return true;
	}
}
