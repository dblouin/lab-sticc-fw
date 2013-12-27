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

import fr.labsticc.framework.core.message.IMessage;

public interface ISmartObjectLocalized<ID, M extends IMessage<ID,M>> extends ISmartObject<ID, M> {

	String getPropertyLabel( String p_propertyName );

	void setPropertyLabel(  String p_propertyName,
     						String p_value );
	
	Properties getLabels();
	
	boolean isRequiringLocalization();
	
	void setRequiringLocalization( boolean pb_value );
}
