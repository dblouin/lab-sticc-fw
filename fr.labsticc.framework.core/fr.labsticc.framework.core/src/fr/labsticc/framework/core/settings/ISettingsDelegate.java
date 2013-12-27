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

import java.util.List;
import java.util.Locale;

import fr.labsticc.framework.core.exception.ResourceAccessException;

public interface ISettingsDelegate {

	<CT> List<IListItem<CT>> getItems( Class<CT> p_codeType )
	throws ResourceAccessException;

	boolean isViewingRangeAsDelta();

	void setViewingRangeAsDelta( boolean pb_value );
	
	int getNbDecimalesDelta();

	void setNbDecimalesDelta( int pi_value );
	
	void setUserLocale( Locale p_locale );
}
