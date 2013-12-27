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

import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations;
import fr.labsticc.framework.settings.model.settings.PropertyAssociation;
import fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property To Types Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl#getAssociatedTypes <em>Associated Types</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl#isContainers <em>Containers</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyToTypesAssociationImpl extends IdentifiedElementImpl implements PropertyToTypesAssociation {
	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EReference property;

	/**
	 * The cached value of the '{@link #getAssociatedTypes() <em>Associated Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociatedTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<EClass> associatedTypes;

	/**
	 * The default value of the '{@link #isContainers() <em>Containers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainers()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTAINERS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isContainers() <em>Containers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContainers()
	 * @generated
	 * @ordered
	 */
	protected boolean containers = CONTAINERS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected FormalLanguageExpression condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyToTypesAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SettingsPackage.Literals.PROPERTY_TO_TYPES_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (EReference)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(EReference newProperty) {
		EReference oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EClass> getAssociatedTypes() {
		if (associatedTypes == null) {
			associatedTypes = new EObjectResolvingEList<EClass>(EClass.class, this, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES);
		}
		return associatedTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingLanguageAssociations getParent() {
		if (eContainerFeatureID() != SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT) return null;
		return (ModelingLanguageAssociations)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ModelingLanguageAssociations newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ModelingLanguageAssociations newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS, ModelingLanguageAssociations.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isContainers() {
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainers(boolean newContainers) {
		boolean oldContainers = containers;
		containers = newContainers;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS, oldContainers, containers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalLanguageExpression getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(FormalLanguageExpression newCondition, NotificationChain msgs) {
		FormalLanguageExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(FormalLanguageExpression newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((ModelingLanguageAssociations)otherEnd, msgs);
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				return basicSetParent(null, msgs);
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION:
				return basicSetCondition(null, msgs);
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				return eInternalContainer().eInverseRemove(this, SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS, ModelingLanguageAssociations.class, msgs);
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES:
				return getAssociatedTypes();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				return getParent();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS:
				return isContainers();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION:
				return getCondition();
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY:
				setProperty((EReference)newValue);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES:
				getAssociatedTypes().clear();
				getAssociatedTypes().addAll((Collection<? extends EClass>)newValue);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				setParent((ModelingLanguageAssociations)newValue);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS:
				setContainers((Boolean)newValue);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION:
				setCondition((FormalLanguageExpression)newValue);
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY:
				setProperty((EReference)null);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES:
				getAssociatedTypes().clear();
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				setParent((ModelingLanguageAssociations)null);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS:
				setContainers(CONTAINERS_EDEFAULT);
				return;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION:
				setCondition((FormalLanguageExpression)null);
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
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY:
				return property != null;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES:
				return associatedTypes != null && !associatedTypes.isEmpty();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PARENT:
				return getParent() != null;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS:
				return containers != CONTAINERS_EDEFAULT;
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyAssociation.class) {
			switch (derivedFeatureID) {
				case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY: return SettingsPackage.PROPERTY_ASSOCIATION__PROPERTY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PropertyAssociation.class) {
			switch (baseFeatureID) {
				case SettingsPackage.PROPERTY_ASSOCIATION__PROPERTY: return SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (containers: ");
		result.append(containers);
		result.append(')');
		return result.toString();
	}

} //PropertyToTypesAssociationImpl
