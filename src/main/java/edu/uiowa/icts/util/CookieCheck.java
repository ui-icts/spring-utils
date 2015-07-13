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

/**
 * <p>CookieCheck class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class CookieCheck extends BodyTagSupport {

	private static final long serialVersionUID = 3786884329856237339L;

	static Log log = LogFactory.getLog( CookieCheck.class );

	private String cookieName = null;

	/**
	 * <p>doStartTag.</p>
	 *
	 * @return a int.
	 */
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

	/**
	 * <p>doAfterBody.</p>
	 *
	 * @return a int.
	 */
	public int doAfterBody() {
		clear();
		return SKIP_BODY;
	}

	/**
	 * <p>doEndTag.</p>
	 *
	 * @return a int.
	 * @throws javax.servlet.jsp.JspException if any.
	 */
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	private void clear() {
		this.cookieName = null;
	}

	/**
	 * <p>Getter for the field <code>cookieName</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getCookieName() {
		return cookieName;
	}

	/**
	 * <p>Setter for the field <code>cookieName</code>.</p>
	 *
	 * @param cookieName a {@link java.lang.String} object.
	 */
	public void setCookieName( String cookieName ) {
		this.cookieName = cookieName;
	}

}
