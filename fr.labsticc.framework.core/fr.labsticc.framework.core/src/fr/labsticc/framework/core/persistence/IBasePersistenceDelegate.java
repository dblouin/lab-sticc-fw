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
package fr.labsticc.framework.core.persistence;

import java.util.List;

import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * The Lab-STICC neutral persistence delegate interface. It is responsible for accessing entities persisted on
 * a database specified by the DBI information object.
 * 
 * @param <BT> The super class of all entity classes that can be persisted by this delegate.
 * @param <DBI> The super class of the database information object.
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface IBasePersistenceDelegate<BT, DBI> {
	
	<T extends BT> T cloneEntity( T p_entity );

	<T extends BT> T persistEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> T readEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> T refreshEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> T findEntity( T p_entity )
	throws ResourceAccessException;

	/**
	 * Creates an empty entity of the specified type.
	 * 
	 * @param <T> The specified entity parameterized type.
	 * @param p_type The specified entity type.
	 * @return The created entity.
	 * @throws ResourceAccessException
	 */
	<T extends BT> T createEntity( Class<T> p_type )
	throws ResourceAccessException;

	/**
	 * Creates an empty entity of the specified type and sets its identifier property with the specified
	 * entity id.
	 * 
	 * @param <T> The specified entity parameterized type.
	 * @param p_type The specified entity type.
	 * @param p_entityId The entity identifier property value.
	 * @return The created entity with its identifier property set.
	 * @throws ResourceAccessException
	 */
	<T extends BT> T createEntity( 	Class<T> p_type,
									Object p_entityId )
	throws ResourceAccessException;

	/**
	 * Creates an empty entity of the specified type and sets its identifier property with the specified
	 * entity id. Register the created entity with the database specified by the database info parameter.
	 * 
	 * @param <T> The specified entity parameterized type.
	 * @param p_type The specified entity type.
	 * @param p_entityId The entity identifier property value.
	 * @param p_databaseInfo An object representing the database with which the created entity should be 
	 * registered. Registering the entity with the database means that subsequent persistence calls on this 
	 * entity without specifying a database should be performed on the database with which it was registered.   
	 * @return The registered created entity with its identifier property set.
	 * @throws ResourceAccessException
	 */
	<T extends BT> T createEntity( 	Class<T> p_type,
									Object p_entityId,
									DBI p_databaseInfo )
	throws ResourceAccessException;

	<T extends BT> boolean removeEntity( T p_entity )
	throws ResourceAccessException;

	<T extends BT> List<T> persistAllEntities( List<T> p_entities )
	throws ResourceAccessException;
	
	<T extends BT> List<T> removeAllEntities( final List<T> p_entities )
	throws ResourceAccessException;

	<T extends BT> int removeAllEntities( Class<T> p_entityClass )
	throws ResourceAccessException;
	
	void addPersistenceListener( IPersistenceListener p_listener );

	boolean removePersistenceListener( IPersistenceListener p_listener );
}
