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

import fr.labsticc.framework.core.message.IMessage;

public interface IObjectRange<V, ID, M extends IMessage<ID, M>> extends IValueObject<V, ID, M> {

	@SuppressWarnings("rawtypes")
	IObjectProperty<IObjectRange> PROPERTY_VALUE_MIN = new ObjectPropertySimpleLevel<IObjectRange>( IObjectRange.class, IValueUnit.class, "minValue", false );
	@SuppressWarnings("rawtypes")
	IObjectProperty<IObjectRange> PROPERTY_VALUE_MAX = new ObjectPropertySimpleLevel<IObjectRange>( IObjectRange.class, IValueUnit.class, "maxValue", false );
	@SuppressWarnings("rawtypes")
	IObjectProperty<IObjectRange> PROPERTY_DELTA = new ObjectPropertySimpleLevel<IObjectRange>( IObjectRange.class, IValueUnit.class, "delta", false );
	
	V getMinValue();
	
	void setMinValue( V p_value );

	V getMaxValue();
	
	void setMaxValue( V p_value );

	/**
	 * Returns the average for a range.
	 * @return
	 */
	V getValue();
	
	void setValue( V p_value );
	
	Object getDelta();

	Class<?> getValueType();
	
	void setMinMaxValues( 	Object p_value,
							Object p_delta );
}
