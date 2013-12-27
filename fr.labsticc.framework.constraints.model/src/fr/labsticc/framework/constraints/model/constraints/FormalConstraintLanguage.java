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

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Constraint Language</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraries <em>Libraries</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getPersister <em>Persister</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getViewer <em>Viewer</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraryDirectory <em>Library Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage()
 * @model
 * @generated
 */
public interface FormalConstraintLanguage extends ConstraintLanguage {

	/**
	 * Returns the value of the '<em><b>Libraries</b></em>' containment reference list.
	 * The list contents are of type {@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary}.
	 * It is bidirectional and its opposite is '{@link fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Libraries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Libraries</em>' containment reference list.
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage_Libraries()
	 * @see fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary#getLanguage
	 * @model opposite="language" containment="true"
	 * @generated
	 */
	EList<FormalLanguageLibrary> getLibraries();

	/**
	 * Returns the value of the '<em><b>Interpreter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interpreter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interpreter</em>' containment reference.
	 * @see #setInterpreter(ServiceDescriptor)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage_Interpreter()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ServiceDescriptor getInterpreter();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getInterpreter <em>Interpreter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpreter</em>' containment reference.
	 * @see #getInterpreter()
	 * @generated
	 */
	void setInterpreter(ServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Persister</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persister</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persister</em>' containment reference.
	 * @see #setPersister(ServiceDescriptor)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage_Persister()
	 * @model containment="true"
	 * @generated
	 */
	ServiceDescriptor getPersister();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getPersister <em>Persister</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persister</em>' containment reference.
	 * @see #getPersister()
	 * @generated
	 */
	void setPersister(ServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Viewer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Viewer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Viewer</em>' containment reference.
	 * @see #setViewer(ServiceDescriptor)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage_Viewer()
	 * @model containment="true"
	 * @generated
	 */
	ServiceDescriptor getViewer();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getViewer <em>Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Viewer</em>' containment reference.
	 * @see #getViewer()
	 * @generated
	 */
	void setViewer(ServiceDescriptor value);

	/**
	 * Returns the value of the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Library Directory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Library Directory</em>' attribute.
	 * @see #setLibraryDirectory(String)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getFormalConstraintLanguage_LibraryDirectory()
	 * @model
	 * @generated
	 */
	String getLibraryDirectory();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage#getLibraryDirectory <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library Directory</em>' attribute.
	 * @see #getLibraryDirectory()
	 * @generated
	 */
	void setLibraryDirectory(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model nameRequired="true"
	 * @generated
	 */
	FormalLanguageLibrary libraryByName(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model idRequired="true"
	 * @generated
	 */
	FormalLanguageLibrary libraryById(String id);
} // FormalConstraintLanguage
