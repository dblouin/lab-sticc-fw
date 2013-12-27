package fr.labsticc.framework.core.exception;

import fr.labsticc.framework.core.exception.FunctionalException;

public class CyclicDependencyException extends FunctionalException {

	private static final long serialVersionUID = -7340016164643121704L;
	
	public CyclicDependencyException() {
		this( null );
	}

	public CyclicDependencyException( final String p_message ) {
		super( p_message );
	}
}
