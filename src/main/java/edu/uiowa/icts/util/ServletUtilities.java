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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Some simple time savers. Part of tutorial on servlets and JSP that appears at
 * http://www.apl.jhu.edu/~hall/java/Servlet-Tutorial/ 1999 Marty Hall; may be
 * freely used or adapted.
 *
 * @author schappetj
 * @version $Id: $
 */
public class ServletUtilities {

	// Approximate values are fine.
	/** Constant <code>SECONDS_PER_MONTH=60 * 60 * 24 * 30</code> */
	public static final int SECONDS_PER_MONTH = 60 * 60 * 24 * 30;
	/** Constant <code>SECONDS_PER_YEAR=60 * 60 * 24 * 365</code> */
	public static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;

	
	public static final String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">";

	/**
	 * <p>headWithTitle.</p>
	 *
	 * @param title a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String headWithTitle( String title ) {
		return ( DOCTYPE + "\n" + "<HTML>\n" + "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" );
	}

	/**
	 * Read a parameter with the specified name, convert it to an int, and
	 * return it. Return the designated default value if the parameter doesn't
	 * exist or if it is an illegal integer format.
	 *
	 * @param request a {@link javax.servlet.http.HttpServletRequest} object.
	 * @param paramName a {@link java.lang.String} object.
	 * @param defaultValue a int.
	 * @return a int.
	 */
	public static int getIntParameter( HttpServletRequest request, String paramName, int defaultValue ) {
		String paramString = request.getParameter( paramName );
		int paramValue;
		try {
			paramValue = Integer.parseInt( paramString );
		} catch ( NumberFormatException nfe ) { // Handles null and bad format
			paramValue = defaultValue;
		}
		return ( paramValue );
	}

	/**
	 * <p>getCookieValue.</p>
	 *
	 * @param cookies an array of {@link javax.servlet.http.Cookie} objects.
	 * @param cookieName a {@link java.lang.String} object.
	 * @param defaultValue a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	public static String getCookieValue( Cookie[] cookies, String cookieName, String defaultValue ) {
		for ( int i = 0; i < cookies.length; i++ ) {
			Cookie cookie = cookies[i];
			if ( cookieName.equals( cookie.getName() ) ) {
				return ( cookie.getValue() );
			}
		}
		return ( defaultValue );
	}

	/**
	 * <p>getCookie.</p>
	 *
	 * @param cookies an array of {@link javax.servlet.http.Cookie} objects.
	 * @param cookieName a {@link java.lang.String} object.
	 * @return a {@link javax.servlet.http.Cookie} object.
	 */
	public static Cookie getCookie( Cookie[] cookies, String cookieName ) {
		for ( int i = 0; i < cookies.length; i++ ) {
			Cookie cookie = cookies[i];
			if ( cookieName.equals( cookie.getName() ) ) {
				return cookie;
			}
		}
		return null;
	}

}
