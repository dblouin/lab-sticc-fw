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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ToolItemCombo {
	private final ToolItem toolItem;
	private final Menu menu;
	private MenuItem selectedItem;
	private Image enabledImage;
	private Image disabledImage;
	private boolean pushActionEnabled;
	private String defaultText;
	private final SelectionListener mnuItemSelectionListener;
	private final Collection<SelectionListener> pushListeners;
	private final Collection<SelectionListener> selectListeners;

	/**
	 * Constructs a DropdownSelectionListener
	 * 
	 * @param dropdown the dropdown this listener belongs to
	 */
	public ToolItemCombo( 	final ToolBar p_toolBar,
							final int pi_style ) {
		menu = new Menu( p_toolBar.getShell(), SWT.POP_UP );
		toolItem = new ToolItem( p_toolBar, pi_style | SWT.DROP_DOWN );
		toolItem.addSelectionListener(  new SelectionAdapter() {
			@Override
		    public void widgetSelected( final SelectionEvent p_event ) {
		        if ( p_event.detail == SWT.ARROW ) {
		        	final Rectangle rect = toolItem.getBounds();
		        	Point point = new Point( rect.x, rect.y + rect.height );
		        	point = toolItem.getParent().toDisplay( point );
		        	menu.setLocation( point.x, point.y );
		        	menu.setVisible( true );
		        }
		        else {
		        	if ( pushActionEnabled ) {
		        		firePushListeners( p_event );
		        	}
		        }
		    }
		} );	
		
		selectedItem = null;
		defaultText = null;
		
		mnuItemSelectionListener = new SelectionAdapter() {
			@Override
			public void widgetSelected( final SelectionEvent p_event ) {
				select( (MenuItem) p_event.widget );
				
				fireSelectListeners( p_event );
			}
		};
		
		pushListeners = new ArrayList<SelectionListener>();
		selectListeners = new ArrayList<SelectionListener>();
		
		enabledImage = null;
		disabledImage = null;
		pushActionEnabled = true;
	}

	/**
	 * Adds an item to the dropdown list
	 * 
	 * @param item the item to add
	 */
	private void add( final String p_itemText ) {
		final MenuItem menuItem = new MenuItem( menu, SWT.PUSH );
		menuItem.setText( p_itemText );
		menuItem.addSelectionListener( mnuItemSelectionListener );
	}
	
	public void addSelectionListener( final SelectionListener p_listener ) {
		selectListeners.add( p_listener );
	}
	
	public boolean removeSelectionListener( final SelectionListener p_listener ) {
		return selectListeners.remove( p_listener );
	}
	
	public void addPushListener( final SelectionListener p_listener ) {
		pushListeners.add( p_listener );
	}
	
	public boolean removePushListener( final SelectionListener p_listener ) {
		return pushListeners.remove( p_listener );
	}
	
	private void fireSelectListeners( final SelectionEvent p_event ) {
		for ( final SelectionListener listener : selectListeners ) {
			listener.widgetSelected( p_event );
		}
	}
	
	private void firePushListeners( final SelectionEvent p_event ) {
		for ( final SelectionListener listener : pushListeners ) {
			listener.widgetSelected( p_event );
		}
	}

	public void setItems( final String[] p_items ) {
		int index = 0;

		for ( final MenuItem item : menu.getItems() ) {
			if ( index >= p_items.length ) {
				item.dispose();
			}
			else {
				item.setText( p_items[ index++ ] );
			}
		}

		while ( index < p_items.length ) {
			add( p_items[ index++ ] );
		}
	}

	public boolean select( final String p_itemText ) {
		MenuItem foundItem = null;
		
		if ( p_itemText !=  null ) {
			for ( final MenuItem item : menu.getItems() ) {
				if ( p_itemText.equals( item.getText() ) ) {
					foundItem  = item;
					
					break;
				}
			}
		}
		
		select( foundItem );
		
		return foundItem != null;
	}

	private void select( final MenuItem p_item ) {
		selectedItem = p_item;
		final String text;
		
		if ( selectedItem == null ) {
			text = defaultText;
		}
		else {
			text = selectedItem.getText();
		}

		toolItem.setText( text );
	}

	public String getText() {
		return selectedItem == null ? null : selectedItem.getText();
	}
	
	public void setNoSelectionText( final String p_text ) {
		defaultText = p_text;
		toolItem.setText( defaultText );
	}
	
	public void setImage( final Image p_image ) {
		enabledImage = p_image;
		
		if ( enabledImage == null ) {
			disabledImage = null;
		}
		else {
			disabledImage = new Image ( toolItem.getDisplay(), enabledImage, SWT.IMAGE_DISABLE );
		}
		
		toolItem.setImage( enabledImage );
	}
	
	public void setToolTipText( final String p_toolTipText ) {
		toolItem.setToolTipText( p_toolTipText );
	}
	
	public void setPushActionEnabled( final boolean pb_enabled ) {
		toolItem.setImage( pb_enabled ? enabledImage : disabledImage );
		pushActionEnabled = pb_enabled;
	}
	
	public void dispose() {
		menu.dispose();
	}
}
