package fr.labsticc.framework.service;

import java.util.Properties;

import fr.labsticc.framework.core.exception.ConfigurationException;
import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.service.config.BaseServicesConfig;

public abstract class AbstractConfigurableAS implements IServiceConfigurable {

	private BaseServicesConfig servicesConfig = null;

	public void setServicesConfig( final BaseServicesConfig p_servicesConfig) {
		servicesConfig = p_servicesConfig;
	}

	public BaseServicesConfig getServicesConfig() {
		return servicesConfig;
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public boolean isProcessAlive( final Object p_processId ) {
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

	@Override
	public void stopServiceProcess( final Object p_processId )
	throws ResourceAccessException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void configure( final Properties p_configProps )
	throws ConfigurationException {
	}
}
