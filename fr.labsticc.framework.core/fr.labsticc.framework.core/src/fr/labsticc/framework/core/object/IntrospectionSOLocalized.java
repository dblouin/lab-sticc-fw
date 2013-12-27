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

import java.util.Properties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import fr.labsticc.framework.core.message.IMessage;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlType(name = "")
@XmlRootElement(name = "")
public abstract class IntrospectionSOLocalized<ID, M extends IMessage<?, M>> extends IntrospectionSmartDataObject<ID, M> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5957282765732527361L;
	@XmlTransient
	private Properties labels = new Properties();

	@Override
	public Object getPropertyValue( String p_propertyName ) {
		if ( getPropertyNames().contains( p_propertyName ) ) {
			return super.getPropertyValue( p_propertyName );
		}
		
		return labels.getProperty( p_propertyName );
	}

	@Override
	public void setPropertyValue( 	String p_propertyName, 
									Object p_value) {
		if ( getPropertyNames().contains( p_propertyName ) ) {
			super.setPropertyValue( p_propertyName, p_value );
		}
		else {
			if ( p_value == null ) {
				labels.remove( p_propertyName );
			}
			else {
				labels.setProperty( p_propertyName, p_value.toString() );
			}
		}
	}
}
