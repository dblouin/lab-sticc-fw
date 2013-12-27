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

import java.io.IOException;
import java.net.URL;

public class URLUtil {

	public static String getContent( final URL p_url ) 
	throws IOException {
		return FileUtil.getContent( p_url.openStream() );
//		Reader reader = new InputStreamReader( p_url.openStream() );
//		
//		try {
//			char[] buffer = new char[ 1 ];
//			final StringBuffer content = new StringBuffer();
//			
//		    while ( reader.read( buffer ) != -1 ) {
//		    	content.append( buffer );
//		    }
//		    
//		    return content.toString();
//		}
//		finally {
//			if ( reader != null ) {
//				reader.close();
//			}
//		}
	}
}
