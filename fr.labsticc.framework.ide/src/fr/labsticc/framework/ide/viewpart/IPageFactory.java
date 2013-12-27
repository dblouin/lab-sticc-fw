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
package fr.labsticc.framework.ide.viewpart;

import org.eclipse.swt.widgets.Composite;

import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.core.exception.IExceptionHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.exception.SystemException;

public interface IPageFactory<T> {

	Object getPageKey( T p_modelObject ) 
	throws FunctionalException, SystemException;

	IViewPartPage<? extends T> createPage( 	Composite p_parent,
											Object p_pageId,
											IExceptionHandler p_exceptionHandler ) 
	throws ResourceAccessException;
}
