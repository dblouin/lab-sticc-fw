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

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import fr.labsticc.framework.core.util.DefaultComparator;

/**
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class TreeColumnSorter extends ViewerSorter {
	private static final DefaultComparator comparator = DefaultComparator.getInstance();
	
	private boolean ascend;
	
	private TreeColumn column;
	
	public TreeColumnSorter() {
		column = null;
		ascend = true;
	}
	
	public void columnClicked( final TreeColumn p_column ) {
		final Tree tree = p_column.getParent();
		
		if ( column == p_column ) {
			ascend = !ascend;
		}
		else {
			column = p_column;
			tree.setSortColumn( column );
			ascend = true;
		}
		
		tree.setSortDirection( ascend ? SWT.UP : SWT.DOWN );
	}
	
	private boolean ascend() {
		return ascend;
	}
	
	@Override
	public int compare( final Viewer p_viewer, 
						final Object p_obj1, 
						final Object p_obj2 ) {
		Object prop1;
		Object prop2;
		
		if ( column == null ) {
			prop1 = p_obj1;
			prop2 = p_obj2;
		}
		else {
			final TreeViewer treeViewer = (TreeViewer) p_viewer;
			final int colIndex = indexOf( treeViewer.getTree(), column );
//			
//			final IBaseLabelProvider lblProvider = treeViewer.getLabelProvider();
//			
//			if ( lblProvider instnaceof TreeCellLabelProvider ) {
//				final TreeCellLabelProvider lblProvider = (TreeCellLabelProvider) treeViewer.getLabelProvider();
			prop1 = getColumnText( treeViewer, p_obj1, colIndex );
			
			if ( prop1 == null ) {
				Image image = getColumnImage( treeViewer, p_obj1, colIndex );
				prop1 = image == null ? null : image.toString();
				
				image = getColumnImage( treeViewer, p_obj2, colIndex );
				prop2 = image == null ? null : image.toString();
			}
			else {
				prop2 = getColumnText( treeViewer, p_obj2, colIndex );
			}
		}
		
		if ( ascend() ) {
			return comparator.compare( prop1, prop2 );
		}
		
		return comparator.compare( prop2, prop1 );
	}
	
	private String getColumnText( 	final TreeViewer p_treeViewer,
									final Object p_element,
									final int pi_colIndex ) {
		final CellLabelProvider cellLabelProvider = p_treeViewer.getLabelProvider( pi_colIndex );
		
		if ( cellLabelProvider instanceof ColumnLabelProvider ) {
			return ( (ColumnLabelProvider) cellLabelProvider ).getText( p_element );
		}

		final IBaseLabelProvider lblProvider = p_treeViewer.getLabelProvider();
		
		if ( lblProvider instanceof TreeCellLabelProvider ) {
			return ( (TreeCellLabelProvider) lblProvider ).getColumnText( p_element, pi_colIndex );
		}
		
		return null;
	}
	
	private Image getColumnImage( 	final TreeViewer p_treeViewer,
									final Object p_element,
									final int pi_colIndex ) {
		final CellLabelProvider cellLabelProvider = p_treeViewer.getLabelProvider( pi_colIndex );
		
		if ( cellLabelProvider instanceof ColumnLabelProvider ) {
			return ( (ColumnLabelProvider) cellLabelProvider ).getImage( p_element );
		}

		final IBaseLabelProvider lblProvider = p_treeViewer.getLabelProvider();
		
		if ( lblProvider instanceof TreeCellLabelProvider ) {
			return ( (TreeCellLabelProvider) lblProvider ).getColumnImage( p_element, pi_colIndex );
		}
		
		return null;
	}

	private static int indexOf( final Tree p_tree,
								final TreeColumn p_column ) {
		int index = 0;
		
		for ( final TreeColumn column : p_tree.getColumns() ) {
			if ( p_column == column ) {
				return index;
			}
			
			index++;
		}
		
		return - 1;
	}
}
