package edu.uiowa.icts.delegate;

import java.util.Collection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

/**
 * @author rrlorent
 */
@SuppressWarnings( "serial" )
public class SpringSecurityIsNotSwitched extends TagSupport {
	
	public int doStartTag() throws JspException {
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		if ( currentAuth != null ) {
			Collection<? extends GrantedAuthority> currentAuthorities = currentAuth.getAuthorities();
			for ( GrantedAuthority ga : currentAuthorities ) {
				if ( ( ga instanceof SwitchUserGrantedAuthority ) && ( ga.getAuthority().equals( SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR ) ) ) {
					return SKIP_BODY;
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}
	
}