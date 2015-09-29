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

/**
 * <p>DataTableSearch class.</p>
 *
 * @author rrlorent
 * @since May 11, 2015
 * @version $Id: $
 */
public class DataTableSearch {

	private String value;
	private Boolean regex;

	/**
	 * <p>Constructor for DataTableSearch.</p>
	 */
	public DataTableSearch() {
		// default no argument constructor required for jackson
	}

	/**
	 * <p>Constructor for DataTableSearch.</p>
	 *
	 * @param value a {@link java.lang.String} object.
	 * @param regex a {@link java.lang.Boolean} object.
	 */
	public DataTableSearch( String value, Boolean regex ) {
		this.value = value;
		this.regex = regex;
	}

	/**
	 * <p>Getter for the field <code>value</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <p>Setter for the field <code>value</code>.</p>
	 *
	 * @param value a {@link java.lang.String} object.
	 */
	public void setValue( String value ) {
		this.value = value;
	}

	/**
	 * <p>Getter for the field <code>regex</code>.</p>
	 *
	 * @return a {@link java.lang.Boolean} object.
	 */
	public Boolean getRegex() {
		return regex;
	}

	/**
	 * <p>Setter for the field <code>regex</code>.</p>
	 *
	 * @param regex a {@link java.lang.Boolean} object.
	 */
	public void setRegex( Boolean regex ) {
		this.regex = regex;
	}

	/** {@inheritDoc} */
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
