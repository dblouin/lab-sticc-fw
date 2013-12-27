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
package fr.labsticc.framework.settings.model.settings;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modeling Language Associations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification <em>Specification</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getLanguage <em>Language</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getTypesAssociations <em>Types Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getModelingLanguageAssociations()
 * @model
 * @generated
 */
public interface ModelingLanguageAssociations extends IdentifiedElement {
	/**
	 * Returns the value of the '<em><b>Specification</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguageAssociations <em>Language Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Specification</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Specification</em>' container reference.
	 * @see #setSpecification(SettingsSpecification)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getModelingLanguageAssociations_Specification()
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguageAssociations
	 * @model opposite="languageAssociations" required="true" transient="false"
	 * @generated
	 */
	SettingsSpecification getSpecification();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification <em>Specification</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Specification</em>' container reference.
	 * @see #getSpecification()
	 * @generated
	 */
	void setSpecification(SettingsSpecification value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language</em>' reference.
	 * @see #setLanguage(ModelingLanguage)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getModelingLanguageAssociations_Language()
	 * @model required="true"
	 * @generated
	 */
	ModelingLanguage getLanguage();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getLanguage <em>Language</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' reference.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(ModelingLanguage value);

	/**
	 * Returns the value of the '<em><b>Types Associations</b></em>' containment reference list.
	 * The list contents are of type {@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation}.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types Associations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Types Associations</em>' containment reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getModelingLanguageAssociations_TypesAssociations()
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<PropertyToTypesAssociation> getTypesAssociations();

} // ModelingLanguageAssociations
