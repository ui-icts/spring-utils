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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

public class SwitchUser extends SwitchUserFilter {

	@Autowired
	private DelegateService delegateService;

	@Override
	public Authentication attemptSwitchUser( HttpServletRequest request ) throws AuthenticationException {
		if ( delegateService.checkAuthentication( request ) ) {
			return super.attemptSwitchUser( request );
		}
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public Authentication attemptExitUser( HttpServletRequest request ) throws AuthenticationException {
		if ( delegateService.checkExitAuthentication( request ) ) {
			return super.attemptExitUser( request );
		}
		return SecurityContextHolder.getContext().getAuthentication();
	}

}