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
import org.eclipse.swt.widgets.Composite;

import fr.labsticc.framework.core.exception.IExceptionHandler;

public abstract class AbstractCompositeEditorViewPage<T> extends Composite implements IViewPartPage<T> {

	private T selectedObject;
	
	private final IExceptionHandler exceptionHandler;

	public AbstractCompositeEditorViewPage( final Composite p_parent, 
											final int pi_style,
											final IExceptionHandler p_exceptionHandler ) {
		super( p_parent, pi_style );
		
		exceptionHandler = p_exceptionHandler;
	}
	
	public void displayData( final T p_object ) {
		selectedObject = p_object;
	}
	
	@Override
	public Collection<IAction> getExternalToolbarActions() {
		return null;
	}
	
	@Override
	public Collection<IAction> getExternalMenuActions() {
		return null;
	}
	
	@Override
	public String getExternalTitle() {
		return null;
	}
	
	@Override
	public Image getExternalImage() {
		return null;
	}

	@Override
	public T saveData() {
		// In this mode, save is performed on the model as soon as a control is edited.
		return getSelectedObject();
	}
	
	@Override
	public T getSelectedObject() {
		return selectedObject;
	}

	protected IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}
	
	protected void handleException( final Throwable p_th,
									final Object p_info ) {
		getExceptionHandler().handleException( p_th, p_info );
	}
}
