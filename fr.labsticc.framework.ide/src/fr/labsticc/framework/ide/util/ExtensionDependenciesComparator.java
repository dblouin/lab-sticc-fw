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
package fr.labsticc.framework.ide.util;

import java.util.Comparator;

import org.eclipse.core.runtime.IExtension;
import org.osgi.framework.BundleException;

public class ExtensionDependenciesComparator implements Comparator<IExtension> {

	private boolean ascending;
	
	public ExtensionDependenciesComparator() {
		this( true );
	}

	public ExtensionDependenciesComparator( final boolean pb_ascending ) {
		ascending = pb_ascending;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * null is considered as the highest
	 */
	@Override
    public int compare(	final IExtension p_ext1, 
    					final IExtension p_ext2 ) {
		if( p_ext1 == null ) {
            return p_ext2 == null ? 0 : 1;
        }

        // At this point, we know that p_ext1 and p_ext2 are not null
        if ( p_ext1.equals( p_ext2 ) ) {
            return 0;
        }

        // At this point, c1 and c2 are not null and not equal, here we
        // compare them to see which is "higher" in the class hierarchy
        try {
	        boolean ext1DepensOnExt2 = PluginUtil.extensionDependsOn( p_ext1, p_ext2 );
	        boolean ext2DepensOnExt1 = PluginUtil.extensionDependsOn( p_ext2, p_ext1 );
	
	        if ( ext1DepensOnExt2 && !ext2DepensOnExt1 ) {
	            return ascending ? 1 : - 1;
	        } 
	        
	        if ( ext2DepensOnExt1 && !ext1DepensOnExt2 ) {
	            return ascending ? - 1 : 1;
	        } 
	
	        // Doesn't matter, sort by name
	        if ( ascending ) {
	        	return p_ext1.getNamespaceIdentifier().compareTo( p_ext2.getNamespaceIdentifier() );
	        }
	        
        	return p_ext2.getNamespaceIdentifier().compareTo( p_ext1.getNamespaceIdentifier() );
        }
        catch ( final BundleException p_ex ) {
        	throw new RuntimeException( p_ex );
        }
    }
}
