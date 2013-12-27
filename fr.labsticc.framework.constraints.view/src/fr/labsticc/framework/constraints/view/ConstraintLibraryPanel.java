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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import fr.labsticc.framework.constraints.view.i18n.ConstraintConsoleMessages;

public class ConstraintLibraryPanel extends BasicPanel {
	
	private TableViewer tblLibraries;
	private Collection<Object[]> dataElements;
	private ToolItem tbiEditLib;
	private ToolItem tbiRefreshLib;
	private ToolItem tbiDeleteLib;

	private final IConstraintLibraryPanelActionHandler actionHandler;

	public ConstraintLibraryPanel( 	final Composite p_parent, 
									final int pi_style,
									final IConstraintLibraryPanelActionHandler p_actionHandler ) {
		super( p_parent, pi_style );
		
		dataElements = new ArrayList<Object[]>();
		actionHandler = p_actionHandler;
		
		createControls();
	}
	
	protected void createControls() {
		setLayout( new GridLayout() );
		tbrActions = new ToolBar( this, SWT.FLAT | SWT.WRAP | SWT.RIGHT );
		
		createToolBarItem( 	ConstraintConsoleMessages.addLibAction_icon, 
							ConstraintConsoleMessages.addLibAction_tip,
							SWT.PUSH,
				new SelectionAdapter() {

					@Override
					public void widgetSelected( final SelectionEvent p_evt ) {
						final Collection<String> addedLibs = actionHandler.addLibrariesRequested( getLibraries() );
						
						// Check if canceled
						if ( addedLibs != null ) {
							setLibraries( addedLibs );
						}
					}
				} );

		tbiEditLib = createToolBarItem( ConstraintConsoleMessages.editLibAction_icon, 
										ConstraintConsoleMessages.editLibAction_tip,
										SWT.PUSH,
										new SelectionAdapter() {

			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				actionHandler.openLibrariesRequested( selectedLibraryIds() );
			}
		} );
		tbiEditLib.setEnabled( false );
		
		tbiRefreshLib = createToolBarItem( 	ConstraintConsoleMessages.refreshLibAction_icon, 
											ConstraintConsoleMessages.refreshLibAction_tip,
											SWT.PUSH,
											new SelectionAdapter() {

		@Override
		public void widgetSelected( final SelectionEvent p_evt ) {
			actionHandler.librariesRefreshRequested( selectedLibraryIds() );
		}
		} );
		tbiRefreshLib.setEnabled( false );

		tbiDeleteLib = createToolBarItem( 	ConstraintConsoleMessages.deleteLibAction_icon, 
											ConstraintConsoleMessages.deleteLibAction_tip,
											SWT.PUSH,
											new SelectionAdapter() {

			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				final Collection<String> deletedLibs = selectedLibraryIds();
				final Collection<String> remainingLibs = getLibraries();
				remainingLibs.removeAll(deletedLibs );
				setLibraries( remainingLibs );
				actionHandler.librariesDeleted( deletedLibs );
			}
		} );
		tbiDeleteLib.setEnabled( false );
		
		tblLibraries = new TableViewer( this, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER );
		final Table table = tblLibraries.getTable();
		table.setHeaderVisible( true );
		table.setLinesVisible( true );
		table.addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseDoubleClick( MouseEvent p_evt ) {
				final Object source = p_evt.getSource();
				
				if ( source instanceof Table ) {
					final Table tbl = (Table) source;
					
					if ( tbl.getSelectionCount() == 1 ) {
						actionHandler.openLibrariesRequested( selectedLibraryIds() );
					}
				}
			}
		} );
		
		final GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		tblLibraries.getControl().setLayoutData( gridData );

		tblLibraries.setContentProvider( new IStructuredContentProvider() {
			
			@Override
			public void inputChanged( 	final Viewer p_viewer, 
										final Object p_oldInput, 
										final Object p_newInput ) {
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public Object[] getElements( final Object p_inputElement ) {
				return ( (List<?>) p_inputElement ).toArray();
			}
		} );
		
		tblLibraries.setInput( dataElements );
		tblLibraries.setComparator( new TableColumnSorter() );
		
		tblLibraries.addPostSelectionChangedListener( new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged( final SelectionChangedEvent p_event ) {
				if ( p_event.getSelection() instanceof IStructuredSelection ) {
					final IStructuredSelection structSel = (IStructuredSelection) p_event.getSelection();
					tbiEditLib.setEnabled( structSel.size() == 1 );
					tbiRefreshLib.setEnabled( structSel.size() > 0 );
					tbiDeleteLib.setEnabled( structSel.size() > 0 );
				}
				else {
					tbiEditLib.setEnabled( false );
					tbiRefreshLib.setEnabled( false );
					tbiDeleteLib.setEnabled( false );
				}
			}
		} );
		
		buildColumns();
	}
	
	private void buildColumns() {
		final TableColumnSortSelectionListener sortListener = new TableColumnSortSelectionListener( tblLibraries );

		TableViewerColumn viewerColumn = new TableViewerColumn( tblLibraries,	SWT.NONE );
		ColumnLabelProvider lblProvider = new ColumnLabelProvider() {
			@Override
			public String getText( final Object p_element ) {
				return String.valueOf( ( (Object[]) p_element )[ 0 ] );
			}
		};
		viewerColumn.setLabelProvider( lblProvider );

		TableColumn column = viewerColumn.getColumn();
		column.setText( ConstraintConsoleMessages.evalOrderHeader );
		column.setWidth( 80 );
		column.setResizable( true );
		column.setMoveable(true);
		column.addSelectionListener( sortListener );

		viewerColumn = new TableViewerColumn( tblLibraries,	SWT.NONE );
		lblProvider = new ColumnLabelProvider() {
			@Override
			public String getText( final Object p_element ) {
				return String.valueOf( ( (Object[]) p_element )[ 1 ] );
			}
		}; 
		viewerColumn.setLabelProvider( lblProvider );

		column = viewerColumn.getColumn();
		column.setText( ConstraintConsoleMessages.libraryHeader );
		column.setWidth( 700 );
		column.setResizable( true );
		column.setMoveable(true);
		column.addSelectionListener( sortListener );
	}
	
	private Collection<String> selectedLibraryIds() {
		final Collection<String> elements = new ArrayList<String>();
		
		for ( final TableItem item : tblLibraries.getTable().getSelection() ) {
			elements.add( item.getText( 1 ) );
		}

		return elements;
	}
	
	public void setLibraries( final Collection<String> p_libraries ) {
		dataElements.clear();

		if ( p_libraries != null ) {
			int index = 0;
			
			for ( final String libName : p_libraries ) {
				dataElements.add( new Object[] { index++, libName } );
			}
		}
		
		Display.getDefault().asyncExec( new Runnable() {
			@Override
			public void run() {
				tblLibraries.refresh();
			}
		} );
	}
	
	public Collection<String> getLibraries() {
		final Collection<String> libraryIds = new ArrayList<String>();
		
		for ( final Object[] library : dataElements ) {
			libraryIds.add( library[ 1 ].toString() );
		}
		
		return libraryIds;
	}
	
	@Override
	public void setEnabled( final boolean pb_value ) {
		super.setEnabled( pb_value );
		
		tblLibraries.getControl().setEnabled( pb_value );
	}
}
