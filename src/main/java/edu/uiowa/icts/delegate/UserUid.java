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

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

/**
 * <p>UserUid class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class UserUid {

	/**
	 * <p>username.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public static String username() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String user = "";
		if ( auth != null ) {
			user = auth.getName();
		} else {
			user = "anonymousUser";
		}
		return user;
	}

	/**
	 * <p>previousUsername.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public static String previousUsername() {
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		if ( currentAuth != null ) {
			Collection<? extends GrantedAuthority> currentAuthorities = currentAuth.getAuthorities();
			for ( GrantedAuthority ga : currentAuthorities ) {
				if ( ( ga instanceof SwitchUserGrantedAuthority ) && ( ga.getAuthority().equals( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR ) ) ) {
					return ( (SwitchUserGrantedAuthority) ga ).getSource().getName();
				}
			}
			return currentAuth.getName();
		}
		return "anonymousUser";
	}
}
