package fr.labsticc.framework.constraints.view;

import java.io.InputStream;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public abstract class BasicPanel extends Composite {

	protected ToolBar tbrActions;
	
	protected BasicPanel( 	final Composite p_parent, 
							final int pi_style ) {
		super( p_parent, pi_style );
	}
	
	protected ToolItem createToolBarItem( 	final String p_imagePath,
										final String p_tooltip,
										final int pi_style,
										final SelectionListener p_listener ) {
		final ToolItem tbiItem = new ToolItem( tbrActions, pi_style );
		tbiItem.setToolTipText( p_tooltip );
		
		if ( p_imagePath != null ) { 
			tbiItem.setImage( createImage( p_imagePath ) );
		}
		
		if ( p_listener != null ) {
			tbiItem.addSelectionListener( p_listener );
		}
	    
	    return tbiItem;
	}
	
	protected Image createImage( final String p_imagePath ) {
		final InputStream imageStr = getClass().getResourceAsStream( p_imagePath );
		
		if ( imageStr == null ) {
			return null;
		}
		
		return new Image( getDisplay(),	imageStr );
	}
	
	@Override
	public void setEnabled( final boolean pb_value ) {
		super.setEnabled( pb_value );
		
		if ( tbrActions != null ) {
			tbrActions.setEnabled( pb_value );
		}
	}
}
