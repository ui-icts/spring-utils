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

/**
 * @author rrlorent
 * @since August 1, 2014
 * @deprecated use {@link edu.uiowa.icts.datatable.DataTableColumn} instead
 */
@Deprecated
public class DataTableHeader {

	private String data = null;
	private String name = null;
	private Boolean searchable = false;
	private Boolean orderable = false;
	private String searchValue = null;
	private Boolean searchRegex = false;

	public String getData() {
		return data;
	}
	
	public void setData( String data ) {
		this.data = data;
	}
	
	/**
	 * @deprecated use setData( String data ) instead
	 */
	@Deprecated
	public void setData( Integer data ) {
		this.data = "" + data;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Boolean getSearchable() {
		return searchable;
	}

	public void setSearchable( Boolean searchable ) {
		this.searchable = searchable;
	}

	public Boolean getOrderable() {
		return orderable;
	}

	public void setOrderable( Boolean orderable ) {
		this.orderable = orderable;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue( String searchValue ) {
		this.searchValue = searchValue;
	}

	public Boolean getSearchRegex() {
		return searchRegex;
	}

	public void setSearchRegex( Boolean searchRegex ) {
		this.searchRegex = searchRegex;
	}

	@Override
	public String toString() {
		return "DataTableHeader [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable=" + orderable + ", searchValue=" + searchValue + ", searchRegex=" + searchRegex + "]";
	}

}