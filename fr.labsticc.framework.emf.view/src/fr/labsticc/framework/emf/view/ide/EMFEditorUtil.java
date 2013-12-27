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
package fr.labsticc.framework.emf.view.ide;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class EMFEditorUtil {

	/**
	 * Open the proper editor for the model object from its resource file extension (according 
	 * to IDE editor file associations preferences) and selects it.
	 * 
	 * @param p_modelObjects
	 * @throws PartInitException
	 */
	public static void selectIntoEditor( final List<? extends EObject> p_modelObjects ) 
	throws PartInitException {
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		
		if ( window != null && window.getActivePage() != null ) {
			for ( final EObject modelObject : p_modelObjects ) {
				if ( modelObject.eResource() != null ) {
					final URI uri = modelObject.eResource().getURI();
					final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile( new Path( uri.toPlatformString( true ) ) );
				
					final IEditorPart editor = IDE.openEditor( window.getActivePage(), file );
					editor.getEditorSite().getSelectionProvider().setSelection( new StructuredSelection( p_modelObjects ) );
					
					break;
				}
			}
		}
	}
	
//	private static Object findElement( 	final EObject p_element,
//										final TreeViewer p_viewer ) {
//		for ( final TreeItem item : p_viewer.getTree().getItems() ) {
//			final Object itemEObject = findElement( p_element, item );
//		
//			if ( itemEObject != null ) {
//				return itemEObject;
//			}
//		}
//		
//		return null;
//	}
//
//	private static Object findElement( 	final EObject p_element,
//										final TreeItem p_item ) {
//		final Object treeObject = AdapterFactoryEditingDomain.unwrap( p_item.getData() );
//		
//		if ( treeObject instanceof EObject ) {
//			final EObject eObject = (EObject) treeObject;
//			
//			if ( EcoreUtil.equals( p_element, eObject ) ) {
//				return p_item.getData();
//			}
//		}
//
//		for ( final TreeItem item : p_item.getItems() ) {
//			final Object foundObject = findElement( p_element, item );
//			
//			if ( foundObject != null ) {
//				return foundObject;
//			}
//		}
//		
//		return null;
//	}
	
	public static EditingDomain findEditingDomain( 	final URI p_uri,
													final boolean pb_restoreEditor ) {
		final IEditingDomainProvider editorEditingDomainProvider = findEditorEditingDomainProvider( p_uri, pb_restoreEditor );
		
		if ( editorEditingDomainProvider != null ) {
			return editorEditingDomainProvider.getEditingDomain();
		}
		
		return null;
	}
	
	public static String getEditorInputName( final URI p_uri ) {
		if ( p_uri.isPlatformResource() ) {
			return p_uri.toPlatformString( true );
		}

		return p_uri.toString();
	}
	
	public static String getResourceName( final IEditorPart p_editorPart ) {
        final IEditorInput editorInput = p_editorPart.getEditorInput();

    	if ( editorInput instanceof IFileEditorInput ) {
        	return ( (IFileEditorInput) editorInput ).getFile().getFullPath().toString();
        }

    	if ( editorInput instanceof IURIEditorInput ) {
        	return ( (IURIEditorInput) editorInput ).getURI().toString();
        }

        return editorInput == null ? null : editorInput.getName();
	}
	
	public static IEditingDomainProvider findEditorEditingDomainProvider( 	final URI p_uri,
																			final boolean pb_restoreEditor ) {
		final String editorInputName = getEditorInputName( p_uri );

		for ( final IWorkbenchWindow window : PlatformUI.getWorkbench().getWorkbenchWindows() ) {
		    for ( final IWorkbenchPage page : window.getPages() ) {
		        for ( final IEditorReference editorRef : page.getEditorReferences() ) {
			        final IEditorPart editor = editorRef.getEditor( pb_restoreEditor );
			        
			        if ( editor != null ) {
			        	final String resourceName = getResourceName( editor );
	
				        if ( editorInputName.equals( resourceName ) && editor instanceof IEditingDomainProvider ) {
				        	return (IEditingDomainProvider) editor;
				        }
			        }
		        }
		    }
		}
		
		return null;
	}
}
