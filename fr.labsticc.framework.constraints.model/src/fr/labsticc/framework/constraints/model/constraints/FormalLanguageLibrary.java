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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Language Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getExpression <em>Expression</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#isDefault <em>Default</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageLibrary()
 * @model
 * @generated
 */
public interface FormalLanguageLibrary extends IdentifiedElement {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(FormalLanguageExpression)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageLibrary_Expression()
	 * @model containment="true" required="true"
	 * @generated
	 */
	FormalLanguageExpression getExpression();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(FormalLanguageExpression value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(boolean)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageLibrary_Default()
	 * @model
	 * @generated
	 */
	boolean isDefault();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#isDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #isDefault()
	 * @generated
	 */
	void setDefault(boolean value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraries <em>Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' container reference.
	 * @see #setLanguage(FormalConstraintLanguage)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalLanguageLibrary_Language()
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraries
	 * @model opposite="libraries" required="true" transient="false"
	 * @generated
	 */
	FormalConstraintLanguage getLanguage();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage <em>Language</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' container reference.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(FormalConstraintLanguage value);

} // FormalLanguageLibrary
