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

import fr.labsticc.framework.core.util.ConstantObject;

public class MessageConstants extends ConstantObject {

	private static final long serialVersionUID = 8035443033049463644L;
	
	public static final MessageConstants BUTTON_OK = new MessageConstants( "BUTTON_OK" );
  public static final MessageConstants BUTTON_OK_CANCEL = new MessageConstants( "BUTTON_OK_CANCEL" );
  public static final MessageConstants BUTTON_YES_NO = new MessageConstants( "BUTTON_YES_NO" );
  public static final MessageConstants BUTTON_YES_NO_CANCEL = new MessageConstants( "BUTTON_YES_NO_CANCEL" );
  public static final MessageConstants BUTTON_RETRY_CANCEL = new MessageConstants( "BUTTON_RETRY_CANCEL" );
  public static final MessageConstants BUTTON_ABORT_RETRY_IGNORE = new MessageConstants( "BUTTON_ABORT_RETRY_IGNORE" );
  
//  public static final MessageConstants ICON_STOP = new MessageConstants( "ICON_STOP" );
//  public static final MessageConstants ICON_QUESTION = new MessageConstants( "ICON_QUESTION" );
//  public static final MessageConstants ICON_HAND = new MessageConstants( "ICON_HAND" );
//  public static final MessageConstants ICON_EXCLAMATION = new MessageConstants( "ICON_EXCLAMATION" );
//  public static final MessageConstants ICON_STAR = new MessageConstants( "ICON_STAR" );

  public MessageConstants( String p_constantName ) {
    super( p_constantName );
  }
}
