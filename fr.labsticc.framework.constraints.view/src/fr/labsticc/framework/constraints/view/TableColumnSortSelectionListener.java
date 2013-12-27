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
package fr.labsticc.framework.constraints.view;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

public class TableColumnSortSelectionListener extends SelectionAdapter {
	private final TableViewer viewer;

	public TableColumnSortSelectionListener( final TableViewer p_tableViewer ) {
		viewer = p_tableViewer;
	}

	@Override
	public void widgetSelected( final SelectionEvent p_evt ) {
		( (TableColumnSorter) viewer.getComparator() ).columnClicked( (TableColumn) p_evt.getSource() );
		viewer.refresh();
    }
}
