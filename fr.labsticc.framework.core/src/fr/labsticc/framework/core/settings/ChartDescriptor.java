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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.labsticc.framework.core.object.IObjectProperty;
import fr.labsticc.framework.core.object.ISmartObject;

public class ChartDescriptor<PT extends ISmartObject<?, ?>, LT extends ISmartObject<?, ?>> implements Serializable, Cloneable {

	private static final long serialVersionUID = 6348995411861545461L;

	private String title = null;

	private IObjectProperty<? super PT> dataProperty = null;

	private List<ChartSectionDescriptor<LT>> sections = new ArrayList<ChartSectionDescriptor<LT>>();

	public ChartDescriptor(	final IObjectProperty<? super PT> p_dataProperty,
							final List<ChartSectionDescriptor<LT>> p_sections ) {
		this( "", p_dataProperty, p_sections );
	}
	
	public ChartDescriptor(	final String p_title,
							final IObjectProperty<? super PT> p_dataProperty,
							final List<ChartSectionDescriptor<LT>> p_sections) {
		setTitle( p_title );
		setDataProperty( p_dataProperty );
		setSections( p_sections );
	}

	public List<ChartSectionDescriptor<LT>> getSections() {
		return sections;
	}

	public void setSections(List<ChartSectionDescriptor<LT>> p_sections ) {
		sections = p_sections;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( final String p_title ) {
		title = p_title;
	}

	public void setDataProperty( IObjectProperty<? super PT> p_dataProperty ) {
		dataProperty = p_dataProperty;
	}

	public IObjectProperty<? super PT> getDataProperty() {
		return dataProperty;
	}
}
