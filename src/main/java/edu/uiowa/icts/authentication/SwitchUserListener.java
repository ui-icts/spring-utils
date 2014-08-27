package edu.uiowa.icts.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.authentication.switchuser.AuthenticationSwitchUserEvent;
import org.springframework.stereotype.Service;

import edu.uiowa.icts.log4j.AuditLogger;

@Service
public class SwitchUserListener implements ApplicationListener<AuthenticationSwitchUserEvent> {

	private static final Log log = LogFactory.getLog( AuthHandle.class );

	@Override
	public void onApplicationEvent( AuthenticationSwitchUserEvent event ) {
		AuditLogger.info( "", event.getAuthentication().getName(), "Switch User", "User switch from " + event.getAuthentication().getName() + " to " + event.getTargetUser().getUsername() );
		log.info( "User switch from " + event.getAuthentication().getName() + " to " + event.getTargetUser().getUsername() );
	}

}