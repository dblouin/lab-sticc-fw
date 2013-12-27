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
 * $Id: OCLSourceViewer.java,v 1.1 2007/04/30 12:39:31 cdamus Exp $
 */
package fr.labsticc.framework.constraints.view;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistEvent;
import org.eclipse.jface.text.contentassist.ICompletionListener;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistantExtension2;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.widgets.Composite;

/**
 * Input field implementation for the OCL Console.
 */
public class ConstraintSourceViewer extends SourceViewer {

    private long contentAssistLastActive;
    
    public ConstraintSourceViewer(	final Composite p_parent, 
    								final int pi_styles ) {
        super( p_parent, null, pi_styles );
    }

	/*
	 * @see ISourceViewer#configure(SourceViewerConfiguration)
	 */
    @Override
	public void configure( final SourceViewerConfiguration p_configuration ) {
        super.configure( p_configuration );
        
        (( IContentAssistantExtension2) fContentAssistant ).addCompletionListener( new ICompletionListener() {
            
        	@Override
        	public void assistSessionEnded(ContentAssistEvent event) {
                contentAssistLastActive = System.currentTimeMillis();
            }
            
        	@Override
            public void assistSessionStarted(ContentAssistEvent event) {
            }
        
        	@Override
            public void selectionChanged( 	final ICompletionProposal proposal,
            								boolean smartToggle ) {
            } } );
    }
   
    /**
     * Queries whether the content-assist popup is currently active.  Actually,
     * this method employs a heuristic, answering <code>true</code> for 500
     * milliseconds after the content-assist is dismissed.
     * 
     * @return whether content-assist is active
     */
    public boolean isContentAssistActive() {
        return System.currentTimeMillis() - contentAssistLastActive < 500L;
    }
    
    public String showCompletionProposals( final List<fr.labsticc.framework.constraints.core.ICompletionProposal> p_proposals ) {
    	getCompletionProcessor().setCompletionProposals( p_proposals );
    	return fContentAssistant.showPossibleCompletions();
    }
    
    private ConstraintCompletionProcessor getCompletionProcessor() {
    	return (ConstraintCompletionProcessor) fContentAssistant.getContentAssistProcessor( IDocument.DEFAULT_CONTENT_TYPE );
    }
}
