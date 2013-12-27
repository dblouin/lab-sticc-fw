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
package fr.labsticc.framework.constraints.ide.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Shell;

import fr.labsticc.framework.constraints.model.constraints.ConstraintsPackage;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageExpression;
import fr.labsticc.framework.constraints.model.constraints.FormalLanguageLibrary;

public class UsedLibrarySelectionDialog extends FeatureEditorDialog {

	public UsedLibrarySelectionDialog( 	final Shell p_parent, 
										final ILabelProvider p_labelProvider, 
										final FormalLanguageExpression p_expression,
										final List<FormalLanguageLibrary> p_choice ) {
		super( 	p_parent,
				p_labelProvider,
				p_expression,
				ConstraintsPackage.eINSTANCE.getFormalLanguageExpression_UsedLibraries(), 
				"Select Constraint Libraries",
				p_choice );
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EList<FormalLanguageLibrary> getResult() {
	   return (EList<FormalLanguageLibrary>) super.getResult();
	}
	
	public Collection<String> selectedLibraryIds() {
		final Collection<String> libraries = new ArrayList<String>();
		
		for ( final FormalLanguageLibrary library : getResult() ) {
			libraries.add( library.getId() );
		}
		
		return libraries;
	}
}
