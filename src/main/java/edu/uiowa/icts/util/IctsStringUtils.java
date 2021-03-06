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

import java.util.StringTokenizer;

/**
 * <p>IctsStringUtils class.</p>
 *
 * @author rrlorent
 * @version $Id: $
 */
public class IctsStringUtils {

	/**
	 * <p>relabel.</p>
	 *
	 * @param sqllabel a {@link java.lang.String} object.
	 * @param leadingCapital a boolean.
	 * @return a {@link java.lang.String} object.
	 */
	public String relabel( String sqllabel, boolean leadingCapital ) {
		StringBuffer result = new StringBuffer();
		StringTokenizer theTokenizer = new StringTokenizer( sqllabel, "_-" );
		while ( theTokenizer.hasMoreTokens() ) {
			String theToken = theTokenizer.nextToken().toLowerCase();
			result.append( Character.toUpperCase( theToken.charAt( 0 ) ) );
			result.append( theToken.substring( 1 ) );
		}
		if ( !leadingCapital ) {
			result.setCharAt( 0, Character.toLowerCase( result.charAt( 0 ) ) );
		}
		return result.toString();
	}

}
