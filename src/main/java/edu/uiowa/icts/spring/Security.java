package edu.uiowa.icts.spring;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class Security {

	public static boolean hasRole( String role ) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if( authentication != null ){
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for ( GrantedAuthority ga : authorities ) {
				if ( ga.getAuthority().equals( role ) ) {
					return true;
				}
			}
		}
		return false;
	}
	
}