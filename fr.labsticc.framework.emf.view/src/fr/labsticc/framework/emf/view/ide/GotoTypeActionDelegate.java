package fr.labsticc.framework.emf.view.ide;

import java.util.Collections;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import fr.labsticc.framework.emf.core.util.EMFUtil;

public class GotoTypeActionDelegate implements IObjectActionDelegate {

	private EcoreEditor part;
	
	private EClassifier eClassifier;
	
	/**
	 * Constructor for GotoSuperClassActionDelegate.
	 */
	public GotoTypeActionDelegate() {
		super();
		
		part = null;
		eClassifier = null;
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
		if ( part != null ) {
			part.setSelectionToViewer( Collections.singletonList( eClassifier ) );
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	@Override
	public void selectionChanged(	final IAction p_action,
									final ISelection p_selection ) {
		final EObject selObject = EMFUtil.selectedObject( p_selection, EObject.class );
		
		if ( selObject instanceof EClass ) {
			final EClass selClass = (EClass) selObject;
			
			if ( selClass.getESuperTypes().isEmpty() ) {
				eClassifier = null;
			}
			else {
				eClassifier = selClass.getESuperTypes().get( 0  );
			}
		}
		// Features, operations, ...
		else if ( selObject instanceof ETypedElement ) {
			eClassifier = ( (ETypedElement) selObject ).getEType();
		}
		else if ( selObject instanceof EGenericType ) {
			eClassifier = ( (EGenericType) selObject ).getEClassifier();
		}

		p_action.setEnabled( eClassifier != null );
		p_action.setText( eClassifier == null ? "No type" : "Go to " + eClassifier.getName() );
	}
}
