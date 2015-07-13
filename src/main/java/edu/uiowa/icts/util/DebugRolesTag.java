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

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>DebugRolesTag class.</p>
 *
 * @user rrlorent
 * @date Sep 9, 2013
 * @author schappetj
 * @version $Id: $
 */
public class DebugRolesTag extends BodyTagSupport {

	private static final long serialVersionUID = 2380363263311326079L;

	private static final Log log = LogFactory.getLog( DebugRolesTag.class );

	private String var = null;

	private Iterator<? extends GrantedAuthority> iterator = null;

	/**
	 * <p>doStartTag.</p>
	 *
	 * @return a int.
	 */
	public int doStartTag() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( auth != null ) {

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			if ( authorities != null ) {

				iterator = authorities.iterator();

			} else {
				log.debug( "no authorities, list is null" );
			}

		} else {
			log.debug( "authentication object is null" );
		}

		if ( iterator != null && iterator.hasNext() ) {
			pageContext.setAttribute( "role", iterator.next().getAuthority() );
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}

	/**
	 * <p>doAfterBody.</p>
	 *
	 * @return a int.
	 */
	public int doAfterBody() {
		if ( iterator != null && iterator.hasNext() ) {
			pageContext.setAttribute( "role", iterator.next().getAuthority() );
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	/**
	 * <p>doEndTag.</p>
	 *
	 * @return a int.
	 * @throws javax.servlet.jsp.JspException if any.
	 */
	public int doEndTag() throws JspException {
		this.iterator = null;
		this.var = null;
		return super.doEndTag();
	}

	/**
	 * <p>Getter for the field <code>var</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getVar() {
		return var;
	}

	/**
	 * <p>Setter for the field <code>var</code>.</p>
	 *
	 * @param var a {@link java.lang.String} object.
	 */
	public void setVar( String var ) {
		this.var = var;
	}

}
