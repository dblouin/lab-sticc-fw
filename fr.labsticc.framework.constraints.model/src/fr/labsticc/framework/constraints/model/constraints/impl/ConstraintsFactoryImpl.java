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

import fr.labsticc.framework.constraints.model.constraints.*;

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
public class ConstraintsFactoryImpl extends EFactoryImpl implements ConstraintsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConstraintsFactory init() {
		try {
			ConstraintsFactory theConstraintsFactory = (ConstraintsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///fr/labsticc/framework/constraints"); 
			if (theConstraintsFactory != null) {
				return theConstraintsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConstraintsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintsFactoryImpl() {
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
			case ConstraintsPackage.CONSTRAINT_LANGUAGES_SPECIFICATION: return createConstraintLanguagesSpecification();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE: return createFormalConstraintLanguage();
			case ConstraintsPackage.NATURAL_CONSTRAINT_LANGUAGE: return createNaturalConstraintLanguage();
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY: return createFormalLanguageLibrary();
			case ConstraintsPackage.FORMAL_LANGUAGE_EXPRESSION: return createFormalLanguageExpression();
			case ConstraintsPackage.NATURAL_LANGUAGE_EXPRESSION: return createNaturalLanguageExpression();
			case ConstraintsPackage.SERVICE_DESCRIPTOR: return createServiceDescriptor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintLanguagesSpecification createConstraintLanguagesSpecification() {
		ConstraintLanguagesSpecificationImpl constraintLanguagesSpecification = new ConstraintLanguagesSpecificationImpl();
		return constraintLanguagesSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalConstraintLanguage createFormalConstraintLanguage() {
		FormalConstraintLanguageImpl formalConstraintLanguage = new FormalConstraintLanguageImpl();
		return formalConstraintLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NaturalConstraintLanguage createNaturalConstraintLanguage() {
		NaturalConstraintLanguageImpl naturalConstraintLanguage = new NaturalConstraintLanguageImpl();
		return naturalConstraintLanguage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalLanguageLibrary createFormalLanguageLibrary() {
		FormalLanguageLibraryImpl formalLanguageLibrary = new FormalLanguageLibraryImpl();
		return formalLanguageLibrary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalLanguageExpression createFormalLanguageExpression() {
		FormalLanguageExpressionImpl formalLanguageExpression = new FormalLanguageExpressionImpl();
		return formalLanguageExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NaturalLanguageExpression createNaturalLanguageExpression() {
		NaturalLanguageExpressionImpl naturalLanguageExpression = new NaturalLanguageExpressionImpl();
		return naturalLanguageExpression;
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
	public ConstraintsPackage getConstraintsPackage() {
		return (ConstraintsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConstraintsPackage getPackage() {
		return ConstraintsPackage.eINSTANCE;
	}

} //ConstraintsFactoryImpl
