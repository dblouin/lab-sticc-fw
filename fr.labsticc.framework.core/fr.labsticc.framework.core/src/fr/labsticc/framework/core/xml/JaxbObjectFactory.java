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
package fr.labsticc.framework.core.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.labsticc.framework.core.exception.ResourceAccessException;
import fr.labsticc.framework.core.util.FileUtil;

public class JaxbObjectFactory {
	
	private final transient Logger trace = LoggerFactory.getLogger( getClass() ); 

	private final JAXBContext jaxbContext;
	
	private boolean validating = true;

	public JaxbObjectFactory() {
		try {
			// Need to specify this class's class loader otherwise will not work when deployed in a plugin. 
			// By default, the current thread class loader is used and does not contain the model classes.
			//	jaxbContext = JAXBContext.newInstance( getClass().getPackage().getName() );
			jaxbContext = JAXBContext.newInstance( 	getClass().getPackage().getName(), 
													getClass().getClassLoader() );
		} 
		catch ( JAXBException p_ex ) {
			throw new IllegalStateException( 	"Unable to initiate JAXB context!", 
												p_ex );
		}
	}
	
	public void writeToXML( Object p_object,
							String p_outputFileName ) 
	throws ResourceAccessException {
		FileOutputStream fileInputStr = null;
		
		try {
			fileInputStr = new FileOutputStream( p_outputFileName );
			writeToXML( p_object, fileInputStr );
		} 
		catch ( FileNotFoundException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
		finally {
			if ( fileInputStr != null ) {
				try {
					fileInputStr.close();
				} 
				catch ( IOException p_ex ) {
					trace.error( toString(), p_ex );
				}
			}
		}
	}

	public void writeToXML( Object p_object,
							OutputStream p_outputStream ) 
	throws ResourceAccessException {
		try {
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, 
	        						true );
	        
	        if ( !isValidating() ) {
	        	marshaller.setSchema( null );
	        }
	        
	        marshaller.marshal( p_object, p_outputStream );
		}
		catch ( JAXBException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}

	public Object readFromXml( String p_resourceName )
	throws ResourceAccessException {
		try {
			return readFromXml( new FileInputStream( p_resourceName ) );
		} 
		catch ( FileNotFoundException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	public Object readFromXml( InputStream p_intpuStr )
	throws ResourceAccessException {
		try {
	        //create our unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			if ( !isValidating() ) {
				unmarshaller.setSchema( null );
			}
			
			unmarshaller.setProperty( "com.sun.xml.internal.bind.ObjectFactory", this );
	        
			//read
	        return unmarshaller.unmarshal( p_intpuStr );
		} 
		catch ( JAXBException p_ex ) {
			throw new ResourceAccessException( p_ex );
		}
	}
	
	public List<File> deleteResources( String p_directoryPath ) {
		return FileUtil.deleteFile( p_directoryPath, true );
	}

	public boolean isValidating() {
		return validating;
	}

	public void setValidating(boolean validating) {
		this.validating = validating;
	}
}
