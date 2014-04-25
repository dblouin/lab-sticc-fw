package fr.labsticc.framework.ide.resource;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public interface IResourceFilter {

	boolean isConsidered( IResource p_resource )
	throws CoreException;
}
