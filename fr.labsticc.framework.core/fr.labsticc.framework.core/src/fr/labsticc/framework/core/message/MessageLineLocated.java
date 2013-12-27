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
package fr.labsticc.framework.core.message;


/**
 * A special message class to represent CC1 errors. It holds the c file name and the line number of the error.
 * @author <a href="mailto:dominique.blouin@univ-ubs.fr">Dominique Blouin</a>
 */
public class MessageLineLocated extends Message<Integer> implements IMessageLocated<Integer, Message<Integer>>{

	private static final long serialVersionUID = 5634263054752031758L;
	
	private final String resourceId;
	
	private final Integer lineNumber;
	
	private final String description;

	public MessageLineLocated( 	final String p_labelText,
								final String p_resourceId,
								final Integer p_lineNumber,
								final String p_description ) {
		super( p_labelText );
		
		resourceId = p_resourceId;
		lineNumber = p_lineNumber;
		description = p_description;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Integer getLocation() {
		return lineNumber;
	}

	@Override
	public String getResourceId() {
		return resourceId;
	}
}
