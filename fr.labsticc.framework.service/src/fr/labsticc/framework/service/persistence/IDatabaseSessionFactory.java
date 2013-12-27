package fr.labsticc.framework.service.persistence;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.object.IBaseObjectFactory;

/**
 * The Lab-STICC neutral persistence database session factory interface. It is responsible for creating the database
 * session objects (equivalent to Spring entity managers) which give access to persisted data.
 * @param <BT>
 * @param <DBI>
 * 
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public interface IDatabaseSessionFactory<BT, DBI> {
	
	IDatabaseSession<BT> createDatabaseSession( DBI p_databaseInfo ) 
	throws ResourceAccessException;

	<T extends BT> IDatabaseSession<BT> getDatabaseSession( final T p_entity )
	throws ResourceAccessException;

	void setObjectFactory( IBaseObjectFactory<BT> p_objectFactory );
}
