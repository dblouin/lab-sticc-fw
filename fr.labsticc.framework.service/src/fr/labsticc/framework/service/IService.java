package fr.labsticc.framework.service;

import fr.labsticc.framework.core.exception.ResourceAccessException;

public interface IService {

	void stopServiceProcess( Object p_processId )
	throws ResourceAccessException;
	
	void dispose();
	
	boolean isProcessAlive( Object p_processId );

	Integer getProcessTimeOut();

	void setProcessTimeOut( Integer p_processTimeOut );
}
