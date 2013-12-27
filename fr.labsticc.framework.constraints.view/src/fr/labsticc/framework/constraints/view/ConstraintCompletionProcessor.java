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
 * $Id: OCLCompletionProcessor.java,v 1.1 2007/04/30 12:39:31 cdamus Exp $
 */

package fr.labsticc.framework.constraints.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;


public class ConstraintCompletionProcessor implements IContentAssistProcessor {

    private static char[] NO_CHARS = {};
    private static IContextInformation[] NO_CONTEXTS = {};
    private static ICompletionProposal[] NO_COMPLETIONS = {};
    
    private static char[] ACTIVATION = {
        '.',
        ':' /* :: */,
        '>' /* -> */,
        '^'};
    
    private final List<fr.labsticc.framework.constraints.core.ICompletionProposal> proposals;
    
    public ConstraintCompletionProcessor() {
    	proposals = new ArrayList<fr.labsticc.framework.constraints.core.ICompletionProposal>();
    }
    
    public void setCompletionProposals( final List<fr.labsticc.framework.constraints.core.ICompletionProposal> p_proposals ) {
    	proposals.clear();
    	proposals.addAll( p_proposals );
    }
    
    public boolean canComputeProposals( final ConstraintDocument p_document,
										final int pi_offset ) 
    throws BadLocationException {
        if ( pi_offset > 0 && p_document.getChar( pi_offset - 1 ) == '>' ) {
            // check that this is an arrow
            
        	if ( pi_offset < 2 || p_document.getChar( pi_offset - 2 ) != '-' ) {
                return false;
            }
        }
        
        if ( pi_offset > 0 && p_document.getChar( pi_offset - 1 ) == ':' ) {
            // check that this is a double-colon
            if ( pi_offset < 2 || p_document.getChar( pi_offset - 2 ) != ':' ) {
                return false;
            }
        }
        
        return true;
    }
   
     @Override
    public ICompletionProposal[] computeCompletionProposals(	final ITextViewer p_viewer,
            													final int pi_offset ) {
        
        ConstraintDocument doc = (ConstraintDocument) p_viewer.getDocument();
        
        int replacementOffset = pi_offset;
        
        try {
        	if ( !canComputeProposals( doc, pi_offset ) ) {
        		return NO_COMPLETIONS;
        	}
//            if ( pi_offset > 0 && doc.getChar( pi_offset - 1 ) == '>' ) {
//                // check that this is an arrow
//                
//            	if ( pi_offset < 2 || doc.getChar( pi_offset - 2 ) != '-' ) {
//                    return NO_COMPLETIONS;
//                }
//            }
//            
//            if ( pi_offset > 0 && doc.getChar( pi_offset - 1 ) == ':' ) {
//                // check that this is a double-colon
//                if ( pi_offset < 2 || doc.getChar( pi_offset - 2 ) != ':' ) {
//                    return NO_COMPLETIONS;
//                }
//            }
            
            loop: while (replacementOffset > 0) {
                char trigger = doc.getChar(--replacementOffset);
                switch (trigger) {
                    case '.':
                    case '>':
                    case ':':
                    case '^':
                    case ' ':
                    case '\t':
                    case '\n':
                    case '\r':
                    case '(':
                    case '[':
                    case '{':
                        replacementOffset++;
                        break loop;
                }
            }
            
            return createCompletions( replacementOffset, pi_offset - replacementOffset );
        } 
        catch ( BadLocationException e ) {
            // just don't return any proposal
            return NO_COMPLETIONS;
        }
    }

    public char[] getCompletionProposalAutoActivationCharacters() {
        return ACTIVATION;
    }

    public IContextInformation[] computeContextInformation(ITextViewer viewer,
            int offset) {
        return NO_CONTEXTS;
    }

    public char[] getContextInformationAutoActivationCharacters() {
        return NO_CHARS;
    }

    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

    public String getErrorMessage() {
        return null;
    }
    
    private ICompletionProposal[] createCompletions( 	final int pi_replacementOffset, 
    													final int pi_replacementLength ) {
        
        final List<ICompletionProposal> result = new ArrayList<ICompletionProposal>();
        
        for ( fr.labsticc.framework.constraints.core.ICompletionProposal proposal : proposals ) {
            String text = proposal.getName();
            String display;
            int cursor;
            
            switch ( proposal.getCompletionKind() ) {
                case OPERATION:
                case SIGNAL:
                    // the description is already complete
                    text = text + "()"; //$NON-NLS-1$
                    display = proposal.getDescription();
                    cursor = text.length() - 1;  // between the ()
                    break;
                case PROPERTY:
                case ENUMERATION_LITERAL:
                case VARIABLE:
                case ASSOCIATION_CLASS:
                    display = text + " : " + proposal.getDescription(); //$NON-NLS-1$
                    cursor = text.length();
                    break;
                default:
                    display = text;
                    cursor = text.length();
                    break;
            }
            
            result.add(	new CompletionProposal( text,
								                pi_replacementOffset,
								                pi_replacementLength,
								                cursor,
								                null,
								                display,
								                null,
								                null ) );
        }
        
        return result.toArray(new ICompletionProposal[result.size()]);
    }
}
