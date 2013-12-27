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
 * A representation of the model object '<em><b>Constraint Language</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage#getSpecification <em>Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getConstraintLanguage()
 * @model abstract="true"
 * @generated
 */
public interface ConstraintLanguage extends IdentifiedElement {

	/**
	 * Returns the value of the '<em><b>Specification</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification#getLanguages <em>Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specification</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specification</em>' container reference.
	 * @see #setSpecification(ConstraintLanguagesSpecification)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getConstraintLanguage_Specification()
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification#getLanguages
	 * @model opposite="languages" required="true" transient="false"
	 * @generated
	 */
	ConstraintLanguagesSpecification getSpecification();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage#getSpecification <em>Specification</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specification</em>' container reference.
	 * @see #getSpecification()
	 * @generated
	 */
	void setSpecification(ConstraintLanguagesSpecification value);
} // ConstraintLanguage
