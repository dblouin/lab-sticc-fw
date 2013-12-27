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

import fr.labsticc.framework.core.object.ObjectPropertySimpleLevel;
import fr.labsticc.framework.core.object.IObjectProperty;
import fr.labsticc.framework.core.object.ISmartObject;

/**
 * Interface for message object in the framework.
 *
 * @author Dominique Blouin
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public interface IMessage<ID, IN extends IMessage<?,?>> extends ISmartObject<ID, IN>{
	
	@SuppressWarnings("rawtypes")
	IObjectProperty<IMessage> PROPERTY_LABEL = new ObjectPropertySimpleLevel( IMessage.class, String.class, "label", false );
	@SuppressWarnings("rawtypes")
	IObjectProperty<IMessage> PROPERTY_CAUSE = new ObjectPropertySimpleLevel( IMessage.class, Throwable.class, "cause", false );
	@SuppressWarnings("rawtypes")
	IObjectProperty<IMessage> PROPERTY_SEVERITY = new ObjectPropertySimpleLevel( IMessage.class, Severity.class, "severity", false );
	@SuppressWarnings("rawtypes")
	IObjectProperty<IMessage> PROPERTY_ID = new ObjectPropertySimpleLevel( IMessage.class, Object.class, "id", false );

	enum Severity { OK, INFO, UNKNOWN, WARNING, ERROR, FATAL };
  
  /**
   * Returns the internationalized label of the message.
   * @return The internationalized label of the message.
   */
  String getLabel();

  void setLabel( String p_label );

  Throwable getCause();

  void setCause( Throwable p_cause );

  void setSeverity( Severity p_severity );

  /**
   * Returns a constant defining the severity of the message (Lethal, information, ...).
   * @return  The constant defining the severity of the message.
   */
  Severity getSeverity();
}
