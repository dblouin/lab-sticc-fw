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
package fr.labsticc.framework.emf.core.message;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.emf.core.object.EMFSmartObjectLocalized;

public abstract class EMFMessage<M extends IMessage<String, M>> extends EMFSmartObjectLocalized<M> implements IMessage<String, M> {

	private static final long serialVersionUID = -7615123753954354087L;
	
	private Throwable cause = null;
	
	/**
	 * The cached value of the '{@link #getInfo() <em>Info</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInfo()
	 * @generated
	 * @ordered
	 */
	protected M info;

	/**
	 * This is true if the Info containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean infoESet;
	
	@Override
	public Throwable getCause() {
		return cause;
	}

	@Override
	public Severity getSeverity() {
		final String severityStr = getSeverityString();
		
		if ( severityStr == null || "".equals( severityStr ) ) {
			return null;
		}
		
		return Severity.valueOf( Severity.class, severityStr );
	}

	@Override
	public void setCause( Throwable p_cause ) {
		cause = p_cause;
	}
	
	public abstract String getSeverityString();

	public abstract void setSeverityString( String p_severity );

	@Override
	public void setSeverity( final Severity p_severity ) {
		setSeverityString( p_severity == null ? null : p_severity.name() );
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public M getInfo() {
		return info;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public abstract void unsetInfo();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetInfo() {
		return infoESet;
	}
}
