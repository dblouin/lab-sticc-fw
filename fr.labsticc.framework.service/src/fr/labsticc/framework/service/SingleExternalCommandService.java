package fr.labsticc.framework.service;

import org.slf4j.Logger;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.externalcommand.ExternalCommand;
import fr.labsticc.framework.core.externalcommand.ExternalCommandException;
import fr.labsticc.framework.core.util.SystemUtil;

public abstract class SingleExternalCommandService extends SingleThreadService {

	protected static final String BIN_EXTENSION = SystemUtil.isWindowsLike() ? ".exe" : "";
	
	private ExternalCommand currentCommand = null;

	protected SingleExternalCommandService( final Logger p_logger ) {
		super( p_logger );
	}
	
	protected void runCommandSync( final ExternalCommand p_command ) 
	throws ExternalCommandException, InterruptedException {
		currentCommand = p_command;
		
		currentCommand.execute( true );
		currentCommand = null;
	}

	protected void runCommandAsync( final ExternalCommand p_command ) 
	throws ExternalCommandException, InterruptedException {
		currentCommand = p_command;
		
		currentCommand.execute( false );
	}

	@Override
	public void stopServiceProcess( final Object p_processId )
	throws ResourceAccessException {
		if ( currentCommand != null ) {
			currentCommand.stop();
		}

		super.stopServiceProcess( p_processId );
	}
}
