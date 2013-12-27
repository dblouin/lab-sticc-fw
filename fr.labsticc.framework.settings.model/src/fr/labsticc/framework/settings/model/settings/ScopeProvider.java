/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package fr.labsticc.framework.settings.model.settings;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see fr.labsticc.framework.settings.model.settings.SettingsPackage#getScopeProvider()
 * @model abstract="true"
 * @generated
 */
public interface ScopeProvider extends IdentifiedElement {
	/**
	 * <!-- begin-user-doc -->
	 * Returns a list of elements visible for the specified context. This scope is defined per modeling language.
	 * <!-- end-user-doc -->
	 * @model contextRequired="true"
	 * @generated
	 */
	EList<Object> getScope(Object context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model elementRequired="true"
	 * @generated
	 */
	Object getElementFromScope(Object element);

} // ScopeProvider
