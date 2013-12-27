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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import fr.labsticc.framework.core.util.DefaultComparator;

/**
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 *
 *	TODO: Move to view framework
 */
public class TableColumnSorter extends ViewerSorter {
	private static final DefaultComparator comparator = DefaultComparator.getInstance();
	
	private boolean ascend;
	
	private TableColumn column;

	public TableColumnSorter() {
		column = null;
		ascend = true;
	}
	
	public void columnClicked( final TableColumn p_column ) {
		final Table table = p_column.getParent();
		
		if ( column == p_column ) {
			ascend = !ascend;
		}
		else {
			if ( column != null ) {
				column.setImage( null );
			}
			
			column = p_column;
			table.setSortColumn( column );
			ascend = true;
		}

		table.setSortDirection( ascend ? SWT.UP : SWT.DOWN );
	}
	
	private boolean ascend() {
		return ascend;
	}
	
	@Override
	public int compare( final Viewer p_viewer, 
						final Object p_obj1, 
						final Object p_obj2 ) {
		final Object prop1;
		final Object prop2;
		
		if ( column == null ) {
			prop1 = p_obj1;
			prop2 = p_obj2;
		}
		else {
			final TableViewer tblViewer = (TableViewer) p_viewer;
			final int index = tblViewer.getTable().indexOf( column );
			final ILabelProvider lblProvider = (ILabelProvider) tblViewer.getLabelProvider( index );
			prop1 = lblProvider.getText( p_obj1 );
			prop2= lblProvider.getText( p_obj2 );
		}
			
		if ( ascend() ) {
			return comparator.compare( prop1, prop2 );
		}
		
		return comparator.compare( prop2, prop1 );
	}
}
