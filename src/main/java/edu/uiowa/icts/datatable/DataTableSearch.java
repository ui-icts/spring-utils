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
 * @author rrlorent
 * @since May 11, 2015
 */
public class DataTableSearch {

	private String value;
	private Boolean regex;

	public DataTableSearch() {
		// default no argument constructor required for jackson
	}

	public DataTableSearch( String value, Boolean regex ) {
		this.value = value;
		this.regex = regex;
	}

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