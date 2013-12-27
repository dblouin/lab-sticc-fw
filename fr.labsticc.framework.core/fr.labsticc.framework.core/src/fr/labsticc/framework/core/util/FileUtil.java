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

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FileUtil {
	
	public static interface ICopyPolicy {
		
		boolean canOverride( final File p_targetFile );
		
		void postCopy( final File p_targetFile );
	};

	public static List<File> deleteFile( 	String p_filePath,
											boolean pb_recursive ) {
		List<File> deletedFiles = new ArrayList<File>();

		File file = new File( p_filePath );
		deleteFile( file, pb_recursive, deletedFiles );
		
		return deletedFiles;
	}
	
	public static void deleteFile( 	File p_file,
									boolean pb_recursive,
									List<File> deletedFiles ) {
		if ( pb_recursive && p_file.isDirectory() ) {
			File[] content = p_file.listFiles();
			
			for ( final File file : content ) {
				deleteFile( file, pb_recursive, deletedFiles );
			}
		}

		deleteSingleFile( p_file, deletedFiles );
	}
	
	private static void deleteSingleFile( 	final File p_file,
											final List<File> deletedFiles ) {
		if ( p_file.isDirectory() ) {
			p_file.delete();
		}
		else if ( p_file.delete() ) {
			deletedFiles.add( p_file );
		}
	}
	
	public static boolean deleteSingleFile( final String p_fileName ) {
		return new File( p_fileName ).delete();
	}
	
	/**
	 * Copy the content of an input file into an output file. Also handles directories.
	 * @param p_inFile
	 * @param p_outFile
	 * @throws IOException
	 */
	public static void copyFile( 	final File p_inFile,
									final File p_outFile ) 
	throws IOException {
		copyFile( p_inFile, p_outFile, null );
	}
		
	public static void copyFile( 	final File p_inFile,
									final File p_outFile,
									final ICopyPolicy p_copyPolicy )
	throws IOException {
		if ( p_inFile.isFile() ) {
			copyFile( p_inFile.getPath(), p_outFile.getPath(), p_copyPolicy );
		}
		else if ( p_inFile.isDirectory() ) {
			final boolean canCopyContent;
			
			if ( p_outFile.exists() ) {
				canCopyContent = p_copyPolicy == null || p_copyPolicy.canOverride( p_outFile );
    		}
			else {
				canCopyContent = p_outFile.mkdir();
			}
	 
			if ( canCopyContent ) {
	    		for ( final String file : p_inFile.list() ) {
	    		   final File srcFile = new File( p_inFile, file );
	    		   final File destFile = new File( p_outFile, file );
	    		   copyFile( srcFile, destFile, p_copyPolicy );
	    		}
			}
		}
	}

	public static void copyFile( 	final String p_inFileName,
									final String p_outFileName ) 
	throws IOException {
		copyFile( p_inFileName, p_outFileName, null );
	}

	public static void copyFile( 	final String p_inFileName,
									final String p_outFileName,
									final ICopyPolicy p_copyPolicy ) 
	throws IOException {
		final File outFile = new File( p_outFileName );

		if ( p_copyPolicy != null && p_copyPolicy.canOverride( outFile ) ) {
			if ( !outFile.canWrite() ) {
				outFile.setWritable( true );
			}
			
			FileReader inFileWriter = null;
			FileWriter outFileWriter  = null;

			try {
				inFileWriter = new FileReader( p_inFileName );
				outFileWriter = new FileWriter( outFile );
				int ch;
		    
			    while ( ( ch = inFileWriter.read() ) != -1 ) {
			    	outFileWriter.write( ch );
			    }
			}
			finally {
				try {
					closeFile( inFileWriter );
				}
				finally {
					closeFile( outFileWriter );
					
					if ( p_copyPolicy != null ) {
						p_copyPolicy.postCopy( outFile );
					}
				}
			}
		}
	}
	
	private static void closeFile( final Closeable p_closeable )
	throws IOException {
		if ( p_closeable != null ) {
			p_closeable.close();
		}
	}
	
	public static String getNameWithoutExtension( final String p_fileName ) {
		return removeExtension( new File( p_fileName ).getName() );
	}

	public static String removeExtension( final String p_fileName ) {
		return removeExtension( new File( p_fileName ) );
	}
	
	public static String removeExtension( final File p_file ) {
		final String rootName = p_file.getPath();
		final int indexExt = rootName.lastIndexOf( '.' );
		
		if ( indexExt > - 1 ) {
			return rootName.substring( 0, indexExt );
		}
		
		return rootName;
	}

	public static String replaceExtension( 	final String p_fileName,
											final String p_newExt ) {
		return p_fileName.substring( 0, p_fileName.lastIndexOf( '.' ) ) + "." + p_newExt;
	}
	
	public static String getFileContent( final String p_filename ) 
	throws IOException {
		BufferedReader reader = null;
		
		try {
			final int size = 1024;
			reader = new BufferedReader( new FileReader( p_filename ), size );
			char[] buffer = new char[ 1 ];
			final StringBuffer content = new StringBuffer();
			
		    while ( reader.read( buffer ) != -1 ) {
		    	content.append( buffer );
		    }
		    
		    return content.toString();
		}
		finally {
			if ( reader != null ) {
				reader.close();
			}
		}
	}
	
	public static String getFileContent( 	final String p_filename,
											final int pi_startPos,
											final int pi_length ) 
	throws IOException {
		return getContent( new FileInputStream( p_filename ), pi_startPos, pi_length );
	}
	
	public static String getContent( final InputStream p_inputStream ) 
	throws IOException {
		final Reader reader = new InputStreamReader( p_inputStream );
		
		try {
			char[] buffer = new char[ 1 ];
			final StringBuffer content = new StringBuffer();
			
		    while ( reader.read( buffer ) != -1 ) {
		    	content.append( buffer );
		    }
		    
		    return content.toString();
		}
		finally {
			if ( reader != null ) {
				reader.close();
			}
		}
	}

	public static String getContent( 	final InputStream p_inputStream,
										final int pi_startPos,
										final int pi_length ) 
	throws IOException {
		InputStreamReader reader = null;
		
		try {
			reader = new InputStreamReader( p_inputStream );
			char[] bytes = new char[ pi_length ];
			
			skip( reader, pi_startPos );
			
			read( reader, bytes, pi_length );
			
			return new String( bytes );
		}
		finally {
			if ( reader != null ) {
				reader.close();
			}
		}
	}

	public static void skip(	final InputStreamReader p_reader,
								int pi_startPos )
	throws IOException {
		long skipped = 0;
		
		while( skipped >= 0 && pi_startPos > 0 && p_reader.ready() ){
			skipped = p_reader.skip( pi_startPos );
			
			if( skipped > 0 ){
				pi_startPos -= skipped;
			}
		}
	}
	
	public static void read( 	final InputStreamReader p_reader,
								final char[] pc_bytes,
								int pi_length )
	throws IOException {
		int bufferOffset = 0;
		int charactersRead = 0;
		
		while( charactersRead >= 0 && pi_length > 0 ){
			charactersRead = p_reader.read( pc_bytes, bufferOffset, pi_length );
			
			if ( charactersRead > 0 ){
				bufferOffset += charactersRead;
				pi_length -= charactersRead;
			}
		}
	}

	/**
	 * Returns a list of all files of the specified type (extension) in the specified directory and its child directories.
	 * @param p_directoryName The root directory to be searched. 
	 * @param p_type The file extension without the dot.
	 * @return
	 */
	public static List<File> findFilesOfTypeInDirectory( 	final String p_directoryName,
															final String p_type ) {
		return findFilesOfTypeInDirectory( p_directoryName, p_type, false );
	}
	
	/**
	 * Returns a list of all files of the specified type (extension) in the specified directory.
	 * @param p_directoryName The root directory to be searched. 
	 * @param p_type The file extension without the dot.
	 * @param pb_recursive If true, subdirectories will also be searched.
	 * @return
	 */
	public static List<File> findFilesOfTypeInDirectory( 	final String p_directoryName,
															final String p_type,
															final boolean pb_recursive ) {
		final List<File> files = new ArrayList<File>();
		
		for ( final File foundFile : findFilesOfTypeAndDirsInDirectory( p_directoryName, p_type, pb_recursive ) ) {
			if ( foundFile.isFile() ) {
				files.add( foundFile );
			}
		}
		
		return files;
	}
	
	private static File[] findFilesOfTypeAndDirsInDirectory( 	final String p_directoryName,
																final String p_type,
																final boolean pb_recursive ) {
		final File srcDir = new File( p_directoryName );
		
		if ( !srcDir.isDirectory() ) {
			throw new IllegalArgumentException( "File " + p_directoryName + " is not a directory." );
		}
		
		final File[] files;
		
		if ( p_type == null ) {
			files = srcDir.listFiles();
		}
		else {
			final String extension = "." + p_type;
			files = srcDir.listFiles( new FileFilter() {
				
				@Override
				public boolean accept( final File p_pathname ) {
					return p_pathname.isDirectory() || p_pathname.getName().endsWith( extension ) ;
				}
			} );
		}
		
		final Collection<File> foundFiles = new HashSet<File>( Arrays.asList( files ) );
		
		if ( pb_recursive ) {
			for ( final File file : files ) {
				if ( file.isDirectory() ) {
					foundFiles.addAll( Arrays.asList( findFilesOfTypeAndDirsInDirectory( file.getPath(), p_type, pb_recursive ) ) );
				}
			}
		}
		
		return foundFiles.toArray( new File[ foundFiles.size() ] );
	}
	
	public static void writeToFile( final String p_fileName,
									final String p_content )
	throws IOException {
		final FileWriter writer = new FileWriter( p_fileName );
		
		try {
			writer.write( p_content );
		}
		finally {
			writer.close();
		}
	}
	
	public static void writeToFile( final String p_fileName,
									final byte[] p_content )
	throws IOException {
		final FileOutputStream writer = new FileOutputStream( p_fileName );
		
		try {
			writer.write( p_content );
		}
		finally {
			writer.close();
		}
	}
	
	public static String getDevice( final File p_file ) {
		final String path = p_file.getPath();
		
		int index = path.indexOf( ":" );
		
		if ( index == - 1 ) {
			return null;
		}
		
		return path.substring( 0, index + 1);
	}
}
