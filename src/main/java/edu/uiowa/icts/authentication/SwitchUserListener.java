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