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
 * </copyright>
 *
 * $Id$
 */
package fr.labsticc.framework.constraints.model.constraints;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Language Expression</b></em>'.
 * <!-- end-user-doc -->z
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraries <em>Used Libraries</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageExpression()
 * @model
 * @generated
 */
public interface FormalLanguageExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Used Libraries</b></em>' reference list.
	 * The list contents are of type {@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Libraries</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Libraries</em>' reference list.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageExpression_UsedLibraries()
	 * @model
	 * @generated
	 */
	EList<FormalLanguageLibrary> getUsedLibraries();

	/**
	 * Returns the value of the '<em><b>Used Library Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Library Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Library Names</em>' attribute list.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageExpression_UsedLibraryNames()
	 * @model default="" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<String> getUsedLibraryNames();

	/**
	 * Returns the value of the '<em><b>Used Library Ids</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used Library Ids</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used Library Ids</em>' attribute list.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageExpression_UsedLibraryIds()
	 * @model default="" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<String> getUsedLibraryIds();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EClassifier)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageExpression_Type()
	 * @model
	 * @generated
	 */
	EClassifier getType();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EClassifier value);

} // FormalLanguageExpression
