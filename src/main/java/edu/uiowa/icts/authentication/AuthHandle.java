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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import edu.uiowa.icts.log4j.AuditLogger;

/**
 * @author rrlorent
 * @since Jul 30, 2014
 */
public class AuthHandle implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	private static final Log log = LogFactory.getLog( AuthHandle.class );

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

	@Override
	public void onAuthenticationFailure( HttpServletRequest req, HttpServletResponse res, AuthenticationException excep ) throws IOException, ServletException {
		AuditLogger.info( "NONE", req.getParameter( "j_username" ), "Error logging in from " + req.getRemoteHost(), excep.getMessage() );
		req.getSession().setAttribute( "SPRING_SECURITY_LAST_EXCEPTION", excep );
		redirectStrategy.sendRedirect( req, res, "/login.html?error=true" );
	}

	@Override
	public void onAuthenticationSuccess( HttpServletRequest req, HttpServletResponse res, Authentication auth ) throws IOException, ServletException {

		log.debug( "successfully authenticated " + String.valueOf( auth.getPrincipal() ) );

		if ( req.getSession().getAttribute( "SPRING_SECURITY_LAST_EXCEPTION" ) != null ) {
			req.getSession().removeAttribute( "SPRING_SECURITY_LAST_EXCEPTION" );
		}

		for ( GrantedAuthority ga : auth.getAuthorities() ) {
			log.debug( ga.getAuthority() );
		}

		HttpSession session = req.getSession();
		String username = req.getParameter( "j_username" );
		session.setAttribute( "username", username );
		
		AuditLogger.info( session.getId(), username, "logged in from", req.getRemoteHost() );
		
		target.onAuthenticationSuccess( req, res, auth );


	}

	public void proceed( HttpServletRequest request, HttpServletResponse response, Authentication auth ) throws IOException, ServletException {
		target.onAuthenticationSuccess( request, response, auth );
	}
}