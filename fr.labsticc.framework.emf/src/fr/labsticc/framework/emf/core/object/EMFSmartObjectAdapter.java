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
package fr.labsticc.framework.emf.core.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.object.ObjectState;
import fr.labsticc.framework.core.object.ISmartObject;
import fr.labsticc.framework.core.object.PropertyNotPresentException;
import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.util.EnumUtil;
import fr.labsticc.framework.core.util.NumberUtil;
import fr.labsticc.framework.core.util.ReflectUtil;
import fr.labsticc.framework.emf.core.util.EMFUtil;

public abstract class EMFSmartObjectAdapter<M extends IMessage<String, M>> extends EObjectImpl implements IEMFSmartObjectAdapter<M> {

	private static final long serialVersionUID = 7616486933389160598L;
	
	@SuppressWarnings("unused")
	private static final transient Logger trace = LoggerFactory.getLogger( EMFSmartObjectAdapter.class );
	
	protected static double epsilon = Math.pow(10, -8);
	
	private String currentInDent = "";
	
	private List<IMessage<?,?>> messages = new ArrayList<IMessage<?,?>>();
	
	protected Collection<String> propertyNames = null;
	
	/**
	 * Holds dynamically added features and their property values. This is to be removed when EMF allows dynamic
	 * features to be added to static models.
	 */
	private Map<EStructuralFeature, Object> dynamicFeatures = new HashMap<EStructuralFeature, Object>();

	@Override
	public String getClassId() {
		return ReflectUtil.decapitalizePropertyName( eClass().getName() );
	}
	
	@Override
	public void attachMessage( IMessage<?,?> p_message) {
		getMessages().add( p_message );
	}

	@Override
	public List<IMessage<?,?>> getMessages() {
		return messages;
	}

	@Override
	public boolean removeMessage( IMessage<?,?> p_message) {
		return messages.remove( p_message );
	}

	
	@Override
	public EMFSmartObjectAdapter<?> clone() {
		return (EMFSmartObjectAdapter<?>) EcoreUtil.copy( this );
	}

	@Override
	public EMFSmartObjectAdapter<?> shallowClone() {
		try {
			@SuppressWarnings("unchecked")
			EMFSmartObjectAdapter<?> dataObject = ( (Class<EMFSmartObjectAdapter<?>>) getClass() ).newInstance();

			for ( String propertyName : getPropertyNames() ) {
				Object l_value = getPropertyValue(propertyName);
	
				if ( l_value instanceof Boolean ) {
					if (Boolean.TRUE.equals(l_value)) {
						dataObject.setPropertyValue(propertyName, l_value);
					}
				} 
				else {
					dataObject.setPropertyValue( propertyName, l_value );
				}
			}
	
			return dataObject;
		} 
		catch ( Exception p_ex ) {
			throw new RuntimeException(p_ex);
		}
	}

	@Override
	public void setCurrentInDent(String p_currentInDent) {
		currentInDent = p_currentInDent;
	}

	@Override
	public String getCurrentInDent() {
		return currentInDent;
	}

	@Override
	public void setPropertyValues( Map<String, Object> p_values ) {
		for (String propertyName : p_values.keySet()) {
			setPropertyValue( propertyName, p_values.get(propertyName) );
		}
	}
	
	@Override
	public Map<String, Object> getPropertyValues() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		for ( String propertyName : getPropertyNames() ) {
			values.put( propertyName, getPropertyValue( propertyName ) );
		}
		
