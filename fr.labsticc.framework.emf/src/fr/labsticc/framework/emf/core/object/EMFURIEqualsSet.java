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

import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;

import fr.labsticc.framework.emf.core.util.EMFUtil;

public class EMFURIEqualsSet<E extends EObject> extends HashSet<E> {

	private static final long serialVersionUID = 2916176668409687525L;

	@Override
    public boolean contains( final Object p_object ) {
		for ( final E element : this ) {
			if ( EMFUtil.equalsByURI( (EObject) p_object, element ) ) {
				return true;
			}
		}
		
		return false; 
	}
}
