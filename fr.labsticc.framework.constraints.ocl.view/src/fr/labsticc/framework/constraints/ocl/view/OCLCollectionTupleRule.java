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

package fr.labsticc.framework.constraints.ocl.view;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import fr.labsticc.framework.constraints.view.ConstraintColorManager;


/**
 * A rule that matches the collection and tuple literal type identifiers and
 * braces.
 * 
 */
class OCLCollectionTupleRule extends WordRule {

    private static final String[] KEYWORDS = {
        "Set", //$NON-NLS-1$
        "OrderedSet", //$NON-NLS-1$
        "Bag", //$NON-NLS-1$
        "Sequence", //$NON-NLS-1$
        "Tuple", //$NON-NLS-1$
    };

    private static final String[] BRACES = {
        "{", //$NON-NLS-1$
        "}", //$NON-NLS-1$
        "}}", //$NON-NLS-1$
        "}}}", //$NON-NLS-1$
    };
    
    OCLCollectionTupleRule(	final ConstraintColorManager p_colorManager,
    						final boolean pb_braces ) {
        super( new CollectionTupleDetector( pb_braces ) );
        
        final IToken token = new Token( new TextAttribute( p_colorManager.getColor( ConstraintColorManager.COLLECTION_TUPLE ), null, SWT.BOLD ) );
        
        if ( pb_braces ) {
            for ( final String word : BRACES ) {
                addWord(word, token);
            }
        } 
        else {
            for ( final String word : KEYWORDS ) {
                addWord(word, token);
            }
        }
    }
    
    private static class CollectionTupleDetector implements IWordDetector {
        private final boolean isBraces;
        
        CollectionTupleDetector(boolean isBraces) {
            this.isBraces = isBraces;
        }
        
    	@Override
        public boolean isWordPart( final char p_char ) {
            return isBraces ? ( p_char == '{' ) || ( p_char == '}' ) : ( p_char >= 'a' && p_char <= 'z' ) || ( p_char >= 'A' && p_char <= 'Z' );
        }

    	@Override
    	public boolean isWordStart( final char p_char ) {
            return isWordPart( p_char );
        }
    }
}
