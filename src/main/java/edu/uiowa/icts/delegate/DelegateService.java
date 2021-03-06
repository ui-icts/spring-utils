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

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>DelegateService interface.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public interface DelegateService {
	
	/**
	 * <p>checkAuthentication.</p>
	 *
	 * @param request a {@link javax.servlet.http.HttpServletRequest} object.
	 * @return a boolean.
	 * @throws org.springframework.security.core.AuthenticationException if any.
	 */
	boolean checkAuthentication( HttpServletRequest request ) throws AuthenticationException;

	/**
	 * <p>checkExitAuthentication.</p>
	 *
	 * @param request a {@link javax.servlet.http.HttpServletRequest} object.
	 * @return a boolean.
	 * @throws org.springframework.security.core.AuthenticationException if any.
	 */
	boolean checkExitAuthentication( HttpServletRequest request ) throws AuthenticationException;
	
}
