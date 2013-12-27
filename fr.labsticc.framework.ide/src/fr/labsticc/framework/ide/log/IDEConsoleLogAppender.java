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
package fr.labsticc.framework.ide.log;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * A Log4j appender for an eclipse console view.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class IDEConsoleLogAppender extends AppenderSkeleton {

	private String consoleName;

	private MessageConsole console;
	
	private static final Color COLOR_ERROR;
	private static final Color COLOR_WARN;
	private static final Color COLOR_DEBUG;
	private static final Color COLOR_INFO;
	private static final Color COLOR_DEFAULT;
	
	static {
		final Display display = ConsolePlugin.getStandardDisplay();
		
		COLOR_ERROR = display.getSystemColor( SWT.COLOR_RED );
		COLOR_WARN = new Color( display, 255, 150, 0 );
		COLOR_INFO = display.getSystemColor( SWT.COLOR_BLUE );
		COLOR_DEBUG = display.getSystemColor( SWT.COLOR_GRAY );
		COLOR_DEFAULT = display.getSystemColor( SWT.COLOR_BLACK );
	}
	
	public IDEConsoleLogAppender() {
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	protected void append( final LoggingEvent p_event ) {
		
		// don't go any further if event is not severe enough.
		if ( isAsSevereAsThreshold( p_event.getLevel() ) ) {
			final IOConsole console = getConsole();
			
			if ( console != null ) {
				final Throwable exception;
				
				if ( p_event.getThrowableInformation() != null && layout.ignoresThrowable() ) {
					exception = p_event.getThrowableInformation().getThrowable();
				}
				else {
					exception = null;
				}
				
				final MessageConsoleStream stream = getNewMessageConsoleStream( p_event.getLevel() );
				stream.print( layout.format( p_event ) );
				
				if ( exception != null ) {
					stream.println( exception.toString() );
				}
			}
		}
	}
	
	private MessageConsoleStream getNewMessageConsoleStream( final Level p_level ) {		
		final Color swtColor;

		switch ( p_level.toInt() ) {
			case Level.ERROR_INT:
				swtColor = COLOR_ERROR;				
				break;
			case Level.WARN_INT:
				swtColor = COLOR_WARN;				
				break;
			case Level.DEBUG_INT:
				swtColor = COLOR_DEBUG;				
				break;
			case Level.INFO_INT:
				swtColor = COLOR_INFO;				
				break;
			default:				
				swtColor = COLOR_DEFAULT;				
		}	
		
		final MessageConsoleStream msgConsoleStream = getConsole().newMessageStream();		
		msgConsoleStream.setColor( swtColor );
		
		return msgConsoleStream;
	}

	public String getConsoleName() {
		return consoleName;
	}
	
	public void setConsoleName( final String p_name ) {
		consoleName = p_name;
		
		console = createConsole();
	}
	
	private MessageConsole createConsole() {
		final String consoleName = getConsoleName();
		final IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
		
		for ( final IConsole console : consoleManager.getConsoles() ) {
			if ( consoleName.equals( console.getName() ) && console instanceof MessageConsole ) {
				return (MessageConsole) console;
			}
		}
			
		final MessageConsole console = new MessageConsole( consoleName, null );
		consoleManager.addConsoles( new IConsole[]{ console } );
		
		return console;
	}
	
	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}
	
	protected MessageConsole getConsole() {
		return console;
	}
}
