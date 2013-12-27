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

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.util.ICloneable;
import fr.labsticc.framework.core.util.IIndentable;

public interface ISmartObject<ID, M extends IMessage<?,?>> extends ICloneable, IIndentable, Serializable {

	@SuppressWarnings("rawtypes")
	IObjectProperty<ISmartObject> PROPERTY_NAME = new ObjectPropertySimpleLevel<ISmartObject>( 	ISmartObject.class, 
																								"name",
																								false );

	@SuppressWarnings("rawtypes")
	IObjectProperty<ISmartObject> PROPERTY_INFO = new ObjectPropertySimpleLevel<ISmartObject>( 	ISmartObject.class, 
																								"info",
																								false );

	@SuppressWarnings("rawtypes")
	IObjectProperty<ISmartObject> PROPERTY_DATA_STATE = new ObjectPropertySimpleLevel<ISmartObject>( 	ISmartObject.class, 
																										"dataState",
																										false );

	void setName( String p_value );
	
	String getName();
	
	void setPropertyValue(  String p_propertyName,
	                 		Object p_value );

	Object getPropertyValue( String p_propertyName );

	Class<?> getPropertyType( String p_propertyName );

	Collection<String> getPropertyNames();
	
	boolean hasProperty( String p_propertyName );

	Map<String, Object> getPropertyValues();

	void setPropertyValues( Map<String, Object> p_values );
	
	void addProperties( ISmartObject<ID, M> p_base );
	
	void addProperty( 	String p_propertyName,
						Object p_propertyValueToAdd );
	
	ID getId();

	void setId( ID p_id );
	
	String getClassId();
	
	void attachMessage( IMessage<?,?> p_message );
	
	boolean removeMessage( IMessage<?,?> p_message );

	List<IMessage<?,?>> getMessages();
	
	M getInfo();

	void setInfo( M p_message );
	
	List<ISmartObject<?, ?>> getChildren();
	
	ObjectState getState();
	
	ObjectState getState( boolean pb_deep );
	
	void setState( ObjectState p_value );

	boolean isValid();
	
	boolean isPropertyValueNull( String p_propertyName );
	
	String getStringPropertyValue( String p_propertyName );
	
	Integer getIntegerPropertyValue( String p_propertyName );
	
	int getIntPropertyValue( String p_propertyName );

	Double getDoublePropertyValue( String p_propertyName );
	
	double getDoubPropertyValue( String p_propertyName );
	
	Long getLongPropertyValue( String p_propertyName );
	
	Date getDatePropertyValue( String p_propertyName );
	
	boolean isPropertyDerived( String p_propertyName );

	boolean isPropertyPrivatelyOwned( String p_propertyName );
	
	ISmartObject<?, ?> getParent();
}
