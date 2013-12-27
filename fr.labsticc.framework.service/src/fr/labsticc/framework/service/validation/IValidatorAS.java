package fr.labsticc.framework.service.validation;

import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.service.IService;

public interface IValidatorAS<Type> extends IService {

	void validate( Type p_data )
	throws FunctionalException;
}
