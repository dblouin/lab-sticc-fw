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
package fr.labsticc.framework.core.settings;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.object.ISmartObject;

public interface IListItem<T> extends ISmartObject<T, IMessage<?,?>> {

	void setCode( T p_code );
	
	T getCode();
	
	void setLabelText( String p_label );
	
	String getLabelText();
	
	boolean isPrefered();

	void setPrefered( boolean pb_value );
}
