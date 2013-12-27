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
package fr.labsticc.framework.constraints.model.constraints.util;

import fr.labsticc.framework.constraints.model.constraints.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage
 * @generated
 */
public class ConstraintsSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ConstraintsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintsSwitch() {
		if (modelPackage == null) {
			modelPackage = ConstraintsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ConstraintsPackage.IDENTIFIED_ELEMENT: {
				IdentifiedElement identifiedElement = (IdentifiedElement)theEObject;
				T result = caseIdentifiedElement(identifiedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.CONSTRAINT_LANGUAGES_SPECIFICATION: {
				ConstraintLanguagesSpecification constraintLanguagesSpecification = (ConstraintLanguagesSpecification)theEObject;
				T result = caseConstraintLanguagesSpecification(constraintLanguagesSpecification);
				if (result == null) result = caseIdentifiedElement(constraintLanguagesSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.CONSTRAINT_LANGUAGE: {
				ConstraintLanguage constraintLanguage = (ConstraintLanguage)theEObject;
				T result = caseConstraintLanguage(constraintLanguage);
				if (result == null) result = caseIdentifiedElement(constraintLanguage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE: {
				FormalConstraintLanguage formalConstraintLanguage = (FormalConstraintLanguage)theEObject;
				T result = caseFormalConstraintLanguage(formalConstraintLanguage);
				if (result == null) result = caseConstraintLanguage(formalConstraintLanguage);
				if (result == null) result = caseIdentifiedElement(formalConstraintLanguage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.NATURAL_CONSTRAINT_LANGUAGE: {
				NaturalConstraintLanguage naturalConstraintLanguage = (NaturalConstraintLanguage)theEObject;
				T result = caseNaturalConstraintLanguage(naturalConstraintLanguage);
				if (result == null) result = caseConstraintLanguage(naturalConstraintLanguage);
				if (result == null) result = caseIdentifiedElement(naturalConstraintLanguage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY: {
				FormalLanguageLibrary formalLanguageLibrary = (FormalLanguageLibrary)theEObject;
				T result = caseFormalLanguageLibrary(formalLanguageLibrary);
				if (result == null) result = caseIdentifiedElement(formalLanguageLibrary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = caseIdentifiedElement(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.FORMAL_LANGUAGE_EXPRESSION: {
				FormalLanguageExpression formalLanguageExpression = (FormalLanguageExpression)theEObject;
				T result = caseFormalLanguageExpression(formalLanguageExpression);
				if (result == null) result = caseExpression(formalLanguageExpression);
				if (result == null) result = caseIdentifiedElement(formalLanguageExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.NATURAL_LANGUAGE_EXPRESSION: {
				NaturalLanguageExpression naturalLanguageExpression = (NaturalLanguageExpression)theEObject;
				T result = caseNaturalLanguageExpression(naturalLanguageExpression);
				if (result == null) result = caseExpression(naturalLanguageExpression);
				if (result == null) result = caseIdentifiedElement(naturalLanguageExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ConstraintsPackage.SERVICE_DESCRIPTOR: {
				ServiceDescriptor serviceDescriptor = (ServiceDescriptor)theEObject;
				T result = caseServiceDescriptor(serviceDescriptor);
				if (result == null) result = caseIdentifiedElement(serviceDescriptor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Languages Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Languages Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintLanguagesSpecification(ConstraintLanguagesSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint Language</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint Language</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintLanguage(ConstraintLanguage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Constraint Language</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Constraint Language</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalConstraintLanguage(FormalConstraintLanguage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Natural Constraint Language</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Natural Constraint Language</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNaturalConstraintLanguage(NaturalConstraintLanguage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Language Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Language Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalLanguageLibrary(FormalLanguageLibrary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formal Language Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formal Language Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormalLanguageExpression(FormalLanguageExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Natural Language Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Natural Language Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNaturalLanguageExpression(NaturalLanguageExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service Descriptor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseServiceDescriptor(ServiceDescriptor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifiedElement(IdentifiedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ConstraintsSwitch
