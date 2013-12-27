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

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

public class EMFContainmentEList<E> extends EObjectContainmentEList<E> {

	private static final long serialVersionUID = 2491378600469930058L;
	
	private final boolean useEquals;

	public EMFContainmentEList( final Class<?> p_dataClass, 
								final InternalEObject p_owner,
								final int p_featureID,
								final boolean pb_useEquals ) {
		super( p_dataClass, p_owner, p_featureID );
		
		useEquals = pb_useEquals;
	}
	
	@Override
	protected boolean useEquals() {
		return useEquals;
	}
	
	@Override
	public boolean contains( final Object p_object ) {
		if ( super.contains( p_object ) ) {
			return true;
		}
		
		if ( useEquals() ) {
			for ( E element : this ) {
				if ( element.equals( p_object ) ) {
					return true;
				}
			}
		}
		
		return false;
	}
}
