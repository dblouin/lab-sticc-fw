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

public class SystemUtil {

	public static String getOSName() {
		return System.getProperty( "os.name" );
	}
	
	public static boolean isLinuxLike() {
	    String osName = getOSName().toLowerCase();

	    return osName != null && osName.contains( "linux" );
	}

	public static boolean isWindowsLike() {
	    String osName = getOSName().toLowerCase();

	    return osName != null && osName.contains( "win" );
	}

	public static boolean isMacLike() {
	    String osName = getOSName().toLowerCase();

	    return osName != null && osName.contains( "mac" );
	}

	public static boolean isJavaVersionLessThan( double pd_version ) {
	    String version = System.getProperty( "java.version" );

	    return version != null && Double.parseDouble( version ) >= pd_version;
	}
	
	public static String getUserDir() {
		return System.getProperty( "user.dir" );
	}
	
	public static void setUserDir( final String p_dir ) {
		System.setProperty( "user.dir", p_dir );
	}
	
	public static String getTempDir() {
		return System.getProperty( "java.io.tmpdir" );
	}

	public static void setTempDir( final String p_dir ) {
		System.setProperty( "java.io.tmpdir", p_dir );
	}
	
	public static String getLineSeparator() {
		return System.getProperty( "line.separator" );
	}
	
	public static Integer getPermissionErrorCode() {
		if ( isLinuxLike() ) {
			return 126;
		}
		
		if ( isWindowsLike() ) {
			return 5;
		}
		
		return null;
	}
}
