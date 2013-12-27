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
package fr.labsticc.framework.constraints.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.core.exception.ConstraintEvaluationException;
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.core.exception.ConstraintParsingException;
import fr.labsticc.framework.core.exception.SystemException;
import fr.labsticc.framework.settings.model.settings.ScopeProvider;

/**
 * Interface defining a service for the interpretation of constraints on a given model context.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface IConstraintInterpretationAS {

	/**
	 * Evaluates this constraint object (typically a string)
	 * @param p_constraintExpression The constraint expression object (typically a string representing the constraint expression).
	 * @param p_libraries A map of key value pairs representing the a string id of a library and its associated code.
	 * @param p_modelingLevel The level at which the constraint is to be evaluated (M1 (model) or M2 (meta-model)).
	 * @param pb_resultsAsString A boolean indicating if the result of the constraint evaluation is to be converted 
	 * to string.
	 * @param p_context The context of the constraints. This is typically the element that must satisfy the constraint.
	 * @return The result of evaluation.
	 * @throws ConstraintEvaluationException Should be thrown whenever a functional exception such as syntactic errors in 
	 * the constraint text be evaluated occur. Detailed error should be added to the exception.
	 * constraint text for example.
	 * @throws SystemException Should be thrown whenever some system error occurs.
	 */
	Object evaluateConstraint( 	Object p_constraintExpression,
								Map<String, String> p_libraries,
						//		ModelingLevel p_modelingLevel,
								boolean pb_resultsAsString,
								Object... p_context ) 
	throws ConstraintException, SystemException;

	/**
	 * Same as {@link evaluateConstraint} but the result should be boolean.
	 * @param p_constraintInvariant The constraint invariant object (typically a string representing the constraint expression).
	 * @param p_libraries A map of key value pairs representing the a string id of a library and its associated code.
	 * @param p_modelingLevel The level at which the constraint is to be evaluated (M1 (model) or M2 (meta-model)).
	 * @param p_context The context of the constraints. This is typically the element that must satisfy the constraint.
	 * @return A collection of boolean values for every context object.
	 * @throws ConstraintEvaluationException Should be thrown whenever a functional exception such as syntactic errors in 
	 * the constraint text be evaluated occur. Detailed error should be added to the exception.
	 * constraint text for example.
	 * @throws SystemException Should be thrown whenever some system error occurs.
	 */
	Collection<Boolean> evaluateInvariant( 	Object p_constraintInvariant,
											Map<String, String> p_libraries,
									//		ModelingLevel p_modelingLevel,
											Object... p_context ) 
	throws ConstraintException, SystemException;
	
	/**
	 * Return a list of proposals for code completion.
	 * @param p_constraintExpression The constraint expression object (typically a string representing the constraint expression).
	 * @param p_libraries A map of key value pairs representing the a string id of a library and its associated code.
	 * @param p_context The context of the constraints. This is typically the element that must satisfy the constraint.
	 * @return A list of proposals for code completion.
	 * @throws ConstraintEvaluationException Should be thrown whenever a functional exception such as syntactic errors in 
	 * the constraint text be evaluated occur. Detailed error should be added to the exception.
	 * constraint text for example.
	 * @throws SystemException Should be thrown whenever some system error occurs.
	 */
	List<ICompletionProposal> computeCompletionProposals(	Object p_constraintExpression,
															Map<String, String> p_libraries,
													//		ModelingLevel p_modelingLevel,
															Object... p_context )
	throws ConstraintException, SystemException;

	/**
	 * Parse a constraint model object representation into a text.
	 * @param p_constraint The model object representing the constraint.
	 * @return The constraint text.
	 */
	String parse( Object p_constraint );
	
	/**
	 * Parse a constraint textual expression into an object.
	 * @param p_constraintText The constraint text.
	 * @return The model object representing the constraint text.
	 */
	Object parse( String p_constraintText )
	throws ConstraintParsingException, SystemException;

	/**
	 * This is called when a library is changed so that the interpretation service can update or invalidate its library
	 * cache if any.
	 * @param p_libraryId The library identifier.
	 */
	void libraryContentChanged( String p_libraryId );
    
    /**
	 * This is called when libraries are changed so that the interpretation service can update or invalidate its library
	 * cache if any.
     * @param p_libraryIds A collection of library identifiers.
     */
    void librariesContentChanged( Collection<String> p_libraryIds );
    
    void setScopeProviders( Collection<ScopeProvider> p_scopeProviders );
}
