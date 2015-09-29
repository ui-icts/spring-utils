package edu.uiowa.icts.spring;

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

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

/**
 * <p>Security class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class Security {

	/**
	 * <p>hasRole.</p>
	 *
	 * @param role a {@link java.lang.String} object.
	 * @return a boolean.
	 */
	public static boolean hasRole( String role ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ( authentication != null ) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for ( GrantedAuthority ga : authorities ) {
				if ( StringUtils.equalsIgnoreCase( ga.getAuthority(), role ) ) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>isAuthenticated.</p>
	 *
	 * @return a boolean.
	 */
	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && ! ( authentication instanceof AnonymousAuthenticationToken ) && authentication.isAuthenticated();
	}

	/**
	 * <p>isUserSwitched.</p>
	 *
	 * @return a boolean.
	 */
	public static boolean isUserSwitched() {
		return hasRole( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR );
	}

}
