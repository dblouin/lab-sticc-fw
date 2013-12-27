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
package fr.labsticc.framework.constraints.model.constraints.impl;

import fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Language Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl#isDefault <em>Default</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl#getLanguage <em>Language</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalLanguageLibraryImpl extends IdentifiedElementImpl implements FormalLanguageLibrary {
	/**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
	protected FormalLanguageExpression expression;

	/**
	 * The default value of the '{@link #isDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefault()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDefault() <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefault()
	 * @generated
	 * @ordered
	 */
	protected boolean default_ = DEFAULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormalLanguageLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.FORMAL_LANGUAGE_LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalLanguageExpression getExpression() {
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(FormalLanguageExpression newExpression, NotificationChain msgs) {
		FormalLanguageExpression oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION, oldExpression, newExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(FormalLanguageExpression newExpression) {
		if (newExpression != expression) {
			NotificationChain msgs = null;
			if (expression != null)
				msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION, null, msgs);
			if (newExpression != null)
				msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION, null, msgs);
			msgs = basicSetExpression(newExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION, newExpression, newExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefault(boolean newDefault) {
		boolean oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__DEFAULT, oldDefault, default_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalConstraintLanguage getLanguage() {
		if (eContainerFeatureID() != ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE) return null;
		return (FormalConstraintLanguage)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLanguage(FormalConstraintLanguage newLanguage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLanguage, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(FormalConstraintLanguage newLanguage) {
		if (newLanguage != eInternalContainer() || (eContainerFeatureID() != ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE && newLanguage != null)) {
			if (EcoreUtil.isAncestor(this, newLanguage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLanguage != null)
				msgs = ((InternalEObject)newLanguage).eInverseAdd(this, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES, FormalConstraintLanguage.class, msgs);
			msgs = basicSetLanguage(newLanguage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE, newLanguage, newLanguage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLanguage((FormalConstraintLanguage)otherEnd, msgs);
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
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION:
				return basicSetExpression(null, msgs);
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				return basicSetLanguage(null, msgs);
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
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				return eInternalContainer().eInverseRemove(this, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES, FormalConstraintLanguage.class, msgs);
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
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION:
				return getExpression();
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__DEFAULT:
				return isDefault();
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				return getLanguage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION:
				setExpression((FormalLanguageExpression)newValue);
				return;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__DEFAULT:
				setDefault((Boolean)newValue);
				return;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				setLanguage((FormalConstraintLanguage)newValue);
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
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION:
				setExpression((FormalLanguageExpression)null);
				return;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__DEFAULT:
				setDefault(DEFAULT_EDEFAULT);
				return;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				setLanguage((FormalConstraintLanguage)null);
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
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__EXPRESSION:
				return expression != null;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__DEFAULT:
				return default_ != DEFAULT_EDEFAULT;
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE:
				return getLanguage() != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (default: ");
		result.append(default_);
		result.append(')');
		return result.toString();
	}

} //FormalLanguageLibraryImpl
