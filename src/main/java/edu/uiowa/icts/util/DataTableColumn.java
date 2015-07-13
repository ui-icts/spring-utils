package edu.uiowa.icts.util;

/*
 * #%L
 * spring-utils
 * %%
 * Copyright (C) 2010 - 2015 University of Iowa Institute for Clinical and Translational Science (ICTS)
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;

/**
 * <p>DataTableColumn class.</p>
 *
 * @author rrlorent
 * @deprecated use {@link edu.uiowa.icts.datatable.DataTableColumn} instead
 * @version $Id: $
 */
@Deprecated
public class DataTableColumn implements Serializable {

	private static final long serialVersionUID = -7520698552551125238L;

	private String data = null;
	private String name = null;
	private String title = null;
	private boolean searchable = true;
	private boolean sortable = true;
	private boolean visible = true;

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 */
	public DataTableColumn( String name, String title ) {
		this( name, name, title );
	}

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param data a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 */
	public DataTableColumn( String data, String name, String title ) {
		this.data = data;
		this.name = name;
		this.title = title;
	}

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String name, String title, boolean visible ) {
		this( name, name, title );
		this.visible = visible;
	}
	
	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param data a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String data, String name, String title, boolean visible ) {
		this( data, name, title );
		this.visible = visible;
	}
	
	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 * @param searchable a boolean.
	 * @param sortable a boolean.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String name, String title, boolean searchable, boolean sortable, boolean visible ) {
		this( name, name, title );
		this.searchable = searchable;
		this.sortable = sortable;
		this.visible = visible;
	}

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param data a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 * @param searchable a boolean.
	 * @param sortable a boolean.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String data, String name, String title, boolean searchable, boolean sortable, boolean visible ) {
		this( data, name, title );
		this.searchable = searchable;
		this.sortable = sortable;
		this.visible = visible;
	}

	/**
	 * <p>Getter for the field <code>name</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>Setter for the field <code>name</code>.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 */
	public void setName( String name ) {
		this.name = name;
	}

	/**
	 * <p>Getter for the field <code>title</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <p>Setter for the field <code>title</code>.</p>
	 *
	 * @param title a {@link java.lang.String} object.
	 */
	public void setTitle( String title ) {
		this.title = title;
	}

	/**
	 * <p>Getter for the field <code>data</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getData() {
		return data;
	}

	/**
	 * <p>Setter for the field <code>data</code>.</p>
	 *
	 * @param data a {@link java.lang.String} object.
	 */
	public void setData( String data ) {
		this.data = data;
	}

	/**
	 * <p>Getter for the field <code>searchable</code>.</p>
	 *
	 * @return a boolean.
	 */
	public boolean getSearchable() {
		return searchable;
	}

	/**
	 * <p>Setter for the field <code>searchable</code>.</p>
	 *
	 * @param searchable a boolean.
	 */
	public void setSearchable( boolean searchable ) {
		this.searchable = searchable;
	}

	/**
	 * <p>Getter for the field <code>sortable</code>.</p>
	 *
	 * @return a boolean.
	 */
	public boolean getSortable() {
		return sortable;
	}

	/**
	 * <p>Setter for the field <code>sortable</code>.</p>
	 *
	 * @param sortable a boolean.
	 */
	public void setSortable( boolean sortable ) {
		this.sortable = sortable;
	}

	/**
	 * <p>Getter for the field <code>visible</code>.</p>
	 *
	 * @return a boolean.
	 */
	public boolean getVisible() {
		return visible;
	}

	/**
	 * <p>Setter for the field <code>visible</code>.</p>
	 *
	 * @param visible a boolean.
	 */
	public void setVisible( boolean visible ) {
		this.visible = visible;
	}

}
