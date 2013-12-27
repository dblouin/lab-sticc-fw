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
/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: OCLConfiguration.java,v 1.1 2007/04/30 12:39:31 cdamus Exp $
 */

package fr.labsticc.framework.constraints.view;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public abstract class ConstraintViewerConfiguration extends SourceViewerConfiguration implements IConstraintLanguageViewerConfig {
    
	private String languageName;
	private String languageId;
	private boolean languageFormal;

	private final RuleBasedScanner scanner;
    private final ConstraintCommentScanner commentScanner;
    private final ConstraintPartitionScanner partitionScanner;
    private final IPresentationReconciler presentationReconciler;

    protected ConstraintViewerConfiguration( final ConstraintColorManager p_colorManager ) {
    	scanner = createScanner( p_colorManager );
    	commentScanner = createCommentScanner( p_colorManager );
    	partitionScanner = createPartitionScanner();
    	presentationReconciler = createPresentationReconciler();
    	languageName = null;
    	languageId = null;
    }

    protected abstract RuleBasedScanner createScanner( ConstraintColorManager p_colorManager );

    protected ConstraintCommentScanner createCommentScanner( final ConstraintColorManager p_colorManager ) {
    	final ConstraintCommentScanner commentScanner = new ConstraintCommentScanner();
    	commentScanner.setDefaultReturnToken( new Token( new TextAttribute( p_colorManager.getColor( ConstraintColorManager.COMMENT ) ) ) );
        
        return commentScanner;
    }
    
    protected abstract ConstraintPartitionScanner createPartitionScanner();

    protected IPresentationReconciler createPresentationReconciler() {
        final PresentationReconciler reconciler = new PresentationReconciler();
        DefaultDamagerRepairer damagerRepairer = new DefaultDamagerRepairer( getScanner() );
        reconciler.setDamager( damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE );
        reconciler.setRepairer( damagerRepairer, IDocument.DEFAULT_CONTENT_TYPE );

        damagerRepairer = new DefaultDamagerRepairer( getCommentScanner() );
        final String commentContentType = getPartitionScanner().getCommentContentType();
        reconciler.setDamager( damagerRepairer, commentContentType );
        reconciler.setRepairer( damagerRepairer, commentContentType );

        return reconciler;
    }

    protected RuleBasedScanner getScanner() {
        return scanner;
    }

    protected ConstraintCommentScanner getCommentScanner() {
        return commentScanner;
    }

    public ConstraintPartitionScanner getPartitionScanner() {
        return partitionScanner;
    }

    @Override
    public IPresentationReconciler getPresentationReconciler( final ISourceViewer p_sourceViewer ) {
        return presentationReconciler;
    }

    @Override
    public String[] getConfiguredContentTypes( final ISourceViewer p_sourceViewer ) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, getPartitionScanner().getCommentContentType() };
    }

    @Override
    public IContentAssistant getContentAssistant( final ISourceViewer p_sourceViewer ) {
        final ContentAssistant result = new ContentAssistant();
        result.setContentAssistProcessor( 	new ConstraintCompletionProcessor(),
        									IDocument.DEFAULT_CONTENT_TYPE);
        result.enableAutoActivation( true );
        result.setShowEmptyList( true );
        //result.enablePrefixCompletion(true);
        
        return result;
    }
    
    @Override
    public void setLanguageName( final String p_languageName ) {
    	languageName = p_languageName;
    }
    
    @Override
    public String getLanguageName() {
    	return languageName;
    }
    
    @Override
    public void setLanguageId( final String p_languageId ) {
    	languageId = p_languageId;
    }
    
    @Override
    public String getLanguageId() {
    	return languageId;
    }

    @Override
	public boolean isLanguageFormal() {
		return languageFormal;
	}

    @Override
	public void setLanguageFormal(boolean languageFormal) {
		this.languageFormal = languageFormal;
	}
}
