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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * <p>DataTable class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "recordsFiltered", "data", "draw", "recordsTotal" } )
public class DataTable {

	@JsonProperty( "recordsFiltered" )
	private Integer recordsFiltered = 0;

	@JsonProperty( "data" )
	private List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();

	@JsonProperty( "draw" )
	private String draw;

	@JsonProperty( "error" )
	private String error;

	@JsonProperty( "recordsTotal" )
	private Integer recordsTotal = 0;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * <p>Constructor for DataTable.</p>
	 */
	public DataTable() {

	}

	/**
	 * <p>Constructor for DataTable.</p>
	 *
	 * @param draw a {@link java.lang.String} object.
	 * @param error a {@link java.lang.String} object.
	 */
	public DataTable( String draw, String error ) {
		this.draw = draw;
		this.error = error;
	}

	/**
	 * <p>Getter for the field <code>recordsFiltered</code>.</p>
	 *
	 * @return The recordsFiltered
	 */
	@JsonProperty( "recordsFiltered" )
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * <p>Setter for the field <code>recordsFiltered</code>.</p>
	 *
	 * @param recordsFiltered The recordsFiltered
	 */
	@JsonProperty( "recordsFiltered" )
	public void setRecordsFiltered( Integer recordsFiltered ) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * <p>Getter for the field <code>data</code>.</p>
	 *
	 * @return The data
	 */
	@JsonProperty( "data" )
	public List<LinkedHashMap<String, Object>> getData() {
		return data == null ? new ArrayList<LinkedHashMap<String, Object>>() : data;
	}

	/**
	 * <p>Setter for the field <code>data</code>.</p>
	 *
	 * @param data The data
	 */
	@JsonProperty( "data" )
	public void setData( List<LinkedHashMap<String, Object>> data ) {
		this.data = data;
	}

	/**
	 * <p>Getter for the field <code>draw</code>.</p>
	 *
	 * @return The draw
	 */
	@JsonProperty( "draw" )
	public String getDraw() {
		return draw;
	}

	/**
	 * <p>Setter for the field <code>draw</code>.</p>
	 *
	 * @param draw The draw
	 */
	@JsonProperty( "draw" )
	public void setDraw( String draw ) {
		this.draw = draw;
	}

	/**
	 * <p>Getter for the field <code>recordsTotal</code>.</p>
	 *
	 * @return The recordsTotal
	 */
	@JsonProperty( "recordsTotal" )
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * <p>Setter for the field <code>recordsTotal</code>.</p>
	 *
	 * @param recordsTotal The recordsTotal
	 */
	@JsonProperty( "recordsTotal" )
	public void setRecordsTotal( Integer recordsTotal ) {
		this.recordsTotal = recordsTotal;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this );
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

	/**
	 * <p>Getter for the field <code>error</code>.</p>
	 *
	 * @return The error
	 */
	@JsonProperty( "error" )
	public String getError() {
		return error;
	}

	/**
	 * <p>Setter for the field <code>error</code>.</p>
	 *
	 * @param error The error
	 */
	@JsonProperty( "error" )
	public void setError( String error ) {
		this.error = error;
	}

}
