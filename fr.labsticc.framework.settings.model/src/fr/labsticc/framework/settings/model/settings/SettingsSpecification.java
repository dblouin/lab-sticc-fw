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
 * A representation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguages <em>Languages</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguageAssociations <em>Language Associations</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getCategoryAssociations <em>Category Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getSettingsSpecification()
 * @model
 * @generated
 */
public interface SettingsSpecification extends IdentifiedElement {
	/**
	 * Returns the value of the '<em><b>Languages</b></em>' containment reference list.
	 * The list contents are of type {@link fr.labsticc.framework.settings.model.settings.ModelingLanguage}.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Languages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Languages</em>' containment reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getSettingsSpecification_Languages()
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage#getSpecification
	 * @model opposite="specification" containment="true" required="true"
	 * @generated
	 */
	EList<ModelingLanguage> getLanguages();

	/**
	 * Returns the value of the '<em><b>Language Associations</b></em>' containment reference list.
	 * The list contents are of type {@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations}.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Language Associations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Language Associations</em>' containment reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getSettingsSpecification_LanguageAssociations()
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification
	 * @model opposite="specification" containment="true"
	 * @generated
	 */
	EList<ModelingLanguageAssociations> getLanguageAssociations();

	/**
	 * Returns the value of the '<em><b>Category Associations</b></em>' containment reference list.
	 * The list contents are of type {@link fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category Associations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category Associations</em>' containment reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getSettingsSpecification_CategoryAssociations()
	 * @model containment="true"
	 * @generated
	 */
	EList<PropertyToCategoriesAssociation> getCategoryAssociations();

} // SettingsSpecification
