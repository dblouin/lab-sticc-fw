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
package fr.labsticc.framework.core.util;

import java.util.Comparator;

public class ClassHierarchyComparator implements Comparator<Class<?>> {
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * null is considered as the highest
	 */
	@Override
    public int compare(	final Class<?> p_class1, 
    					final Class<?> p_class2 ) {
		if( p_class1 == null ) {
            return p_class2 == null ? 0 : 1;
        }

        // At this point, we know that c1 and c2 are not null
        if ( p_class1.equals( p_class2 ) ) {
            return 0;
        }

        // At this point, c1 and c2 are not null and not equal, here we
        // compare them to see which is "higher" in the class hierarchy
        boolean c1Lower = p_class2.isAssignableFrom( p_class1 );
        boolean c2Lower = p_class1.isAssignableFrom( p_class2 );

        if ( c1Lower && !c2Lower ) {
            return - 1;
        } 
        
        if ( c2Lower && !c1Lower ) {
            return 1;
        } 

        // Doesn't matter, sort consistently on classname
        return p_class1.getName().compareTo( p_class2.getName() );
    }
}
