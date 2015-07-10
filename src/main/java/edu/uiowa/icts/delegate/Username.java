package edu.uiowa.icts.delegate;

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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author rrlorent
 */
@SuppressWarnings( "serial" )
public class Username extends TagSupport {

	private static final Log log = LogFactory.getLog( Username.class );

	public int doStartTag() throws JspException {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user = "";
			if ( auth != null ) {
				user = auth.getName();
			} else {
				user = "anonymousUser";
			}
			pageContext.getOut().write( user );
		} catch ( IOException e ) {
			log.error( "problem writing username", e );
		}
		return EVAL_PAGE;
	}
}