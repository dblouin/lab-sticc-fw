package fr.labsticc.framework.emf.core.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class ResourceDepComparator implements Comparator<Resource> {
	
	@Override
    public int compare(	final Resource p_res1, 
    					final Resource p_res2 ) {
		if( p_res1 == null ) {
            return p_res2 == null ? 0 : 1;
        }

        // At this point, we know that c1 and c2 are not null
        if ( p_res1.equals( p_res2 ) ) {
            return 0;
        }

        boolean res1DepOnRes2 = dependsOn( p_res1, p_res2 );
        boolean res2DepOnRes1 = dependsOn( p_res2, p_res1 );
        
        if ( res1DepOnRes2 && !res2DepOnRes1 ) {
            return 1;
        } 

        if ( res2DepOnRes1 && !res1DepOnRes2 ) {
            return - 1;
        } 

        return 0;
    }
	
	public static boolean dependsOn( 	final Resource p_resource1,
										final Resource p_resource2 ) {
		return dependsOn( p_resource1, p_resource2, new HashSet<Resource>() );
	}
	
	private static boolean dependsOn( 	final Resource p_resource1,
										final Resource p_resource2,
										final Set<Resource> p_traversedRes ) {
		if ( p_resource1 == p_resource2 ) {
			return true;
		}
		
		final Iterator<EObject> content = p_resource1.getAllContents();

		while ( content.hasNext() ) {
			final EObject elem = content.next();

			for ( final EObject crossRef : elem.eCrossReferences() ) {
				final Resource crossRes = crossRef.eResource();
				
				if ( crossRes != null && crossRes != p_resource1 && !p_traversedRes.contains( crossRes ) ) {
					p_traversedRes.add( crossRes );
					
					if ( dependsOn( crossRes, p_resource2, p_traversedRes ) ) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
