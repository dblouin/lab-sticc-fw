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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see fr.labsticc.framework.settings.model.settings.SettingsFactory
 * @model kind="package"
 * @generated
 */
public interface SettingsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "settings";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///www.lab-sticc.fr/settings";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "settings";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SettingsPackage eINSTANCE = fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.IdentifiedElementImpl <em>Identified Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.IdentifiedElementImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getIdentifiedElement()
	 * @generated
	 */
	int IDENTIFIED_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIED_ELEMENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIED_ELEMENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIED_ELEMENT__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Identified Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIED_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl <em>Modeling Language Associations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelingLanguageAssociations()
	 * @generated
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Types Associations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Modeling Language Associations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_ASSOCIATIONS_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getSettingsSpecification()
	 * @generated
	 */
	int SETTINGS_SPECIFICATION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Languages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__LANGUAGES = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language Associations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Category Associations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SETTINGS_SPECIFICATION_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl <em>Property To Types Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyToTypesAssociation()
	 * @generated
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__PROPERTY = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Associated Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__PARENT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION__CONDITION = IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Property To Types Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_TYPES_ASSOCIATION_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageImpl <em>Modeling Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelingLanguage()
	 * @generated
	 */
	int MODELING_LANGUAGE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__SPECIFICATION = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__PACKAGES = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File Extensions</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__FILE_EXTENSIONS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE__MODEL_INTERFACE = IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Modeling Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODELING_LANGUAGE_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.CategoryImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Sub Categories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__SUB_CATEGORIES = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent Category</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PARENT_CATEGORY = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyAssociationImpl <em>Property Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyAssociationImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyAssociation()
	 * @generated
	 */
	int PROPERTY_ASSOCIATION = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ASSOCIATION__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ASSOCIATION__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ASSOCIATION__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ASSOCIATION__PROPERTY = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_ASSOCIATION_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToCategoriesAssociationImpl <em>Property To Categories Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyToCategoriesAssociationImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyToCategoriesAssociation()
	 * @generated
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION__ID = PROPERTY_ASSOCIATION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION__NAME = PROPERTY_ASSOCIATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION__DESCRIPTION = PROPERTY_ASSOCIATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION__PROPERTY = PROPERTY_ASSOCIATION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Associated Categories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION__ASSOCIATED_CATEGORIES = PROPERTY_ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property To Categories Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_TO_CATEGORIES_ASSOCIATION_FEATURE_COUNT = PROPERTY_ASSOCIATION_FEATURE_COUNT + 1;


	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.ServiceDescriptorImpl <em>Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.ServiceDescriptorImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getServiceDescriptor()
	 * @generated
	 */
	int SERVICE_DESCRIPTOR = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__PROVIDER_ID = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Service Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__SERVICE_CLASS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.ScopeProviderImpl <em>Scope Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.ScopeProviderImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getScopeProvider()
	 * @generated
	 */
	int SCOPE_PROVIDER = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_PROVIDER__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_PROVIDER__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_PROVIDER__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Scope Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_PROVIDER_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelInterfaceImpl <em>Model Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.settings.model.settings.impl.ModelInterfaceImpl
	 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelInterface()
	 * @generated
	 */
	int MODEL_INTERFACE = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_INTERFACE__ID = SCOPE_PROVIDER__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_INTERFACE__NAME = SCOPE_PROVIDER__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_INTERFACE__DESCRIPTION = SCOPE_PROVIDER__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Model Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_INTERFACE_FEATURE_COUNT = SCOPE_PROVIDER_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.IdentifiedElement <em>Identified Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identified Element</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.IdentifiedElement
	 * @generated
	 */
	EClass getIdentifiedElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.IdentifiedElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.IdentifiedElement#getId()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.IdentifiedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.IdentifiedElement#getName()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.IdentifiedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.IdentifiedElement#getDescription()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Description();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations <em>Modeling Language Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modeling Language Associations</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations
	 * @generated
	 */
	EClass getModelingLanguageAssociations();

	/**
	 * Returns the meta object for the container reference '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Specification</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getSpecification()
	 * @see #getModelingLanguageAssociations()
	 * @generated
	 */
	EReference getModelingLanguageAssociations_Specification();

	/**
	 * Returns the meta object for the reference '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Language</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getLanguage()
	 * @see #getModelingLanguageAssociations()
	 * @generated
	 */
	EReference getModelingLanguageAssociations_Language();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getTypesAssociations <em>Types Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types Associations</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations#getTypesAssociations()
	 * @see #getModelingLanguageAssociations()
	 * @generated
	 */
	EReference getModelingLanguageAssociations_TypesAssociations();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification
	 * @generated
	 */
	EClass getSettingsSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguages <em>Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Languages</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguages()
	 * @see #getSettingsSpecification()
	 * @generated
	 */
	EReference getSettingsSpecification_Languages();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguageAssociations <em>Language Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Language Associations</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification#getLanguageAssociations()
	 * @see #getSettingsSpecification()
	 * @generated
	 */
	EReference getSettingsSpecification_LanguageAssociations();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification#getCategoryAssociations <em>Category Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Category Associations</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification#getCategoryAssociations()
	 * @see #getSettingsSpecification()
	 * @generated
	 */
	EReference getSettingsSpecification_CategoryAssociations();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation <em>Property To Types Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property To Types Association</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation
	 * @generated
	 */
	EClass getPropertyToTypesAssociation();

	/**
	 * Returns the meta object for the reference list '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getAssociatedTypes <em>Associated Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Associated Types</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getAssociatedTypes()
	 * @see #getPropertyToTypesAssociation()
	 * @generated
	 */
	EReference getPropertyToTypesAssociation_AssociatedTypes();

	/**
	 * Returns the meta object for the container reference '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getParent()
	 * @see #getPropertyToTypesAssociation()
	 * @generated
	 */
	EReference getPropertyToTypesAssociation_Parent();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#isContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Containers</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#isContainers()
	 * @see #getPropertyToTypesAssociation()
	 * @generated
	 */
	EAttribute getPropertyToTypesAssociation_Containers();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation#getCondition()
	 * @see #getPropertyToTypesAssociation()
	 * @generated
	 */
	EReference getPropertyToTypesAssociation_Condition();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage <em>Modeling Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modeling Language</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage
	 * @generated
	 */
	EClass getModelingLanguage();

	/**
	 * Returns the meta object for the container reference '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Specification</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage#getSpecification()
	 * @see #getModelingLanguage()
	 * @generated
	 */
	EReference getModelingLanguage_Specification();

	/**
	 * Returns the meta object for the reference list '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage#getPackages <em>Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Packages</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage#getPackages()
	 * @see #getModelingLanguage()
	 * @generated
	 */
	EReference getModelingLanguage_Packages();

	/**
	 * Returns the meta object for the attribute list '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage#getFileExtensions <em>File Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>File Extensions</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage#getFileExtensions()
	 * @see #getModelingLanguage()
	 * @generated
	 */
	EAttribute getModelingLanguage_FileExtensions();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage#getModelInterface <em>Model Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model Interface</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage#getModelInterface()
	 * @see #getModelingLanguage()
	 * @generated
	 */
	EReference getModelingLanguage_ModelInterface();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the reference list '{@link fr.labsticc.framework.settings.model.settings.Category#getSubCategories <em>Sub Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sub Categories</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.Category#getSubCategories()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_SubCategories();

	/**
	 * Returns the meta object for the reference '{@link fr.labsticc.framework.settings.model.settings.Category#getParentCategory <em>Parent Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Category</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.Category#getParentCategory()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_ParentCategory();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation <em>Property To Categories Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property To Categories Association</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation
	 * @generated
	 */
	EClass getPropertyToCategoriesAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation#getAssociatedCategories <em>Associated Categories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Associated Categories</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation#getAssociatedCategories()
	 * @see #getPropertyToCategoriesAssociation()
	 * @generated
	 */
	EReference getPropertyToCategoriesAssociation_AssociatedCategories();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.PropertyAssociation <em>Property Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Association</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyAssociation
	 * @generated
	 */
	EClass getPropertyAssociation();

	/**
	 * Returns the meta object for the reference '{@link fr.labsticc.framework.settings.model.settings.PropertyAssociation#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyAssociation#getProperty()
	 * @see #getPropertyAssociation()
	 * @generated
	 */
	EReference getPropertyAssociation_Property();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.ServiceDescriptor <em>Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Descriptor</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ServiceDescriptor
	 * @generated
	 */
	EClass getServiceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.ServiceDescriptor#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ServiceDescriptor#getProviderId()
	 * @see #getServiceDescriptor()
	 * @generated
	 */
	EAttribute getServiceDescriptor_ProviderId();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.settings.model.settings.ServiceDescriptor#getServiceClass <em>Service Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Class</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ServiceDescriptor#getServiceClass()
	 * @see #getServiceDescriptor()
	 * @generated
	 */
	EAttribute getServiceDescriptor_ServiceClass();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.ModelInterface <em>Model Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Interface</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ModelInterface
	 * @generated
	 */
	EClass getModelInterface();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.settings.model.settings.ScopeProvider <em>Scope Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Provider</em>'.
	 * @see fr.labsticc.framework.settings.model.settings.ScopeProvider
	 * @generated
	 */
	EClass getScopeProvider();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SettingsFactory getSettingsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.IdentifiedElementImpl <em>Identified Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.IdentifiedElementImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getIdentifiedElement()
		 * @generated
		 */
		EClass IDENTIFIED_ELEMENT = eINSTANCE.getIdentifiedElement();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIED_ELEMENT__ID = eINSTANCE.getIdentifiedElement_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIED_ELEMENT__NAME = eINSTANCE.getIdentifiedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIED_ELEMENT__DESCRIPTION = eINSTANCE.getIdentifiedElement_Description();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl <em>Modeling Language Associations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageAssociationsImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelingLanguageAssociations()
		 * @generated
		 */
		EClass MODELING_LANGUAGE_ASSOCIATIONS = eINSTANCE.getModelingLanguageAssociations();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION = eINSTANCE.getModelingLanguageAssociations_Specification();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE = eINSTANCE.getModelingLanguageAssociations_Language();

		/**
		 * The meta object literal for the '<em><b>Types Associations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS = eINSTANCE.getModelingLanguageAssociations_TypesAssociations();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsSpecificationImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getSettingsSpecification()
		 * @generated
		 */
		EClass SETTINGS_SPECIFICATION = eINSTANCE.getSettingsSpecification();

		/**
		 * The meta object literal for the '<em><b>Languages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SETTINGS_SPECIFICATION__LANGUAGES = eINSTANCE.getSettingsSpecification_Languages();

		/**
		 * The meta object literal for the '<em><b>Language Associations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS = eINSTANCE.getSettingsSpecification_LanguageAssociations();

		/**
		 * The meta object literal for the '<em><b>Category Associations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS = eINSTANCE.getSettingsSpecification_CategoryAssociations();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl <em>Property To Types Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyToTypesAssociationImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyToTypesAssociation()
		 * @generated
		 */
		EClass PROPERTY_TO_TYPES_ASSOCIATION = eINSTANCE.getPropertyToTypesAssociation();

		/**
		 * The meta object literal for the '<em><b>Associated Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES = eINSTANCE.getPropertyToTypesAssociation_AssociatedTypes();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_TO_TYPES_ASSOCIATION__PARENT = eINSTANCE.getPropertyToTypesAssociation_Parent();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS = eINSTANCE.getPropertyToTypesAssociation_Containers();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_TO_TYPES_ASSOCIATION__CONDITION = eINSTANCE.getPropertyToTypesAssociation_Condition();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageImpl <em>Modeling Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.ModelingLanguageImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelingLanguage()
		 * @generated
		 */
		EClass MODELING_LANGUAGE = eINSTANCE.getModelingLanguage();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE__SPECIFICATION = eINSTANCE.getModelingLanguage_Specification();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE__PACKAGES = eINSTANCE.getModelingLanguage_Packages();

		/**
		 * The meta object literal for the '<em><b>File Extensions</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODELING_LANGUAGE__FILE_EXTENSIONS = eINSTANCE.getModelingLanguage_FileExtensions();

		/**
		 * The meta object literal for the '<em><b>Model Interface</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODELING_LANGUAGE__MODEL_INTERFACE = eINSTANCE.getModelingLanguage_ModelInterface();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.CategoryImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Sub Categories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__SUB_CATEGORIES = eINSTANCE.getCategory_SubCategories();

		/**
		 * The meta object literal for the '<em><b>Parent Category</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATEGORY__PARENT_CATEGORY = eINSTANCE.getCategory_ParentCategory();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyToCategoriesAssociationImpl <em>Property To Categories Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyToCategoriesAssociationImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyToCategoriesAssociation()
		 * @generated
		 */
		EClass PROPERTY_TO_CATEGORIES_ASSOCIATION = eINSTANCE.getPropertyToCategoriesAssociation();

		/**
		 * The meta object literal for the '<em><b>Associated Categories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_TO_CATEGORIES_ASSOCIATION__ASSOCIATED_CATEGORIES = eINSTANCE.getPropertyToCategoriesAssociation_AssociatedCategories();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.PropertyAssociationImpl <em>Property Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.PropertyAssociationImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getPropertyAssociation()
		 * @generated
		 */
		EClass PROPERTY_ASSOCIATION = eINSTANCE.getPropertyAssociation();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_ASSOCIATION__PROPERTY = eINSTANCE.getPropertyAssociation_Property();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.ServiceDescriptorImpl <em>Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.ServiceDescriptorImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getServiceDescriptor()
		 * @generated
		 */
		EClass SERVICE_DESCRIPTOR = eINSTANCE.getServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_DESCRIPTOR__PROVIDER_ID = eINSTANCE.getServiceDescriptor_ProviderId();

		/**
		 * The meta object literal for the '<em><b>Service Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_DESCRIPTOR__SERVICE_CLASS = eINSTANCE.getServiceDescriptor_ServiceClass();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.ModelInterfaceImpl <em>Model Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.ModelInterfaceImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getModelInterface()
		 * @generated
		 */
		EClass MODEL_INTERFACE = eINSTANCE.getModelInterface();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.settings.model.settings.impl.ScopeProviderImpl <em>Scope Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.settings.model.settings.impl.ScopeProviderImpl
		 * @see fr.labsticc.framework.settings.model.settings.impl.SettingsPackageImpl#getScopeProvider()
		 * @generated
		 */
		EClass SCOPE_PROVIDER = eINSTANCE.getScopeProvider();

	}

} //SettingsPackage
