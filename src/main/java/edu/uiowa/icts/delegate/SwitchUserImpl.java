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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

/**
 * <p>SwitchUserImpl class.</p>
 *
 * @author rrlorent
 * Basic implementation of edu.uiowa.icts.delegate.DelegateService.
 * Extend this class if you wish to have more information other than username in the switched session.
 * @version $Id: $
 */
public class SwitchUserImpl implements DelegateService {

	private static final Log log = LogFactory.getLog( SwitchUserImpl.class );

	private String usernameParameter = "j_username";

	/** {@inheritDoc} */
	@Override
	public boolean checkAuthentication( HttpServletRequest request ) throws AuthenticationException {

		Authentication current_authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> current_authorities = current_authentication.getAuthorities();

		for ( GrantedAuthority ga : current_authorities ) {
			if ( ( ga instanceof SwitchUserGrantedAuthority ) && ( ga.getAuthority().equals( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR ) ) ) {
				log.debug( "user already switched" );
				return false;
			}
		}

		String user = request.getParameter( usernameParameter );
		if ( user != null ) {
			return true;
		} else {
			return false;
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean checkExitAuthentication( HttpServletRequest request ) throws AuthenticationException {

		Authentication current_authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> current_authorities = current_authentication.getAuthorities();

		for ( GrantedAuthority ga : current_authorities ) {
			if ( ( ga instanceof SwitchUserGrantedAuthority ) && ( ga.getAuthority().equals( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR ) ) ) {
				SwitchUserGrantedAuthority sw = (SwitchUserGrantedAuthority) ga;
				String user = sw.getSource().getName();
				if ( user != null ) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * <p>Getter for the field <code>usernameParameter</code>.</p>
	 *
	 * @return a {@link java.lang.String} object.
	 */
	public String getUsernameParameter() {
		return usernameParameter;
	}

	/**
	 * <p>Setter for the field <code>usernameParameter</code>.</p>
	 *
	 * @param usernameParameter a {@link java.lang.String} object.
	 */
	public void setUsernameParameter( String usernameParameter ) {
		this.usernameParameter = usernameParameter;
	}

}
