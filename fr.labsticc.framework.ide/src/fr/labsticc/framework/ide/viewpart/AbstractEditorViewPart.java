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
 ********************************************************************************
 * Copyright (c) 2009 Lab-STICC, Université de Bretagne-Sud.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the CeCILL-B FREE SOFTWARE LICENSE AGREEMENT
 * which accompanies this distribution, and is available at
 * http://www.cecill.info/licences/Licence_CeCILL-B_V1-en.html
 *
 * Contributors:
 *    Skander TURKI (Lab-STICC, UBS)
 *******************************************************************************
 * </copyright>
 *
 * $Id$
 */

package fr.labsticc.framework.ide.viewpart;

import java.util.Collection;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ScrolledPageBook;
import org.eclipse.ui.part.ViewPart;

import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.core.exception.IExceptionHandler;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.exception.SystemException;

/**
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 *
 */
public abstract class AbstractEditorViewPart<T> extends ViewPart implements ISelectionListener {

	private ScrolledPageBook pageBook;

	private final IExceptionHandler exceptionHandler;

	private final IPageFactory<T> pageFactory;
	
	private String defaultTitle;
	
	private ImageData defaultImageData;
	
	private IContributionItem[] defaultToolBarItems;
	private IContributionItem[] defaultMenuItems;
	
	//private ISelection previousSelection;

	public AbstractEditorViewPart( 	final IPageFactory<T> p_pageFactory,
									final IExceptionHandler p_exceptionHandler ) {
    	super();
    	
		pageFactory = p_pageFactory;	
    	exceptionHandler = p_exceptionHandler;
    	//previousSelection = null;
    }

    @Override
    public void selectionChanged( 	final IWorkbenchPart p_part, 
    								final ISelection p_selection ) {
    	//previousSelection = p_selection;

    	if ( getSite().getPage().isPartVisible( this ) ) {
        //	logger.debug( "Selection changed part visible" );
	    	final T modelObject = selectedModelObject( p_part, p_selection );
	    	
			try {
		    	final Object pageKey = pageFactory.getPageKey( modelObject );
				
				if ( !pageBook.hasPage( pageKey ) ) {
					final Composite parentPage = pageBook.createPage( pageKey );
					parentPage.setLayout( new FillLayout() );
					
					pageFactory.createPage( parentPage, pageKey, exceptionHandler );
				}
		
				switchPage( pageKey, modelObject );
			}
			catch ( final Throwable p_th ) {
				exceptionHandler.handleException( p_th, modelObject );
			}
    	}
	}
    
    private void switchPage( 	final Object p_pageKey,
    							final T p_modelObject ) 
    throws FunctionalException, SystemException {
		final IViewPartPage<T> previousPage = getCurrentPage(); 
		final T previousModelObject;
		
		if ( previousPage != null ) {
			previousPage.saveData();
			previousModelObject = previousPage.getSelectedObject();
		}
		else {
			previousModelObject = null;
		}

    	if ( p_modelObject != previousModelObject ) {
    		if ( isDifferentPage( previousModelObject, p_pageKey ) ) {
    			
//    			pageBook.removePage( p_pageKey );
//				final Composite parentPage = pageBook.createPage( p_pageKey );
//				parentPage.setLayout( new FillLayout() );
//				
//				pageFactory.createPage( parentPage, p_pageKey, exceptionHandler );
    			pageBook.showPage( p_pageKey );
    		}

    		final IViewPartPage<T> currentPage = getCurrentPage();
			custommizeViewForPage( currentPage );
			currentPage.displayData( p_modelObject );
    	}
    }
    
    /**
     * Return if the page needs to be shown. Showing the same page several  times causes the page size to be calculated
     * wrong once in a while, on windows at least.
     * @param p_previousModelObject
     * @param p_newPageKey
     * @return
     * @throws ResourceAccessException
     */
    private boolean isDifferentPage( final T p_previousModelObject,
    								final Object p_newPageKey ) 
    throws FunctionalException, SystemException {
    	
    	if ( p_previousModelObject == null || p_newPageKey == null ) {
    		return true;
    	}
    	
    	return !p_newPageKey.equals( pageFactory.getPageKey( p_previousModelObject ) );
    }
    
