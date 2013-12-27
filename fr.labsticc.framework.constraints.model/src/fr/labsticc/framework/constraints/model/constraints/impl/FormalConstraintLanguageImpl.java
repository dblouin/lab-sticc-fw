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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;
import fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Formal Constraint Language</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl#getLibraries <em>Libraries</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl#getInterpreter <em>Interpreter</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl#getPersister <em>Persister</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl#getViewer <em>Viewer</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.impl.FormalConstraintLanguageImpl#getLibraryDirectory <em>Library Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormalConstraintLanguageImpl extends ConstraintLanguageImpl implements FormalConstraintLanguage {
	/**
	 * The cached value of the '{@link #getLibraries() <em>Libraries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraries()
	 * @generated
	 * @ordered
	 */
	protected EList<FormalLanguageLibrary> libraries;

	/**
	 * The cached value of the '{@link #getInterpreter() <em>Interpreter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterpreter()
	 * @generated
	 * @ordered
	 */
	protected ServiceDescriptor interpreter;

	/**
	 * The cached value of the '{@link #getPersister() <em>Persister</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersister()
	 * @generated
	 * @ordered
	 */
	protected ServiceDescriptor persister;

	/**
	 * The cached value of the '{@link #getViewer() <em>Viewer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewer()
	 * @generated
	 * @ordered
	 */
	protected ServiceDescriptor viewer;

	/**
	 * The default value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final String LIBRARY_DIRECTORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibraryDirectory() <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryDirectory()
	 * @generated
	 * @ordered
	 */
	protected String libraryDirectory = LIBRARY_DIRECTORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormalConstraintLanguageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormalLanguageLibrary> getLibraries() {
		if (libraries == null) {
			libraries = new EObjectContainmentWithInverseEList<FormalLanguageLibrary>(FormalLanguageLibrary.class, this, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES, ConstraintsPackage.FORMAL_LANGUAGE_LIBRARY__LANGUAGE);
		}
		return libraries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDescriptor getInterpreter() {
		return interpreter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterpreter(ServiceDescriptor newInterpreter, NotificationChain msgs) {
		ServiceDescriptor oldInterpreter = interpreter;
		interpreter = newInterpreter;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER, oldInterpreter, newInterpreter);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterpreter(ServiceDescriptor newInterpreter) {
		if (newInterpreter != interpreter) {
			NotificationChain msgs = null;
			if (interpreter != null)
				msgs = ((InternalEObject)interpreter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER, null, msgs);
			if (newInterpreter != null)
				msgs = ((InternalEObject)newInterpreter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER, null, msgs);
			msgs = basicSetInterpreter(newInterpreter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER, newInterpreter, newInterpreter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDescriptor getPersister() {
		return persister;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPersister(ServiceDescriptor newPersister, NotificationChain msgs) {
		ServiceDescriptor oldPersister = persister;
		persister = newPersister;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER, oldPersister, newPersister);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersister(ServiceDescriptor newPersister) {
		if (newPersister != persister) {
			NotificationChain msgs = null;
			if (persister != null)
				msgs = ((InternalEObject)persister).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER, null, msgs);
			if (newPersister != null)
				msgs = ((InternalEObject)newPersister).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER, null, msgs);
			msgs = basicSetPersister(newPersister, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER, newPersister, newPersister));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceDescriptor getViewer() {
		return viewer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetViewer(ServiceDescriptor newViewer, NotificationChain msgs) {
		ServiceDescriptor oldViewer = viewer;
		viewer = newViewer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER, oldViewer, newViewer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewer(ServiceDescriptor newViewer) {
		if (newViewer != viewer) {
			NotificationChain msgs = null;
			if (viewer != null)
				msgs = ((InternalEObject)viewer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER, null, msgs);
			if (newViewer != null)
				msgs = ((InternalEObject)newViewer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER, null, msgs);
			msgs = basicSetViewer(newViewer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER, newViewer, newViewer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLibraryDirectory() {
		return libraryDirectory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLibraryDirectory(String newLibraryDirectory) {
		String oldLibraryDirectory = libraryDirectory;
		libraryDirectory = newLibraryDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY, oldLibraryDirectory, libraryDirectory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public FormalLanguageLibrary libraryByName( final String p_name ) {
		for ( final FormalLanguageLibrary library : getLibraries() ) {
			if ( p_name.equals( library.getName() ) ) {
				return library;
			}
		}
		
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public FormalLanguageLibrary libraryById( final String p_id ) {
		for ( final FormalLanguageLibrary library : getLibraries() ) {
			if ( p_id.equals( library.getId() ) ) {
				return library;
			}
		}
		
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLibraries()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				return ((InternalEList<?>)getLibraries()).basicRemove(otherEnd, msgs);
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
				return basicSetInterpreter(null, msgs);
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
				return basicSetPersister(null, msgs);
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				return basicSetViewer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				return getLibraries();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
				return getInterpreter();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
				return getPersister();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				return getViewer();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY:
				return getLibraryDirectory();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				getLibraries().clear();
				getLibraries().addAll((Collection<? extends FormalLanguageLibrary>)newValue);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
				setInterpreter((ServiceDescriptor)newValue);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
				setPersister((ServiceDescriptor)newValue);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				setViewer((ServiceDescriptor)newValue);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY:
				setLibraryDirectory((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				getLibraries().clear();
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
				setInterpreter((ServiceDescriptor)null);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
				setPersister((ServiceDescriptor)null);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				setViewer((ServiceDescriptor)null);
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY:
				setLibraryDirectory(LIBRARY_DIRECTORY_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
				return libraries != null && !libraries.isEmpty();
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
				return interpreter != null;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
				return persister != null;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				return viewer != null;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY:
				return LIBRARY_DIRECTORY_EDEFAULT == null ? libraryDirectory != null : !LIBRARY_DIRECTORY_EDEFAULT.equals(libraryDirectory);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (libraryDirectory: ");
		result.append(libraryDirectory);
		result.append(')');
		return result.toString();
	}

} //FormalConstraintLanguageImpl
