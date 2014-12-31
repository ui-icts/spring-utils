package edu.uiowa.icts.util;

import org.apache.commons.lang.StringUtils;

/**
 * @author rrlorent
 */
public class SortColumn {

	static final String ASC = "asc";
	static final String DESC = "desc";

	private String column;
	private String direction;

	public SortColumn( String column, String direction ) {
		this.setColumn( column );
		this.setDirection( direction );
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn( String column ) {
		this.column = column;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection( String direction ) {
		if ( !StringUtils.equalsIgnoreCase( direction, ASC ) && !StringUtils.equalsIgnoreCase( direction, DESC ) ) {
			throw new IllegalArgumentException( "sort direction not supported " + direction );
		}
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "SortColumn [column=" + column + ", direction=" + direction + "]";
	}
}