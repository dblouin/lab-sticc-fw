package fr.labsticc.framework.service.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.IResourceHandler;
import fr.labsticc.framework.core.exception.ConfigurationException;
import fr.labsticc.framework.core.exception.ResourceAccessException;

public abstract class BaseServicesConfig {
	
	protected static final String DOT = ".";
	
	protected static final String SPACE = " ";
	
	protected static final String DOUBLE_QUOTE = "\"";

	private static final String SERVICES_CONFIG_FILE_NAME = "services_config.properties";

	private static final transient Logger trace = LoggerFactory.getLogger( BaseServicesConfig.class );
	
	private final IResourceHandler resourceHandler;

	private final String serviceName;
	
	private String workingDir = null;

	private String tempDir = null;

	public BaseServicesConfig( 	final Properties p_configProps,
								final String p_serviceName,
								final IResourceHandler p_resourceHandler ) 
	throws ConfigurationException {
		serviceName = p_serviceName;
		resourceHandler = p_resourceHandler;
		
		final Properties allConfigProps = new Properties();

		try {
			allConfigProps.load( getClass().getResourceAsStream( SERVICES_CONFIG_FILE_NAME ) );
		}
		catch ( final IOException p_ex ) {
			trace.warn( "File " + SERVICES_CONFIG_FILE_NAME + " not found for service " + getServiceName() );
		}
		
		if ( p_configProps != null ) {
			
			// Overrides local properties with globally set properties
			allConfigProps.putAll( p_configProps );
		}
		
		initConfigProperties( allConfigProps );
	}

	protected void initConfigProperties( Properties p_configProps ) 
	throws ConfigurationException {
		initWorkingDir( p_configProps );
	}

	protected abstract void initWorkingDir( final Properties p_configProps )
	throws ConfigurationException;

	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String p_workingDir) {
		workingDir = p_workingDir;
	}

	public String getTempDir() {
		return tempDir;
	}

	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}
	
	protected String getServiceName() {
		return serviceName;
	}
	
	protected String locateResource( final String p_resource ) 
	throws ResourceAccessException {
		if ( getResourceHandler() == null ) {
			return p_resource;
		}
		
		return getResourceHandler().locateResource( p_resource, getClass().getClassLoader() );
	}

	protected IResourceHandler getResourceHandler() {
		return resourceHandler;
	}
}
