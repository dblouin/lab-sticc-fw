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
package fr.labsticc.framework.core.externalcommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.util.SystemUtil;

/**
 * This class is used to execute a process that is external to the Java runtime VM. 
 */
public class ExternalCommand {

	protected static final Level logLevelDefault = Level.INFO;

	protected static final String LINE_SEPARATOR = SystemUtil.getLineSeparator();

	private String command;

	private String commandArgs = "";

	private final String[] envParams;
	
	private File workingDir = null;
	
	private boolean usingSystemConsole = false;

	private final Logger logger;

	private Level logLevel = logLevelDefault;
	
	private boolean loggingLineBreaks;

	private final IExternalCommandExceptionHandler exceptionHandler;

	private Process process;

	private boolean destroyed = false;

	private ProcessIO errStream = null;
	private ProcessIO outStream = null;

	private PrintWriter console = null;

	public ExternalCommand( final String p_command,
							final String p_commandArgs,
							final Logger p_logger ) {
		this( p_command, p_commandArgs, (String) null, p_logger );
	}

	public ExternalCommand( final String p_command, 
							final String p_commandArgs,
							final String p_workingDir,
							final Logger p_logger ) {
		this( p_command, p_commandArgs, null, p_workingDir, p_logger, logLevelDefault );
	}

	public ExternalCommand( final String p_command,
							final String p_commandArgs,
							final String p_workingDir,
							final String p_symbolicName ) {
		this( p_command, p_commandArgs, p_workingDir, LoggerFactory.getLogger( p_symbolicName ) );
	}

	/**
	 * Use this constructor in case the external command/process has arguments.
	 * @param p_cmdArgs String[] The first item in the array is the system command 
	 *        followed by arguments.
	 * @param p_envParams String[] Environment parameters, 'name=value' pairs
	 */
	public ExternalCommand( final String p_command, 
							final String p_commandArgs,
							final String[] p_envParams,
							final Logger p_logger ) {
		this( p_command, p_commandArgs, p_envParams, null, p_logger );
	}

	/**
	 * Use this constructor in case the external command/process has arguments.
	 * @param p_cmdArgs String[] The first item in the array is the system command.
	 * @param p_envParams String[]
	 * @param p_dir File
	 */
	public ExternalCommand( final String p_command, 
							final String p_commandArgs,
							final String[] p_envParams, 
							final String p_workingDir,
							final Logger p_logger ) {
		this( p_command, p_commandArgs, p_envParams, p_workingDir, p_logger, logLevelDefault );
	}

	public ExternalCommand( final String p_command, 
							final String p_commandArgs,
							final String[] p_envParams,
							final String p_workingDir,
							final Logger p_logger,
							final Level p_logLevel ) {
		command = p_command;
		commandArgs = p_commandArgs;
		workingDir = new File( p_workingDir );
		logger = p_logger;
		logLevel = p_logLevel;
		loggingLineBreaks = false;

		// Adds the working directory to the if some environment parameters are passed, do nothing 
		if ( SystemUtil.isLinuxLike() && p_envParams == null ) {
			final List<String> params = new ArrayList<String>();
			
			for ( final Map.Entry<String, String> entry : System.getenv().entrySet() ) {
				params.add( entry.getKey() + "=" + entry.getValue() );
			}

			final String execDir = new File( p_command ).getParent();
			final String extraDirs = execDir + ":" + p_workingDir;
			final String exist = System.getenv( "LD_LIBRARY_PATH" );
			params.add( "LD_LIBRARY_PATH=" + ( exist == null ? extraDirs : exist + ":" + extraDirs ) );
			envParams = params.toArray( new String[ params.size() ] );
		}
		else {
			envParams = p_envParams;
		}
		
		exceptionHandler = createExceptionHandler();
	}

	/**
	 * @param p_waitToFinish True if the thread must wait for the external process
	 * to finish before returning, False otherwise
	 * @return status returned from the external process or 0(successful)
	 */
	public Object execute( boolean pb_waitToFinish ) 
	throws ExternalCommandException, InterruptedException {
		final String fullCommand = getFullCommand();
		Integer retCode = null;

		if ( logger != null ) {
			logger.info( "Starting process " + fullCommand );
		}

		try {
			final Runtime rt = Runtime.getRuntime();

			if ( SystemUtil.isLinuxLike() ) {
				process = rt.exec( "/bin/bash", envParams, workingDir );
				startIOs( process );
				console = new PrintWriter( 	new BufferedWriter( new OutputStreamWriter( process.getOutputStream() ) ), 
											true ); 
				console.println( "cd " + workingDir );
				console.println( fullCommand );
				console.println( "exit" );
			}
//		    else if ( SystemUtil.isWindowsLike() ) { 
//		    	// TODO: This was removed for SoftExplorer. Is it really needed for CAT?
//		    	process = rt.exec( "cmd", envParams, workingDir );
//		    	startIOs( process );
//		    	console = new PrintWriter( new BufferedWriter( new OutputStreamWriter( process.getOutputStream() ) ), true );
//		    	final String device = FileUtil.getDevice( workingDir );
//		    	
//		    	if ( device != null ) {
//			    	console.println( device );
//		    	}
//
//		    	console.println( "cd " + workingDir );
//		    	console.println( fullCommand );
//		    	//console.println( "exit" );
//		    }
			else {
				console = null;
				process = rt.exec( fullCommand, envParams, workingDir );
				startIOs( process );
			}

			retCode = pb_waitToFinish ? process.waitFor() : process.exitValue();

			while( areIOsProcessesAlive() ) {
				Thread.sleep( 10 );
			}

			if ( destroyed ) {
				destroyed = false;

				throw new InterruptedException( "Process " + command + " stopped successfully." );
			}
			
			handleReturnCode( retCode );
		}
		catch ( final InterruptedException p_ex ) {
			throw p_ex;
		}
		catch ( final PermissionException p_ex ) {
			logger.warn( "Executables do not seem to have execution permission. Trying to give permission..." );

			if ( SystemUtil.isLinuxLike() ) {
				new ExternalCommand( "chmod", "-R +x .", getWorkingDir().getPath(), logger ).execute( true );
			}

			return execute( pb_waitToFinish );
		}
		catch ( final Throwable p_th ) {
			getExceptionHandler().handleException( p_th, fullCommand );
		} 

		return retCode;
	}
	
