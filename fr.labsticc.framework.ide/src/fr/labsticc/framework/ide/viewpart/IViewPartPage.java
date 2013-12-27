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

import java.util.Collection;

import org.eclipse.jface.action.IAction;
import org.eclipse.swt.graphics.Image;

public interface IViewPartPage<T> {

	T saveData();
	
	void displayData( T p_dataObject );
	
	T getSelectedObject();
	
	String getExternalTitle();
	
	Image getExternalImage();
	
	Collection<IAction> getExternalToolbarActions();

	Collection<IAction> getExternalMenuActions();
	
	void dispose();
}
