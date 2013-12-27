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
package fr.labsticc.framework.constraints.model.service;

import fr.labsticc.framework.constraints.core.exception.ConstraintException;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.service.IConstraintServicesFacade;
import fr.labsticc.framework.core.exception.SystemException;

public interface IConstraintExpressionServicesFacade extends IConstraintServicesFacade {

	Object evaluateExpression( 	final FormalLanguageExpression p_expression,
								final Object p_context ) 
	throws SystemException, ConstraintException;

	Object evaluateExpression( 	final FormalLanguageExpression p_expression,
								final Object[] p_context ) 
	throws SystemException, ConstraintException;
}
