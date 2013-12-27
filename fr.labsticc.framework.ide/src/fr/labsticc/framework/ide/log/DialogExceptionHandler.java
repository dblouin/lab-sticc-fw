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
package fr.labsticc.framework.ide.log;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.slf4j.Logger;

import fr.labsticc.framework.core.exception.BaseExceptionHandler;
import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.core.exception.SystemException;

public class DialogExceptionHandler extends BaseExceptionHandler {
	
	public DialogExceptionHandler( final Logger p_logger ) {
		super( p_logger );
	}

	private static final String UNKNOWN_ERROR_TITLE = "Unknown Error";
	private static final String UNKNOWN_ERROR_MSG = "Please see error log view for more info.";

	private static final String SYSTEM_ERROR_TITLE = "System Error";
	private static final String SYSTEM_ERROR_MSG = "Please see plugin error log view for more info.";

	private static final String FUNCTIONAL_ERROR_TITLE = "Functional Error";
	//private static final String FUNCTIONAL_ERROR_MSG = " Please review the data.";

	@Override
	public void handleException( 	final Throwable p_th, 
									final Object p_info) {
		super.handleException( p_th, p_info );
		
		if ( 	!( p_th instanceof SystemException ) &&
				!( p_th instanceof FunctionalException ) &&
				p_th.getCause() == null ) {
			showError( UNKNOWN_ERROR_TITLE, ( ( p_th.getMessage() == null || "".equals( p_th.getLocalizedMessage() ) ) ? ""  : p_th.getLocalizedMessage() + ": " ) + UNKNOWN_ERROR_MSG );
		}
	}

	@Override
	public void handleException( 	final SystemException p_ex, 
									final Object p_info ) {
		super.handleException( p_ex, p_info );
		
		showError( SYSTEM_ERROR_TITLE, p_ex.getMessage() + SYSTEM_ERROR_MSG );
	}

	@Override
	public void handleException( 	final FunctionalException p_ex, 
									final Object p_info ) {
		super.handleException( p_ex, p_info );
		
		showError( FUNCTIONAL_ERROR_TITLE, p_ex.getMessage() /*+ FUNCTIONAL_ERROR_MSG*/ );
	}

	protected void showError( 	final String p_errorTitle,
								final String p_message ) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				
				final Shell shell = window == null ? null : window.getShell();
				
				if ( shell != null ) {
					MessageDialog.openError( shell,	p_errorTitle, p_message );
				}
			}
		} );
	}
}
