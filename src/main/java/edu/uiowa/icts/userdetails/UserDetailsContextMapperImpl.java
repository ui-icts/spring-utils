package edu.uiowa.icts.userdetails;

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

/**
 * @author rrlorent
 * @since May 18, 2015
 */
@Component
public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {

	private static final Log log = LogFactory.getLog( UserDetailsContextMapperImpl.class );

	private String rolePrefix = "ROLE_";
	private List<String> authorities;

	@Override
	public UserDetails mapUserFromContext( DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> grantedAuthorities ) {

		log.debug( "mapping :: " + username );

		List<GrantedAuthority> mappedAuthorities = new ArrayList<GrantedAuthority>( grantedAuthorities );

		if ( this.authorities != null && !this.authorities.isEmpty() ) {
			for ( String authority : this.authorities ) {
				mappedAuthorities.add( new SimpleGrantedAuthority( ( this.rolePrefix == null ? "" : this.rolePrefix.trim() ) + authority ) );
			}
		}

		return new User( username, "[PROTECED]", mappedAuthorities );
	}

	@Override
	public void mapUserToContext( UserDetails user, DirContextAdapter ctx ) {
		// TODO
	}

	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix( String rolePrefix ) {
		this.rolePrefix = rolePrefix;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities( List<String> authorities ) {
		this.authorities = authorities;
	}

}