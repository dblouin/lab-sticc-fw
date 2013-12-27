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

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

import fr.labsticc.framework.core.exception.SystemException;

public abstract class AbstractEditorCommand<T> extends RecordingCommand {

	private Throwable exception;
	
	private T result;
	
	private final Object context;
	
	public AbstractEditorCommand( final Object p_context ) {
		super( getEditingDomain( p_context ) );

		context = p_context;
		result = null;
		exception = null;
	}

	@Override
	public final void doExecute() {
		try {
			executeCommand();
		}
		catch ( final Throwable p_th ) {
			exception = p_th;
		}
	}

	protected abstract void executeCommand()
	throws Throwable;
	
	public T getExecResult() {
		return result;
	}
	
	protected void setExecResult( final T p_result ) {
		result = p_result;
	}

	public Throwable getException() {
		return exception;
	}

	public Object getContext() {
		return context;
	}

	public T perform() 
	throws SystemException, InterruptedException {
		return perform( false, null, false );
	}
	
	public T perform( 	final boolean pb_save,
						final Map<?,?> p_saveOptions,
						final boolean pb_avoidUpdate ) 
	throws SystemException, InterruptedException {
		final EditingDomain editingDomain = getEditingDomain();

		if ( editingDomain == null ) {
			execute();
		}
		else {
			editingDomain.getCommandStack().execute( AbstractEditorCommand.this );
		}

		if ( pb_save ) {
			try {
				save( p_saveOptions );
			}
			catch ( final IOException p_ex ) {
				exception = p_ex;
			}
		}
		
		if ( exception == null ) {
			return getExecResult();
		}

		if ( exception instanceof SystemException ) {
			throw (SystemException) exception;
		}

		if ( exception instanceof InterruptedException ) {
			throw (InterruptedException) exception;
		}

		throw new SystemException( exception );
	}
	
	private void save( final Map<?, ?> p_saveOptions ) 
	throws IOException {
		final Resource resource = getResource();
		
		if ( resource == null ) {
			throw new IOException( "Resource of object " + context + " should not be null." );
		}

		resource.save( p_saveOptions );
		final EditingDomain editingDomain = getEditingDomain();
		
		if ( editingDomain != null && editingDomain.getCommandStack() instanceof BasicCommandStack ) {
			( (BasicCommandStack) editingDomain.getCommandStack() ).saveIsDone();
		}
	}

	private static EditingDomain getEditingDomain( final Object p_context ) {
		final EditingDomain domain = TransactionUtil.getEditingDomain( p_context );
		
		if ( domain != null ) {
			return domain;
		}
		
		// Allows to be usable with generated EMF tree editor.
		return AdapterFactoryEditingDomain.getEditingDomainFor( p_context );
	}
	
	public Resource getResource() {
		if ( context instanceof Resource ) {
			return (Resource) getContext();
		}
		
		if ( context instanceof EObject ) {
			final EObject eContext = (EObject) context;

			if ( eContext.eResource() != null ) {
				return eContext.eResource();
			}
			
			if ( eContext.eContainer() != null ) {
				return eContext.eContainer().eResource();
			}
		}
		
		return null;
	}
}
