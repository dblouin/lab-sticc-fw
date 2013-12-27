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
package fr.labsticc.framework.ide.util;

import java.util.Collection;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import fr.labsticc.framework.core.IResourceHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * A class to open editors of various kinds within the Eclipse IDE.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique BLOUIN</a>
 */
public class EditorUtil {

	public static void openEditors( final Collection<String> p_fileIds,
									final IResourceHandler p_resourceHandler ) 
	throws PartInitException, ResourceAccessException {
		for ( final String fileId : p_fileIds) {
			openEditor( fileId, p_resourceHandler );
		}
	}

	public static void openEditor( 	final String p_resourceId,
									final IResourceHandler p_resourceHandler ) 
	throws PartInitException, ResourceAccessException {
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		if ( window != null && window.getActivePage() != null ) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation( new Path( p_resourceId ) );
			
			if ( file == null ) {
				// The file may be outside the workspace
				IFileStore fileStore = EFS.getLocalFileSystem().getStore( new Path( p_resourceId ) );
				
				if ( !fileStore.fetchInfo().exists() ) {
					if ( p_resourceHandler == null ) {
						throw createResourceNotFoundException( p_resourceId );
					}
					
					final String location = p_resourceHandler.locateResource( p_resourceId );
					
					if ( location == null ) {
						throw createResourceNotFoundException( p_resourceId );
					}
					
					fileStore = EFS.getLocalFileSystem().getStore( new Path( location ) );

					if ( !fileStore.fetchInfo().exists() ) {
						throw createResourceNotFoundException( p_resourceId );
					}
				}

				IDE.openEditorOnFileStore( window.getActivePage(), fileStore );
			}
			else {
				IDE.openEditor( window.getActivePage(), file );
			}
		}
	}
	
	private static ResourceAccessException createResourceNotFoundException( final String p_resourceId ) {
		return new ResourceAccessException( "Unable to find resource " + p_resourceId );
	}
}
