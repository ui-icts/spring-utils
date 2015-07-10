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
 * @author rrlorent
 * @since May 11, 2015
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

	public DataTableColumn() {
		// default no argument constructor required for jackson
	}

	public DataTableColumn( String name, String title ) {
		this( name, name, title );
	}

	public DataTableColumn( String data, String name, String title ) {
		this.data = data;
		this.name = name;
		this.title = title;
	}

	public DataTableColumn( String name, String title, boolean visible ) {
		this( name, name, title );
		this.visible = visible;
	}

	public DataTableColumn( String data, String name, String title, boolean visible ) {
		this( data, name, title );
		this.visible = visible;
	}

	public DataTableColumn( String name, String title, boolean searchable, boolean orderable, boolean visible ) {
		this( name, name, title );
		this.searchable = searchable;
		this.orderable = orderable;
		this.visible = visible;
	}

	public DataTableColumn( String data, String name, String title, boolean searchable, boolean orderable, boolean visible ) {
		this( data, name, title );
		this.searchable = searchable;
		this.orderable = orderable;
		this.visible = visible;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName( String className ) {
		this.className = className;
	}

	public Boolean getOrderable() {
		return orderable;
	}

	public void setOrderable( Boolean orderable ) {
		this.orderable = orderable;
	}

	public Boolean getSearchable() {
		return searchable;
	}

	public void setSearchable( Boolean searchable ) {
		this.searchable = searchable;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible( Boolean visible ) {
		this.visible = visible;
	}

	public DataTableSearch getSearch() {
		return search;
	}

	public void setSearch( DataTableSearch search ) {
		this.search = search;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty( String name, Object value ) {
		this.additionalProperties.put( name, value );
	}

	@Override
	public String toString() {
		return String.format( "DataTableColumn [data=%s, name=%s, title=%s, className=%s, orderable=%s, searchable=%s, visible=%s, search=%s, additionalProperties=%s]", data, name, title, className, orderable, searchable, visible, search, additionalProperties );
	}

}