package edu.uiowa.icts.util;

/**
 * @author rrlorent
 * @since August 1, 2014
 */
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