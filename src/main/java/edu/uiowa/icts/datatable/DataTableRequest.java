package edu.uiowa.icts.datatable;

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
 * @author rrlorent
 * @since May 11, 2015
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

	public Integer getLength() {
		return length;
	}

	public void setLength( Integer length ) {
		this.length = length;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart( Integer start ) {
		this.start = start;
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw( String draw ) {
		this.draw = draw;
	}

	public Boolean getIndividualSearch() {
		return individualSearch;
	}

	public void setIndividualSearch( Boolean individualSearch ) {
		this.individualSearch = individualSearch;
	}

	public List<DataTableColumn> getColumns() {
		return columns;
	}

	public void setColumns( List<DataTableColumn> columns ) {
		this.columns = columns;
	}

	public List<DataTableOrder> getOrder() {
		return order;
	}

	public void setOrder( List<DataTableOrder> order ) {
		this.order = order;
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