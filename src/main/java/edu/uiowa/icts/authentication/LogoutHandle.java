package edu.uiowa.icts.authentication;

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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import edu.uiowa.icts.log4j.AuditLogger;

/**
 * <p>LogoutHandle class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class LogoutHandle implements LogoutSuccessHandler {

	private LogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();

	/** {@inheritDoc} */
	@Override
	public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String username = request.getParameter( "j_username" );
		session.setAttribute( "username", username );
		AuditLogger.info( session.getId(), username, "logged out", request.getRemoteHost() );
		handler.onLogoutSuccess( request, response, authentication );
	}
}
