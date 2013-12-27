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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.view.i18n.ConstraintConsoleMessages;
import fr.labsticc.framework.core.exception.IExceptionHandler;

public class ConstraintPanel extends Composite {
	
	private ConstraintConsolePanel pnlConsole;
	private ConstraintLibraryPanel pnlLibraries;
	private final IConstraintPanelActionHandler actionHandler;

	public ConstraintPanel(	final Composite p_parent, 
							final int pi_style,
							final IConstraintPanelActionHandler p_actionHandler,
							final IConstraintViewersConfiguration p_constraintViewsConfig,
							final IExceptionHandler p_exceptionHandler ) {
		super( p_parent, pi_style );
		
		actionHandler = p_actionHandler;
		createControls( p_constraintViewsConfig, p_exceptionHandler );
	}
	
	protected void createControls(	final IConstraintViewersConfiguration p_constraintViewsConfig,
									final IExceptionHandler p_exceptionHandler ) {
		setLayout( new FillLayout() );
		final TabFolder tabFolder = new TabFolder( this, SWT.NONE );
		//tabFolder.setLayout( new FillLayout() );
		final IConstraintConsolePanelActionHandler consolePanelActionHandler = new IConstraintConsolePanelActionHandler() {
			
			@Override
			public void constraintLanguageChanged( final String p_newLanguageId ) {
				pnlLibraries.setLibraries( actionHandler.constraintLanguageChanged( p_newLanguageId ) );
			}
			
			@Override
			public void constraintTextChanged(	final String p_languageId,
												final String p_constraintText ) {
				actionHandler.constraintTextChanged( p_languageId, p_constraintText );
			}
			
			@Override
			public String constraintLoadingRequested( final String p_languageId ) {
				return actionHandler.constraintLoadingRequested( p_languageId );
			}

			@Override
			public void constraintSavingRequested(	final String p_languageId,
													final String p_constraintText) {
				actionHandler.constraintSavingRequested( p_languageId, p_constraintText );
			}
			
			@Override
			public String constraintEvaluationRequested(	final String p_languageId,
															final String p_constraintText ) {
				return actionHandler.constraintEvaluationRequested( p_languageId, 
																	p_constraintText, 
																	pnlLibraries.getLibraries() );
			}
			
			@Override
			public List<ICompletionProposal> completionProposalRequested(	final String p_languageId,
																			final String p_constraintText ) {
				return actionHandler.completionProposalRequested( p_languageId,	p_constraintText, pnlLibraries.getLibraries() );
			}
		};
		
		pnlConsole = new ConstraintConsolePanel( 	tabFolder, 
													SWT.NULL, 
													consolePanelActionHandler, 
													p_constraintViewsConfig,
													p_exceptionHandler );

		final TabItem itemConsole = new TabItem( tabFolder, SWT.NULL );
		itemConsole.setText( ConstraintConsoleMessages.consoleTabTitle );
		itemConsole.setControl( pnlConsole );
		
		pnlLibraries = new ConstraintLibraryPanel( tabFolder, SWT.NULL, new IConstraintLibraryPanelActionHandler() {
			
			@Override
			public void openLibrariesRequested( final Collection<String> p_libraryNames ) {
				actionHandler.openLibrariesRequested( p_libraryNames );
			}
			
			@Override
			public void librariesRefreshRequested( final Collection<String> p_libraryNames ) {
				actionHandler.librariesRefreshRequested( p_libraryNames );
			}
			
			@Override
			public void librariesDeleted( final Collection<String> p_libraryNames ) {
				actionHandler.librariesDeleted( p_libraryNames );
			}
			
			@Override
			public Collection<String> addLibrariesRequested( final Collection<String> p_currentLibraryNames ) {
				return actionHandler.addLibrariesRequested( getLanguageId(), p_currentLibraryNames );
			}
		} );
		
		final TabItem itemLibraries = new TabItem( tabFolder, SWT.NULL );
		itemLibraries.setText( ConstraintConsoleMessages.libraryTabTitle );
		itemLibraries.setControl( pnlLibraries );
	}
	
	public void displayData( 	final String p_constraintExpression,
								final String p_constraintEvalResult,
								final Collection<String> p_libraries ) {
		displayConstraint( p_constraintExpression, p_constraintEvalResult );
		setLibraries( p_libraries );
	}
	
	public void displayConstraint( 	final String p_constraintExpression,
									final String p_constraintEvalResult ) {
		pnlConsole.displayData( p_constraintExpression, p_constraintEvalResult );
	}
	
	public void setConstraintText( final String p_constraintExpression ) {
		pnlConsole.setConstraintText( p_constraintExpression );
	}

	public void setLibraries( final Collection<String> p_libraries ) {
		pnlLibraries.setLibraries( p_libraries );
	}
	
	public boolean displayCompletionProposals( final List<ICompletionProposal> p_proposals ) {
		return pnlConsole.displayCompletionProposals( p_proposals );
	}

	public void configure( 	final Set<String> p_definedLanguages,
							final String p_languageId ) {
		pnlConsole.configure( p_definedLanguages, p_languageId );
		
		pnlLibraries.setEnabled( pnlConsole.isLanguageFormal() );
	}

	public void configure( final String p_languageName ) {
		configure( null, p_languageName );
	}

		
	public String getConstraintText() {
		return pnlConsole.getConstraintText();
	}
	
	public String getLanguageId() {
		return pnlConsole.getLanguageId();
	}
	
	@Override
	public void setEnabled( final boolean pb_value ) {
		pnlConsole.setEnabled( pb_value );
		pnlLibraries.setEnabled( pb_value );
		
		super.setEnabled( pb_value );
	}

	public IConstraintPanelActionHandler getActionHandler() {
		return actionHandler;
	}
}
