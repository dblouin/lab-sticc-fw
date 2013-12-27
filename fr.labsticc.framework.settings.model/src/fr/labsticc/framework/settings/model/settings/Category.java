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
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.Category#getSubCategories <em>Sub Categories</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.Category#getParentCategory <em>Parent Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getCategory()
 * @model
 * @generated
 */
public interface Category extends IdentifiedElement {
	/**
	 * Returns the value of the '<em><b>Sub Categories</b></em>' reference list.
	 * The list contents are of type {@link fr.labsticc.framework.settings.model.settings.Category}.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.Category#getParentCategory <em>Parent Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Categories</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Categories</em>' reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getCategory_SubCategories()
	 * @see fr.labsticc.framework.settings.model.settings.Category#getParentCategory
	 * @model opposite="parentCategory"
	 * @generated
	 */
	EList<Category> getSubCategories();

	/**
	 * Returns the value of the '<em><b>Parent Category</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.Category#getSubCategories <em>Sub Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Category</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Category</em>' reference.
	 * @see #setParentCategory(Category)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getCategory_ParentCategory()
	 * @see fr.labsticc.framework.settings.model.settings.Category#getSubCategories
	 * @model opposite="subCategories"
	 * @generated
	 */
	Category getParentCategory();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.Category#getParentCategory <em>Parent Category</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Category</em>' reference.
	 * @see #getParentCategory()
	 * @generated
	 */
	void setParentCategory(Category value);

} // Category
