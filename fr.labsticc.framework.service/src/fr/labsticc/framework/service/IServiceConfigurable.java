package fr.labsticc.framework.service;

import java.util.Properties;

import fr.labsticc.framework.core.exception.ConfigurationException;

public interface IServiceConfigurable extends IService {

	void configure( final Properties p_configProps ) 
	throws ConfigurationException;
}
