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

public class MessageFormatException extends RuntimeException {
	
	private static final long serialVersionUID = 9088168199859446471L;

	public MessageFormatException( String p_message ) {
		super( p_message );
	}
  
	/**
	 * Constructor.
	 * @param p_ex Exception cause
	 */
	public MessageFormatException( Throwable p_th ) {
		super( p_th );
	}

  /**
   * @param p_message Exception message
   * @param p_ex Exception cause
   */
  public MessageFormatException( 	String p_message,
		  							Throwable p_th ) {
    super( p_message, p_th );
  }
}