	protected IExternalCommandExceptionHandler createExceptionHandler() {
		return new PermissionErrorCommandExceptionHandler( 0, SystemUtil.getPermissionErrorCode() );
	}
	
	protected void handleReturnCode( final int pi_retCode ) 
	throws ExternalCommandException {
		getExceptionHandler().handleReturnCode( pi_retCode, getFullCommand() );
	}

	public void stop() {
		if ( process != null ) {
			if ( console != null && SystemUtil.isWindowsLike() ) {
				console.println( (char)3 );
			}
			
			destroyed = true;
			process.destroy();
			stopIOs();
			logger.info( "Process " + command + " killed upon user requests." );
		}
	}

	protected void startIOs( final Process p_process ) {
		errStream = buildProcessIO( p_process.getErrorStream(), Level.SEVERE );
		outStream = buildProcessIO( p_process.getInputStream(), Level.INFO );
		errStream.start(); // process any errors from the command
		outStream.start(); // process any output from the command
	}

	protected void stopIOs() {
		if ( errStream != null ) {
			errStream.end();
		}

		if ( outStream != null ) {
			outStream.end();
		}
	}

	private boolean areIOsProcessesAlive() {
		if ( errStream == null ) {
			return outStream != null && outStream.isAlive();
		}

		if ( outStream == null ) {
			return errStream != null && errStream.isAlive();
		}

		return errStream.isAlive() || outStream.isAlive();
	}

	protected class ProcessIO extends Thread {
		private final InputStream inputStr;
		private final Level streamType;
		private boolean stopRequested = false;

		protected ProcessIO( 	final InputStream p_inputStr, 
								final Level p_type ) {
			inputStr = p_inputStr;
			streamType = p_type;
		}

		@Override
		public void run() {
			try {
				final InputStreamReader reader = new InputStreamReader( inputStr );
				final BufferedReader buffReader = new BufferedReader( reader );
				String streamData = null;

				while( !stopRequested && ( streamData = buffReader.readLine() ) != null ) {
					log( streamType, streamData );
				}
			}
			catch( final IOException p_ex ) {
				logger.error( "Error processing " + streamType + " stream", p_ex );
			}
		}

		public void end() {
			stopRequested = true;
		}
	}

	protected void log( final Level p_level,
						final String p_line ) {
		if ( !"".equals( p_line ) ) {
			final int i_level = p_level.intValue();

			if ( i_level >= logLevel.intValue() ) {
				if ( i_level < Level.OFF.intValue() ) {
					if ( i_level < Level.SEVERE.intValue() ) {
						if ( i_level < Level.WARNING.intValue() ) {
							logger.info( p_line );
						}
						else {
							logger.warn( p_line );
						}
					}
					else {
						logger.error( p_line );
					}
				}
			}
		}
	}

	protected ProcessIO buildProcessIO( final InputStream p_inputStr, 
										final Level p_logLevel ) {
		return new ProcessIO( p_inputStr, p_logLevel );
	}

	public Level getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(Level logLevel) {
		this.logLevel = logLevel;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getFullCommand() {
		return command + ( commandArgs == null ? "" : ( " " + commandArgs ) );
	}

	public IExternalCommandExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File workingDir) {
		this.workingDir = workingDir;
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer( "External Command: " );
		buffer.append( getCommand() );
		buffer.append( LINE_SEPARATOR );
		buffer.append( "Arguments: " );
		buffer.append( getCommandArgs() );

		return buffer.toString();
	}

	protected String getCommandArgs() {
		return commandArgs;
	}

	protected void setCommandArgs(String commandArgs) {
		this.commandArgs = commandArgs;
	}
	
	public boolean isLoggingLineBreaks() {
		return loggingLineBreaks;
	}
	
	protected void setLoggingLineBreaks( final boolean pb_loggingLineBreaks ) {
		loggingLineBreaks = pb_loggingLineBreaks;
	}

	public boolean isUsingSystemConsole() {
		return usingSystemConsole;
	}

	public void setUsingSystemConsole( final boolean pb_usingSystemConsole ) {
		usingSystemConsole = pb_usingSystemConsole;
	}
}
