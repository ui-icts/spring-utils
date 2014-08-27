package edu.uiowa.icts.util;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @user rrlorent
 * @date Sep 9, 2013
 */
public class DebugRolesTag extends BodyTagSupport {

	private static final long serialVersionUID = 2380363263311326079L;

	private static final Log log = LogFactory.getLog( DebugRolesTag.class );

	private String var = null;

	private Iterator<? extends GrantedAuthority> iterator = null;

	public int doStartTag() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( auth != null ) {

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			if ( authorities != null ) {

				iterator = authorities.iterator();

			} else {
				log.debug( "no authorities, list is null" );
			}

		} else {
			log.debug( "authentication object is null" );
		}

		if ( iterator != null && iterator.hasNext() ) {
			pageContext.setAttribute( "role", iterator.next().getAuthority() );
			return EVAL_BODY_INCLUDE;
		}

		return SKIP_BODY;
	}

	public int doAfterBody() {
		if ( iterator != null && iterator.hasNext() ) {
			pageContext.setAttribute( "role", iterator.next().getAuthority() );
			return EVAL_BODY_AGAIN;
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		this.iterator = null;
		this.var = null;
		return super.doEndTag();
	}

	public String getVar() {
		return var;
	}

	public void setVar( String var ) {
		this.var = var;
	}

}