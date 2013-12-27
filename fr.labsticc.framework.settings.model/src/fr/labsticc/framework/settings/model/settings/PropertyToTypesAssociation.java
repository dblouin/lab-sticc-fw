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

import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property To Types Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getAssociatedTypes <em>Associated Types</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent <em>Parent</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#isContainers <em>Containers</em>}</li>
 *   <li>{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getPropertyToTypesAssociation()
 * @model
 * @generated
 */
public interface PropertyToTypesAssociation extends IdentifiedElement, PropertyAssociation {
	/**
	 * Returns the value of the '<em><b>Associated Types</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associated Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associated Types</em>' reference list.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getPropertyToTypesAssociation_AssociatedTypes()
	 * @model
	 * @generated
	 */
	EList<EClass> getAssociatedTypes();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getTypesAssociations <em>Types Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(ModelingLanguageAssociations)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getPropertyToTypesAssociation_Parent()
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getTypesAssociations
	 * @model opposite="typesAssociations" required="true" transient="false"
	 * @generated
	 */
	ModelingLanguageAssociations getParent();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ModelingLanguageAssociations value);

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' attribute.
	 * @see #setContainers(boolean)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getPropertyToTypesAssociation_Containers()
	 * @model required="true"
	 * @generated
	 */
	boolean isContainers();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#isContainers <em>Containers</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containers</em>' attribute.
	 * @see #isContainers()
	 * @generated
	 */
	void setContainers(boolean value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(FormalLanguageExpression)
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getPropertyToTypesAssociation_Condition()
	 * @model containment="true"
	 * @generated
	 */
	FormalLanguageExpression getCondition();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(FormalLanguageExpression value);

} // PropertyToTypesAssociation
