package edu.uiowa.icts.userdetails;

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