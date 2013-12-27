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
package fr.labsticc.framework.constraints.core;

	public enum CompletionKind {
		/** Indicates a property completion choice. */
		PROPERTY,

		/** Indicates an operation completion choice. */
		OPERATION,

		/** Indicates a signal completion choice. */
		SIGNAL,
		
		/** Indicates an enumeration literal completion choice. */
		ENUMERATION_LITERAL,

		/** Indicates a state name completion choice. */
		STATE,
		
		/** Indicates a type name completion choice. */
		TYPE,
		
		/** Indicates an association-class navigation choice. */
		ASSOCIATION_CLASS,
		
		/** Indicates a package choice (which helps to complete type choices). */
		PACKAGE,

		/** Indicates a variable name completion choice. */
		VARIABLE;
}
