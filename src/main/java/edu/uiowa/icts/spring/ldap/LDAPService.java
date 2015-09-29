package edu.uiowa.icts.spring.ldap;

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

import java.util.List;

/**
 * <p>LDAPService interface.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
@SuppressWarnings("rawtypes")
public interface LDAPService {
	
	/**
	 * <p>getAllPersonNames.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
	public List getAllPersonNames();
	/**
	 * <p>findByUsername.</p>
	 *
	 * @param username a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List<LDAPPerson> findByUsername(String username);
	/**
	 * <p>getByUsername.</p>
	 *
	 * @param username a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List<LDAPPerson> getByUsername(String username);
	/**
	 * <p>search.</p>
	 *
	 * @param value a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List search(String value);
	/**
	 * <p>findByFullname.</p>
	 *
	 * @param firstname a {@link java.lang.String} object.
	 * @param lastname a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List<LDAPPerson> findByFullname(String firstname, String lastname);
	/**
	 * <p>searchFirstName.</p>
	 *
	 * @param firstName a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List<LDAPPerson> searchFirstName( String firstName );
	/**
	 * <p>searchLastName.</p>
	 *
	 * @param lastName a {@link java.lang.String} object.
	 * @return a {@link java.util.List} object.
	 */
	public List<LDAPPerson> searchLastName( String lastName );
	
}
