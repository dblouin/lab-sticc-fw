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
package fr.labsticc.framework.core.settings;

import fr.labsticc.framework.core.message.IMessage;
import fr.labsticc.framework.core.object.IntrospectionSmartDataObject;

public class ListItem<T> extends IntrospectionSmartDataObject<T, IMessage<?,?>> implements IListItem<T> {
	
	private static final long serialVersionUID = -8492525425050997785L;

	private IMessage<?,?> info = null;

	private T code = null;

	private String label = null;
	
	private boolean prefered = false;
	
	public ListItem() {
		this( null );
	}

	public ListItem( final T p_code ) {
		this( p_code, null );
	}
	
	public ListItem( 	final T p_code,
						final String p_label ) {
		setCode( p_code );
		setLabelText( p_label );
	}
	
	@Override
	public T getCode() {
		return code;
	}

	@Override
	public String getLabelText() {
		return label;
	}

	@Override
	public void setCode( final T p_code ) {
		code = p_code;
	}

	@Override
	public void setLabelText( final String p_label ) {
		label = p_label;
	}

	@Override
	public IMessage<?,?> getInfo() {
		return info;
	}

	@Override
	public void setInfo( final IMessage<?,?> p_message ) {
		info = p_message;
	}

	@Override
	public boolean isPrefered() {
		return prefered;
	}

	@Override
	public void setPrefered( final boolean pb_prefered ) {
		prefered = pb_prefered;
	}
}
