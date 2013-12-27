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
package fr.labsticc.framework.constraints.lute.view;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

import fr.labsticc.framework.constraints.view.ConstraintColorManager;
import fr.labsticc.framework.constraints.view.ConstraintPartitionScanner;
import fr.labsticc.framework.constraints.view.ConstraintViewerConfiguration;

public class LuteViewerConfiguration extends ConstraintViewerConfiguration {

    public LuteViewerConfiguration() {
        super( new ConstraintColorManager() );
    }

    @Override
    protected LuteScanner createScanner( final ConstraintColorManager p_colorManager ) {
    	final LuteScanner scanner = new LuteScanner( p_colorManager );
        scanner.setDefaultReturnToken( new Token( new TextAttribute( p_colorManager.getColor( ConstraintColorManager.DEFAULT ) ) ) );
        
        return scanner;
    }

	@Override
	protected ConstraintPartitionScanner createPartitionScanner() {
		return new LutePartitionScanner();
	}
}
