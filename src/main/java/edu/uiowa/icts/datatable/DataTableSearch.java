package edu.uiowa.icts.datatable;

/**
 * @author rrlorent
 * @since May 11, 2015
 */
public class DataTableSearch {

	private String value;
	private Boolean regex;

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public Boolean getRegex() {
		return regex;
	}

	public void setRegex( Boolean regex ) {
		this.regex = regex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "DataTableSearch [" );
		if ( value != null ) {
			builder.append( "value=" );
			builder.append( value );
			builder.append( ", " );
		}
		if ( regex != null ) {
			builder.append( "regex=" );
			builder.append( regex );
		}
		builder.append( "]" );
		return builder.toString();
	}

}