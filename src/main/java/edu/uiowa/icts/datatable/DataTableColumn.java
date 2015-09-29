package edu.uiowa.icts.datatable;

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

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * <p>DataTableColumn class.</p>
 *
 * @author rrlorent
 * @since May 11, 2015
 * @version $Id: $
 */
public class DataTableColumn {

	private String data;
	private String name;
	private String title;
	private String className;
	private Boolean orderable = true;
	private Boolean searchable = true;
	private Boolean visible = true;

	private DataTableSearch search;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 */
	public DataTableColumn() {
		// default no argument constructor required for jackson
	}

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
	 * @param orderable a boolean.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String name, String title, boolean searchable, boolean orderable, boolean visible ) {
		this( name, name, title );
		this.searchable = searchable;
		this.orderable = orderable;
		this.visible = visible;
	}

	/**
	 * <p>Constructor for DataTableColumn.</p>
	 *
	 * @param data a {@link java.lang.String} object.
	 * @param name a {@link java.lang.String} object.
	 * @param title a {@link java.lang.String} object.
	 * @param searchable a boolean.
	 * @param orderable a boolean.
	 * @param visible a boolean.
	 */
	public DataTableColumn( String data, String name, String title, boolean searchable, boolean orderable, boolean visible ) {
		this( data, name, title );
		this.searchable = searchable;
		this.orderable = orderable;
		this.visible = visible;
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
	 * <p>Getter for the field <code>className</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * <p>Setter for the field <code>className</code>.</p>
	 *
	 * @param className a {@link java.lang.String} object.
	 */
	public void setClassName( String className ) {
		this.className = className;
	}

	/**
	 * <p>Getter for the field <code>orderable</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getOrderable() {
		return orderable;
	}

	/**
	 * <p>Setter for the field <code>orderable</code>.</p>
	 *
	 * @param orderable a {@link java.lang.Boolean} object.
	 */
	public void setOrderable( Boolean orderable ) {
		this.orderable = orderable;
	}

	/**
	 * <p>Getter for the field <code>searchable</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getSearchable() {
		return searchable;
	}

	/**
	 * <p>Setter for the field <code>searchable</code>.</p>
	 *
	 * @param searchable a {@link java.lang.Boolean} object.
	 */
	public void setSearchable( Boolean searchable ) {
		this.searchable = searchable;
	}

	/**
	 * <p>Getter for the field <code>visible</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getVisible() {
		return visible;
	}

	/**
	 * <p>Setter for the field <code>visible</code>.</p>
	 *
	 * @param visible a {@link java.lang.Boolean} object.
	 */
	public void setVisible( Boolean visible ) {
		this.visible = visible;
	}

	/**
	 * <p>Getter for the field <code>search</code>.</p>
	 *
	 * @return a {@link edu.uiowa.icts.datatable.DataTableSearch} object.
	 */
	public DataTableSearch getSearch() {
		return search;
	}

	/**
	 * <p>Setter for the field <code>search</code>.</p>
	 *
	 * @param search a {@link edu.uiowa.icts.datatable.DataTableSearch} object.
	 */
	public void setSearch( DataTableSearch search ) {
		this.search = search;
	}

	/**
	 * <p>Getter for the field <code>additionalProperties</code>.</p>
	 *
	 * @return a {@link java.util.Map} object.
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * <p>setAdditionalProperty.</p>
	 *
	 * @param name a {@link java.lang.String} object.
	 * @param value a {@link java.lang.Object} object.
	 */
	@JsonAnySetter
	public void setAdditionalProperty( String name, Object value ) {
		this.additionalProperties.put( name, value );
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return String.format( "DataTableColumn [data=%s, name=%s, title=%s, className=%s, orderable=%s, searchable=%s, visible=%s, search=%s, additionalProperties=%s]", data, name, title, className, orderable, searchable, visible, search, additionalProperties );
	}

}
