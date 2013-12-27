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


import java.text.MessageFormat;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;

/**
 * log4j appender to an eclipse plugin's Ilog.
 */
public class PluginLogAppender extends AppenderSkeleton {

	private String symbolicName;
	
	public PluginLogAppender() {
		this( null );
	}

	public PluginLogAppender( String p_symbolicName ) {
		setSymbolicName( p_symbolicName );
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	protected void append( final LoggingEvent p_event ) {
		
		// don't go any further if event is not severe enough.
		if(! isAsSevereAsThreshold( p_event.getLevel() ) ) return;
		
		ILog log = getBundleILog();
		if(log == null) return;
		
		// if throwable information is available, extract it.
		Throwable t = null;
		if( p_event.getThrowableInformation() != null && layout.ignoresThrowable())
			t = p_event.getThrowableInformation().getThrowable();
		
		// build an Eclipse Status record, map severity and code from Event.
		Status s = new Status(getSeverity( p_event ),
				getSymbolicName(),
				getCode( p_event ),
				layout.format( p_event ), 
				t);

		
		log.log(s);

	}

	/**
	 * map LoggingEvent's level to Status severity
	 * @param ev
	 * @return
	 */
	private int getSeverity(LoggingEvent ev) {
		
		Level level = ev.getLevel();
		if(level == Level.FATAL || level == Level.ERROR)
			return IStatus.ERROR;
		else if(level == Level.WARN)
			return IStatus.WARNING;
		else if(level == Level.INFO) 
			return IStatus.INFO;
		else // debug, trace and custom levels
			return IStatus.OK;
	}
	
	/**
	 * Return the pluginId under which the messages will be logged
	 * @return
	 */
	public String getSymbolicName() {
		return this.symbolicName;
	}
	
	public void setSymbolicName(String name) {
		this.symbolicName = name;
	}
	/**
	 * map LoggingEvent to Status code
	 * @param ev
	 * @return
	 */
	private int getCode(LoggingEvent ev) {
		return 0;
	}
	
	private ILog getBundleILog() {
		// get the bundle for a plug-in
		Bundle b = Platform.getBundle(getSymbolicName());
		if(b == null) {
			String m = MessageFormat.format("Plugin: {0} not found in {1}.",
					new Object[] {getSymbolicName(), this.name});
			this.errorHandler.error(m);
			return null;
		}
	
		return Platform.getLog(b);
		

	}
	
	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}
}
