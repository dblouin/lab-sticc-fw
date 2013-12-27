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
package fr.labsticc.framework.constraints.lute.view;

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
class LuteSetRule extends WordRule {

    private static final String[] BRACES = {
        "{", //$NON-NLS-1$
        "}", //$NON-NLS-1$
        "}}", //$NON-NLS-1$
        "}}}", //$NON-NLS-1$
    };
    
    LuteSetRule( final ConstraintColorManager p_colorManager ) {
        super( new SetDetector() );
        
        final IToken token = new Token( new TextAttribute( p_colorManager.getColor( ConstraintColorManager.COLLECTION_TUPLE ), null, SWT.BOLD ) );
        
        for ( final String word : BRACES ) {
            addWord( word, token );
        }
    }
    
    private static class SetDetector implements IWordDetector {

    	SetDetector() {
        }
        
    	@Override
        public boolean isWordPart( final char p_char ) {
            return p_char == '{' || p_char == '}';
        }

    	@Override
        public boolean isWordStart( final char p_char ) {
            return isWordPart( p_char );
        }
    }
}
