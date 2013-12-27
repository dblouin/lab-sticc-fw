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
package fr.labsticc.framework.settings.model.settings.util;

import fr.labsticc.framework.settings.model.settings.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage
 * @generated
 */
public class SettingsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SettingsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SettingsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SettingsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SettingsSwitch<Adapter> modelSwitch =
		new SettingsSwitch<Adapter>() {
			@Override
			public Adapter caseIdentifiedElement(IdentifiedElement object) {
				return createIdentifiedElementAdapter();
			}
			@Override
			public Adapter caseModelingLanguageAssociations(ModelingLanguageAssociations object) {
				return createModelingLanguageAssociationsAdapter();
			}
			@Override
			public Adapter caseSettingsSpecification(SettingsSpecification object) {
				return createSettingsSpecificationAdapter();
			}
			@Override
			public Adapter casePropertyToTypesAssociation(PropertyToTypesAssociation object) {
				return createPropertyToTypesAssociationAdapter();
			}
			@Override
			public Adapter caseModelingLanguage(ModelingLanguage object) {
				return createModelingLanguageAdapter();
			}
			@Override
			public Adapter caseCategory(Category object) {
				return createCategoryAdapter();
			}
			@Override
			public Adapter casePropertyToCategoriesAssociation(PropertyToCategoriesAssociation object) {
				return createPropertyToCategoriesAssociationAdapter();
			}
			@Override
			public Adapter casePropertyAssociation(PropertyAssociation object) {
				return createPropertyAssociationAdapter();
			}
			@Override
			public Adapter caseServiceDescriptor(ServiceDescriptor object) {
				return createServiceDescriptorAdapter();
			}
			@Override
			public Adapter caseModelInterface(ModelInterface object) {
				return createModelInterfaceAdapter();
			}
			@Override
			public Adapter caseScopeProvider(ScopeProvider object) {
				return createScopeProviderAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.IdentifiedElement <em>Identified Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.IdentifiedElement
	 * @generated
	 */
	public Adapter createIdentifiedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations <em>Modeling Language Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguageAssociations
	 * @generated
	 */
	public Adapter createModelingLanguageAssociationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.SettingsSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.SettingsSpecification
	 * @generated
	 */
	public Adapter createSettingsSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation <em>Property To Types Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToTypesAssociation
	 * @generated
	 */
	public Adapter createPropertyToTypesAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.ModelingLanguage <em>Modeling Language</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.ModelingLanguage
	 * @generated
	 */
	public Adapter createModelingLanguageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.Category
	 * @generated
	 */
	public Adapter createCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation <em>Property To Categories Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyToCategoriesAssociation
	 * @generated
	 */
	public Adapter createPropertyToCategoriesAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.PropertyAssociation <em>Property Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.PropertyAssociation
	 * @generated
	 */
	public Adapter createPropertyAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.ServiceDescriptor <em>Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.ServiceDescriptor
	 * @generated
	 */
	public Adapter createServiceDescriptorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.ModelInterface <em>Model Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.ModelInterface
	 * @generated
	 */
	public Adapter createModelInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fr.labsticc.framework.settings.model.settings.ScopeProvider <em>Scope Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fr.labsticc.framework.settings.model.settings.ScopeProvider
	 * @generated
	 */
	public Adapter createScopeProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SettingsAdapterFactory
