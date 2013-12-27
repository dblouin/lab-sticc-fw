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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import fr.labsticc.framework.constraints.view.ConstraintPartitionScanner;

/**
 * A rule-based partition scanner for OCL text.
 */
class OCLPartitionScanner extends ConstraintPartitionScanner {

    public static final String COMMENT = "__ocl_comment"; //$NON-NLS-1$
    
    OCLPartitionScanner() {
        super();

        final List<IRule> rules = new ArrayList<IRule>();
        
        final IToken commentToken = new Token( COMMENT );
        
        // rule for single-line comments
        rules.add(new EndOfLineRule( "--", commentToken ) ); //$NON-NLS-1$

        // rule for paragraph comments
        rules.add(new MultiLineRule("/*", "*/", commentToken)); //$NON-NLS-1$ //$NON-NLS-2$
        
        setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
    }

	@Override
	public String getCommentContentType() {
		return COMMENT;
	}
}
