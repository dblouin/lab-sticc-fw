package fr.labsticc.framework.emf.view.ide;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import fr.labsticc.framework.emf.core.util.EMFUtil;

public class GotoElementActionDelegate implements IObjectActionDelegate {

	private EcoreEditor part;
	
	private EPackage ePackage;
	
	/**
	 * Constructor for GotoSuperClassActionDelegate.
	 */
	public GotoElementActionDelegate() {
		super();
		
		part = null;
		ePackage = null;
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	@Override
	public void setActivePart(	final IAction p_action,
								final IWorkbenchPart p_targetPart ) {
		if ( p_targetPart instanceof EcoreEditor ) {
			part = (EcoreEditor) p_targetPart;
		}
		else {
			part = null;
		}
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	@Override
	public void run( final IAction p_action ) {
		if ( part != null && ePackage != null ) {
			final OpenEcoreTypeSelectionDialog dialog = new OpenEcoreTypeSelectionDialog( 	part.getSite().getShell(),
																							ePackage );
			final int result = dialog.open();
			
			if ( result == OpenEcoreTypeSelectionDialog.OK && dialog.getFirstResult() != null ) {
				part.setSelectionToViewer( Collections.singletonList( dialog.getFirstResult() ) );
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	@Override
	public void selectionChanged(	final IAction p_action,
									final ISelection p_selection ) {
		final EObject selObject = EMFUtil.selectedObject( p_selection, EObject.class );
		ePackage = (EPackage) EMFUtil.getRootObject( selObject, EcorePackage.eINSTANCE.getEPackage() );
		
		p_action.setEnabled( ePackage != null );
	}
}
