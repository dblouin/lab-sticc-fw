package fr.labsticc.framework.ide.viewpart;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public abstract class TreeCellLabelProvider extends CellLabelProvider {

	protected abstract Image getColumnImage(	Object p_element, 
												int pi_columnIndex );

	protected abstract String getColumnText(	Object p_element, 
												int pi_columnIndex );
	
	@Override
	public void update( final ViewerCell p_cell ) {
		p_cell.setText( getColumnText( p_cell.getElement(), p_cell.getColumnIndex() ) );
		p_cell.setImage( getColumnImage( p_cell.getElement(), p_cell.getColumnIndex() ) );
	}

	@Override
	public boolean useNativeToolTip(Object object) {
		return true;
	}
}
