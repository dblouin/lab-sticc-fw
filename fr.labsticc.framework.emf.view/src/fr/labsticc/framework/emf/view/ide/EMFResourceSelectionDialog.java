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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class EMFResourceSelectionDialog extends ResourceDialog {
	
	private final String[] fileExtensions;
	
	private final boolean directorySelection;
	
	private final String filterPath;

	public EMFResourceSelectionDialog( 	final Shell p_parent,
										final String p_title,
										final int pi_style,
										final String[] p_fileExtensions ) {
		this( p_parent, p_title, pi_style, p_fileExtensions, false, null );
	}

	public EMFResourceSelectionDialog( 	final Shell p_parent,
										final String p_title,
										final int pi_style,
										final String[] p_fileExtensions,
										final boolean pb_directorySelection,
										final String p_filterPath ) {
		super( p_parent, p_title, SWT.OPEN | SWT.MULTI );
		
		fileExtensions = p_fileExtensions;
		directorySelection = pb_directorySelection;
		filterPath = p_filterPath;
	}
	
	@Override
	protected void prepareBrowseFileSystemButton( final Button p_btnBrowseFileSystem ) {
		p_btnBrowseFileSystem.addSelectionListener( new SelectionAdapter() {
			@Override
	        public void widgetSelected( final SelectionEvent p_event ) {
				if ( directorySelection ) {
					openFileSystemDirectoryDialog();
				}
				else {
					openFileSystemFileDialog();
				}
	         }
	       } );
	  }
	
	private void openFileSystemFileDialog() {
		final FileDialog fileDialog = new FileDialog( getShell(), style );
		fileDialog.setFilterExtensions( fileExtensions );
		fileDialog.setFilterPath( filterPath );
		fileDialog.open();
		
		if ( isMulti() ) {
			final String filterPath = fileDialog.getFilterPath();
            final String[] fileNames = fileDialog.getFileNames();
            final StringBuffer uris = new StringBuffer();
     
            for ( final String fileName : fileNames ) {
            	uris.append( URI.createFileURI( filterPath + File.separator + fileName ).toString() );
            	uris.append("  ");
            }             
     
            uriField.setText((uriField.getText() + "  " + uris.toString()).trim());
		}
		else {
			final String fileName = fileDialog.getFileName();
		    
			if ( fileName != null ) {
		    	uriField.setText(URI.createFileURI( filterPath + File.separator + fileName).toString() );
		    }
		}
	}
	
	private void openFileSystemDirectoryDialog() {
		final DirectoryDialog dialog = new DirectoryDialog( getShell(), style );
		dialog.setFilterPath( filterPath );
		final String dirPath = dialog.open();
		
		if ( dirPath != null ) {
			uriField.setText( URI.createFileURI( dirPath ).toString() );
        }
	}

	  /**
	   * Called to prepare the Browse Workspace button, this implementation adds a selection listener
	   * that creates an appropriate {@link WorkspaceResourceDialog}.  
	   */
	@Override
	protected void prepareBrowseWorkspaceButton( final Button p_btnBrowseWorkspace ) {
		final List<ViewerFilter> filters = new ArrayList<ViewerFilter>();
		filters.add( new ViewerFilter() {
			
			@Override
			public boolean select( 	final Viewer p_viewer, 
									final Object p_parentElement, 
									final Object p_element ) {
				if ( fileExtensions != null && p_element instanceof IResource ) {
					for ( final String ext : fileExtensions ) {
						final String resExt = ( (IResource) p_element ).getFileExtension();
						
						if ( resExt == null || ext.equalsIgnoreCase( "*." + resExt ) ) {
							return true;
						}
					}
					
					return false;
					//return Arrays.binarySearch( fileExtensions, "\\*\\." + ( (IResource) p_element ).getFileExtension() ) != - 1;
				}

				return true;
			}
		} );
		
		p_btnBrowseWorkspace.addSelectionListener( new SelectionAdapter() {
			@Override
	        public void widgetSelected( final SelectionEvent p_event ) {
				if ( directorySelection ) {
					openWorkspaceDirectoryDialog( filters );
				}
				else {
					openWorkspaceFileDialog( filters );
				}
//	           if ( isMulti() ) {
//	             final StringBuffer uris = new StringBuffer();
//
//	             final IFile[] files = WorkspaceResourceDialog.openFileSelection(	getShell(), 
//	            		 															null, 
//	            		 															null, 
//	            		 															true, 
//	            		 															null, 
//	            		 															filters );
//	             for ( final IFile file : files ) {
//	               uris.append(URI.createPlatformResourceURI( file.getFullPath().toString(), true ) );
//	               uris.append("  ");
//	             }
//	             
//	             uriField.setText( ( uriField.getText() + "  " + uris.toString() ).trim() );
//	           }
//	           else {
//	             IFile file = null;
//
//	             if ( isSave() ) {
//	               file = WorkspaceResourceDialog.openNewFile(getShell(), null, null, null, null);
//	             }
//	             else {
//	               final IFile[] files = WorkspaceResourceDialog.openFileSelection(	getShell(), 
//		            		   														null, 
//		            		   														null, 
//		            		   														false, 
//		            		   														null, 
//		            		   														filters );
//	               if ( files.length != 0 ) {
//	                 file = files[0];
//	               }
//	             }
//	             
//	             if ( file != null ) {
//	               uriField.setText(URI.createPlatformResourceURI(file.getFullPath().toString(), true).toString());
//	             }
//	           }
	         }
	       } );      
	}
	
	private void openWorkspaceFileDialog( final List<ViewerFilter> p_filters ) {
        if ( isMulti() ) {
            final StringBuffer uris = new StringBuffer();

            final IFile[] files = WorkspaceResourceDialog.openFileSelection(	getShell(), 
           		 																null, 
           		 																null, 
           		 																true, 
           		 																null, 
           		 																p_filters );
            for ( final IFile file : files ) {
            	uris.append(URI.createPlatformResourceURI( file.getFullPath().toString(), true ) );
            	uris.append("  ");
            }
            
            uriField.setText( ( uriField.getText() + "  " + uris.toString() ).trim() );
        }
        else {
        	IFile file = null;

        	if ( isSave() ) {
        	 file = WorkspaceResourceDialog.openNewFile(getShell(), null, null, null, null);
        	}
        	else {
        		final IFile[] files = WorkspaceResourceDialog.openFileSelection(	getShell(), 
        				  															null, 
		            		   														null, 
		            		   														false, 
		            		   														null, 
		            		   														p_filters );
        		if ( files.length != 0 ) {
        			file = files[0];
        		}
        	}
            
        	if ( file != null ) {
        		uriField.setText(URI.createPlatformResourceURI(file.getFullPath().toString(), true).toString());
        	}
        }
	}
	
	private void openWorkspaceDirectoryDialog( final List<ViewerFilter> p_filters ) {
        final IContainer[] folders = WorkspaceResourceDialog.openFolderSelection(	getShell(), 
																					null, 
																					null, 
																					isMulti(), 
																					new String[] { filterPath }, 
																					p_filters );

        final StringBuffer uris = new StringBuffer();

        for ( final IContainer folder : folders ) {
        	uris.append(URI.createPlatformResourceURI( folder.getFullPath().toString(), true ) );
        	uris.append("  ");
        }
        
        uriField.setText( ( uriField.getText() + "  " + uris.toString() ).trim() );
	}
}
