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
import fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;
import fr.labsticc.framework.settings.model.settings.SettingsSpecification;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modeling Language Associations</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl#getSpecification <em>Specification</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl#getTypesAssociations <em>Types Associations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelingLanguageAssociationsImpl extends IdentifiedElementImpl implements ModelingLanguageAssociations {
	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected ModelingLanguage language;

	/**
	 * The cached value of the '{@link #getTypesAssociations() <em>Types Associations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypesAssociations()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyToTypesAssociation> typesAssociations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelingLanguageAssociationsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SettingsPackage.Literals.MODELING_LANGUAGE_ASSOCIATIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsSpecification getSpecification() {
		if (eContainerFeatureID() != SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION) return null;
		return (SettingsSpecification)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpecification(SettingsSpecification newSpecification, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSpecification, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecification(SettingsSpecification newSpecification) {
		if (newSpecification != eInternalContainer() || (eContainerFeatureID() != SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION && newSpecification != null)) {
			if (EcoreUtil.isAncestor(this, newSpecification))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSpecification != null)
				msgs = ((InternalEObject)newSpecification).eInverseAdd(this, SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS, SettingsSpecification.class, msgs);
			msgs = basicSetSpecification(newSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION, newSpecification, newSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingLanguage getLanguage() {
		if (language != null && language.eIsProxy()) {
			InternalEObject oldLanguage = (InternalEObject)language;
			language = (ModelingLanguage)eResolveProxy(oldLanguage);
			if (language != oldLanguage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE, oldLanguage, language));
			}
		}
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingLanguage basicGetLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(ModelingLanguage newLanguage) {
		ModelingLanguage oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE, oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PropertyToTypesAssociation> getTypesAssociations() {
		if (typesAssociations == null) {
			typesAssociations = new EObjectContainmentWithInverseEList<PropertyToTypesAssociation>(PropertyToTypesAssociation.class, this, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT);
		}
		return typesAssociations;
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
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSpecification((SettingsSpecification)otherEnd, msgs);
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTypesAssociations()).basicAdd(otherEnd, msgs);
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
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				return basicSetSpecification(null, msgs);
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				return ((InternalEList<?>)getTypesAssociations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				return eInternalContainer().eInverseRemove(this, SettingsPackage.SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS, SettingsSpecification.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				return getSpecification();
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE:
				if (resolve) return getLanguage();
				return basicGetLanguage();
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				return getTypesAssociations();
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
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				setSpecification((SettingsSpecification)newValue);
				return;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE:
				setLanguage((ModelingLanguage)newValue);
				return;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				getTypesAssociations().clear();
				getTypesAssociations().addAll((Collection<? extends PropertyToTypesAssociation>)newValue);
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
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				setSpecification((SettingsSpecification)null);
				return;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE:
				setLanguage((ModelingLanguage)null);
				return;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				getTypesAssociations().clear();
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
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION:
				return getSpecification() != null;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE:
				return language != null;
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS:
				return typesAssociations != null && !typesAssociations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelingLanguageAssociationsImpl
