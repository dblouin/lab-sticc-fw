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
package fr.labsticc.framework.constraints.model.constraints.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import fr.labsticc.framework.constraints.model.constraints.ConstraintsFactory;
import fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage;
import fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage;

/**
 * This is the item provider adapter for a {@link fr.labsticc.framework.constraints.model.constraints.FormalConstraintLanguage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FormalConstraintLanguageItemProvider
	extends ConstraintLanguageItemProvider
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
	public FormalConstraintLanguageItemProvider(AdapterFactory adapterFactory) {
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

			addLibraryDirectoryPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Library Directory feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLibraryDirectoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_FormalConstraintLanguage_libraryDirectory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_FormalConstraintLanguage_libraryDirectory_feature", "_UI_FormalConstraintLanguage_type"),
				 ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES);
			childrenFeatures.add(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER);
			childrenFeatures.add(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER);
			childrenFeatures.add(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__VIEWER);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns FormalConstraintLanguage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/FormalConstraintLanguage"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((FormalConstraintLanguage)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_FormalConstraintLanguage_type") :
			getString("_UI_FormalConstraintLanguage_type") + " " + label;
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

		switch (notification.getFeatureID(FormalConstraintLanguage.class)) {
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARY_DIRECTORY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES:
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER:
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER:
			case ConstraintsPackage.FORMAL_CONSTRAINT_LANGUAGE__VIEWER:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

		newChildDescriptors.add
			(createChildParameter
				(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__LIBRARIES,
				 ConstraintsFactory.eINSTANCE.createFormalLanguageLibrary()));

		newChildDescriptors.add
			(createChildParameter
				(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER,
				 ConstraintsFactory.eINSTANCE.createServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER,
				 ConstraintsFactory.eINSTANCE.createServiceDescriptor()));

		newChildDescriptors.add
			(createChildParameter
				(ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__VIEWER,
				 ConstraintsFactory.eINSTANCE.createServiceDescriptor()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__INTERPRETER ||
			childFeature == ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__PERSISTER ||
			childFeature == ConstraintsPackage.Literals.FORMAL_CONSTRAINT_LANGUAGE__VIEWER;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
