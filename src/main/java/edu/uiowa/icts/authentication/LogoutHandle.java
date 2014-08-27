package edu.uiowa.icts.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import edu.uiowa.icts.log4j.AuditLogger;

public class LogoutHandle implements LogoutSuccessHandler {

	private LogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();

	@Override
	public void onLogoutSuccess( HttpServletRequest request, HttpServletResponse response, Authentication authentication ) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String username = request.getParameter( "j_username" );
		session.setAttribute( "username", username );
		AuditLogger.info( session.getId(), username, "logged out", request.getRemoteHost() );
		handler.onLogoutSuccess( request, response, authentication );
	}
}