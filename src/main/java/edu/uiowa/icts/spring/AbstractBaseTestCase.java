package edu.uiowa.icts.spring;

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

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractBaseTestCase extends TestCase {

	protected final Log log = LogFactory.getLog( getClass().getName() );

	@Override
	protected void setUp() throws Exception {
		log.debug( "=== starting " + getName() + " =============================" );
	}

	@Override
	protected void tearDown() throws Exception {
		log.debug( "=== ending " + getName() + " =============================\n" );
	}

	public void assertTextPresent( String expected, String value ) {
		if ( value == null || !value.contains( expected ) ) {
			fail( "expected presence of '" + expected + "' but was '" + value + "'" );
		}
	}

}