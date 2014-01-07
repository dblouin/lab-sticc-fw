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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.osate.analysis.lute.LuteConstants;


/**
 * Rule to match Lute keywords.
 */
class LuteKeywordRule extends WordRule {
    
	LuteKeywordRule() {
        super( new LuteKeywordDetector() );
        
        final Color keywordForeground = new Color( Display.getDefault(), 127, 0, 85 );
        final IToken token = new Token( new TextAttribute( keywordForeground, null, SWT.BOLD ) );
        
        for ( final String word : LuteConstants.LUTE_KEYWORDS ) {
            addWord( word, token );
        }
    }
    
    private static class LuteKeywordDetector implements IWordDetector {
    
    	@Override
        public boolean isWordPart( final char p_char ) {
            return p_char == '_' || (p_char >= 'a' && p_char <= 'z') || (p_char >= 'A' && p_char <= 'Z');
        }
    
    	@Override
        public boolean isWordStart(char c) {
            return isWordPart(c);
        }
    }
}
