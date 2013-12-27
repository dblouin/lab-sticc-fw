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

import fr.labsticc.framework.core.object.IObjectProperty;
import fr.labsticc.framework.core.object.ISmartObject;

/**
 * DOCUMENT ME!
 * 
 * @author $author$
 * @version $Revision$
 */
public class ColumnDescriptor<DT extends ISmartObject<?, ?>> {
	public static enum ColumnType {	BOOLEAN, 
									TEXT, 
									DATE, 
									DECIMAL, 
									BIGDECIMAL, 
									INTEGER, 
									LONG, 
									TEXT_FORMATTED, 
									SEARCH, 
									COMBO, 
									PERCENT, 
									COMMAND_LINK, 
									IMAGE };

	public static enum EditMode {
		ALWAYS_EDITABLE, NEVER_EDITABLE, INSERTION_EDITABLE
	};

	public static enum Alignment {
		LEFT, RIGHT, CENTER
	};
	
	public static final String PROPERTY_PROPERTY_NAME = "propertyName";

	private IObjectProperty<? super DT> dataProperty = null;
	private ColumnType columnType = null;
	private Integer width = null;
	private EditMode editMode = null;
	private EditMode footerEditMode = null;
	private Integer order = -1;
	private String format = null;
	private Alignment dataAlignment = null;
	private String headerText = null;
	private Alignment headerAlignment = null;
	private boolean movable = true;
	private boolean sortable = true;

	public ColumnDescriptor(	final IObjectProperty<? super DT> p_dataProperty, 
								final ColumnType p_columnType,
								final String p_format, 
								final Integer p_order, 
								final Integer p_width, 
								final EditMode p_editMode, 
								final Alignment p_dataAlignment,
								final Alignment p_headerAlignment ) {
		this( p_dataProperty, p_columnType, p_format, p_order, p_width, p_editMode, p_dataAlignment, null, p_headerAlignment );
	}
	
	public ColumnDescriptor(	final IObjectProperty<? super DT> p_dataProperty, 
								final ColumnType p_columnType,
								final String p_format, 
								final Integer p_order, 
								final Integer p_width, 
								final EditMode p_editMode, 
								final Alignment p_dataAlignment,
								final String p_headerText,
								final Alignment p_headerAlignment ) {
		this( p_dataProperty, p_columnType, p_format, p_order, p_width, p_editMode, null, p_dataAlignment, p_headerText, p_headerAlignment );
	}
	
	public ColumnDescriptor(	final IObjectProperty<? super DT> p_dataProperty, 
								final ColumnType p_columnType,
								final String p_format, 
								final Integer p_order, 
								final Integer p_width, 
								final EditMode p_editMode, 
								final EditMode p_footerEditMode,
								final Alignment p_dataAlignment,
								final String p_headerText, 
								final Alignment p_headerAlignment ) {
		setDataProperty( p_dataProperty );
		setColumnType( p_columnType );
		setFormat(p_format );
		setOrder(p_order);
		setWidth(p_width);
		setEditMode(p_editMode);
		setFooterEditMode(p_footerEditMode);
		setDataAlignment(p_dataAlignment);
		setHeaderText(p_headerText);
		setHeaderAlignment(p_headerAlignment);
	}

	public IObjectProperty<? super DT> getObjectProperty() {
		return dataProperty;
	}

	public void setDataProperty( IObjectProperty<? super DT> p_dataProperty ) {
		this.dataProperty = p_dataProperty;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnType p_columnType) {
		this.columnType = p_columnType;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer p_width) {
		this.width = p_width;
	}

	public EditMode getEditMode() {
		return editMode;
	}

	public void setEditMode(EditMode p_editMode) {
		this.editMode = p_editMode;
	}

	public EditMode getFooterEditMode() {
		return footerEditMode;
	}

	public void setFooterEditMode(EditMode p_footerEditMode) {
		this.footerEditMode = p_footerEditMode;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer p_order) {
		this.order = p_order;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String p_format) {
		this.format = p_format;
	}

	public Alignment getDataAlignment() {
		return dataAlignment;
	}

	public void setDataAlignment(Alignment p_dataAlignment) {
		this.dataAlignment = p_dataAlignment;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String p_headerText) {
		this.headerText = p_headerText;
	}

	public Alignment getHeaderAlignment() {
		return headerAlignment;
	}

	public void setHeaderAlignment(Alignment p_headerAlignment) {
		this.headerAlignment = p_headerAlignment;
	}

	public boolean isMovable() {
		return movable;
	}

	public void setMovable(boolean pb_movable) {
		this.movable = pb_movable;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean pb_sortable) {
		this.sortable = pb_sortable;
	}
}
