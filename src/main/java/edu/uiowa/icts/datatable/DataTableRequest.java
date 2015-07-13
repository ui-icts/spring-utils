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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.uiowa.icts.spring.GenericDaoListOptions;
import edu.uiowa.icts.util.SortColumn;

/**
 * <p>DataTableRequest class.</p>
 *
 * @author rrlorent
 * @since May 11, 2015
 * @version $Id: $
 */
public class DataTableRequest {

	private Integer length;
	private Integer start;
	private String draw;
	private Boolean individualSearch = false;

	private List<DataTableColumn> columns = new ArrayList<DataTableColumn>();
	private List<DataTableOrder> order = new ArrayList<DataTableOrder>();

	private DataTableSearch search;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * <p>getGenericDaoListOptions.</p>
	 *
	 * @return a {@link edu.uiowa.icts.spring.GenericDaoListOptions} object.
	 */
	@JsonIgnore
	public GenericDaoListOptions getGenericDaoListOptions() {
		GenericDaoListOptions options = new GenericDaoListOptions();
		if ( this.columns != null ) {
			if ( individualSearch != null && individualSearch ) {
				Map<String, List<Object>> likes = new HashMap<String, List<Object>>();
				for ( DataTableColumn column : this.columns ) {
					if ( column.getSearchable() != null && column.getSearchable() ) {
						if ( column.getSearch() != null && column.getSearch().getValue() != null ) {
							List<Object> values = new ArrayList<Object>();
							for ( String splitSearchValue : StringUtils.split( column.getSearch().getValue(), ' ' ) ) {
								values.add( splitSearchValue );
							}
							likes.put( column.getName(), values );
						}
					}
				}
				options.setLikes( likes );
			} else {
				List<String> searchColumns = new ArrayList<String>();
				for ( DataTableColumn column : this.columns ) {
					if ( column.getSearchable() != null && column.getSearchable() ) {
						searchColumns.add( column.getName() );
					}
				}
				if ( this.search != null ) {
					options.setSearch( this.search.getValue() );
				}
				options.setSearchColumns( searchColumns );
			}
		}

		if ( this.order != null ) {
			List<SortColumn> sortColumns = new ArrayList<SortColumn>();
			for ( DataTableOrder dto : this.order ) {
				if ( dto.getColumn() < this.columns.size() ) {
					DataTableColumn col = this.columns.get( dto.getColumn() );
					if ( col != null && col.getSearchable() != null && col.getSearchable() ) {
						sortColumns.add( new SortColumn( col.getName(), dto.getDir() == null ? "asc" : dto.getDir() ) );
					}
				}
			}
			options.setSorts( sortColumns );
		}

		options.setLimit( length );
		options.setStart( start );

		return options;
	}

	/**
	 * <p>Getter for the field <code>length</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * <p>Setter for the field <code>length</code>.</p>
	 *
	 * @param length a {@link java.lang.Integer} object.
	 */
	public void setLength( Integer length ) {
		this.length = length;
	}

	/**
	 * <p>Getter for the field <code>start</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * <p>Setter for the field <code>start</code>.</p>
	 *
	 * @param start a {@link java.lang.Integer} object.
	 */
	public void setStart( Integer start ) {
		this.start = start;
	}

	/**
	 * <p>Getter for the field <code>draw</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDraw() {
		return draw;
	}

	/**
	 * <p>Setter for the field <code>draw</code>.</p>
	 *
	 * @param draw a {@link java.lang.String} object.
	 */
	public void setDraw( String draw ) {
		this.draw = draw;
	}

	/**
	 * <p>Getter for the field <code>individualSearch</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getIndividualSearch() {
		return individualSearch;
	}

	/**
	 * <p>Setter for the field <code>individualSearch</code>.</p>
	 *
	 * @param individualSearch a {@link java.lang.Boolean} object.
	 */
	public void setIndividualSearch( Boolean individualSearch ) {
		this.individualSearch = individualSearch;
	}

	/**
	 * <p>Getter for the field <code>columns</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<DataTableColumn> getColumns() {
		return columns;
	}

	/**
	 * <p>Setter for the field <code>columns</code>.</p>
	 *
	 * @param columns a {@link java.util.List} object.
	 */
	public void setColumns( List<DataTableColumn> columns ) {
		this.columns = columns;
	}

	/**
	 * <p>Getter for the field <code>order</code>.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List<DataTableOrder> getOrder() {
		return order;
	}

	/**
	 * <p>Setter for the field <code>order</code>.</p>
	 *
	 * @param order a {@link java.util.List} object.
	 */
	public void setOrder( List<DataTableOrder> order ) {
		this.order = order;
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
		StringBuilder builder = new StringBuilder();
		builder.append( "DataTableRequest [" );
		if ( length != null ) {
			builder.append( "length=" );
			builder.append( length );
			builder.append( ", " );
		}
		if ( start != null ) {
			builder.append( "start=" );
			builder.append( start );
			builder.append( ", " );
		}
		if ( draw != null ) {
			builder.append( "draw=" );
			builder.append( draw );
			builder.append( ", " );
		}
		if ( individualSearch != null ) {
			builder.append( "individualSearch=" );
			builder.append( individualSearch );
			builder.append( ", " );
		}
		if ( columns != null ) {
			builder.append( "columns=" );
			builder.append( columns );
			builder.append( ", " );
		}
		if ( order != null ) {
			builder.append( "order=" );
			builder.append( order );
			builder.append( ", " );
		}
		if ( search != null ) {
			builder.append( "search=" );
			builder.append( search );
		}
		builder.append( "]" );
		return builder.toString();
	}

}
