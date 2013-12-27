package fr.labsticc.framework.service.validation;

import fr.labsticc.framework.core.exception.FunctionalException;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.message.Message;
import fr.labsticc.framework.core.util.NumberUtil;

public abstract class ValidatorBaseAbstract<T> implements IValidatorAS<T> {
	
	private static final IMessage.Severity DEFAULT_SEVERITY = IMessage.Severity.WARNING; 

	@Override
	public void validate( final T p_data ) 
	throws FunctionalException {
		if ( p_data == null ) {
			raiseErrorException( "Component to be validated cannot be null." );
		}
	}

	protected static void validateWarningNullValue( final Object p_value,
													final String p_valueName ) 
	throws FunctionalException {
		validateNullValue( p_value, p_valueName, IMessage.Severity.WARNING );
	}

	protected static void validateErrorNullValue( 	final Object p_value,
													final String p_valueName ) 
	throws FunctionalException {
		validateNullValue( p_value, p_valueName, IMessage.Severity.ERROR );
	}

	protected static void validateFatalNullValue( 	final Object p_value,
													final String p_valueName ) 
	throws FunctionalException {
		validateNullValue( p_value, p_valueName, IMessage.Severity.FATAL );
	}
	
	protected static void validateDefaultNullValue( final Object p_value,
													final String p_valueName ) 
	throws FunctionalException {
		validateNullValue( p_value, p_valueName, DEFAULT_SEVERITY );
	}
	
	protected static void validateNullValue( 	final Object p_value,
												final String p_valueName,
												final IMessage.Severity p_severity ) 
	throws FunctionalException {
		if ( p_value == null ) {
			raiseException( p_valueName + " not set.", p_severity );
		}
	}

	protected static void validateErrorRange( 	final Number p_value,
												final Number p_valueMin,
												final Number p_valueMax,
												final String p_valueName ) 
	throws FunctionalException {
		validateRange( p_value, p_valueMin, p_valueMax, p_valueName, IMessage.Severity.ERROR );
	}
	
	protected static void validateDefaultRange( final Number p_value,
												final Number p_valueMin,
												final Number p_valueMax,
												final String p_valueName ) 
	throws FunctionalException {
		validateRange( p_value, p_valueMin, p_valueMax, p_valueName, DEFAULT_SEVERITY );
	}
	
	protected static void validateRange( 	final Number p_value,
											final Number p_valueMin,
											final Number p_valueMax,
											final String p_valueName,
											final IMessage.Severity p_severity ) 
	throws FunctionalException {
		if ( p_value != null  ) {
			if ( p_valueMin != null && NumberUtil.isLessThan( p_value, p_valueMin ) ) {
				raiseException( p_valueName + " cannot be less than " + p_valueMin + ".", p_severity );
			}
			
			if ( p_valueMax != null && NumberUtil.isGreaterThan( p_value, p_valueMax ) ) {
				raiseException( p_valueName + " cannot be greater than " + p_valueMax + ".", p_severity );
			}
		}
	}

	protected static void raiseDefaultException( final String p_messageText ) 
	throws FunctionalException {
		raiseException( p_messageText, DEFAULT_SEVERITY );
	}
	
	protected static void raiseException( 	final String p_messageText,
											final IMessage.Severity p_severity ) 
	throws FunctionalException {
		switch ( p_severity ) {
			case FATAL:
				raiseFatalException( p_messageText );
			case WARNING:
				raiseWarningException( p_messageText );
			case INFO:
				raiseInfoException( p_messageText );
			default:
				raiseErrorException( p_messageText );
		}
	}

	protected static void raiseFatalException( final String p_messageText ) 
	throws FunctionalException {
		throw new FunctionalException( new Message<String>( p_messageText, IMessage.Severity.FATAL  ) );
	}

	protected static void raiseErrorException( final String p_messageText ) 
	throws FunctionalException {
		throw new FunctionalException( new Message<String>( p_messageText, IMessage.Severity.ERROR  ) );
	}

	protected static void raiseWarningException( final String p_messageText ) 
	throws FunctionalException {
		throw new FunctionalException( new Message<String>( p_messageText, IMessage.Severity.WARNING  ) );
	}

	protected static void raiseInfoException( final String p_messageText ) 
	throws FunctionalException {
		throw new FunctionalException( new Message<String>( p_messageText, IMessage.Severity.INFO  ) );
	}

	protected static void raiseUnknownException( final String p_messageText ) 
	throws FunctionalException {
		throw new FunctionalException( new Message<String>( p_messageText, IMessage.Severity.UNKNOWN  ) );
	}
	
	 @Override
	 public void dispose() {
	 }

	@Override
	public void stopServiceProcess(Object id) 
	throws ResourceAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isProcessAlive(Object id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getProcessTimeOut() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProcessTimeOut(Integer timeOut) {
		throw new UnsupportedOperationException();
	}
}
