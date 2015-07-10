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