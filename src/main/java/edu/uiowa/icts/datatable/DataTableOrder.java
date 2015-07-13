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
 * <p>DataTableOrder class.</p>
 *
 * @author rrlorent
 * @since May 11, 2015
 * @version $Id: $
 */
public class DataTableOrder {

	private Integer column;
	private String dir;

	/**
	 * <p>Getter for the field <code>column</code>.</p>
	 *
	 * @return a {@link java.lang.Integer} object.
	 */
	public Integer getColumn() {
		return column;
	}

	/**
	 * <p>Setter for the field <code>column</code>.</p>
	 *
	 * @param column a {@link java.lang.Integer} object.
	 */
	public void setColumn( Integer column ) {
		this.column = column;
	}

	/**
	 * <p>Getter for the field <code>dir</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * <p>Setter for the field <code>dir</code>.</p>
	 *
	 * @param dir a {@link java.lang.String} object.
	 */
	public void setDir( String dir ) {
		this.dir = dir;
	}

	/** {@inheritDoc} */
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
