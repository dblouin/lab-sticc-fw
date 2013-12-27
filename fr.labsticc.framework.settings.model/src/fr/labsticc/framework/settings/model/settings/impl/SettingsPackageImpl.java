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

import fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage;
import fr.labsticc.framework.settings.model.settings.Category;
import fr.labsticc.framework.settings.model.settings.IdentifiedElement;
import fr.labsticc.framework.settings.model.settings.ModelInterface;
import fr.labsticc.framework.settings.model.settings.ModelingLanguage;
import fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations;
import fr.labsticc.framework.settings.model.settings.PropertyAssociation;
import fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation;
import fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation;
import fr.labsticc.framework.settings.model.settings.ScopeProvider;
import fr.labsticc.framework.settings.model.settings.ServiceDescriptor;
import fr.labsticc.framework.settings.model.settings.SettingsFactory;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;
import fr.labsticc.framework.settings.model.settings.SettingsSpecification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SettingsPackageImpl extends EPackageImpl implements SettingsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass identifiedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelingLanguageAssociationsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass settingsSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyToTypesAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelingLanguageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass categoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyToCategoriesAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceDescriptorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeProviderEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SettingsPackageImpl() {
		super(eNS_URI, SettingsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SettingsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SettingsPackage init() {
		if (isInited) return (SettingsPackage)EPackage.Registry.INSTANCE.getEPackage(SettingsPackage.eNS_URI);

		// Obtain or create and register package
		SettingsPackageImpl theSettingsPackage = (SettingsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SettingsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SettingsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ConstraintsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSettingsPackage.createPackageContents();

		// Initialize created meta-data
		theSettingsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSettingsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SettingsPackage.eNS_URI, theSettingsPackage);
		return theSettingsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIdentifiedElement() {
		return identifiedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentifiedElement_Id() {
		return (EAttribute)identifiedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentifiedElement_Name() {
		return (EAttribute)identifiedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIdentifiedElement_Description() {
		return (EAttribute)identifiedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelingLanguageAssociations() {
		return modelingLanguageAssociationsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguageAssociations_Specification() {
		return (EReference)modelingLanguageAssociationsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguageAssociations_Language() {
		return (EReference)modelingLanguageAssociationsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguageAssociations_TypesAssociations() {
		return (EReference)modelingLanguageAssociationsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSettingsSpecification() {
		return settingsSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSettingsSpecification_Languages() {
		return (EReference)settingsSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSettingsSpecification_LanguageAssociations() {
		return (EReference)settingsSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSettingsSpecification_CategoryAssociations() {
		return (EReference)settingsSpecificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyToTypesAssociation() {
		return propertyToTypesAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyToTypesAssociation_AssociatedTypes() {
		return (EReference)propertyToTypesAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyToTypesAssociation_Parent() {
		return (EReference)propertyToTypesAssociationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyToTypesAssociation_Containers() {
		return (EAttribute)propertyToTypesAssociationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyToTypesAssociation_Condition() {
		return (EReference)propertyToTypesAssociationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelingLanguage() {
		return modelingLanguageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguage_Specification() {
		return (EReference)modelingLanguageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguage_Packages() {
		return (EReference)modelingLanguageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModelingLanguage_FileExtensions() {
		return (EAttribute)modelingLanguageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModelingLanguage_ModelInterface() {
		return (EReference)modelingLanguageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCategory() {
		return categoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCategory_SubCategories() {
		return (EReference)categoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCategory_ParentCategory() {
		return (EReference)categoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyToCategoriesAssociation() {
		return propertyToCategoriesAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyToCategoriesAssociation_AssociatedCategories() {
		return (EReference)propertyToCategoriesAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyAssociation() {
		return propertyAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyAssociation_Property() {
		return (EReference)propertyAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceDescriptor() {
		return serviceDescriptorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceDescriptor_ProviderId() {
		return (EAttribute)serviceDescriptorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getServiceDescriptor_ServiceClass() {
		return (EAttribute)serviceDescriptorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModelInterface() {
		return modelInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopeProvider() {
		return scopeProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsFactory getSettingsFactory() {
		return (SettingsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		identifiedElementEClass = createEClass(IDENTIFIED_ELEMENT);
		createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__ID);
		createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__NAME);
		createEAttribute(identifiedElementEClass, IDENTIFIED_ELEMENT__DESCRIPTION);

		modelingLanguageAssociationsEClass = createEClass(MODELING_LANGUAGE_ASSOCIATIONS);
		createEReference(modelingLanguageAssociationsEClass, MODELING_LANGUAGE_ASSOCIATIONS__SPECIFICATION);
		createEReference(modelingLanguageAssociationsEClass, MODELING_LANGUAGE_ASSOCIATIONS__LANGUAGE);
		createEReference(modelingLanguageAssociationsEClass, MODELING_LANGUAGE_ASSOCIATIONS__TYPES_ASSOCIATIONS);

		settingsSpecificationEClass = createEClass(SETTINGS_SPECIFICATION);
		createEReference(settingsSpecificationEClass, SETTINGS_SPECIFICATION__LANGUAGES);
		createEReference(settingsSpecificationEClass, SETTINGS_SPECIFICATION__LANGUAGE_ASSOCIATIONS);
		createEReference(settingsSpecificationEClass, SETTINGS_SPECIFICATION__CATEGORY_ASSOCIATIONS);

		propertyToTypesAssociationEClass = createEClass(PROPERTY_TO_TYPES_ASSOCIATION);
		createEReference(propertyToTypesAssociationEClass, PROPERTY_TO_TYPES_ASSOCIATION__ASSOCIATED_TYPES);
		createEReference(propertyToTypesAssociationEClass, PROPERTY_TO_TYPES_ASSOCIATION__PARENT);
		createEAttribute(propertyToTypesAssociationEClass, PROPERTY_TO_TYPES_ASSOCIATION__CONTAINERS);
		createEReference(propertyToTypesAssociationEClass, PROPERTY_TO_TYPES_ASSOCIATION__CONDITION);

		modelingLanguageEClass = createEClass(MODELING_LANGUAGE);
		createEReference(modelingLanguageEClass, MODELING_LANGUAGE__SPECIFICATION);
		createEReference(modelingLanguageEClass, MODELING_LANGUAGE__PACKAGES);
		createEAttribute(modelingLanguageEClass, MODELING_LANGUAGE__FILE_EXTENSIONS);
		createEReference(modelingLanguageEClass, MODELING_LANGUAGE__MODEL_INTERFACE);

		categoryEClass = createEClass(CATEGORY);
		createEReference(categoryEClass, CATEGORY__SUB_CATEGORIES);
		createEReference(categoryEClass, CATEGORY__PARENT_CATEGORY);

		propertyToCategoriesAssociationEClass = createEClass(PROPERTY_TO_CATEGORIES_ASSOCIATION);
		createEReference(propertyToCategoriesAssociationEClass, PROPERTY_TO_CATEGORIES_ASSOCIATION__ASSOCIATED_CATEGORIES);

		propertyAssociationEClass = createEClass(PROPERTY_ASSOCIATION);
		createEReference(propertyAssociationEClass, PROPERTY_ASSOCIATION__PROPERTY);

		serviceDescriptorEClass = createEClass(SERVICE_DESCRIPTOR);
		createEAttribute(serviceDescriptorEClass, SERVICE_DESCRIPTOR__PROVIDER_ID);
		createEAttribute(serviceDescriptorEClass, SERVICE_DESCRIPTOR__SERVICE_CLASS);

		modelInterfaceEClass = createEClass(MODEL_INTERFACE);

		scopeProviderEClass = createEClass(SCOPE_PROVIDER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ConstraintsPackage theConstraintsPackage = (ConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(ConstraintsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		modelingLanguageAssociationsEClass.getESuperTypes().add(this.getIdentifiedElement());
		settingsSpecificationEClass.getESuperTypes().add(this.getIdentifiedElement());
		propertyToTypesAssociationEClass.getESuperTypes().add(this.getIdentifiedElement());
		propertyToTypesAssociationEClass.getESuperTypes().add(this.getPropertyAssociation());
		modelingLanguageEClass.getESuperTypes().add(this.getIdentifiedElement());
		categoryEClass.getESuperTypes().add(this.getIdentifiedElement());
		propertyToCategoriesAssociationEClass.getESuperTypes().add(this.getPropertyAssociation());
		propertyAssociationEClass.getESuperTypes().add(this.getIdentifiedElement());
		serviceDescriptorEClass.getESuperTypes().add(this.getIdentifiedElement());
		modelInterfaceEClass.getESuperTypes().add(this.getScopeProvider());
		scopeProviderEClass.getESuperTypes().add(this.getIdentifiedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(identifiedElementEClass, IdentifiedElement.class, "IdentifiedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIdentifiedElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentifiedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIdentifiedElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, IdentifiedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelingLanguageAssociationsEClass, ModelingLanguageAssociations.class, "ModelingLanguageAssociations", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelingLanguageAssociations_Specification(), this.getSettingsSpecification(), this.getSettingsSpecification_LanguageAssociations(), "specification", null, 1, 1, ModelingLanguageAssociations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingLanguageAssociations_Language(), this.getModelingLanguage(), null, "language", null, 1, 1, ModelingLanguageAssociations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingLanguageAssociations_TypesAssociations(), this.getPropertyToTypesAssociation(), this.getPropertyToTypesAssociation_Parent(), "typesAssociations", null, 0, -1, ModelingLanguageAssociations.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(settingsSpecificationEClass, SettingsSpecification.class, "SettingsSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSettingsSpecification_Languages(), this.getModelingLanguage(), this.getModelingLanguage_Specification(), "languages", null, 1, -1, SettingsSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSettingsSpecification_LanguageAssociations(), this.getModelingLanguageAssociations(), this.getModelingLanguageAssociations_Specification(), "languageAssociations", null, 0, -1, SettingsSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSettingsSpecification_CategoryAssociations(), this.getPropertyToCategoriesAssociation(), null, "categoryAssociations", null, 0, -1, SettingsSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyToTypesAssociationEClass, PropertyToTypesAssociation.class, "PropertyToTypesAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyToTypesAssociation_AssociatedTypes(), ecorePackage.getEClass(), null, "associatedTypes", null, 0, -1, PropertyToTypesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyToTypesAssociation_Parent(), this.getModelingLanguageAssociations(), this.getModelingLanguageAssociations_TypesAssociations(), "parent", null, 1, 1, PropertyToTypesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPropertyToTypesAssociation_Containers(), ecorePackage.getEBoolean(), "containers", null, 1, 1, PropertyToTypesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPropertyToTypesAssociation_Condition(), theConstraintsPackage.getFormalLanguageExpression(), null, "condition", null, 0, 1, PropertyToTypesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelingLanguageEClass, ModelingLanguage.class, "ModelingLanguage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModelingLanguage_Specification(), this.getSettingsSpecification(), this.getSettingsSpecification_Languages(), "specification", null, 1, 1, ModelingLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingLanguage_Packages(), ecorePackage.getEPackage(), null, "packages", null, 1, -1, ModelingLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelingLanguage_FileExtensions(), ecorePackage.getEString(), "fileExtensions", null, 1, -1, ModelingLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelingLanguage_ModelInterface(), this.getServiceDescriptor(), null, "modelInterface", null, 0, 1, ModelingLanguage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategory_SubCategories(), this.getCategory(), this.getCategory_ParentCategory(), "subCategories", null, 0, -1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategory_ParentCategory(), this.getCategory(), this.getCategory_SubCategories(), "parentCategory", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyToCategoriesAssociationEClass, PropertyToCategoriesAssociation.class, "PropertyToCategoriesAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyToCategoriesAssociation_AssociatedCategories(), this.getCategory(), null, "associatedCategories", null, 0, -1, PropertyToCategoriesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyAssociationEClass, PropertyAssociation.class, "PropertyAssociation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyAssociation_Property(), ecorePackage.getEReference(), null, "property", null, 1, 1, PropertyAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceDescriptorEClass, ServiceDescriptor.class, "ServiceDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getServiceDescriptor_ProviderId(), ecorePackage.getEString(), "providerId", null, 1, 1, ServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType(this.getModelInterface());
		g1.getETypeArguments().add(g2);
		initEAttribute(getServiceDescriptor_ServiceClass(), g1, "serviceClass", null, 1, 1, ServiceDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelInterfaceEClass, ModelInterface.class, "ModelInterface", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "getPropertyValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_component", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_property", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "getPropertyValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_component", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_property", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_unit", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "getDefaultPropertyValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_property", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, null, "setPropertyValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_component", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_property", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_value", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_unit", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEBoolean(), "isComponent", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_object", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEBoolean(), "isPropertyValue", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_object", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "getPropertyFromValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_propValue", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "componentTypes", 1, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_component", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEJavaObject(), "selectedModelObject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_editor", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_selection", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEBoolean(), "isModelResource", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEResource(), "p_resource", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelInterfaceEClass, ecorePackage.getEString(), "getLanguageName", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(modelInterfaceEClass, ecorePackage.getEResourceSet(), "getCustomResourcet", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEString(), "getElementName", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_element", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEString(), "getElementText", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_element", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(modelInterfaceEClass, ecorePackage.getEBoolean(), "isModelElement", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "p_object", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scopeProviderEClass, ScopeProvider.class, "ScopeProvider", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(scopeProviderEClass, ecorePackage.getEJavaObject(), "getScope", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "context", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scopeProviderEClass, ecorePackage.getEJavaObject(), "getElementFromScope", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "element", 1, 1, IS_UNIQUE, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SettingsPackageImpl
