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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.util.EnumUtil;
import fr.labsticc.framework.core.util.ICloneable;
import fr.labsticc.framework.core.util.IIndentable;
import fr.labsticc.framework.core.util.NumberUtil;
import fr.labsticc.framework.core.util.ReflectUtil;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType(name = "")
@XmlRootElement(name = "list")
public abstract class AbstractSmartObject<ID, M extends IMessage<?,?>> implements ISmartObject<ID, M> {
	
	private static final long serialVersionUID = 3723734272971633012L;

	protected static double epsilon = Math.pow(10, -8);
	
	@XmlTransient
	private String currentInDent = "";
	
	private ID id = null;
	
	private String name = getClass().getSimpleName();
	
	@XmlTransient
	private List<IMessage<?,?>> messages = new ArrayList<IMessage<?,?>>();
	
	private ISmartObject<?, ?> parent = null;
	
	@Override
	public void attachMessage( IMessage<?,?> p_message ) {
		getMessages().add( p_message );
	}

	@Override
	public List<IMessage<?,?>> getMessages() {
		return messages;
	}

	@Override
	public boolean removeMessage( IMessage<?,?> p_message ) {
		return messages.remove( p_message );
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals( Object p_value ) {
		if ( !(p_value instanceof ISmartObject) ) {
			return false;
		}

		//IBaseTO l_dataObject = (IBaseTO) p_value;
		//IBaseTO l_actualDataObject = shallowClone();
		ISmartObject<ID, M> l_dataObjectToCompare = (ISmartObject<ID, M>) p_value;//l_dataObject.shallowClone();

		for ( String propertyName : getPropertyNames() ) {
			Object actualObject = getPropertyValue(propertyName);
			Object objectToCompare = l_dataObjectToCompare.getPropertyValue(propertyName);

			if ( actualObject == null ) {
				if ( objectToCompare != null ) {
					return false;
				}
			} 
			else if (objectToCompare == null) {
				if (actualObject != null) {
					return false;
				}
			} 
			else if ( actualObject instanceof Double && objectToCompare instanceof Double) {
				double ld_actualValue = ((Double) actualObject).doubleValue();
				double ld_valueToCompare = ((Double) objectToCompare)
						.doubleValue();
				double ld_averageValue = Math.abs(ld_actualValue
						+ ld_valueToCompare) / 2.0;
				if (Math.abs(ld_actualValue - ld_valueToCompare) > epsilon
						* ld_averageValue) {
					return false;
				}
			} 
			else if ( !actualObject.equals( objectToCompare ) ) {
				return false;
			}
		}

		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public AbstractSmartObject<ID, M> clone() {
		try {
			// Cast needed because getClass returns Class<?>.
			AbstractSmartObject<ID, M> dataObject = ( (Class<AbstractSmartObject<ID, M>>) getClass() ).newInstance();

			for (String propertyName : getPropertyNames()) {
				Object propertyValue = getPropertyValue(propertyName);
				Object clonedPropertyValue = propertyValue;
	
				if ( propertyValue instanceof ICloneable ) {
					clonedPropertyValue = ( (ICloneable) propertyValue ).clone();
				} 
				else if ( propertyValue instanceof ArrayList ) {
					clonedPropertyValue = ( (ArrayList<?>) propertyValue ).clone();
				}
//				else if ( propertyValue instanceof Collection ) {
//					try {
//						Method cloneMethod = propertyValue.getClass().getMethod( "clone", (Class<?>) null );
//						clonedPropertyValue = cloneMethod.invoke( propertyValue, (Object[]) null );
//					}
//					catch( NoSuchMethodException p_ex ) {
//					}
//				}

				dataObject.setPropertyValue( propertyName, clonedPropertyValue );
			}
	
			return dataObject;
		} 
		catch (Exception p_ex) {
			throw new RuntimeException(p_ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractSmartObject<ID, M> shallowClone() {
		try {
			AbstractSmartObject<ID, M> dataObject = ( (Class<AbstractSmartObject<ID, M>>) getClass() ).newInstance();

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
	public String toString() {
		String l_lineSep = System.getProperty("line.separator");
		StringBuffer l_buffer = new StringBuffer(l_lineSep + currentInDent
				+ getName() + "=");

		for (String propertyName : getPropertyNames()) {
			Object lo_valeur = getPropertyValue(propertyName);

			if (!(lo_valeur instanceof IntrospectionSmartDataObject<?, ?> )) {
				l_buffer.append(l_lineSep + currentInDent + TAB).append(
						propertyName).append("=");
			}

			if (lo_valeur instanceof String) {
				l_buffer.append("\"");
			} else if (lo_valeur instanceof IIndentable) {
				((IIndentable) lo_valeur).setCurrentInDent(currentInDent + TAB);
			}

			l_buffer.append(lo_valeur);

			if (lo_valeur instanceof String) {
				l_buffer.append("\"");
			} else if (lo_valeur instanceof IIndentable) {
				((IIndentable) lo_valeur).setCurrentInDent("");
			}
		}

		return l_buffer.toString();
	}

	@Override
	public void setPropertyValues( Map<String, Object> p_values ) {
		for (String propertyName : p_values.keySet()) {
			setPropertyValue( propertyName, p_values.get(propertyName) );
		}
	}
	
	@Override
	public void setName( String p_value ) {
		name = p_value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	@XmlTransient
	public Map<String, Object> getPropertyValues() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		for ( String propertyName : getPropertyNames() ) {
			values.put( propertyName, getPropertyValue( propertyName ) );
		}
		
		return values;
	}

	@Override
	public void addProperties( ISmartObject<ID, M> p_base ) {
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
					( (ISmartObject<ID, M>) existValue ).addProperties( (ISmartObject<ID, M>) p_propertyValue );
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
	public ID getId() {
		return id;
	}

	@Override
	public void setId(ID p_id) {
		id = p_id;
	}

	@Override
	public ObjectState getState() {
		return getState( false );
	}
	
	@Override
	public ObjectState getState( boolean pb_deep ) {
		ObjectState state = ObjectState.OK;
		
		if ( getInfo() != null ) {
			switch ( getInfo().getSeverity() ) {
				case ERROR:
					return ObjectState.ERROR;
				case WARNING:
					state = ObjectState.WARNING;
			}
		}
		
		if ( pb_deep ) {
			for ( String propertyName : getPropertyNames() ) {
				Object value = getPropertyValue( propertyName );
				
				if ( value instanceof ISmartObject<?,?> ) {
					state = EnumUtil.max( state, ( (ISmartObject<?,?>) value ).getState(), ObjectState.values() );
					
					if ( ObjectState.ERROR.equals( state ) ) {
						return state;
					}
				}
				else if ( value instanceof List<?> ) {
					for ( Object childData : (List<?>) value ) {
						if ( childData instanceof ISmartObject<?,?> ) {
							state = EnumUtil.max( state, ( (ISmartObject<?,?>) childData ).getState(), ObjectState.values() );
						
							if ( ObjectState.ERROR.equals( state ) ) {
								return state;
							}
						}
					}
				}
			}
		}
		
		return state;
	}

	@Override
	public void setState( ObjectState p_state ) {
		//throw new UnsupportedOperationException( "This implementation does not support setting state. State is obtained from info state" );
	}
	
	@Override
	public boolean isValid() {
		return !ObjectState.ERROR.equals( getState( true) );
	}
	
	@Override
	public boolean isPropertyValueNull( String p_propertyName ) {
		return getPropertyValue(p_propertyName) == null;
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
	public String getClassId() {
		return ReflectUtil.decapitalizePropertyName( getClass().getSimpleName() );
	}

	@Override
	public ISmartObject<?, ?> getParent() {
		return parent;
	}
}
