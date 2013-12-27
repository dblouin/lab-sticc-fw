package fr.labsticc.framework.compatibility;

import org.eclipse.core.resources.IFile;

import fr.labsticc.framework.core.exception.ResourceAccessException;

/**
 * Provides means to update workspace resources following a change in tools or environment on which they depend.
 * @author dblouin
 */
public interface ICompatibilityUpdater {
	
	/**
	 * An id for this update. This may be used to store in the preferences the files that have been updated for this update
	 * to avoid looking up if the update is needed every time.
	 * @return A string uniquely identifying this update.
	 */
	String getUpdateId();
	
	/**
	 * A description to be displayed to user of this update.
	 * @return
	 */
	String getDescription();

	/**
	 * Returns an update content object representing the updated data and a summary of the performed changes. Committing
	 * the changes is the responsibility of the caller who may prompt users to accept the changes.  
	 * @param p_resource
	 * @return
	 * @throws ResourceAccessException
	 */
	IUpdatedData update( IFile p_file )
	throws ResourceAccessException;
}
