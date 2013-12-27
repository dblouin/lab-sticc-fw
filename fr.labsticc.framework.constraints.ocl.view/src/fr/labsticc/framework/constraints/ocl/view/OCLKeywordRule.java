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
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;


/**
 * Rule to match OCL keywords and pseudo-keywords (that aren't actually
 * reserved by the specification).
 * 
 */
class OCLKeywordRule extends WordRule {

    private static final String[] KEYWORDS = {
        "package", //$NON-NLS-1$
        "endpackage", //$NON-NLS-1$
        "context", //$NON-NLS-1$
        "inv", //$NON-NLS-1$
        "pre", //$NON-NLS-1$
        "post", //$NON-NLS-1$
        "body", //$NON-NLS-1$
        "derive", //$NON-NLS-1$
        "init", //$NON-NLS-1$
        "def", //$NON-NLS-1$
        
        "@pre", //$NON-NLS-1$
        
        "self", //$NON-NLS-1$
        "result", //$NON-NLS-1$
        
        "true", //$NON-NLS-1$
        "false", //$NON-NLS-1$
        "null", //$NON-NLS-1$
        "OclInvalid", //$NON-NLS-1$
        
        "and", //$NON-NLS-1$
        "or", //$NON-NLS-1$
        "xor", //$NON-NLS-1$
        "implies", //$NON-NLS-1$
        "not", //$NON-NLS-1$
        
        "let", //$NON-NLS-1$
        "in", //$NON-NLS-1$
        "if", //$NON-NLS-1$
        "then", //$NON-NLS-1$
        "else", //$NON-NLS-1$
        "endif", //$NON-NLS-1$
        
        "attr", //$NON-NLS-1$
        "oper", //$NON-NLS-1$
    };
    
    OCLKeywordRule() {
        super(new OCLKeywordDetector());
        
        final Color keywordForeground = new Color( Display.getDefault(), 127, 0, 85 );
        final IToken token = new Token( new TextAttribute( keywordForeground, null, SWT.BOLD ) );
        
        for ( final String word : KEYWORDS) {
            addWord(word, token);
        }
    }
    
    private static class OCLKeywordDetector implements IWordDetector {
    
    	@Override
        public boolean isWordPart(char c) {
            return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }
    
    	@Override
        public boolean isWordStart(char c) {
            return c == '@' || isWordPart(c);
        }
    }
}
