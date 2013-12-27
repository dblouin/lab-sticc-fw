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
package fr.labsticc.framework.constraints.view;

public class BlankConstraintViewerConfig implements	IConstraintLanguageViewerConfig {

	private String languageId;
	
	private String languageName;
	
	private boolean languageFormal;
	
	public BlankConstraintViewerConfig( final String p_languageId,
										final String p_languageName,
										final boolean pb_languageFormal ) {
		languageId = p_languageId;
		languageName = p_languageName;
		languageFormal = pb_languageFormal;
	}
	
	@Override
	public String getLanguageName() {
		return languageName;
	}
    
    @Override
    public void setLanguageName( final String p_value ) {
    	languageName = p_value;
    }
    
    @Override
    public String getLanguageId() {
    	return languageId;
    }
    
    @Override
    public void setLanguageId( final String p_value ) {
    	languageId = p_value;
    }

	@Override
	public boolean isLanguageFormal() {
		return languageFormal;
	}

	@Override
	public void setLanguageFormal( final boolean pb_languageFormal ) {
		languageFormal = pb_languageFormal;
	}
}
