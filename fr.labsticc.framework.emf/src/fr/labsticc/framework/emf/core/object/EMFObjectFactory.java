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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.object.IBaseObjectFactory;
import fr.labsticc.framework.emf.core.util.EMFUtil;

public class EMFObjectFactory<BT extends IEMFSmartObjectAdapter<?>> implements IBaseObjectFactory<BT> {

	public EMFObjectFactory() {
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BT> T createObject( final Class<T> p_dataType )
	throws ResourceAccessException {
		final EClassifier eClassifier = EMFUtil.getEClassifier( p_dataType );
		
		if ( eClassifier instanceof EClass ) {
			final EFactory emfFactory = eClassifier.getEPackage().getEFactoryInstance();
			
			return (T) emfFactory.create( (EClass) eClassifier );
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends BT> T cloneObject( final T p_data ) {
		return (T) p_data.clone();
	}
}
