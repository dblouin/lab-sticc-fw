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
package fr.labsticc.framework.settings.model.settings.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import fr.labsticc.framework.emf.view.editor.RuntimeRegistryItemProvider;
import fr.labsticc.framework.settings.model.settings.IdentifiedElement;
import fr.labsticc.framework.settings.model.settings.SettingsPackage;

/**
 * This is the item provider adapter for a {@link fr.labsticc.framework.settings.model.settings.IdentifiedElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
public class IdentifiedElementItemProvider extends RuntimeRegistryItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiedElementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addDescriptionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdentifiedElement_id_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdentifiedElement_id_feature", "_UI_IdentifiedElement_type"),
				 SettingsPackage.Literals.IDENTIFIED_ELEMENT__ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdentifiedElement_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdentifiedElement_name_feature", "_UI_IdentifiedElement_type"),
				 SettingsPackage.Literals.IDENTIFIED_ELEMENT__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Description feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_IdentifiedElement_description_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_IdentifiedElement_description_feature", "_UI_IdentifiedElement_type"),
				 SettingsPackage.Literals.IDENTIFIED_ELEMENT__DESCRIPTION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((IdentifiedElement)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_IdentifiedElement_type") :
			getString("_UI_IdentifiedElement_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(IdentifiedElement.class)) {
			case SettingsPackage.IDENTIFIED_ELEMENT__ID:
			case SettingsPackage.IDENTIFIED_ELEMENT__NAME:
			case SettingsPackage.IDENTIFIED_ELEMENT__DESCRIPTION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return SettingsEditPlugin.INSTANCE;
	}

	/**
	 * DB: Override this so that runtime registry packages can be used in the editor.
	 * DB: Not needed anymore since present in super class.
	 */
//	@Override
//	protected ItemPropertyDescriptor createItemPropertyDescriptor( 	final AdapterFactory adapterFactory,
//																	final ResourceLocator resourceLocator,
//																	final String displayName,
//																	final String description,
//																	final EStructuralFeature feature,
//																	final boolean isSettable,
//																	final boolean multiLine,
//																	final boolean sortChoices,
//																	final Object staticImage,
//																	final String category,
//																	final String[] filterFlags ) {
//	    return new ItemPropertyDescriptor(	adapterFactory,
//										 	resourceLocator,
//										    displayName,
//										    description,
//										    feature,
//										    isSettable,
//										    multiLine,
//										    sortChoices,
//										    staticImage,
//										    category,
//										    filterFlags ) {
//
//	        @Override
//	        public Collection<?> getChoiceOfValues(Object object) {
//	          Collection<?> result = super.getChoiceOfValues(object);
//	         
//	          if (feature instanceof EReference && object instanceof EObject) {
//	            @SuppressWarnings("unchecked")
//	            List<EObject> eObjects = (List<EObject>)(List<?>)new LinkedList<Object>(result);
//	            Resource resource = ((EObject)object).eResource();
//	            if (resource != null)
//	            {
//	              ResourceSet resourceSet = resource.getResourceSet();
//	              if (resourceSet != null)
//	              {
//	                Collection<EObject> visited = new HashSet<EObject>(eObjects);
//	                Registry packageRegistry = resourceSet.getPackageRegistry();
//	                for (String nsURI : packageRegistry.keySet())
//	                {
//	                  collectReachableObjectsOfType(visited, eObjects, packageRegistry.getEPackage(nsURI), feature.getEType());
//	                }
//	              }
//	            }
//	            result = eObjects;
//	          }
//	          return result;
//	        }
//	    };
//	}
}
