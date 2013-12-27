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
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

/**
 * Rule to match Lute identifiers.
 */
class LuteIdentifierRule extends WordRule {
    
    LuteIdentifierRule() {
        super( new LuteKeywordDetector(), new Token( new TextAttribute( null ) ) );
    }
    
    private static class LuteKeywordDetector implements IWordDetector {
    
    	@Override
        public boolean isWordPart( final char p_char ) {
            return isWordStart( p_char ) || p_char == '$' || (p_char >= '0' && p_char <= '9');
        }
    
    	@Override
        public boolean isWordStart( final char p_char ) {
            return p_char == '_' || (p_char >= 'a' && p_char <= 'z') || (p_char >= 'A' && p_char <= 'Z');
        }
    }
}
