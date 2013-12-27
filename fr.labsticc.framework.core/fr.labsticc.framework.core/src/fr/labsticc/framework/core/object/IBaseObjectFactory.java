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

import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * The Lab-STICC development framework neutral object factory.
 *  
 * @param <BT> The super class of all objects that can be created by this factory.
 *  
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface IBaseObjectFactory<BT> {
	
	<T extends BT> T createObject( Class<T> p_objectType )
	throws ResourceAccessException;
	
	<T extends BT> T cloneObject( T p_object );
}
