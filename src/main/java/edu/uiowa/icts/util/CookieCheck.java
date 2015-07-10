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
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CookieCheck extends BodyTagSupport {

	private static final long serialVersionUID = 3786884329856237339L;

	static Log log = LogFactory.getLog( CookieCheck.class );

	private String cookieName = null;

	public int doStartTag() {

		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		Cookie[] cookies = request.getCookies();

		String value = ServletUtilities.getCookieValue( cookies, cookieName, null );

		log.debug( value );

		if ( value != null ) {

		} else {

		}

		return SKIP_BODY;
	}

	public int doAfterBody() {
		clear();
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	private void clear() {
		this.cookieName = null;
	}

	public String getCookieName() {
		return cookieName;
	}

	public void setCookieName( String cookieName ) {
		this.cookieName = cookieName;
	}

}