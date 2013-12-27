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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TreeColumn;

public class TreeColumnSortSelectionListener extends SelectionAdapter {
	private final TreeViewer viewer;

	public TreeColumnSortSelectionListener( final TreeViewer p_viewer ) {
		viewer = p_viewer;
	}

	@Override
	public void widgetSelected( final SelectionEvent p_evt ) {
		( (TreeColumnSorter) viewer.getComparator() ).columnClicked( (TreeColumn ) p_evt.getSource() );
		viewer.refresh();
    }
}
