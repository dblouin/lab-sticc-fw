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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.text.AbstractDocument;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.view.i18n.ConstraintConsoleMessages;
import fr.labsticc.framework.core.exception.IExceptionHandler;
import fr.labsticc.framework.core.util.DefaultComparator;

public class ConstraintConsolePanel extends BasicPanel {
	
	private final ConstraintColorManager colorManager;
	private final Color clrOutputDefault;
	private final Color clrOutputResults;
	private SashForm sashForm;
	
	private final Map<String, IConstraintLanguageViewerConfig> viewerConfigurations;

	private ToolItemCombo cmbLanguages;
	private ToolItem tbiClearEvalResults;

	private TextViewer vewResults;
	private ConstraintSourceViewer vewSource;
	private final SourceTextChangeListener sourceTextChangeListener;
	
	private final IConstraintConsolePanelActionHandler actionHandler;
	private final IExceptionHandler exceptionHandler;
	//private ModelingLevel modelingLevel;
	
	private boolean languageFormal;
	
	private final List<String> history;
	private int currentHistoryPointer;
	private String lastOCLExpression;

	public ConstraintConsolePanel( 	final Composite p_parent,
									final int pi_style,
									final IConstraintConsolePanelActionHandler p_actionHandler,
									final IConstraintViewersConfiguration p_constraintViewsConfig,
									final IExceptionHandler p_exceptionHandler ) {
		super( p_parent, pi_style );
		colorManager = new ConstraintColorManager();
		clrOutputDefault = colorManager.getColor( ConstraintColorManager.DEFAULT );
		clrOutputResults = colorManager.getColor( ConstraintColorManager.OUTPUT_RESULTS );
		
		viewerConfigurations = p_constraintViewsConfig.viewerConfigurations();
		actionHandler = p_actionHandler;
		exceptionHandler = p_exceptionHandler;
		//modelingLevel = ModelingLevel.M2;
		languageFormal = true;
		
		sourceTextChangeListener = new SourceTextChangeListener();
		
		history = new ArrayList<String>();
		currentHistoryPointer = 0;
		lastOCLExpression = null;
		
		createControls();
	}
	
	private String languageId( final String p_languageName ) {
		if ( p_languageName != null ) {
			for ( final Map.Entry<String, IConstraintLanguageViewerConfig> configEntry : viewerConfigurations.entrySet() )  {
				if ( p_languageName.equals( configEntry.getValue().getLanguageName() ) ) {
					return configEntry.getKey();
				}
			}
		}
		
		return null;
	}
	
