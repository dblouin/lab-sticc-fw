package fr.labsticc.framework.settings.model.settings;

import fr.labsticc.framework.core.exception.FunctionalException;

public class AssociatedPropertyException extends FunctionalException {

	private static final long serialVersionUID = -3485158387307629876L;

	public AssociatedPropertyException( final String p_message ) {
		super( p_message );
	}
}
