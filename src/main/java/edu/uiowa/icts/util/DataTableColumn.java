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
 * @author rrlorent
 * @deprecated use {@link edu.uiowa.icts.datatable.DataTableColumn} instead
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
	
	public DataTableColumn( String name, String title, boolean searchable, boolean sortable, boolean visible ) {
		this( name, name, title );
		this.searchable = searchable;
		this.sortable = sortable;
		this.visible = visible;
	}

	public DataTableColumn( String data, String name, String title, boolean searchable, boolean sortable, boolean visible ) {
		this( data, name, title );
		this.searchable = searchable;
		this.sortable = sortable;
		this.visible = visible;
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

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public boolean getSearchable() {
		return searchable;
	}

	public void setSearchable( boolean searchable ) {
		this.searchable = searchable;
	}

	public boolean getSortable() {
		return sortable;
	}

	public void setSortable( boolean sortable ) {
		this.sortable = sortable;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setVisible( boolean visible ) {
		this.visible = visible;
	}

}