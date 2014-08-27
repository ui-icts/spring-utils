package edu.uiowa.icts.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author rrlorent
 */
public class IctsSecurityFilter extends GenericFilterBean {

	private static final Log log = LogFactory.getLog( IctsSecurityFilter.class );

	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		log.debug( "filtering request " + servletRequest.getRequestURI() );

		//		ServletContext sc = getServletContext();
		//		Enumeration names = sc.getInitParameterNames();
		//		while( names.hasMoreElements() ){
		//			log.debug(names.nextElement());
		//		}

		chain.doFilter( request, response );
	}

}