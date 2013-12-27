/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.labsticc.framework.settings.model.settings;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getModelInterface()
 * @model abstract="true"
 * @generated
 */
public interface ModelInterface extends ScopeProvider {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model p_componentRequired="true" p_propertyRequired="true"
	 * @generated NOT
	 */
	Object getPropertyValue(Object p_component, Object p_property)
	throws AssociatedPropertyException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model p_componentRequired="true" p_propertyRequired="true"
	 * @generated NOT
	 */
	Object getPropertyValue(Object p_component, Object p_property, Object p_unit)
	throws AssociatedPropertyException;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * <!-- end-user-doc -->
	 * @model p_propertyRequired="true"
	 * @generated
	 */
	Object getDefaultPropertyValue(Object p_property);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model p_componentRequired="true" p_propertyRequired="true" p_valueRequired="true"
	 * @generated NOT
	 */
	void setPropertyValue(Object p_component, Object p_property, Object p_value, Object p_unit)
	throws AssociatedPropertyException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_objectRequired="true"
	 * @generated
	 */
	boolean isComponent(Object p_object);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_objectRequired="true"
	 * @generated
	 */
	boolean isPropertyValue(Object p_object);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model p_propValueRequired="true"
	 * @generated
	 */
	Object getPropertyFromValue(Object p_propValue);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_componentRequired="true"
	 * @generated
	 */
	EList<Object> componentTypes(Object p_component);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model p_editorRequired="true" p_selectionRequired="true"
	 * @generated
	 */
	Object selectedModelObject(Object p_editor, Object p_selection);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_resourceRequired="true"
	 * @generated
	 */
	boolean isModelResource(Resource p_resource);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" required="true"
	 * @generated
	 */
	String getLanguageName();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	ResourceSet getCustomResourcet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_elementRequired="true"
	 * @generated
	 */
	String getElementName(Object p_element);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_elementRequired="true"
	 * @generated
	 */
	String getElementText(Object p_element);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" p_objectRequired="true"
	 * @generated
	 */
	boolean isModelElement(Object p_object);
} // ModelInterface