		return values;
	}

	@Override
	public void addProperties( ISmartObject<String, M> p_base ) {
		for ( String propertyName : getPropertyNames() ) {
			addProperty( propertyName, p_base.getPropertyValue( propertyName ) );
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addProperty( 	String p_propertyName,
								Object p_propertyValue ) {
		if ( p_propertyValue != null ) {
			Object existValue = getPropertyValue( p_propertyName );

			if ( existValue == null ) {
				setPropertyValue( p_propertyName, p_propertyValue );
			}
			else {
				if ( existValue instanceof Number ) {
					setPropertyValue( 	p_propertyName, 
										NumberUtil.add( (Number) existValue, (Number) p_propertyValue ) );
				}
				else if ( existValue instanceof ISmartObject ) {
					( (ISmartObject<String, IMessage<?,?>>) existValue ).addProperties( (ISmartObject<String, IMessage<?,?>>) p_propertyValue );
				}
				else if ( existValue instanceof Collection && !( (Collection<?>) p_propertyValue ).isEmpty() ) {
					( (Collection<Object>) existValue ).addAll( (Collection<Object>) p_propertyValue );
				}
				else if ( existValue instanceof IMessage.Severity ) {
					setPropertyValue( p_propertyName, EnumUtil.max( (IMessage.Severity) existValue, 
																	(IMessage.Severity) p_propertyValue, 
																	IMessage.Severity.values() ) );
				}
			}
		}
	}

	@Override
	public ObjectState getState() {
		return getState( false );
	}
	
	@Override
	public ObjectState getState( final boolean pb_deep ) {
		ObjectState state = null;
		
		if ( getInfo() != null && getInfo().getSeverity() != null ) {
			switch ( getInfo().getSeverity() ) {
				case FATAL:
					state = ObjectState.FATAL;
					break;
				case ERROR:
					state = ObjectState.ERROR;
					break;
				case WARNING:
					state = ObjectState.WARNING;
					break;
				case UNKNOWN:
					state = ObjectState.UNKNOWN;
					break;
				case INFO:
					state = ObjectState.INFO;
					break;
				case OK:
					state = ObjectState.OK;
			}
		}
		
		if ( pb_deep ) {
			final Iterator<EObject> contentIt = EcoreUtil.getAllContents( this, true );
			
			while ( contentIt.hasNext() ) {
				final EObject obj = contentIt.next();
				
				if ( obj instanceof ISmartObject<?,?> ) {
					final ISmartObject<?,?> smd = (ISmartObject<?,?>) obj;
					final ObjectState smdState = smd.getState();
					
					if ( state == null ) {
						state = smdState;
					}
					else {
						state = state.max( smdState );
					}
							
					if ( ObjectState.FATAL.equals( state ) ) {
						return state;
					}
				}
			}
		}
		
		return state;
	}

	@Override
	public void setState( final ObjectState p_state ) {
		throw new UnsupportedOperationException( "This implementation does not support setting states. State is obtained from info state" );
	}
	
	@Override
	public boolean isValid() {
		return !ObjectState.FATAL.equals( getState() );
	}
	
	@Override
	public boolean isPropertyValueNull( String p_propertyName ) {
		return getPropertyValue( p_propertyName ) == null;
	}
	
	@Override
	public String getStringPropertyValue( String p_propertyName ) {
		return (String) getPropertyValue(p_propertyName);
	}
	
	@Override
	public Integer getIntegerPropertyValue( String p_propertyName ) {
		return (Integer) getPropertyValue(p_propertyName);
	}
	
	@Override
	public int getIntPropertyValue( String p_propertyName ) {
		return getPropertyValue(p_propertyName) == null ? 0 : getIntegerPropertyValue(p_propertyName).intValue();
	}

	@Override
	public Double getDoublePropertyValue( String p_propertyName ) {
		return (Double) getPropertyValue(p_propertyName);
	}
	
	@Override
	public double getDoubPropertyValue( String p_propertyName ) {
		return getPropertyValue(p_propertyName) == null ? 0.0 : getDoublePropertyValue(p_propertyName).doubleValue();
	}
	
	@Override
	public Long getLongPropertyValue( String p_propertyName ) {
		return (Long) getPropertyValue(p_propertyName);
	}
	
	@Override
	public Date getDatePropertyValue( String p_propertyName ) {
		return (Date) getPropertyValue(p_propertyName);
	}

	protected static double getDoubleValue( Double p_value ) {
		return p_value == null ? 0.0 : p_value;
	}
	
	protected static Double addValues( 	Double p_value1,
										Double p_value2 ) {
		if ( p_value1 == null ) {
			if ( p_value2 == null ) {
				return null;
			}
			
			return p_value2;
		}
		
		return p_value1 + getDoubleValue( p_value2 );
	}

	@Override
	public Collection<String> getPropertyNames() {
		if ( propertyNames == null ) {
			propertyNames = new ArrayList<String>();
		
			for ( EStructuralFeature feature : eClass().getEAllStructuralFeatures() ) {
				propertyNames.add( feature.getName() );
			}
			
			propertyNames.add( PROPERTY_DATA_STATE.getRootPropertyName() );
		}

		return propertyNames;
	}

	@Override
	public Class<?> getPropertyType( String p_name ) {
		if ( PROPERTY_DATA_STATE.getRootPropertyName().equals( p_name ) ) {
			return ObjectState.class;
		}
		
		final EStructuralFeature feature = getEStructuralFeature( p_name );
		
		if ( feature == null ) {
			throw new NullPointerException( "No property feature for " + p_name + " in class " + eClass() );
		}
		
		if ( feature.isMany() ) {
			return List.class;
		}
		
       	return EMFUtil.getFeatureType( eClass(), feature );
	}

	@Override
	public Object getPropertyValue( String p_name ) {
		if ( PROPERTY_DATA_STATE.getRootPropertyName().equals( p_name ) ) {
			return getState();
		}
		
		final EStructuralFeature eFeature = getEStructuralFeature( p_name );
		
		if ( eFeature == null ) {
			throw new PropertyNotPresentException( eClass().getName(), p_name );
		}
		
		try {
			return eGet( eFeature );
		}
		catch( final NullPointerException p_ex ) {
			throw p_ex; 
		}
	}

	@Override
	public void setPropertyValue(	String p_name, 
									Object p_value ) {
		if ( PROPERTY_DATA_STATE.getRootPropertyName().equals( p_name ) ) {
			setState( (ObjectState) p_value );
		}
		
		final EStructuralFeature eFeature = getEStructuralFeature( p_name );
		
		if ( eFeature == null ) {
			throw new IllegalArgumentException( "No feature in class " + eClass().getName() + " for property " + p_name );
		}

		eSet( eFeature, p_value );
	}
	
	@Override
	public boolean hasProperty( final String p_propertyName ) {
		return getPropertyNames().contains( p_propertyName );
	}

	protected EStructuralFeature getEStructuralFeature( final String p_name ) {
		return eClass().getEStructuralFeature( p_name );
	}

	@Override
	public List<ISmartObject<?, ?>> getChildren() {
		final List<ISmartObject<?, ?>> children = new ArrayList<ISmartObject<?,?>>();
		
		for ( EObject child : eContents() ) {
			if  ( child instanceof ISmartObject<?,?> ) {
				children.add( (ISmartObject<?,?>) child );
			}
		}
		
		return children;
	}
	
	@Override
	public boolean isPropertyDerived( final String p_propertyName ) {
		if ( PROPERTY_DATA_STATE.getRootPropertyName().equals( p_propertyName ) ) {
			return true;
		}
		
		final EStructuralFeature feature = eClass().getEStructuralFeature( p_propertyName );
		
		if ( feature == null ) {
			return false;
		}
		
		return feature.isDerived();
	}
	
	@Override
	public boolean isPropertyPrivatelyOwned( final String p_propertyName ) {
		final EReference reference = EMFUtil.getEReference( eClass(), p_propertyName );
		
		if ( reference == null ) {
			return true;
		}
		
		return reference.isContainment();
	}

	@Override
	public ISmartObject<?, ?> getParent() {
		return (ISmartObject<?, ?>) eContainer();
	}

	/* 
	 * Handles the dynamic features. This is to be removed when EMF allows dynamic features to be added to static models.
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eSet(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
	 */
	@Override
	public void eSet( 	final EStructuralFeature p_feature, 
						final Object p_newValue ) {
		if ( isDynamicFeature( p_feature ) ) {
			checkValue( p_feature, p_newValue );
			dynamicFeatures.put( p_feature, p_newValue );
		}
		else {
			super.eSet( p_feature, p_newValue );
		}
	}
	
	private void checkValue( 	final EStructuralFeature p_feature, 
								final Object p_newValue ) {
		if ( p_newValue != null ) {
			if ( p_feature.getEType() == null ) {
				throw new IllegalArgumentException( p_feature.getName() + " feature type cannot be null." );
			}
			
			if ( !p_feature.getEType().isInstance( p_newValue ) ) {
				throw new IllegalArgumentException( "Value " + String.valueOf( p_newValue ) + " is of the wrong type. Should be of type " +  p_feature.getEType() + "." );
			}
		}
	}

	/* 
	 * Handles the dynamic features. This is to be removed when EMF allows dynamic features to be added to static models.
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eGet(org.eclipse.emf.ecore.EStructuralFeature, boolean, boolean)
	 */
	@Override
	public Object eGet( final EStructuralFeature p_feature, 
						final boolean pb_resolve, 
						final boolean pb_coreType ) {
		if ( isDynamicFeature( p_feature ) ) {
			return dynamicFeatures.get( p_feature );
		}

		return super.eGet( p_feature, pb_resolve, pb_coreType );
	}
	
	private boolean isDynamicFeature( final EStructuralFeature p_feature ) {
		return getEStructuralFeature( p_feature.getName() ) == null;
	}
} 
