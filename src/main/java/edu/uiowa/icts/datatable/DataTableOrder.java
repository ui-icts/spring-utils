package edu.uiowa.icts.datatable;

/**
 * @author rrlorent
 * @since May 11, 2015
 */
public class DataTableOrder {

	private Integer column;
	private String dir;

	public Integer getColumn() {
		return column;
	}

	public void setColumn( Integer column ) {
		this.column = column;
	}

	public String getDir() {
		return dir;
	}

	public void setDir( String dir ) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "DataTableOrder [" );
		if ( column != null ) {
			builder.append( "column=" );
			builder.append( column );
			builder.append( ", " );
		}
		if ( dir != null ) {
			builder.append( "dir=" );
			builder.append( dir );
		}
		builder.append( "]" );
		return builder.toString();
	}

}