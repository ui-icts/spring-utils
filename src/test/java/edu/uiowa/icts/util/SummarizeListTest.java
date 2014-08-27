package edu.uiowa.icts.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SummarizeListTest {

	SummarizeList lp = new SummarizeList();

	@Test
	public void asFloatTest() {
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0" }));
		List<Float> l = lp.asFloat();
		assertTrue(l.size() == 3);
		assertTrue(l.contains(1.0f));
		assertTrue(l.contains(2.0f));
		assertTrue(l.contains(3.0f));

		lp.setList(new ArrayList<String>());
		l = lp.asFloat();
		assertTrue(l.size() == 0);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		l = lp.asFloat();
		assertTrue(l.size() == 0);
	}

	@Test
	public void asIntegerTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3" }));
		List<Integer> l = lp.asInteger();
		assertTrue(l.size() == 3);
		assertTrue(l.contains(1));
		assertTrue(l.contains(2));
		assertTrue(l.contains(3));

		lp.setList(new ArrayList<String>());
		l = lp.asInteger();
		assertTrue(l.size() == 0);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		l = lp.asInteger();
		assertTrue(l.size() == 0);
	}

	@Test
	public void avgFloatTest() {
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0" }));
		assertTrue(lp.avgFloat() == 2.0f);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		assertTrue(lp.avgFloat() == null);
	}

	@Test
	public void avgIntegerTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3" }));
		assertTrue(lp.avgInteger() == 2);

		lp.setList(Arrays.asList(new String[] { "1", "2", "3", "4" }));
		assertTrue(lp.avgInteger() == 2);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		assertTrue(lp.avgInteger() == null);
	}

	@Test
	public void categoryCountTest() {
		lp.setList(Arrays.asList(new String[] { "foo", "bar", "bar", "foo",
				"foo", null }));
		Map<String, Integer> m = lp.categoryCount();
		assertTrue(m.get("foo") == 3);
		assertTrue(m.get("bar") == 2);
		assertTrue(m.get(null) == 1);
		testDescendingOrder(m);

		m = lp.categoryCount(2);
		assertTrue(m.get("foo") == 3);
		assertTrue(m.get("bar") == 2);
		assertTrue(m.get(null) == null);
		testDescendingOrder(m);
	}
	
	public void testDescendingOrder(Map<String, Integer> m) {
		String a = m.toString();
		int prev = 500;
		while(a.indexOf('=') != -1){
			a = a.substring(a.indexOf('=') + 1);
			assertTrue(prev > a.charAt(0));
			prev = a.charAt(0);
		}
	}
	
	@Test
	public void medianFloatTest(){
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }));
		assertTrue(lp.medianFloat() == 2.5f);
		
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0"}));
		assertTrue(lp.medianFloat() == 2.0f);
		
		lp.setList( new ArrayList<String>() );
		assertTrue( lp.medianFloat() == null );
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertTrue( lp.medianFloat() == null );
	}
	
	@Test
	public void isFloatTest() {
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "foo", "bar", "bizz" }));
		assertTrue(lp.isFloat());
		
		lp.setList(Arrays.asList(new String[] { "1.0", "foo", "bar", "bizz" }));
		assertFalse(lp.isFloat());
	}
	
	@Test
	public void isIntegerTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "foo", "bar", "bizz" }));
		assertTrue(lp.isInteger());
		
		lp.setList(Arrays.asList(new String[] { "1", "foo", "bar", "bizz" }));
		assertFalse(lp.isInteger());
		
		lp.setList( new ArrayList<String>() );
		assertFalse( lp.isInteger() );
	}
	
	@Test
	public void maxFloatTest() {
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }));
		assertTrue(lp.maxFloat() == 4.0f);
		
		lp.setList( new ArrayList<String>() );
		assertTrue( lp.maxFloat() == null );
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertTrue( lp.maxFloat() == null );
	}
	
	@Test
	public void maxIntegerTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3", "4"}));
		assertTrue(lp.maxInteger() == 4);
		
		lp.setList( new ArrayList<String>() );
		assertTrue( lp.maxInteger() == null );
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertTrue( lp.maxInteger() == null );
	}
	
	@Test
	public void minFloatTest() {
		lp.setList( Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }));
		assertTrue(lp.minFloat() == 1.0f);
		
		lp.setList( new ArrayList<String>() );
		assertTrue( lp.maxFloat() == null );
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertTrue( lp.maxFloat() == null );
	}
	
	@Test
	public void minIntegerTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3", "4"}));
		assertTrue(lp.minInteger() == 1);
		
		lp.setList( new ArrayList<String>() );
		assertTrue( lp.minInteger() == null );
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertTrue( lp.minInteger() == null );
	}

	@Test
	public void setIgnoreListTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3" }));
		lp.setIgnoreList(Arrays.asList(new String[] { "1" }));
		assertFalse(lp.asInteger().contains(1));
	}
	
	@Test
	public void getSummaryTest() {
		lp.setList(Arrays.asList(new String[] { "1", "2", "3" }));
		assertTrue(lp.getSummary().contains("Numeric"));
		
		lp.setList(Arrays.asList(new String[] { "ab-c", "de-f", "gh-i" }));
		assertTrue(lp.getSummary().contains("Identifier"));
		
		lp.setList(Arrays.asList(new String[] { "ab-c", "ab-c", "de-f" }));
		assertTrue(lp.getSummary().contains("Categorical"));
	
	}
}

