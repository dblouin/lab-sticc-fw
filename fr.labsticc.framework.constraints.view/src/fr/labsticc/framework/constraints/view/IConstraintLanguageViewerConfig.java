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

public interface IConstraintLanguageViewerConfig {

    /**
     * The setter is needed for implementers that are declared in the settings model.
     * @param p_languageName
     */
    void setLanguageName( String p_languageName );
    
    String getLanguageName();

    /**
     * The setter is needed for implementers that are declared in the settings model.
     * @param p_languageId
     */
    void setLanguageId( String p_languageId );
    
    String getLanguageId();
    
    boolean isLanguageFormal();

    /**
     * The setter is needed for implementers that are declared in the settings model.
     * @param pb_languageFormal
     */
    void setLanguageFormal( boolean pb_languageFormal );
}
