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
/**
 * <copyright>
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: OCLInterpreterMessages.java,v 1.5 2007/10/25 03:11:49 cdamus Exp $
 */

package fr.labsticc.framework.constraints.core.exception;

import org.eclipse.osgi.util.NLS;

public class ConstraintInterpreterMessages extends NLS {

	private static final String BUNDLE_NAME = ConstraintInterpreterMessages.class.getName();

	public static String noContext;
	public static String noExpression;
	public static String console_badContextForQuery;
	public static String console_badContextForConstraint;
	public static String console_closeAction_label;
	
	static {
		NLS.initializeMessages( BUNDLE_NAME, ConstraintInterpreterMessages.class );
	}
}
