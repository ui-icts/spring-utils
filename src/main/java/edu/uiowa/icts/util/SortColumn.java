package edu.uiowa.icts.util;

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