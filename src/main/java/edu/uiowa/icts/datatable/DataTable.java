package edu.uiowa.icts.datatable;

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

@JsonInclude( JsonInclude.Include.NON_NULL )
@JsonPropertyOrder( { "recordsFiltered", "data", "draw", "recordsTotal" } )
public class DataTable {

	@JsonProperty( "recordsFiltered" )
	private Integer recordsFiltered;

	@JsonProperty( "data" )
	private List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String, Object>>();

	@JsonProperty( "draw" )
	private String draw;

	@JsonProperty( "error" )
	private String error;

	@JsonProperty( "recordsTotal" )
	private Integer recordsTotal;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The recordsFiltered
	 */
	@JsonProperty( "recordsFiltered" )
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * 
	 * @param recordsFiltered
	 *            The recordsFiltered
	 */
	@JsonProperty( "recordsFiltered" )
	public void setRecordsFiltered( Integer recordsFiltered ) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * 
	 * @return The data
	 */
	@JsonProperty( "data" )
	public List<LinkedHashMap<String, Object>> getData() {
		return data == null ? new ArrayList<LinkedHashMap<String, Object>>() : data;
	}

	/**
	 * 
	 * @param data
	 *            The data
	 */
	@JsonProperty( "data" )
	public void setData( List<LinkedHashMap<String, Object>> data ) {
		this.data = data;
	}

	/**
	 * 
	 * @return The draw
	 */
	@JsonProperty( "draw" )
	public String getDraw() {
		return draw;
	}

	/**
	 * 
	 * @param draw
	 *            The draw
	 */
	@JsonProperty( "draw" )
	public void setDraw( String draw ) {
		this.draw = draw;
	}

	/**
	 * 
	 * @return The recordsTotal
	 */
	@JsonProperty( "recordsTotal" )
	public Integer getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * 
	 * @param recordsTotal
	 *            The recordsTotal
	 */
	@JsonProperty( "recordsTotal" )
	public void setRecordsTotal( Integer recordsTotal ) {
		this.recordsTotal = recordsTotal;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this );
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty( String name, Object value ) {
		this.additionalProperties.put( name, value );
	}

	/**
	 * 
	 * @return The error
	 */
	@JsonProperty( "error" )
	public String getError() {
		return error;
	}

	/**
	 * 
	 * @param error
	 *            The error
	 */
	@JsonProperty( "error" )
	public void setError( String error ) {
		this.error = error;
	}

}
