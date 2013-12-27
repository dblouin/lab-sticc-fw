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
import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.core.exception.SystemException;

public interface IConstraintServicesFacade {

	Object evaluateConstraint( 	String p_languageId,
								Object p_constraintExpression,
								Map<String, String> p_constraintLibs,
							//	ModelingLevel p_modelingLevel,
								boolean pb_resultsAsString,
								Object... p_context ) 
	throws ConstraintException, SystemException;

	Collection<Boolean> evaluateInvariant( 	String p_languageId,
											Object p_constraintExpression,
											Map<String, String> p_constraintLibs,
									//		ModelingLevel p_modelingLevel,
											Object... p_context ) 
	throws ConstraintException, SystemException;
	
	List<ICompletionProposal> computeCompletionProposals(	String p_languageId,
															Object p_constraintExpression,
															Map<String, String> p_constraintLibs,
												//			ModelingLevel p_modelingLevel,
															Object... p_context )
	throws ConstraintException, SystemException;

	Object readConstraint( 	String p_languageId,
							String p_resourceId ) 
	throws SystemException, InterruptedException;

	String readConstraintText( 	String p_languageId,
								String p_resourceId ) 
	throws SystemException, InterruptedException;

	void persistConstraint(	String p_languageId, 
							String p_resourceId,
							String p_constraintText )
	throws ConstraintException, SystemException,	InterruptedException;

	void libraryContentChanged( String p_languageId,
								String p_libraryId )
	throws SystemException;
    
    void librariesContentChanged( 	String p_languageId,
									Collection<String> p_libraryIds )
	throws SystemException;
}