    private void custommizeViewForPage( final IViewPartPage<T> p_page ) {
    	final String title = p_page.getExternalTitle();
		setPartName( title == null ? defaultTitle : title );

		final Image image = p_page.getExternalImage();
		setTitleImage( image == null ? new Image( getSite().getShell().getDisplay(), defaultImageData ) : image );

		fillToolBar( p_page.getExternalToolbarActions() );
		fillMenus( p_page.getExternalMenuActions() );
    }

	private void fillToolBar( final Collection<IAction> p_actions ) {
		final IActionBars bars = getViewSite().getActionBars();
		final IToolBarManager manager = bars.getToolBarManager();
		
		if ( defaultToolBarItems == null )  {
			defaultToolBarItems = manager.getItems();
		}

		manager.removeAll();

		if ( p_actions == null ) {
			for ( final IContributionItem item : defaultToolBarItems ) {
				manager.add( item );
			}
		}
		else {
			for ( final IAction action : p_actions ) {
				manager.add( action );
			}
		}
		
		manager.update( false );
	}

	private void fillMenus( final Collection<IAction> p_actions ) {
		final IActionBars bars = getViewSite().getActionBars();
		final IMenuManager manager = bars.getMenuManager();
		
		if ( defaultMenuItems == null )  {
			defaultMenuItems = manager.getItems();
		}

		manager.removeAll();
		
		if ( p_actions == null ) {
			for ( final IContributionItem item : defaultMenuItems ) {
				manager.add( item );
			}
		}
		else {
			for ( final IAction action : p_actions ) {
				manager.add( action );
			}
		}

		manager.update();
	}
   
    @SuppressWarnings("unchecked")
	protected IViewPartPage<T> getCurrentPage() {
    	final Composite parentPage = (Composite) pageBook.getCurrentPage();
    	
    	if ( parentPage == null ) {
    		return null;
    	}
		
    	return (IViewPartPage<T>) parentPage.getChildren()[ 0 ];
    }

	@Override
	public void createPartControl( final Composite p_parent ) {
		p_parent.setLayout( new FillLayout() );
		pageBook = new ScrolledPageBook( p_parent, SWT.V_SCROLL | SWT.H_SCROLL );
		pageBook.setLayout( new FillLayout() );

		defaultTitle = getPartName();
		defaultImageData = getTitleImage().getImageData();
		getSite().getPage().addPostSelectionListener( this );
	}

	@Override
	public void setFocus() {
		final IEditorPart editor = getSite().getPage().getActiveEditor();
		final IWorkbenchPart part;
		
		if ( editor == null || editor.getSite() == null ) {
			part = null;
		}
		else {
			part = editor.getSite().getPart();
		}
		
		selectionChanged( part, getEditorSelection( editor ) );
	}
	
	protected ISelection getEditorSelection() {
		return getEditorSelection( getSite().getPage().getActiveEditor() );
	}
	
	protected static ISelection getEditorSelection( final IEditorPart p_editor ) {
		if ( p_editor != null ) {
			if ( p_editor.getEditorSite() == null ) {
				final IWorkbenchPage page = p_editor.getSite().getPage();
	
				if ( page != null ) {
					return page.getSelection( p_editor.getSite().getId() );
				}
			}
			else {
				if ( p_editor.getEditorSite() == null || p_editor.getEditorSite().getSelectionProvider() == null ) {
					return null;
				}
				
				return p_editor.getEditorSite().getSelectionProvider().getSelection();
			}
		}
		
		return null;
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removePostSelectionListener( this );
		
		if ( pageBook != null ) {
			pageBook.dispose();
		}
		
		super.dispose();
	}
	
	protected ScrolledPageBook getPageBook() {
		return pageBook;
	}

    protected IPageFactory<T> getPageFactory() {
		return pageFactory;
	}
	
	protected abstract T selectedModelObject( 	IWorkbenchPart p_part,
												ISelection p_selection );

	protected IExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}
//
//	protected ISelection getPreviousSelection() {
//		return previousSelection;
//	}
}
