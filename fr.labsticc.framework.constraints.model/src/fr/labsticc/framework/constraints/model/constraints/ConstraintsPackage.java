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
package fr.labsticc.framework.constraints.model.constraints;

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
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsFactory
 * @model kind="package"
 * @generated
 */
public interface ConstraintsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "constraints";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///fr/labsticc/framework/constraints";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cst";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConstraintsPackage eINSTANCE = fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl.init();

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.IdentifiedElementImpl <em>Identified Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.IdentifiedElementImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getIdentifiedElement()
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
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguagesSpecificationImpl <em>Constraint Languages Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguagesSpecificationImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getConstraintLanguagesSpecification()
	 * @generated
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Languages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION__LANGUAGES = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint Languages Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGES_SPECIFICATION_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguageImpl <em>Constraint Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguageImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getConstraintLanguage()
	 * @generated
	 */
	int CONSTRAINT_LANGUAGE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGE__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGE__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGE__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGE__SPECIFICATION = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_LANGUAGE_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl <em>Formal Constraint Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalConstraintLanguage()
	 * @generated
	 */
	int FORMAL_CONSTRAINT_LANGUAGE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__ID = CONSTRAINT_LANGUAGE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__NAME = CONSTRAINT_LANGUAGE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__DESCRIPTION = CONSTRAINT_LANGUAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__SPECIFICATION = CONSTRAINT_LANGUAGE__SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Libraries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpreter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Persister</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__PERSISTER = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Viewer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__VIEWER = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Formal Constraint Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_CONSTRAINT_LANGUAGE_FEATURE_COUNT = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.NaturalConstraintLanguageImpl <em>Natural Constraint Language</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.NaturalConstraintLanguageImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getNaturalConstraintLanguage()
	 * @generated
	 */
	int NATURAL_CONSTRAINT_LANGUAGE = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_CONSTRAINT_LANGUAGE__ID = CONSTRAINT_LANGUAGE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_CONSTRAINT_LANGUAGE__NAME = CONSTRAINT_LANGUAGE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_CONSTRAINT_LANGUAGE__DESCRIPTION = CONSTRAINT_LANGUAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_CONSTRAINT_LANGUAGE__SPECIFICATION = CONSTRAINT_LANGUAGE__SPECIFICATION;

	/**
	 * The number of structural features of the '<em>Natural Constraint Language</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_CONSTRAINT_LANGUAGE_FEATURE_COUNT = CONSTRAINT_LANGUAGE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl <em>Formal Language Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalLanguageLibrary()
	 * @generated
	 */
	int FORMAL_LANGUAGE_LIBRARY = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__EXPRESSION = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__DEFAULT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Language</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY__LANGUAGE = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Formal Language Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_LIBRARY_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ExpressionImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__ID = IDENTIFIED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__NAME = IDENTIFIED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__DESCRIPTION = IDENTIFIED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__LANGUAGE = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TEXT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageExpressionImpl <em>Formal Language Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageExpressionImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalLanguageExpression()
	 * @generated
	 */
	int FORMAL_LANGUAGE_EXPRESSION = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__NAME = EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__DESCRIPTION = EXPRESSION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__LANGUAGE = EXPRESSION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The feature id for the '<em><b>Used Libraries</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARIES = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Used Library Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARY_NAMES = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Used Library Ids</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARY_IDS = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Formal Language Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMAL_LANGUAGE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.NaturalLanguageExpressionImpl <em>Natural Language Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.NaturalLanguageExpressionImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getNaturalLanguageExpression()
	 * @generated
	 */
	int NATURAL_LANGUAGE_EXPRESSION = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION__NAME = EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION__DESCRIPTION = EXPRESSION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION__LANGUAGE = EXPRESSION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION__TEXT = EXPRESSION__TEXT;

	/**
	 * The number of structural features of the '<em>Natural Language Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_LANGUAGE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ServiceDescriptorImpl <em>Service Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ServiceDescriptorImpl
	 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getServiceDescriptor()
	 * @generated
	 */
	int SERVICE_DESCRIPTOR = 9;

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
	 * The feature id for the '<em><b>Service Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__SERVICE_CLASS = IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR__PROVIDER_ID = IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_DESCRIPTOR_FEATURE_COUNT = IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification <em>Constraint Languages Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Languages Specification</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification
	 * @generated
	 */
	EClass getConstraintLanguagesSpecification();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification#getLanguages <em>Languages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Languages</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintLanguagesSpecification#getLanguages()
	 * @see #getConstraintLanguagesSpecification()
	 * @generated
	 */
	EReference getConstraintLanguagesSpecification_Languages();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage <em>Constraint Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Language</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage
	 * @generated
	 */
	EClass getConstraintLanguage();

	/**
	 * Returns the meta object for the container reference '{@link fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage#getSpecification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Specification</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintLanguage#getSpecification()
	 * @see #getConstraintLanguage()
	 * @generated
	 */
	EReference getConstraintLanguage_Specification();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage <em>Formal Constraint Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Constraint Language</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage
	 * @generated
	 */
	EClass getFormalConstraintLanguage();

	/**
	 * Returns the meta object for the containment reference list '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraries <em>Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Libraries</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraries()
	 * @see #getFormalConstraintLanguage()
	 * @generated
	 */
	EReference getFormalConstraintLanguage_Libraries();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getInterpreter <em>Interpreter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interpreter</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getInterpreter()
	 * @see #getFormalConstraintLanguage()
	 * @generated
	 */
	EReference getFormalConstraintLanguage_Interpreter();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getPersister <em>Persister</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Persister</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getPersister()
	 * @see #getFormalConstraintLanguage()
	 * @generated
	 */
	EReference getFormalConstraintLanguage_Persister();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getViewer <em>Viewer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Viewer</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getViewer()
	 * @see #getFormalConstraintLanguage()
	 * @generated
	 */
	EReference getFormalConstraintLanguage_Viewer();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraryDirectory <em>Library Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Library Directory</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraryDirectory()
	 * @see #getFormalConstraintLanguage()
	 * @generated
	 */
	EAttribute getFormalConstraintLanguage_LibraryDirectory();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.NaturalConstraintLanguage <em>Natural Constraint Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Natural Constraint Language</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.NaturalConstraintLanguage
	 * @generated
	 */
	EClass getNaturalConstraintLanguage();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary <em>Formal Language Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Language Library</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary
	 * @generated
	 */
	EClass getFormalLanguageLibrary();

	/**
	 * Returns the meta object for the containment reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getExpression()
	 * @see #getFormalLanguageLibrary()
	 * @generated
	 */
	EReference getFormalLanguageLibrary_Expression();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#isDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#isDefault()
	 * @see #getFormalLanguageLibrary()
	 * @generated
	 */
	EAttribute getFormalLanguageLibrary_Default();

	/**
	 * Returns the meta object for the container reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Language</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage()
	 * @see #getFormalLanguageLibrary()
	 * @generated
	 */
	EReference getFormalLanguageLibrary_Language();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the reference '{@link fr.labsticc.framework.constraints.model.constraints.Expression#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Language</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.Expression#getLanguage()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_Language();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.Expression#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.Expression#getText()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_Text();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression <em>Formal Language Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formal Language Expression</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression
	 * @generated
	 */
	EClass getFormalLanguageExpression();

	/**
	 * Returns the meta object for the reference list '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraries <em>Used Libraries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used Libraries</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraries()
	 * @see #getFormalLanguageExpression()
	 * @generated
	 */
	EReference getFormalLanguageExpression_UsedLibraries();

	/**
	 * Returns the meta object for the attribute list '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraryNames <em>Used Library Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Used Library Names</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraryNames()
	 * @see #getFormalLanguageExpression()
	 * @generated
	 */
	EAttribute getFormalLanguageExpression_UsedLibraryNames();

	/**
	 * Returns the meta object for the attribute list '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraryIds <em>Used Library Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Used Library Ids</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getUsedLibraryIds()
	 * @see #getFormalLanguageExpression()
	 * @generated
	 */
	EAttribute getFormalLanguageExpression_UsedLibraryIds();

	/**
	 * Returns the meta object for the reference '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression#getType()
	 * @see #getFormalLanguageExpression()
	 * @generated
	 */
	EReference getFormalLanguageExpression_Type();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.NaturalLanguageExpression <em>Natural Language Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Natural Language Expression</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.NaturalLanguageExpression
	 * @generated
	 */
	EClass getNaturalLanguageExpression();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor <em>Service Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Descriptor</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor
	 * @generated
	 */
	EClass getServiceDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getServiceClass <em>Service Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Service Class</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getServiceClass()
	 * @see #getServiceDescriptor()
	 * @generated
	 */
	EAttribute getServiceDescriptor_ServiceClass();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getProviderId <em>Provider Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provider Id</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getProviderId()
	 * @see #getServiceDescriptor()
	 * @generated
	 */
	EAttribute getServiceDescriptor_ProviderId();

	/**
	 * Returns the meta object for class '{@link fr.labsticc.framework.constraints.model.constraints.IdentifiedElement <em>Identified Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identified Element</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.IdentifiedElement
	 * @generated
	 */
	EClass getIdentifiedElement();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getId()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Id();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getName()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see fr.labsticc.framework.constraints.model.constraints.IdentifiedElement#getDescription()
	 * @see #getIdentifiedElement()
	 * @generated
	 */
	EAttribute getIdentifiedElement_Description();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConstraintsFactory getConstraintsFactory();

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
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguagesSpecificationImpl <em>Constraint Languages Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguagesSpecificationImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getConstraintLanguagesSpecification()
		 * @generated
		 */
		EClass CONSTRAINT_LANGUAGES_SPECIFICATION = eINSTANCE.getConstraintLanguagesSpecification();

		/**
		 * The meta object literal for the '<em><b>Languages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_LANGUAGES_SPECIFICATION__LANGUAGES = eINSTANCE.getConstraintLanguagesSpecification_Languages();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguageImpl <em>Constraint Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintLanguageImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getConstraintLanguage()
		 * @generated
		 */
		EClass CONSTRAINT_LANGUAGE = eINSTANCE.getConstraintLanguage();

		/**
		 * The meta object literal for the '<em><b>Specification</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT_LANGUAGE__SPECIFICATION = eINSTANCE.getConstraintLanguage_Specification();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl <em>Formal Constraint Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalConstraintLanguage()
		 * @generated
		 */
		EClass FORMAL_CONSTRAINT_LANGUAGE = eINSTANCE.getFormalConstraintLanguage();

		/**
		 * The meta object literal for the '<em><b>Libraries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES = eINSTANCE.getFormalConstraintLanguage_Libraries();

		/**
		 * The meta object literal for the '<em><b>Interpreter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER = eINSTANCE.getFormalConstraintLanguage_Interpreter();

		/**
		 * The meta object literal for the '<em><b>Persister</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_CONSTRAINT_LANGUAGE__PERSISTER = eINSTANCE.getFormalConstraintLanguage_Persister();

		/**
		 * The meta object literal for the '<em><b>Viewer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_CONSTRAINT_LANGUAGE__VIEWER = eINSTANCE.getFormalConstraintLanguage_Viewer();

		/**
		 * The meta object literal for the '<em><b>Library Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY = eINSTANCE.getFormalConstraintLanguage_LibraryDirectory();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.NaturalConstraintLanguageImpl <em>Natural Constraint Language</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.NaturalConstraintLanguageImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getNaturalConstraintLanguage()
		 * @generated
		 */
		EClass NATURAL_CONSTRAINT_LANGUAGE = eINSTANCE.getNaturalConstraintLanguage();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl <em>Formal Language Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageLibraryImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalLanguageLibrary()
		 * @generated
		 */
		EClass FORMAL_LANGUAGE_LIBRARY = eINSTANCE.getFormalLanguageLibrary();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_LANGUAGE_LIBRARY__EXPRESSION = eINSTANCE.getFormalLanguageLibrary_Expression();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_LANGUAGE_LIBRARY__DEFAULT = eINSTANCE.getFormalLanguageLibrary_Default();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_LANGUAGE_LIBRARY__LANGUAGE = eINSTANCE.getFormalLanguageLibrary_Language();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ExpressionImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__LANGUAGE = eINSTANCE.getExpression_Language();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__TEXT = eINSTANCE.getExpression_Text();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageExpressionImpl <em>Formal Language Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.FormalLanguageExpressionImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getFormalLanguageExpression()
		 * @generated
		 */
		EClass FORMAL_LANGUAGE_EXPRESSION = eINSTANCE.getFormalLanguageExpression();

		/**
		 * The meta object literal for the '<em><b>Used Libraries</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARIES = eINSTANCE.getFormalLanguageExpression_UsedLibraries();

		/**
		 * The meta object literal for the '<em><b>Used Library Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARY_NAMES = eINSTANCE.getFormalLanguageExpression_UsedLibraryNames();

		/**
		 * The meta object literal for the '<em><b>Used Library Ids</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMAL_LANGUAGE_EXPRESSION__USED_LIBRARY_IDS = eINSTANCE.getFormalLanguageExpression_UsedLibraryIds();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORMAL_LANGUAGE_EXPRESSION__TYPE = eINSTANCE.getFormalLanguageExpression_Type();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.NaturalLanguageExpressionImpl <em>Natural Language Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.NaturalLanguageExpressionImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getNaturalLanguageExpression()
		 * @generated
		 */
		EClass NATURAL_LANGUAGE_EXPRESSION = eINSTANCE.getNaturalLanguageExpression();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.ServiceDescriptorImpl <em>Service Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ServiceDescriptorImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getServiceDescriptor()
		 * @generated
		 */
		EClass SERVICE_DESCRIPTOR = eINSTANCE.getServiceDescriptor();

		/**
		 * The meta object literal for the '<em><b>Service Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_DESCRIPTOR__SERVICE_CLASS = eINSTANCE.getServiceDescriptor_ServiceClass();

		/**
		 * The meta object literal for the '<em><b>Provider Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_DESCRIPTOR__PROVIDER_ID = eINSTANCE.getServiceDescriptor_ProviderId();

		/**
		 * The meta object literal for the '{@link fr.labsticc.framework.constraints.model.constraints.impl.IdentifiedElementImpl <em>Identified Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.IdentifiedElementImpl
		 * @see fr.labsticc.framework.constraints.model.constraints.impl.ConstraintsPackageImpl#getIdentifiedElement()
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

	}

} //ConstraintsPackage
