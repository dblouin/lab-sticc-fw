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

import java.util.Properties;

import fr.labsticc.framework.core.message.IMessage;

public abstract class EMFSmartObjectLocalized<M extends IMessage<String, M>> extends EMFSmartObjectAdapter<M> {

	private static final long serialVersionUID = -1496597556151648130L;

	private Properties labels = new Properties();
	
	private boolean requiringLocalization = true;

	@Override
	public String getPropertyLabel( final String p_propertyName ) {
		return labels.getProperty( p_propertyName );
	}
	
	@Override
	public void setPropertyLabel( 	final String p_propertyName,
									final String p_label ) {
		labels.setProperty( p_propertyName, p_label );
	}

	@Override
	public Properties getLabels() {
		return labels;
	}

	@Override
	public boolean isRequiringLocalization() {
		return requiringLocalization;
	}
	
	@Override
	public void setRequiringLocalization( final boolean pb_value ) {
		requiringLocalization = pb_value;
	}
}