	protected void createControls() {
		setLayout( new GridLayout() );

		tbrActions = new ToolBar( this, SWT.FLAT | SWT.WRAP | SWT.RIGHT );

		cmbLanguages = new ToolItemCombo( tbrActions, SWT.NULL );
		cmbLanguages.setImage( createImage( ConstraintConsoleMessages.evalAction_icon ) );
		cmbLanguages.setNoSelectionText( ConstraintConsoleMessages.langSelectNoSelect );
		cmbLanguages.setToolTipText( ConstraintConsoleMessages.evalAction_tip );
		
		cmbLanguages.addPushListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				sourceTextChangeListener.processConstraintTextChanged();
				final String result = actionHandler.constraintEvaluationRequested( getLanguageId(), getConstraintExpression()/*, getModelingLevel()*/ ); 
				
		    	if ( result != null ) {
					if ( history.size() > 0 && history.get( 0 ).trim().length() == 0 ) {
						history.remove( 0 );
					}
					
					if ( 	lastOCLExpression != null && 
							( history.size() == 0 || !history.get( 0 ).equals( lastOCLExpression.trim() ) ) ) {
						history.add( 0, lastOCLExpression.trim() );
					}
					
					currentHistoryPointer = 0;
					
					displayData( getConstraintExpression(), result );
				}					
			}
		} );
		
		cmbLanguages.addSelectionListener( new SelectionAdapter() {
			@Override
			public void widgetSelected( final SelectionEvent p_event ) {
				// Properly configures the constraint editor for the selected language.
				final String languageName = ( (Item) p_event.widget ).getText();
				final String languageId = languageId( languageName );
				actionHandler.constraintLanguageChanged( languageId );
				configure( languageId );
			}
		} );
		cmbLanguages.setPushActionEnabled( false );

		tbiClearEvalResults = createToolBarItem( 	ConstraintConsoleMessages.clearResultsAction_icon, 
													ConstraintConsoleMessages.clearResultsAction_tip,
													SWT.PUSH,
													new SelectionAdapter() {
			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				vewResults.getDocument().set( null );
			}
		} );

		createToolBarItem( 	ConstraintConsoleMessages.delete_expressionAction_icon, 
							ConstraintConsoleMessages.delete_expressionAction_tip,
							SWT.PUSH,
							new SelectionAdapter() {

			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				vewSource.getDocument().set( null );
			}
		} );

		createToolBarItem( 	ConstraintConsoleMessages.loadAction_icon, 
							ConstraintConsoleMessages.loadAction_tip,
							SWT.PUSH,
							new SelectionAdapter() {

			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				final String loadedCstr = actionHandler.constraintLoadingRequested( getLanguageId() );
				
				// Do not erase if the action was canceled.
				if ( loadedCstr != null ) {
					displayData( loadedCstr, null );
				}
			}
		} );

		createToolBarItem( 	ConstraintConsoleMessages.saveAction_icon, 
							ConstraintConsoleMessages.saveAction_tip,
							SWT.PUSH,
							new SelectionAdapter() {

			@Override
			public void widgetSelected( final SelectionEvent p_evt ) {
				actionHandler.constraintSavingRequested( getLanguageId(), getConstraintExpression() );
			}
		} );

		sashForm = new SashForm( this, SWT.VERTICAL | SWT.LEFT_TO_RIGHT );
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		data.grabExcessVerticalSpace = true;
		data.verticalAlignment = SWT.FILL;
	    sashForm.setLayoutData( data );
		sashForm.setLayout( new GridLayout() );
		vewResults = new TextViewer( sashForm, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL );
		vewResults.getTextWidget().setLayoutData( new GridData( GridData.FILL_BOTH ) );
		vewResults.setEditable( false );
		vewResults.setDocument( new Document() );
		
		vewSource = new ConstraintSourceViewer( sashForm, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL );
		vewSource.getTextWidget().addKeyListener( new InputKeyListener() );
		
		data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		data.grabExcessVerticalSpace = true;
		data.verticalAlignment = SWT.FILL;
		vewSource.getControl().setLayoutData( data );

		// Populate the language choice combo and set the first defined language as default.
		if ( !viewerConfigurations.isEmpty() ) {
			final Set<String> languageIds = new HashSet<String>();
			
			for ( final IConstraintLanguageViewerConfig config : viewerConfigurations.values() ) {
				languageIds.add( config.getLanguageId() );
			}
			
			configure( languageIds, null );
		}
	}
	
	private class SourceTextChangeListener implements FocusListener {
		private final DefaultComparator comparator = DefaultComparator.getInstance();
		private String savedText = null;
		
		@Override
		public void focusGained(FocusEvent e) {
			savedText = vewSource.getDocument().get();
		}
	
		@Override
		public void focusLost( final FocusEvent p_evt ) {
			processConstraintTextChanged();
		}
		
		public void processConstraintTextChanged() {
			if ( comparator.compare( savedText, vewSource.getDocument().get() ) != 0 ) {
				actionHandler.constraintTextChanged( getLanguageId(), getConstraintExpression() );
			}
		}
	}
	
	public void configure( 	final Set<String> p_definedLanguages,
							final String p_languageId ) {
		if ( p_definedLanguages != null ) {
			assert !p_definedLanguages.isEmpty() : "No defined languages.";
			
			final Collection<String> languageNames = new ArrayList<String>();
			
			for ( final String languageId : p_definedLanguages ) {
				final IConstraintLanguageViewerConfig config = viewerConfigurations.get( languageId ); 
				
				assert config != null : "No viewer configuration for language " + languageId;
				
				languageNames.add( config.getLanguageName() );
			}
			
			cmbLanguages.setItems( languageNames.toArray( new String[ languageNames.size() ] ) );
		}
		
		configure( p_languageId );
	}

	public void configure( final String p_languageId ) {
		vewSource.unconfigure();

		final IConstraintLanguageViewerConfig config = viewerConfigurations.get( p_languageId );
		
		if ( config == null ) {
			cmbLanguages.select( null );
			vewResults.getTextWidget().setVisible( false );
			vewSource.setDocument( new Document() );
			sashForm.setWeights( new int[] { 0, 1 } );
			languageFormal = false;
		}
		else {
			cmbLanguages.select( config.getLanguageName() );
			languageFormal = config.isLanguageFormal();
			
			if ( isLanguageFormal() ) {
				vewResults.getTextWidget().setVisible( true );
				vewResults.getDocument().set( null );
				sashForm.setWeights( new int[] { 2, 1 } );
		
				if ( config instanceof ConstraintViewerConfiguration ) {
					ConstraintViewerConfiguration srcConfig = (ConstraintViewerConfiguration) config;
					vewSource.configure( srcConfig );
					vewSource.setDocument( new ConstraintDocument( srcConfig.getPartitionScanner() ) );
				}
			}
			else {
				vewResults.getTextWidget().setVisible( false );
				vewSource.setDocument( new Document() );
				sashForm.setWeights( new int[] { 0, 1 } );
			}
		}

		manageToolbar( isLanguageFormal() );
		vewSource.getDocument().addDocumentListener( new IDocumentListener() {
			
			private String previousExp = null;

			@Override
			public void documentAboutToBeChanged( final DocumentEvent p_event ) {
	    		previousExp = getConstraintExpression();
			}

			@Override
			public void documentChanged( final DocumentEvent p_event ) {
    			final String newCstExpr = getConstraintExpression();
    			
	    		if ( DefaultComparator.getInstance().compare( previousExp, newCstExpr ) != 0 ) {
	    			actionHandler.constraintTextChanged( getLanguageId(), newCstExpr );
	    		}
			}
		} );
	}

	private void manageToolbar( final boolean pb_formalLanguage ) {
		cmbLanguages.setPushActionEnabled( pb_formalLanguage );
		tbiClearEvalResults.setEnabled( pb_formalLanguage );
	}
	
	public String getConstraintText() {
		return vewSource.getDocument().get();
	}
	
	/**
	 * Appends the specified text to the output viewer.
	 * 
	 * @param text the text to append
	 * @param color the color to print the text with
	 * @param bold whether to print the text bold
	 */
	private void append( 	String p_text, 
							final Color p_color, 
							final boolean pb_bold ) 
	throws BadLocationException {
		final IDocument doc = vewResults.getDocument();
		int offset = doc.getLength();
		int length = p_text.length();
		
		p_text = p_text + System.getProperty( "line.separator" );
		
		if ( offset > 0 ) {
			doc.replace( offset, 0, p_text );
		} 
		else {
			doc.set( p_text );
		}
		
		final StyleRange style = new StyleRange();
		style.start = offset;
		style.length = length;
		style.foreground = p_color;
		
		if ( pb_bold ) {
			style.fontStyle = SWT.BOLD;
		}
		
		vewResults.getTextWidget().setStyleRange( style );
	}
	
	/**
	 * Ensures that the last text printed to the output viewer is shown.
	 */
	private void scrollText() {
		vewResults.revealRange( vewResults.getDocument().getLength(), 0 );
	}
	
	private void displayEvaluationResult( 	final String p_constraintExpression,
											final String p_constraintEvalResult ) {
		final IDocument doc = vewResults.getDocument();
		
		try {
			if ( doc.getLength() > 0 ) {
				// separate previous output by a blank line
				append( "", clrOutputDefault, false ); //$NON-NLS-1$
			}
			
			append( ConstraintConsoleMessages.evaluating, clrOutputDefault, true );
			append( p_constraintExpression, clrOutputDefault, false );
			append( ConstraintConsoleMessages.results, clrOutputDefault, true );
	        
	       // switch ( getModelingLevel() ) {
	         //   case M2:
	        append( p_constraintEvalResult, clrOutputResults, false );
	           //     break;
//	            case M1:
//	                // just report a successful parse
//	            	append( /*"Successfully parsed."*/ConstraintConsoleMessages.parsed, clrOutputResults, false );
//	                break;
//	        }
	        
	        scrollText();

	        // store the successfully parsed expression
			lastOCLExpression = p_constraintExpression;
		}
		catch( final BadLocationException p_ex ) {
			handleException( p_ex );
		}
	}
	
	public void displayData( 	final String p_constraintExpression,
								final String p_constraintEvalResult ) {
		setConstraintText( p_constraintExpression );

		if ( p_constraintEvalResult != null ) {
			displayEvaluationResult( p_constraintExpression, p_constraintEvalResult );
		}
	}
	
	public void setConstraintText( final String p_constraintExpression ) {
		final AbstractDocument document = (AbstractDocument) vewSource.getDocument();
		document.stopListenerNotification();
		vewSource.getDocument().set( p_constraintExpression );
		document.resumeListenerNotification();
	}
	
	@Override
	public void setEnabled( final boolean pb_value ) {
		
		// To grey the buttons
		tbrActions.setEnabled( pb_value );
		
		super.setEnabled( pb_value );
	}

	public boolean displayCompletionProposals( final List<ICompletionProposal> p_proposals ) {
		final String result = vewSource.showCompletionProposals( p_proposals );
		
		return result == null;
	}

	private void handleException( final Throwable p_th ) {
		exceptionHandler.handleException( p_th, null );
	}
		
	/**
	 * A key listener that listens for the Enter key to evaluate the OCL
	 * expression.
	 */
	private class InputKeyListener implements KeyListener {

		public InputKeyListener() {
		}
		
		@Override
		public void keyPressed( final KeyEvent p_evt ) {
			switch ( p_evt.keyCode ) {
				case SWT.PAGE_UP :
					if ( !vewSource.isContentAssistActive() && ( p_evt.stateMask & ( SWT.CTRL | SWT.SHIFT ) ) == 0 ) {					
						// history
						if ( currentHistoryPointer == 0 && history.size() > 0 ) {
							if (history.size() > 0 && history.get(0).length() == 0) {
								history.remove(0);
							}
							
							history.add( 0, vewSource.getDocument().get().trim() );
							currentHistoryPointer = 1;
							vewSource.getDocument().set(history.get(currentHistoryPointer));
						}
						else if ( currentHistoryPointer < history.size() - 1 ) {
							currentHistoryPointer++;
							vewSource.getDocument().set( history.get( currentHistoryPointer ) );
						}
					}				
					break;
				case SWT.PAGE_DOWN :
					if ( !vewSource.isContentAssistActive()	&& ( p_evt.stateMask & ( SWT.CTRL | SWT.SHIFT ) ) == 0 ) {					
						// history
						if ( currentHistoryPointer > 0 ) {
							currentHistoryPointer--;
							vewSource.getDocument().set( history.get( currentHistoryPointer ) );
						}
					}				
					break;
				}
		}
		
		@Override
		public void keyReleased( final KeyEvent p_evt ) {
			switch ( p_evt.keyCode ) {
				case ' ':
				    if ( ( p_evt.stateMask & SWT.CTRL ) == SWT.CTRL ) {
				    	try {
				    		displayCompletionProposals( actionHandler.completionProposalRequested( 	getLanguageId(),
												    												getConstraintExpressionToCursor() ) );
				    	}
				    	catch ( final BadLocationException p_ex ) {
				    		exceptionHandler.handleException( p_ex , null );
				    	}
				    }
				    
				    break;
			}
		}
	}
	
	private String getConstraintExpressionToCursor() 
	throws BadLocationException {
		final IDocument doc = vewSource.getDocument();
		return doc.get( 0, vewSource.getSelectedRange().x );
	}
	
	private String getConstraintExpression() {
		return vewSource.getDocument().get();
	}
//	
//	public ModelingLevel getModelingLevel() {
//		return modelingLevel;
//	}
//
//	public void setModelingLevel( final ModelingLevel p_modelingLevel ) {
//		modelingLevel = p_modelingLevel;
//	}

	@Override
	public void dispose() {
		colorManager.dispose();
		cmbLanguages.dispose();
		
		super.dispose();
	}
	
	public String getLanguageId() {
		return languageId( cmbLanguages.getText() );
	}
	
	public boolean isLanguageFormal() {
		return languageFormal;
	}
}
