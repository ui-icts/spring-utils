package edu.uiowa.icts.delegate;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author rrlorent
 */
@SuppressWarnings( "serial" )
public class Username extends TagSupport {

	private static final Log log = LogFactory.getLog( Username.class );

	public int doStartTag() throws JspException {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String user = "";
			if ( auth != null ) {
				user = auth.getName();
			} else {
				user = "anonymousUser";
			}
			pageContext.getOut().write( user );
		} catch ( IOException e ) {
			log.error( "problem writing username", e );
		}
		return EVAL_PAGE;
	}
}