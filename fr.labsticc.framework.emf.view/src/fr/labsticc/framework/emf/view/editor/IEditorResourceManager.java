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
package fr.labsticc.framework.emf.view.editor;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Shell;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.exception.SystemException;

public interface IEditorResourceManager extends IResourceChangeListener {

	Resource loadOrCreateResource( URI p_resURI );
	
	Resource loadResource( URI p_resURI );
	
	void saveResource( 	Shell p_shell,
						Resource p_resource )
	throws ResourceAccessException, InterruptedException;

	void saveResources( Shell p_shell )
	throws SystemException, InterruptedException;
	
	boolean hasRemovedResources();

	boolean hasChangedResources();
	
	Iterator<Resource> changedResources();
	
	Iterator<Resource> removedResources();
	
	void removeSavedResourcesFromChangedResources();
	
	void clearChangedResources();
	
	void clearRemovedResources();

	void clearSavedResources();
	
	void clearAllResources();

	void dispose();

	Collection<Diagnostic> getAllDiagnosticsKO();
	
	void inhibitChangeListening();

	void activateChangeListening();
	
	boolean isListeningForChangeEvents();
	
	EditingDomain getEditingDomain();

	boolean addEditorListener( final IEditorListener p_listener );
	
	boolean removeEditorListener( final IEditorListener p_listener );
	
	void addIgnoredResourceExtension( String p_extension );
	
	boolean removeIgnoredResourceExtension( String p_extension );
}
