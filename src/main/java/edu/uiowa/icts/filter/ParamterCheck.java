package edu.uiowa.icts.filter;

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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * <p>ParamterCheck class.</p>
 *
 * @author schappetj
 *
 ***************************
 * @version $Id: $
 ******/
public class ParamterCheck implements Filter {


/**************
 ** <filter>
 **  <filter-name>ParameterCheckFilter</filter-name>
 **   <filter-class>
 **       edu.uiowa.icts.filter.ParamterCheck
 **   </filter-class>
 **   <init-param>
 **       <param-name>test-param</param-name>
 **       <param-value>This parameter is for testing.</param-value>
 **   </init-param>
 ** </filter>
 ** <filter-mapping>
 **   <filter-name>ParameterCheckFilter</filter-name>
 **   <url-pattern>/*</url-pattern>
 ** </filter-mapping>
 **
 **
 **/

	
	static class FilteredRequest extends HttpServletRequestWrapper {

		/* These are the characters allowed by the Javascript validation */
		static String allowedChars = "+-0123456789#*";
		
		public FilteredRequest( ServletRequest request ) {
			super( (HttpServletRequest) request );
		}

		public String sanitize( String input ) {
			String result = "";
			for ( int i = 0; i < input.length(); i++ ) {
				if ( allowedChars.indexOf( input.charAt( i ) ) >= 0 ) {
					result += input.charAt( i );
				}
			}
			return result;
		}

		public String getParameter( String paramName ) {
			String value = super.getParameter( paramName );
			if ( "dangerousParamName".equals( paramName ) ) {
				value = sanitize( value );
			}
			return value;
		}

		public String[] getParameterValues( String paramName ) {
			String values[] = super.getParameterValues( paramName );
			if ( "dangerousParamName".equals( paramName ) ) {
				for ( int index = 0; index < values.length; index++ ) {
					values[index] = sanitize( values[index] );
				}
			}
			return values;
		}
	}

	/** {@inheritDoc} */
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {
		chain.doFilter( new FilteredRequest( request ), response );
	}

	/**
	 * <p>destroy.</p>
	 */
	public void destroy() {

	}

	/** {@inheritDoc} */
	public void init( FilterConfig filterConfig ) {
		// do stuff
	}

}
