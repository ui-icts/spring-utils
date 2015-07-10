package edu.uiowa.icts.util;

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

import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TopNTest {
	
	private static final Log log = LogFactory.getLog( TopNTest.class );

	@Test
	public void testTopNInt() {
		TopN<Double> top10 = new TopN<Double>( 10 );
		top10.inTopN( 100d );
		int val = top10.inTopN( 500d );
		log.debug( val );
		assertTrue( top10.max() == 500d );
	}

	@Test
	public void testTopN() {
		TopN<Double> top10 = new TopN<Double>( 10 );
		top10.inTopN( 100d );
		top10.inTopN( 90d );
		top10.inTopN( 120d );
		assertTrue( top10.max() == 120d );
	}

	@Test
	public void testReset() {
		TopN<Double> top10 = new TopN<Double>( 10 );
		top10.inTopN( 100d );
		top10.inTopN( 90d );
		top10.inTopN( 120d );
		top10.reset();
		assertTrue( top10.max() == 0 );
	}

	@Test
	public void testResetInteger() {
		TopN<Double> top10 = new TopN<Double>( 10 );
		top10.inTopN( 100d );
		top10.inTopN( 90d );
		top10.inTopN( 120d );
		top10.reset();
		assertTrue( top10.max() == 0 );
	}

	@Test
	public void testInTopN() {
		TopN<Double> top10 = new TopN<Double>( 10 );
		assertTrue( top10.inTopN( 100d ) == 0 );
		assertTrue( top10.inTopN( 90d ) == 1 );
		assertTrue( top10.inTopN( 120d ) == 0 );
	}
}