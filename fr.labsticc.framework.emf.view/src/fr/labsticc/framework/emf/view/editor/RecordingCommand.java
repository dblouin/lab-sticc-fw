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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.emf.transaction.internal.EMFTransactionPlugin;
import org.eclipse.emf.transaction.internal.EMFTransactionStatusCodes;
import org.eclipse.emf.transaction.internal.l10n.Messages;
import org.eclipse.emf.transaction.util.ConditionalRedoCommand;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * Copied for EMF transaction and modified so that it can be executed with a null or non transactional editing 
 * domain.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
@SuppressWarnings("restriction")
public abstract class RecordingCommand extends AbstractCommand implements ConditionalRedoCommand {

	private final EditingDomain editingDomain;
	private Transaction transaction;
	private TransactionChangeDescription change;

	/**
	 * Initializes me with the editing domain in which I am to be executed.
	 * 
	 * @param domain my domain
	 */
	public RecordingCommand( EditingDomain domain ) {
		super();

		this.editingDomain = domain;
	}

	/**
	 * Initializes me with my editing domain and a human-readable label.
	 * 
	 * @param domain my domain
	 * @param label my user-friendly label
	 */
	public RecordingCommand(TransactionalEditingDomain domain, String label) {
		super(label);

		this.editingDomain = domain;
	}

	/**
	 * Initializes me with my editing domain, label, and more expansive
	 * description.
	 * 
	 * @param domain my domain
	 * @param label my label
	 * @param description my long description
	 */
	public RecordingCommand(TransactionalEditingDomain domain, String label, String description) {
		super(label, description);

		this.editingDomain = domain;
	}

	/**
	 * Subclasses should override this if they have more preparation to do.
	 * By default, the result is just <code>true</code>.
	 */
	@Override
	protected boolean prepare() {
		return true;
	}

	/**
	 * Implements the execution with automatic recording of undo information.
	 * Delegates the actual model changes to the subclass's implementation of
	 * the {@link #doExecute()} method.
	 * 
	 * @see #doExecute()
	 */
	public final void execute() {
		Transaction nested = null;
		final InternalTransactionalEditingDomain internalDomain = editingDomain instanceof InternalTransactionalEditingDomain ? (InternalTransactionalEditingDomain) editingDomain : null;

		if ( internalDomain != null && ( isNestedCommand() || isTriggerCommand() ) && isUndoable() ) {
			// need to create a nested transaction so that we can capture its
			//    changes for undo/redo
			
			try {
				nested = internalDomain.startTransaction(false, null);
			}
			catch ( final InterruptedException p_ex ) {
				// can't proceed with non-undoable changes
				internalDomain.getActiveTransaction().abort(	new Status(	IStatus.ERROR,
																			EMFTransactionPlugin.getPluginId(),
																			EMFTransactionStatusCodes.PRECOMMIT_INTERRUPTED,
																			Messages.precommitInterrupted, 
																			p_ex ) );
			}
		}

		try {
			// invoke the subclass before getting the transaction, because if an
			//    exception occurs, we don't want to be undoable
			doExecute();

			transaction = internalDomain == null ? null : internalDomain.getActiveTransaction();
		} 
		finally {        
			if (nested != null) {
				if ( transaction == null ) {
					// failed to execute.  Roll back
					nested.rollback();
				}
				else {
					try {
						nested.commit();
					}
					catch (RollbackException e) {
						// propagate the rollback
						((InternalTransaction) transaction).abort(e.getStatus());
					}
				}
			}
		}
	}

	/**
	 * I can be undone if I successfully recorded the changes that I executed.
	 * Subclasses would not normally need to override this method.
	 */
	@Override
	public boolean canUndo() {
		return canApplyChange();
	}

	/**
	 * I can be redone if I successfully recorded the changes that I executed.
	 * Subclasses would not normally need to override this method.
	 */
	public boolean canRedo() {
		return canApplyChange();
	}

	private boolean canApplyChange() {
		if ((change == null) && (transaction != null)) {
			change = transaction.getChangeDescription();
		}

		return (change == null) || change.canApply();
	}

	/**
	 * Undoes the changes that I recorded.
	 * Subclasses would not normally need to override this method.
	 * 
	 * @throws IllegalStateException if I am not {@linkplain #canUndo() undoable}
	 * 
	 * @see #canUndo()
	 */
	@Override
	public final void undo() {
		if (!canUndo()) {
			throw new IllegalStateException("command is not undoable"); //$NON-NLS-1$
		}

		if (change != null) {
			change.applyAndReverse();
		}
	}

	/**
	 * Redoes the changes that I recorded.
	 * Subclasses would not normally need to override this method.
	 * 
	 * @throws IllegalStateException if I am not {@linkplain #canRedo() redoable}
	 * 
	 * @see #canRedo()
	 */
	public final void redo() {
		if (!canRedo()) {
			throw new IllegalStateException("command is not redoable"); //$NON-NLS-1$
		}

		if (change != null) {
			change.applyAndReverse();
		}
	}

	/**
	 * Implemented by subclasses to perform the necessary changes in the model.
	 * These changes are applied by direct manipulation of the model objects,
	 * <em>not</em> by executing commands.
	 */
	protected abstract void doExecute();

	// Documentation copied from the inherited specification
	@Override
	public Command chain(Command command) {
		return new ConditionalRedoCommand.Compound().chain(this).chain(command);
	}

	private Transaction getActiveTransaction() {
		if ( editingDomain == null || !( editingDomain instanceof InternalTransactionalEditingDomain) ) {
			return null;
		}
		
		return ( (InternalTransactionalEditingDomain) editingDomain ).getActiveTransaction();
	}

	/**
	 * Queries whether I am a nested command, not executing as the root command
	 * of the active transaction.
	 * 
	 * @return whether I am not the root command being executed in a transaction
	 */
	private boolean isNestedCommand() {
		boolean result = false;

		Transaction tx = getActiveTransaction();
		if (tx != null) {
			Object rootCommand = tx.getOptions().get(
					TransactionImpl.OPTION_EXECUTING_COMMAND);

			result = (rootCommand != null) && !rootCommand.equals(this);
		}

		return result;
	}

	/**
	 * Queries whether I am executing in the context of a trigger transaction.
	 * That is to say, whether I am a trigger command.
	 * 
	 * @return whether the active transaction is a trigger transaction
	 */
	private boolean isTriggerCommand() {
		boolean result = false;
		Transaction tx = getActiveTransaction();

		while (!result && (tx != null)) {
			result = Boolean.TRUE.equals(tx.getOptions().get(
					TransactionImpl.OPTION_IS_TRIGGER_TRANSACTION));
			tx = tx.getParent();
		}

		return result;
	}

	/**
	 * Queries whether I am executing in the context of a transaction that is
	 * intended to be undoable.
	 * 
	 * @return whether the active transaction is recording undo information
	 */
	private boolean isUndoable() {
		boolean result = true;
		Transaction tx = getActiveTransaction();

		while (result && (tx != null)) {
			result = !Boolean.TRUE.equals(tx.getOptions().get(Transaction.OPTION_NO_UNDO))
			&& !Boolean.TRUE.equals(tx.getOptions().get(Transaction.OPTION_UNPROTECTED));
			tx = tx.getParent();
		}

		return result;
	}

	/**
	 * Extends the inherited implementation by disposing my change description,
	 * if any.
	 */
	@Override
	public void dispose() {
		super.dispose();

		if (change != null) {
			TransactionUtil.dispose(change);
		}
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
}
