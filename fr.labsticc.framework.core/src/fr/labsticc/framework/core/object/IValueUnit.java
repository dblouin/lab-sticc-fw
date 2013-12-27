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

public interface IValueUnit<V, U, ID, M extends IMessage<ID, M>> extends IValueObject<V, ID, M> {

	@SuppressWarnings("rawtypes")
	IObjectProperty<IValueUnit> PROPERTY_UNIT = new ObjectPropertySimpleLevel<IValueUnit>( IValueUnit.class, Enum.class, "unit", false );

	void setValue( V p_value );
	
	U getUnit();
	
	void setUnit( U p_unit );
}
