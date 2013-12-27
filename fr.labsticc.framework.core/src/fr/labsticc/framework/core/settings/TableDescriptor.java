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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.labsticc.framework.core.object.IObjectProperty;
import fr.labsticc.framework.core.object.ISmartObject;

public class TableDescriptor<PT extends ISmartObject<?, ?>, LT extends ISmartObject<?, ?>> implements Serializable, Cloneable {

	private static final long serialVersionUID = -9020488185755444231L;

	private String title = null;

	private IObjectProperty<? super PT> objectProperty = null;

	private List<ColumnDescriptor<LT>> columns = new ArrayList<ColumnDescriptor<LT>>();

	public TableDescriptor(	final IObjectProperty<? super PT> p_objectProperty,
							final List<ColumnDescriptor<LT>> p_columns) {
		this( "", p_objectProperty, p_columns );
	}
	
	public TableDescriptor(	final String p_title,
							final IObjectProperty<? super PT> p_objectProperty,
							final List<ColumnDescriptor<LT>> p_columns) {
		setTitle( p_title );
		setObjectProperty( p_objectProperty );
		setColumns( p_columns );
	}

	public void setColumns( final List<ColumnDescriptor<LT>> p_columns ) {
		columns = p_columns;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( final String p_title ) {
		title = p_title;
	}

	public int getNumberOfColumns() {
		return getColumns().size();
	}

	public List<ColumnDescriptor<LT>> getColumns() {
		return columns;
	}

	public ColumnDescriptor<LT> getColumn( final IObjectProperty<? super LT> p_colProperty ) {
		for ( final ColumnDescriptor<LT> column : getColumns() ) {
			if ( p_colProperty.equals( column.getObjectProperty() ) ) {
				return column;
			}
		}
		
		return null;
	}
	
	public boolean removeColumn( final IObjectProperty<? super LT> p_colProperty ) {
		final ColumnDescriptor<LT> column = getColumn( p_colProperty );
		
		if ( column == null ) {
			return false;
		}
		
		return getColumns().remove( column );
	}

	public void setObjectProperty( IObjectProperty<? super PT> p_objectProperty ) {
		objectProperty = p_objectProperty;
	}

	public IObjectProperty<? super PT> getObjectProperty() {
		return objectProperty;
	}
	
	public Integer getColumnOrder( IObjectProperty<LT> p_colObjectProperty ) {
		for ( ColumnDescriptor<LT> column : getColumns() ) {
			if ( p_colObjectProperty.equals( column.getObjectProperty() ) ) {
				return column.getOrder();
			}
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public IObjectProperty<? super LT>[] getColumnObjectProperties() {
		IObjectProperty<? super LT>[] objectProperties = new IObjectProperty[ getColumns().size() ];
		
		for ( ColumnDescriptor<LT> column : getColumns() ) {
			objectProperties[ column.getOrder() ] = column.getObjectProperty();
		}
		
		return objectProperties;
	}
	
	public Map<IObjectProperty<? super LT>, ColumnDescriptor.EditMode> getColumnEditables() {
		Map<IObjectProperty<? super LT>, ColumnDescriptor.EditMode> editables = new HashMap<IObjectProperty<? super LT>, ColumnDescriptor.EditMode>();
		
		for ( ColumnDescriptor<LT> column : getColumns() ) {
			editables.put( column.getObjectProperty(), column.getEditMode() );
		}
		
		return editables;
	}
	
	public void addColumn( final ColumnDescriptor<LT> p_column ) {
		List<ColumnDescriptor<LT>> columns = getColumns();
		
		for ( int index = p_column.getOrder(); index < columns.size(); index++ ) {
			ColumnDescriptor<LT> column = columns.get( index );
			column.setOrder( column.getOrder() + 1 );
		}
		
		getColumns().add( p_column.getOrder(), p_column );
	}
}
