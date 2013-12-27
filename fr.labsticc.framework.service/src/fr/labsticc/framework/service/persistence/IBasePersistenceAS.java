package fr.labsticc.framework.service.persistence;

import java.util.List;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.object.IBaseObjectFactory;
import fr.labsticc.framework.service.IServiceConfigurable;

public interface IBasePersistenceAS<BT> extends IServiceConfigurable {
	
	<T extends BT> T cloneEntity( T p_entity );

	<T extends BT> T persistEntity( IDatabaseSession<BT> p_session,
									T p_entity )
	throws ResourceAccessException;

	<T extends BT> T findEntity( 	IDatabaseSession<BT> p_session,
									T p_entity )
	throws ResourceAccessException;

	<T extends BT> T readEntity( 	IDatabaseSession<BT> p_session,
									T p_entity )
	throws ResourceAccessException;

	<T extends BT> T refreshEntity( IDatabaseSession<BT> p_session,
									T p_entity )
	throws ResourceAccessException;

	<T extends BT> T createEntity( Class<T> p_entityType )
	throws ResourceAccessException;

	<T extends BT> T createEntity( 	IDatabaseSession<BT> p_session,
									Class<T> p_entityType,
									Object p_entityId )
	throws ResourceAccessException;

	<T extends BT> boolean removeEntity( 	IDatabaseSession<BT> p_session,
											T p_entity )
	throws ResourceAccessException;

	<T extends BT> List<T> persistAllEntities( 	IDatabaseSession<BT> p_session,
												List<T> p_entities )
	throws ResourceAccessException;

	<T extends BT> int removeAllEntities( 	IDatabaseSession<BT> p_session,
											Class<T> p_entityType )
	throws ResourceAccessException;

	IBaseObjectFactory<BT> getObjectFactory();

	void setObjectFactory( IBaseObjectFactory<BT> p_value );
}
