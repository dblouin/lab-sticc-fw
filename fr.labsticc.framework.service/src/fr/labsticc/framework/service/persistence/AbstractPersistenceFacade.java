package fr.labsticc.framework.service.persistence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.persistence.IBasePersistenceDelegate;
import fr.labsticc.framework.core.persistence.IPersistenceListener;
import fr.labsticc.framework.service.IService;

public abstract class AbstractPersistenceFacade<BT, DBI> implements IBasePersistenceDelegate<BT, DBI>, IService {

	protected final Logger trace = LoggerFactory.getLogger( getClass() );
	
	private List<IPersistenceListener> listeners = new ArrayList<IPersistenceListener>();
	
	@Override
	public <T extends BT> T cloneEntity( final T p_entity ) {
		return getPersistenceAS().cloneEntity( p_entity );
	}

	@Override
	public <T extends BT> T createEntity( final Class<T> p_type )
	throws ResourceAccessException {
		return getPersistenceAS().createEntity( p_type );
	}

	@Override
	public <T extends BT> T createEntity( 	final Class<T> p_type,
											final Object p_entityId,
											final DBI p_databaseInfo )
	throws ResourceAccessException {
		return getPersistenceAS().createEntity( getDatabaseSession( p_databaseInfo ),
												p_type, 
												p_entityId );
	}

	@Override
	public <T extends BT> T persistEntity( final T p_entity )
	throws ResourceAccessException {
		trace.debug( "Starting persistEntity" );
		final T persistedEntity = getPersistenceAS().persistEntity( getDatabaseSessionFromEntity( p_entity ), 
																	p_entity );
		fireEntityPersisted( persistedEntity );
		
		return persistedEntity;
	}
	
	private <T extends BT> void fireEntityPersisted( final T p_entity ) {
		for ( final IPersistenceListener listener : listeners ) {
			listener.entityPersisted( p_entity );
		}
	}

	@Override
	public <T extends BT> T readEntity( final T p_entity )
	throws ResourceAccessException {
		return getPersistenceAS().readEntity( getDatabaseSessionFromEntity( p_entity ), p_entity );
	}

	@Override
	public <T extends BT> T refreshEntity( final T p_entity )
	throws ResourceAccessException {
		return getPersistenceAS().refreshEntity( getDatabaseSessionFromEntity( p_entity ), p_entity );
	}

	@Override
	public <T extends BT> boolean removeEntity( final T p_entity )
	throws ResourceAccessException {
		return getPersistenceAS().removeEntity( getDatabaseSessionFromEntity( p_entity ), p_entity);
	}

	@Override
	public <T extends BT> List<T> persistAllEntities( final List<T> p_entities )
	throws ResourceAccessException {
		if ( !p_entities.isEmpty() ) {
			final List<T> persistedEntities = getPersistenceAS().persistAllEntities( 	getDatabaseSessionFromEntity( p_entities.get( 0 ) ),
																						p_entities );
			
			for ( final T entity : persistedEntities ) {
				fireEntityPersisted( entity );
			}
			
			return persistedEntities;
		}
		
		return p_entities;
	}

	@Override
	public <T extends BT> List<T> removeAllEntities( final List<T> p_entities )
	throws ResourceAccessException {
		final List<T> copy = new ArrayList<T>();
		copy.addAll( p_entities );
		
		for ( final T entity : copy ) {
			final boolean removed = getPersistenceAS().removeEntity(  	getDatabaseSessionFromEntity( entity ), 
																		entity );
			
			if ( removed ) {
				p_entities.remove( entity );
			}
		}
		
		return p_entities;
	}
	
	@Override
	public <T extends BT> int removeAllEntities( final Class<T> p_entityClass )
	throws ResourceAccessException {
		int nbRemovedEntities = getPersistenceAS().removeAllEntities( 	getDatabaseSessionFromEntity( null ), 
																		p_entityClass );
		
		for ( final IPersistenceListener listener : listeners ) {
			listener.entitiesRemoved( p_entityClass );
		}
		
		return nbRemovedEntities;
	}
	
	@Override
	public <T extends BT> T findEntity( T p_entity ) 
	throws ResourceAccessException {
		return getPersistenceAS().findEntity( getDatabaseSessionFromEntity( p_entity ), p_entity );
	}

	@Override
	public void addPersistenceListener( final IPersistenceListener p_listener ) {
		listeners.add( p_listener);
	}

	@Override
	public boolean removePersistenceListener( final IPersistenceListener p_listener ) {
		return listeners.remove( p_listener );
	}
	
	protected abstract <T extends BT> IDatabaseSession<BT> getDatabaseSession( final DBI p_dbInfo )
	throws ResourceAccessException;

	protected abstract IBasePersistenceAS<BT> getPersistenceAS();

	protected abstract <T extends BT> IDatabaseSession<BT> getDatabaseSessionFromEntity( final T p_entity )
	throws ResourceAccessException;

	@Override
	public boolean isProcessAlive( final Object p_id ) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getProcessTimeOut() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setProcessTimeOut( final Integer p_timeOut ) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void stopServiceProcess( final Object p_processId )
	throws ResourceAccessException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void dispose() {
	}
}
