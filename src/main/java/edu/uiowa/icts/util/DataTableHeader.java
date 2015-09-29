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
 * <p>DataTableHeader class.</p>
 *
 * @author rrlorent
 * @since August 1, 2014
 * @deprecated use {@link edu.uiowa.icts.datatable.DataTableColumn} instead
 * @version $Id: $
 */
@Deprecated
public class DataTableHeader {

	private String data = null;
	private String name = null;
	private Boolean searchable = false;
	private Boolean orderable = false;
	private String searchValue = null;
	private Boolean searchRegex = false;

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
	 * <p>Setter for the field <code>data</code>.</p>
	 *
	 * @deprecated use setData( String data ) instead
	 * @param data a {@link java.lang.Integer} object.
	 */
	@Deprecated
	public void setData( Integer data ) {
		this.data = "" + data;
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
	 * <p>Getter for the field <code>searchValue</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * <p>Setter for the field <code>searchValue</code>.</p>
	 *
	 * @param searchValue a {@link java.lang.String} object.
	 */
	public void setSearchValue( String searchValue ) {
		this.searchValue = searchValue;
	}

	/**
	 * <p>Getter for the field <code>searchRegex</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getSearchRegex() {
		return searchRegex;
	}

	/**
	 * <p>Setter for the field <code>searchRegex</code>.</p>
	 *
	 * @param searchRegex a {@link java.lang.Boolean} object.
	 */
	public void setSearchRegex( Boolean searchRegex ) {
		this.searchRegex = searchRegex;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "DataTableHeader [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable=" + orderable + ", searchValue=" + searchValue + ", searchRegex=" + searchRegex + "]";
	}

}
