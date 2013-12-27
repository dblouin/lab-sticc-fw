package fr.labsticc.framework.constraints.core.exception;

public class ConstraintParsingException extends ConstraintException {

	private static final long serialVersionUID = -4690562586256685970L;

	public ConstraintParsingException() {
		super();
	}

	public ConstraintParsingException( final String p_message ) {
		super( p_message );
	}
	
	public ConstraintParsingException( final Object p_diagnostic ) {
		super( p_diagnostic );
	}

	public ConstraintParsingException( 	final Object p_diagnostic,
										final String p_message ) {
		super( p_diagnostic, p_message );
	}
}
