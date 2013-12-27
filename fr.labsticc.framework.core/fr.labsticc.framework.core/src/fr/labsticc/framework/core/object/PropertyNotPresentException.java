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
package fr.labsticc.framework.core.object;

public class PropertyNotPresentException extends RuntimeException {

	private static final long serialVersionUID = 8314205100976225812L;
	
	private final String propertyName;
	private final String className;

    public PropertyNotPresentException( final String p_className,
    									final String p_propertyName ) {
    	propertyName = p_propertyName;
    	className = p_className;
    }

	public String getPropertyName() {
		return propertyName;
	}

	public String getClassName() {
		return className;
	}
	
	@Override
	public String toString() {
		return "No feature for property " + String.valueOf( getPropertyName() ) + " in class " + String.valueOf( getClassName() );
	}
}
