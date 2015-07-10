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

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author rrlorent
 */
public class IctsSecurityFilter extends GenericFilterBean {

	private static final Log log = LogFactory.getLog( IctsSecurityFilter.class );

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		log.debug( "filtering request " + servletRequest.getRequestURI() );

		//		ServletContext sc = getServletContext();
		//		Enumeration names = sc.getInitParameterNames();
		//		while( names.hasMoreElements() ){
		//			log.debug(names.nextElement());
		//		}

		chain.doFilter( request, response );
	}

}