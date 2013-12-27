package fr.labsticc.framework.emf.view.editor;

import java.util.List;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;

/**
 * A combo box cell editor whose elements can be changed. 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class CustomExtendedComboBoxCellEditor extends ExtendedComboBoxCellEditor {

	public CustomExtendedComboBoxCellEditor(	final Composite p_composite,
												final List<?> p_elements,
												final ILabelProvider p_labelProvider ) {
		super( p_composite, p_elements, p_labelProvider );
	}

	@SuppressWarnings("unchecked")
	public void updateChoice( 	@SuppressWarnings("rawtypes") final List p_choiceElements,
								final Object p_selectedValue ) {
		originalList.clear();
		originalList.addAll( p_choiceElements );
		
		refreshItems( null );
		
		if ( p_selectedValue != null ) {
			setValue( p_selectedValue );
		}
	}
	
	@Override
	protected void refreshItems( final String p_filter ) {
	    final CCombo combo = (CCombo) getControl();
	    
	    if ( combo != null && !combo.isDisposed() ) {
    		final String[] items = createItems( originalList, labelProvider, p_filter, sorted);
   			final Object previousValue = getValue();
   			list = originalList;
	    	combo.setItems(items);
		    	  
			if (list.contains( previousValue ) ) {
				setValue(previousValue);
			}
			else if (!list.isEmpty()) {
				setValue(list.get(0));
			}
	    }
	}
}
