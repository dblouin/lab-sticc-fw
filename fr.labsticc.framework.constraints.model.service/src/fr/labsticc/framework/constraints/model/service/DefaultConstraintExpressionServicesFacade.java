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
package fr.labsticc.framework.constraints.model.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;
import fr.labsticc.framework.constraints.service.DefaultConstraintServicesFacade;
import fr.labsticc.framework.constraints.service.IConstraintInterpretationAS;
import fr.labsticc.framework.constraints.service.IConstraintPersistenceAS;
import fr.labsticc.framework.core.exception.SystemException;

public class DefaultConstraintExpressionServicesFacade extends DefaultConstraintServicesFacade implements IConstraintExpressionServicesFacade {

	public DefaultConstraintExpressionServicesFacade( 	final Map<String, IConstraintInterpretationAS> p_interpreterServices,
														final Map<String, IConstraintPersistenceAS> p_persistenceServices ) {
		super( p_interpreterServices, p_persistenceServices );
	}

	@Override
	public Object evaluateExpression( 	final FormalLanguageExpression p_expression,
										final Object p_context ) 
	throws SystemException, ConstraintException {
		@SuppressWarnings("unchecked")
		final List<Object> result = ( List<Object>) evaluateExpression( p_expression, new Object[]{ p_context } );
		
		return result.isEmpty() ? null : result.get( 0 );
	}

	/**
	 * @param p_constraintExpr
	 * @return
	 */
	protected Map<String, String> libraries( final FormalLanguageExpression p_constraintExpr ) {
		final Map<String, String> libraries = new LinkedHashMap<String, String>();
		
		for ( final FormalLanguageLibrary library : p_constraintExpr.getUsedLibraries() ) {
			final FormalLanguageExpression expression = library.getExpression();
			
			if ( expression != null ) {
				libraries.put( library.getId(), expression.getText() );
			}
		}
		
		return libraries;
	}

	@Override
	public Object evaluateExpression( 	final FormalLanguageExpression p_expression,
										final Object[] p_context )
	throws SystemException,	ConstraintException {
		return evaluateConstraint( 	p_expression.getLanguage().getId(),
									p_expression.getText(),
									libraries( p_expression ),
									//ModelingLevel.M2,
									false,
									p_context );
	}
}
