package edu.uiowa.icts.datatable;

/**
 * @author rrlorent
 * @since May 11, 2015
 */
public class DataTableColumn {

	private String data;
	private String name;
	private String title;
	private Boolean orderable = true;
	private Boolean searchable = true;
	private Boolean visible = true;

	private DataTableSearch search;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "DataTableColumn [" );
		if ( data != null ) {
			builder.append( "data=" );
			builder.append( data );
			builder.append( ", " );
		}
		if ( name != null ) {
			builder.append( "name=" );
			builder.append( name );
			builder.append( ", " );
		}
		if ( title != null ) {
			builder.append( "title=" );
			builder.append( title );
			builder.append( ", " );
		}
		if ( orderable != null ) {
			builder.append( "orderable=" );
			builder.append( orderable );
			builder.append( ", " );
		}
		if ( searchable != null ) {
			builder.append( "searchable=" );
			builder.append( searchable );
			builder.append( ", " );
		}
		if ( visible != null ) {
			builder.append( "visible=" );
			builder.append( visible );
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