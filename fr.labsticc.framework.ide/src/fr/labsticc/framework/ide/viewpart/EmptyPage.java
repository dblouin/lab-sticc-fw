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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.labsticc.framework.core.exception.IExceptionHandler;

public class EmptyPage<T> extends AbstractCompositeEditorViewPage<T> {

	public EmptyPage( 	final Composite p_parent,
						final IExceptionHandler p_exceptionHandler ) {
		super( p_parent, SWT.NULL, p_exceptionHandler );
		
		setLayout( new FillLayout() );
		final Label label = new Label( this, SWT.NULL );
		label.setText( "Nothing to show for this selection." );
	}
}
