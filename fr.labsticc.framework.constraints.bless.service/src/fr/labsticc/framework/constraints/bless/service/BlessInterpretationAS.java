/*******************************************************************************
 * Copyright (c) 2012 Lab-STICC Universite de Bretagne Sud, Lorient.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the CeCILL-B license available
 * at :
 * en : http://www.cecill.info/licences/Licence_CeCILL-B_V1-en.html
 * fr : http://www.cecill.info/licences/Licence_CeCILL-B_V1-fr.html
 * 
 * Contributors:
 * Dominique BLOUIN (Lab-STICC UBS), dominique.blouin@univ-ubs.fr
 ******************************************************************************/
package fr.labsticc.framework.constraints.bless.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.core.exception.ConstraintEvaluationException;
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.core.exception.ConstraintParsingException;
import fr.labsticc.framework.constraints.service.AbstractConstraintInterpretationAS;
import fr.labsticc.framework.core.exception.SystemException;

public class BlessInterpretationAS extends AbstractConstraintInterpretationAS {

	private static final Logger logger = LoggerFactory.getLogger( BlessInterpretationAS.class );

	private static final String INVALID = "BlessInvalid";
	
	private final List<Object> notImplResults;
	
	public BlessInterpretationAS() {
		super( Pattern.compile( "Result: (.+)" ), INVALID );
		
		notImplResults = new ArrayList<Object>();
	}

	@Override
	public List<? extends Object> evaluateConstraint( 	final Object p_constraintExpression,
														final Map<String, String> p_libraries,
														final boolean pb_resultsAsString,
														final Object... p_context ) 
	throws ConstraintException, SystemException {
		final Object result;
		
		if ( pb_resultsAsString ) {
			result = Boolean.FALSE.toString();
		}
		else {
			result = Boolean.FALSE;
		}
		
		notImplResults.clear();
		
		for ( final Object constextElem : p_context ) {
			notImplResults.add( result );
		}
		
		return notImplResults;
	}

	@Override
	public List<ICompletionProposal> computeCompletionProposals(	final Object p_constraintExpression, 
																	final Map<String, String> p_libraries,
																	final Object... p_context )
	throws ConstraintEvaluationException, SystemException {
		return Collections.emptyList();
	}

	@Override
	public String parse( final Object p_constraint ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object parse( final String p_expressionStr ) 
	throws ConstraintParsingException, SystemException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void libraryContentChanged( final String p_libraryId ) {
	}
}