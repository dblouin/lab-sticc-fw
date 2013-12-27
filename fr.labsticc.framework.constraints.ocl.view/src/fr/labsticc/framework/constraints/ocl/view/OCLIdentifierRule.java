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
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;


/**
 * Rule to match OCL identifiers.
 */
class OCLIdentifierRule extends WordRule {
    
    OCLIdentifierRule() {
        super(new OCLKeywordDetector(), new Token(new TextAttribute(null)));
    }
    
    private static class OCLKeywordDetector implements IWordDetector {
    
    	@Override
        public boolean isWordPart(char c) {
            return isWordStart(c) || c == '$' || (c >= '0' && c <= '9');
        }
    
    	@Override
        public boolean isWordStart(char c) {
            return c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
        }
    }
}
