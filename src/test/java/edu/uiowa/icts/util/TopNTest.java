package edu.uiowa.icts.util;

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