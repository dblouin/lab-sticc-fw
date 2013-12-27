package fr.labsticc.framework.service.persistence;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.object.IBaseObjectFactory;
import fr.labsticc.framework.service.AbstractConfigurableAS;

public abstract class BasePersistenceAS<BT> extends AbstractConfigurableAS implements IBasePersistenceAS<BT> {

	private IBaseObjectFactory<BT> objectFactory;
	
	@SuppressWarnings("unused")
	private final transient Logger trace = LoggerFactory.getLogger( getClass() ); 

	@Override
	public <E extends BT> E persistEntity( 	final IDatabaseSession<BT> p_session,
											final E p_entity )
	throws ResourceAccessException {
		return p_session.persistEntity( p_entity );	
	}

	@Override
	public <E extends BT> E readEntity( final IDatabaseSession<BT> p_session,
										final E p_entity )
	throws ResourceAccessException {
		return p_session.readEntity( p_entity );
	}

	@Override
	public <E extends BT> E refreshEntity( 	final IDatabaseSession<BT> p_session,
											final E p_entity )
	throws ResourceAccessException {
		return p_session.refreshEntity( p_entity );
	}
	
	@Override
	public <E extends BT> E createEntity( Class<E> p_entityType )
	throws ResourceAccessException {
		return getObjectFactory().createObject( p_entityType );
	}

	@Override
	public <E extends BT> E createEntity( 	final IDatabaseSession<BT> p_session,
											final Class<E> p_entityType,
											final Object p_entityId )
	throws ResourceAccessException {
		return p_session.createEntity( p_entityType, p_entityId );
	}

	@Override
	public <E extends BT> E cloneEntity(E p_entity ) {
		return getObjectFactory().cloneObject( p_entity );
	}

	@Override
	public <E extends BT> boolean removeEntity( final IDatabaseSession<BT> p_session,
												final E p_entity )
	throws ResourceAccessException {
		return p_session.removeEntity( p_entity );
	}

	@Override
	public <E extends BT> List<E> persistAllEntities( 	final IDatabaseSession<BT> p_session,
														final List<E> p_entity )
	throws ResourceAccessException {
		return p_session.persistAllEntities( p_entity );	
	}

	public <E extends BT> int removeAllEntities( 	final IDatabaseSession<BT> p_session,
													final Class<E> p_entityType )
	throws ResourceAccessException {
		return p_session.removeAllEntities( p_entityType );
	}

	@Override
	public <E extends BT> E findEntity( final IDatabaseSession<BT> p_session, 
										E p_entity ) 
	throws ResourceAccessException {
		E entity = readEntity( p_session, p_entity );
		
		if ( entity == null ) {
			entity = p_entity;
		}
		
		return entity;
	}

	@Override
	public IBaseObjectFactory<BT> getObjectFactory() {
		return objectFactory;
	}

	@Override
	public void setObjectFactory( final IBaseObjectFactory<BT> p_factory ) {
		objectFactory = p_factory;
	}
}
