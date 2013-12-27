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

import fr.labsticc.framework.core.util.EnumUtil;

public enum ObjectState {

	OK, 
	
	INFO,
	
	UNKNOWN,
	
	WARNING, 
	
	ERROR,
	
	FATAL;
	
	public ObjectState max( final ObjectState p_state2 ) {
		return EnumUtil.max( this, p_state2, values() );
	}

	public boolean isLowerThan( final ObjectState p_state2 ) {
		if ( p_state2 == null ) {
			return true;
		}
		
		return ordinal() < p_state2.ordinal();
	}
}
