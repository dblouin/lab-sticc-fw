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

import org.eclipse.emf.ecore.EObject;

import fr.labsticc.framework.constraints.core.ICompletionProposal;
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.core.exception.SystemException;

public class DefaultConstraintServicesFacade implements	IConstraintServicesFacade {
	
	private final Map<String, IConstraintInterpretationAS> interpreterServices;
	private final Map<String, IConstraintPersistenceAS> persistenceServices;
	
	public DefaultConstraintServicesFacade( final Map<String, IConstraintInterpretationAS> p_interpreterServices,
											final Map<String, IConstraintPersistenceAS> p_persistenceServices ) {
		interpreterServices = p_interpreterServices;
		persistenceServices = p_persistenceServices;
	}
	
	private IConstraintInterpretationAS getInterpreterAS( final String p_languageId )
	throws SystemException {
		final IConstraintInterpretationAS interpreter = interpreterServices.get( p_languageId );
		
		if ( interpreter == null ) {
			throw new SystemException( "No interpreter defined in facade for language " + p_languageId + ". Please check the constraints language configuration preferences." );
		}
		
		return interpreter;
	}
	
	private IConstraintPersistenceAS getPersistenceAS( final String p_languageId ) {
		final IConstraintPersistenceAS service = persistenceServices.get( p_languageId );
		
		assert service != null : "No persistence service defined in facade for language " + p_languageId;
		
		return service;
	}

	@Override
	public List<ICompletionProposal> computeCompletionProposals(	final String p_languageId, 
																	final Object p_constraintExpression,
																	final Map<String, String> p_constraintLibs,
																	//final ModelingLevel p_modelingLevel, 
																	final Object... p_context )
	throws ConstraintException, SystemException {
		return getInterpreterAS( p_languageId ).computeCompletionProposals( p_constraintExpression,
																			p_constraintLibs,
																		//	p_modelingLevel,
																			p_context );
	}

	@Override
	public Object evaluateConstraint(	final String p_languageId, 
										final Object p_constraintExpression,
										final Map<String, String> p_constraintLibs,
									//	final ModelingLevel p_modelingLevel, 
										final boolean pb_resultsAsString, 
										Object... p_context )
	throws ConstraintException, SystemException {
		return getInterpreterAS( p_languageId ).evaluateConstraint( p_constraintExpression, 
																	p_constraintLibs,
																//	p_modelingLevel,
																	pb_resultsAsString,
																	p_context );
	}

	@Override
	public EObject readConstraint(	final String p_languageId, 
									final String p_resourceId )
	throws SystemException, InterruptedException {
		return getPersistenceAS( p_languageId ).readEntity( p_resourceId );
	}
	
	@Override
	public String readConstraintText( 	final String p_languageId,
										final String p_resourceId ) 
	throws SystemException,	InterruptedException {
		final EObject constraint = readConstraint( p_languageId, p_resourceId );
		
		return getInterpreterAS( p_languageId ).parse( constraint );
	}

	@Override
	public void persistConstraint(	final String p_languageId, 
									final String p_resourceId,
									final String p_constraintText )
	throws ConstraintException, SystemException, InterruptedException {
		final EObject constraint = (EObject) getInterpreterAS( p_languageId ).parse( p_constraintText );
		
		getPersistenceAS( p_languageId ).persistEntity( p_resourceId, constraint );
	}

	@Override
	public Collection<Boolean> evaluateInvariant(	final String p_languageId,
													final Object p_constraintExpression,
													final Map<String, String> p_constraintLibs,
												//	final ModelingLevel p_modelingLevel,
													final Object... p_context )
	throws ConstraintException, SystemException {
		return getInterpreterAS( p_languageId ).evaluateInvariant( 	p_constraintExpression, 
																	p_constraintLibs,
															//		p_modelingLevel,
																	p_context );
	}

	@Override
	public void libraryContentChanged( final String p_languageId,
								final String p_libraryId ) 
	throws SystemException {
		getInterpreterAS( p_languageId ).libraryContentChanged( p_libraryId );
	}

	@Override
	public void librariesContentChanged(	final String p_languageId,
									final Collection<String> p_libraryIds ) 
	throws SystemException {
		getInterpreterAS( p_languageId ).librariesContentChanged( p_libraryIds );
	}
}
