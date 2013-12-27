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
package fr.labsticc.framework.constraints.ocl.view;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.Token;

import fr.labsticc.framework.constraints.view.ConstraintColorManager;
import fr.labsticc.framework.constraints.view.ConstraintPartitionScanner;
import fr.labsticc.framework.constraints.view.ConstraintViewerConfiguration;

public class OCLViewerConfiguration extends ConstraintViewerConfiguration {

    public OCLViewerConfiguration() {
        super( new ConstraintColorManager() );
    }

    @Override
    protected OCLScanner createScanner( final ConstraintColorManager p_colorManager ) {
    	final OCLScanner oclScanner = new OCLScanner( p_colorManager );
        oclScanner.setDefaultReturnToken( new Token( new TextAttribute( p_colorManager.getColor( ConstraintColorManager.DEFAULT ) ) ) );
        
        return oclScanner;
    }

	@Override
	protected ConstraintPartitionScanner createPartitionScanner() {
		return new OCLPartitionScanner();
	}
}
