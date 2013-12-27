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
package fr.labsticc.framework.core.transformation;

import fr.labsticc.framework.core.exception.SystemException;

/**
 * This class represents a system exception that may occur when using model transformations.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class TransformationException extends SystemException {

	private static final long serialVersionUID = 9208640436579340424L;

	public TransformationException( final String p_message ) {
		super( p_message );
	}

	public TransformationException( final Throwable p_cause ) {
		super( p_cause );
	}

	public TransformationException(	final String p_message,
									final Throwable p_cause ) {
		super( p_message, p_cause );
	}
}
