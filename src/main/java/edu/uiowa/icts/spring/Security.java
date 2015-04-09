package edu.uiowa.icts.spring;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

public class Security {

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

	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && ! ( authentication instanceof AnonymousAuthenticationToken ) && authentication.isAuthenticated();
	}

	public static boolean isUserSwitched() {
		return hasRole( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR );
	}

}