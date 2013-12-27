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

import org.eclipse.emf.ecore.EObject;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.object.ISmartObjectLocalized;

public interface IEMFSmartObjectAdapter<M extends IMessage<String, M>> extends EObject, ISmartObjectLocalized<String, M>  {
}
