package fr.labsticc.framework.service.persistence;

import java.util.List;

import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * The Lab-STICC development framework neutral interface of a persistence database session. A database session
 * defines the API for creating, retrieving and storing a persisted entity (hereafter entity) from a database.
 * The session has the proper information for identifying the database.
 *
 * @param <BT> The root object class of all objects that can be persisted by this session.
 * @param <DBI> The database information base class.
 *  
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface IDatabaseSession<BT> {
	
	<T extends BT> T createEntity( 	Class<T> p_entityClass,
									Object p_entityId )
	throws ResourceAccessException;

	/**
	 * Reads an object from the database associated with this session.
	 * 
	 * @param <T> The class of the object to be read.
	 * @param p_templateEntity A template instance of the object to be read. The instance
	 * must have an identifier property valued to identify a single object in the database.
	 *  
	 * @return The object read from the database.
	 * 
	 * @throws ResourceAccessException
	 */
	<T extends BT> T readEntity( T p_templateEntity )
	throws ResourceAccessException;
	
	/**
	 * Refreshes the specified object from the database associated with this session.
	 * 
	 * @param <T> The actual class of the object to be refreshed.
	 * @param p_entity The object to be refreshed. The instance must have been previously read so that
	 * an identifier property is valued to identify a single object in the database.
	 *  
	 * @return The object refreshed from the database.
	 * 
	 * @throws ResourceAccessException
	 */
	<T extends BT> T refreshEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> int refreshAllEntities( Class<T> p_entityClass )
	throws ResourceAccessException;

	<T extends BT> T persistEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> boolean removeEntity( T p_templateEntity )
	throws ResourceAccessException;

	<T extends BT> List<T> persistAllEntities( List<T> p_entities )
	throws ResourceAccessException;

	<T extends BT> int removeAllEntities( Class<T> p_entityClass )
	throws ResourceAccessException;
}
