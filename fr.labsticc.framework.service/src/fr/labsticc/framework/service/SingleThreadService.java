package fr.labsticc.framework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;

public class SingleThreadService implements IService {

	private final transient Logger logger;
	
	private boolean stopRequested = false;

	private boolean alive = false;
	
	private Integer processTimeOut = null;
	
	public SingleThreadService( final Logger p_logger ) {
		if ( p_logger == null ) {
			logger = LoggerFactory.getLogger( getClass() );
		}
		else {
			logger = p_logger;
		}
	}

	protected boolean isAlive() {
		return alive;
	}

	protected void setAlive(boolean alive) {
		this.alive = alive;
	}

	@Override
	public void stopServiceProcess( final Object p_processId )
	throws ResourceAccessException {
		setStopRequested( true );
		setAlive( false );
	}

	@Override
	public boolean isProcessAlive( final Object p_processId ) {
		return isAlive();
	}
	
	@Override
	public void dispose() {
	}
	
	protected boolean isStopRequested() {
		return stopRequested;
	}

	protected void setStopRequested(boolean stopRequested) {
		this.stopRequested = stopRequested;
	}
	
	public void startProcess() {
		setStopRequested( false );
		setAlive( true );
	}

	protected void checkInterruption()
	throws InterruptedException {
		if ( isStopRequested() ) {
			throw new InterruptedException();
		}
	}

	protected Logger getLogger() {
		return logger;
	}

	@Override
	public Integer getProcessTimeOut() {
		return processTimeOut;
	}

	@Override
	public void setProcessTimeOut( final Integer p_processTimeOut ) {
		processTimeOut = p_processTimeOut;
	}
}
