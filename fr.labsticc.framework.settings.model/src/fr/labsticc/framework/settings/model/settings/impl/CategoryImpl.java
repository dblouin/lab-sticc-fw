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

import fr.labsticc.framework.settings.model.settings.Category;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.CategoryImpl#getSubCategories <em>Sub Categories</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.impl.CategoryImpl#getParentCategory <em>Parent Category</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CategoryImpl extends IdentifiedElementImpl implements Category {
	/**
	 * The cached value of the '{@link #getSubCategories() <em>Sub Categories</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<Category> subCategories;

	/**
	 * The cached value of the '{@link #getParentCategory() <em>Parent Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentCategory()
	 * @generated
	 * @ordered
	 */
	protected Category parentCategory;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CategoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SettingsPackage.Literals.CATEGORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Category> getSubCategories() {
		if (subCategories == null) {
			subCategories = new EObjectWithInverseResolvingEList<Category>(Category.class, this, SettingsPackage.CATEGORY__SUB_CATEGORIES, SettingsPackage.CATEGORY__PARENT_CATEGORY);
		}
		return subCategories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category getParentCategory() {
		if (parentCategory != null && parentCategory.eIsProxy()) {
			InternalEObject oldParentCategory = (InternalEObject)parentCategory;
			parentCategory = (Category)eResolveProxy(oldParentCategory);
			if (parentCategory != oldParentCategory) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SettingsPackage.CATEGORY__PARENT_CATEGORY, oldParentCategory, parentCategory));
			}
		}
		return parentCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category basicGetParentCategory() {
		return parentCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentCategory(Category newParentCategory, NotificationChain msgs) {
		Category oldParentCategory = parentCategory;
		parentCategory = newParentCategory;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SettingsPackage.CATEGORY__PARENT_CATEGORY, oldParentCategory, newParentCategory);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentCategory(Category newParentCategory) {
		if (newParentCategory != parentCategory) {
			NotificationChain msgs = null;
			if (parentCategory != null)
				msgs = ((InternalEObject)parentCategory).eInverseRemove(this, SettingsPackage.CATEGORY__SUB_CATEGORIES, Category.class, msgs);
			if (newParentCategory != null)
				msgs = ((InternalEObject)newParentCategory).eInverseAdd(this, SettingsPackage.CATEGORY__SUB_CATEGORIES, Category.class, msgs);
			msgs = basicSetParentCategory(newParentCategory, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SettingsPackage.CATEGORY__PARENT_CATEGORY, newParentCategory, newParentCategory));
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubCategories()).basicAdd(otherEnd, msgs);
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				if (parentCategory != null)
					msgs = ((InternalEObject)parentCategory).eInverseRemove(this, SettingsPackage.CATEGORY__SUB_CATEGORIES, Category.class, msgs);
				return basicSetParentCategory((Category)otherEnd, msgs);
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				return ((InternalEList<?>)getSubCategories()).basicRemove(otherEnd, msgs);
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				return basicSetParentCategory(null, msgs);
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				return getSubCategories();
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				if (resolve) return getParentCategory();
				return basicGetParentCategory();
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				getSubCategories().clear();
				getSubCategories().addAll((Collection<? extends Category>)newValue);
				return;
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				setParentCategory((Category)newValue);
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				getSubCategories().clear();
				return;
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				setParentCategory((Category)null);
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
			case SettingsPackage.CATEGORY__SUB_CATEGORIES:
				return subCategories != null && !subCategories.isEmpty();
			case SettingsPackage.CATEGORY__PARENT_CATEGORY:
				return parentCategory != null;
		}
		return super.eIsSet(featureID);
	}

} //CategoryImpl
