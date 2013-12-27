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
package fr.labsticc.framework.constraints.view.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * Message accessor for the constraint panel.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class ConstraintConsoleMessages extends NLS {

	private static final String BUNDLE_NAME = ConstraintConsoleMessages.class.getName();

	public static String evaluating;
	public static String results;
	public static String saveAction_icon;
	public static String saveAction_tip;
	public static String loadAction_icon;
	public static String loadAction_tip;
	public static String addLibAction_icon;
	public static String addLibAction_tip;
	public static String editLibAction_icon;
	public static String editLibAction_tip;
	public static String refreshLibAction_icon;
	public static String refreshLibAction_tip;
	public static String deleteLibAction_icon;
	public static String deleteLibAction_tip;
	public static String parsed;
	public static String evalAction_tip; 
	public static String evalAction_icon; 
	public static String clearResultsAction_tip; 
	public static String clearResultsAction_icon; 
	public static String delete_expressionAction_tip; 
	public static String delete_expressionAction_icon;
	public static String evalOrderHeader;
	public static String libraryHeader;
	public static String langSelectNoSelect;
	public static String consoleTabTitle;
	public static String libraryTabTitle;
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, ConstraintConsoleMessages.class);
	}
}
