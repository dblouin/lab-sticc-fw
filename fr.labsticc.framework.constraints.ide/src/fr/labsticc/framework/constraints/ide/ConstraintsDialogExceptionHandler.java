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
package fr.labsticc.framework.constraints.ide;

import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.ide.log.DialogExceptionHandler;

public class ConstraintsDialogExceptionHandler extends DialogExceptionHandler {
	
	public ConstraintsDialogExceptionHandler( final Logger p_logger ) {
		super( p_logger );
	}

	@Override
	public void handleException(	final FunctionalException p_ex, 
									final Object p_info ) {
		if ( p_ex instanceof ConstraintException ) {
			final ConstraintException cstException = (ConstraintException) p_ex;

			if ( cstException.getDiagnostic() instanceof Diagnostic ) {
				final Diagnostic diagnostic = (Diagnostic) ( (ConstraintException) p_ex ).getDiagnostic();
				final String title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_title");
				final String message = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_message");
				
				DiagnosticDialog.open( 	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
										title, 
										message, 
										diagnostic );
			}
			else {
				showError( "Constraint Exception", p_ex.getLocalizedMessage() );
			}
	    }
		else {
			super.handleException( p_ex, p_info );
		}
	}
}
