package fr.labsticc.framework.ide.action;

import org.eclipse.ui.actions.WorkspaceModifyOperation;

public abstract class ExceptionWorkspaceModifyOperation extends WorkspaceModifyOperation {
	
	protected Throwable exception;
	
	public ExceptionWorkspaceModifyOperation() {
		exception = null;
	}

	public Throwable getException() {
		return exception;
	}
}
