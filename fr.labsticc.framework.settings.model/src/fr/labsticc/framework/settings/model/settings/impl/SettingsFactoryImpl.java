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

import fr.labsticc.framework.settings.model.settings.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SettingsFactoryImpl extends EFactoryImpl implements SettingsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SettingsFactory init() {
		try {
			SettingsFactory theSettingsFactory = (SettingsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///www.lab-sticc.fr/settings"); 
			if (theSettingsFactory != null) {
				return theSettingsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SettingsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SettingsPackage.MODELING_LANGUAGE_ASSOCIATIONS: return createModelingLanguageAssociations();
			case SettingsPackage.SETTINGS_SPECIFICATION: return createSettingsSpecification();
			case SettingsPackage.PROPERTY_TO_TYPES_ASSOCIATION: return createPropertyToTypesAssociation();
			case SettingsPackage.MODELING_LANGUAGE: return createModelingLanguage();
			case SettingsPackage.CATEGORY: return createCategory();
			case SettingsPackage.PROPERTY_TO_CATEGORIES_ASSOCIATION: return createPropertyToCategoriesAssociation();
			case SettingsPackage.SERVICE_DESCRIPTOR: return createServiceDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingLanguageAssociations createModelingLanguageAssociations() {
		ModelingLanguageAssociationsImpl modelingLanguageAssociations = new ModelingLanguageAssociationsImpl();
		return modelingLanguageAssociations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsSpecification createSettingsSpecification() {
		SettingsSpecificationImpl settingsSpecification = new SettingsSpecificationImpl();
		return settingsSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyToTypesAssociation createPropertyToTypesAssociation() {
		PropertyToTypesAssociationImpl propertyToTypesAssociation = new PropertyToTypesAssociationImpl();
		return propertyToTypesAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelingLanguage createModelingLanguage() {
		ModelingLanguageImpl modelingLanguage = new ModelingLanguageImpl();
		return modelingLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Category createCategory() {
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyToCategoriesAssociation createPropertyToCategoriesAssociation() {
		PropertyToCategoriesAssociationImpl propertyToCategoriesAssociation = new PropertyToCategoriesAssociationImpl();
		return propertyToCategoriesAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDescriptor createServiceDescriptor() {
		ServiceDescriptorImpl serviceDescriptor = new ServiceDescriptorImpl();
		return serviceDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsPackage getSettingsPackage() {
		return (SettingsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SettingsPackage getPackage() {
		return SettingsPackage.eINSTANCE;
	}

} //SettingsFactoryImpl
