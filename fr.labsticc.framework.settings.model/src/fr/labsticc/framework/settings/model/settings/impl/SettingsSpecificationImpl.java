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
package fr.labsticc.framework.settings.model.settings.impl;

import fr.labsticc.framework.settings.model.settings.ModelingLanguage;
import fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations;
import fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;
import fr.labsticc.framework.settings.model.settings.SettingsSpecification;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl#getLanguages <em>Languages</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl#getLanguageAssociations <em>Language Associations</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl#getCategoryAssociations <em>Category Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SettingsSpecificationImpl extends IdentifiedElementImpl implements SettingsSpecification {
	/**
	 * The cached value of the '{@link #getLanguages() <em>Languages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguages()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelingLanguage> languages;

	/**
	 * The cached value of the '{@link #getLanguageAssociations() <em>Language Associations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguageAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelingLanguageAssociations> languageAssociations;

	/**
	 * The cached value of the '{@link #getCategoryAssociations() <em>Category Associations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategoryAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyToCategoriesAssociation> categoryAssociations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SettingsSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SettingsPackage.Literals.SETTINGS_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelingLanguage> getLanguages() {
		if (languages == null) {
			languages = new EObjectContainmentWithInverseEList<ModelingLanguage>(ModelingLanguage.class, this, SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES, SettingsPackage.MODELING_LANGUAGE__SPECIFICATION);
		}
		return languages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelingLanguageAssociations> getLanguageAssociations() {
		if (languageAssociations == null) {
			languageAssociations = new EObjectContainmentWithInverseEList<ModelingLanguageAssociations>(ModelingLanguageAssociations.class, this, SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION);
		}
		return languageAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyToCategoriesAssociation> getCategoryAssociations() {
		if (categoryAssociations == null) {
			categoryAssociations = new EObjectContainmentEList<PropertyToCategoriesAssociation>(PropertyToCategoriesAssociation.class, this, SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS);
		}
		return categoryAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLanguages()).basicAdd(otherEnd, msgs);
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLanguageAssociations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				return ((InternalEList<?>)getLanguages()).basicRemove(otherEnd, msgs);
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				return ((InternalEList<?>)getLanguageAssociations()).basicRemove(otherEnd, msgs);
			case SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS:
				return ((InternalEList<?>)getCategoryAssociations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				return getLanguages();
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				return getLanguageAssociations();
			case SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS:
				return getCategoryAssociations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				getLanguages().clear();
				getLanguages().addAll((Collection<? extends ModelingLanguage>)newValue);
				return;
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				getLanguageAssociations().clear();
				getLanguageAssociations().addAll((Collection<? extends ModelingLanguageAssociations>)newValue);
				return;
			case SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS:
				getCategoryAssociations().clear();
				getCategoryAssociations().addAll((Collection<? extends PropertyToCategoriesAssociation>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				getLanguages().clear();
				return;
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				getLanguageAssociations().clear();
				return;
			case SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS:
				getCategoryAssociations().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGES:
				return languages != null && !languages.isEmpty();
			case SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS:
				return languageAssociations != null && !languageAssociations.isEmpty();
			case SettingsPackage.SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS:
				return categoryAssociations != null && !categoryAssociations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SettingsSpecificationImpl
