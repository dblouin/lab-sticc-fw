/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.labsticc.framework.constraints.model.constraints;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getServiceClass <em>Service Class</em>}</li>
 *   <li>{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getProviderId <em>Provider Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getServiceDescriptor()
 * @model
 * @generated
 */
public interface ServiceDescriptor extends IdentifiedElement {
	/**
	 * Returns the value of the '<em><b>Service Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Class</em>' attribute.
	 * @see #setServiceClass(String)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getServiceDescriptor_ServiceClass()
	 * @model required="true"
	 * @generated
	 */
	String getServiceClass();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getServiceClass <em>Service Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Class</em>' attribute.
	 * @see #getServiceClass()
	 * @generated
	 */
	void setServiceClass(String value);

	/**
	 * Returns the value of the '<em><b>Provider Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provider Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provider Id</em>' attribute.
	 * @see #setProviderId(String)
	 * @see fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage#getServiceDescriptor_ProviderId()
	 * @model required="true"
	 * @generated
	 */
	String getProviderId();

	/**
	 * Sets the value of the '{@link fr.labsticc.framework.constraints.model.constraints.ServiceDescriptor#getProviderId <em>Provider Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provider Id</em>' attribute.
	 * @see #getProviderId()
	 * @generated
	 */
	void setProviderId(String value);

} // ServiceDescriptor
