package fr.labsticc.framework.constraints.core.exception;

public class ConstraintInvalidReturnTypeException extends ConstraintException {

	private static final long serialVersionUID = 3935481582075006569L;

	public ConstraintInvalidReturnTypeException() {
		super();
	}

	public ConstraintInvalidReturnTypeException( final String p_message ) {
		super( p_message );
	}

	public ConstraintInvalidReturnTypeException( final Object p_diagnostic ) {
		super( p_diagnostic );
	}

	public ConstraintInvalidReturnTypeException( 	final Object p_diagnostic,
													final String p_message ) {
		super( p_diagnostic, p_message );
	}
}
