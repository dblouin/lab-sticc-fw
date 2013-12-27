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
/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: OCLDocument.java,v 1.3 2007/10/25 03:11:50 cdamus Exp $
 */
package fr.labsticc.framework.constraints.view;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;

public class ConstraintDocument extends Document {

    public ConstraintDocument( final ConstraintPartitionScanner p_partitionScanner ) {
        super();
        
        final IDocumentPartitioner partitioner = new FastPartitioner( 	p_partitionScanner,
                														new String[] { p_partitionScanner.getCommentContentType() } );
        partitioner.connect( this );
        setDocumentPartitioner( partitioner );
    }
}
