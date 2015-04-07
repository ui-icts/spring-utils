package edu.uiowa.icts.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SummarizeListTest {

	@Test
	public void asFloatTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1.0", "2.0", "3.0" }), null, -1);
		List<Float> l = lp.asFloat();
		assertTrue(l.size() == 3);
		assertTrue(l.contains(1.0f));
		assertTrue(l.contains(2.0f));
		assertTrue(l.contains(3.0f));

		lp.setList(new ArrayList<String>());
		l = lp.asFloat();
		assertEquals(l.size() ,0);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		l = lp.asFloat();
		assertEquals(l.size() ,0);
	}

	@Test
	public void asIntegerTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3" }), null, -1);
		List<Integer> l = lp.asInteger();
		assertEquals(l.size() ,3);
		assertTrue(l.contains(1));
		assertTrue(l.contains(2));
		assertTrue(l.contains(3));

		lp.setList(new ArrayList<String>());
		l = lp.asInteger();
		assertEquals(l.size() ,0);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		l = lp.asInteger();
		assertEquals(l.size() ,0);
	}

	@Test
	public void avgFloatTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1.0", "2.0", "3.0" }), null, -1);
		assertEquals(lp.avgFloat(), (Float)2.0f);

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		assertNull(lp.avgFloat());
	}

	@Test
	public void avgIntegerTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3" }),null, -1);
		assertEquals(lp.avgInteger(), new Integer(2));

		lp.setList(Arrays.asList(new String[] { "1", "2", "3", "4" }));
		assertEquals(lp.avgInteger(), new Integer(2));

		lp.setList(Arrays.asList(new String[] { "tomato", "potato" }));
		assertNull(lp.avgInteger());
	}

	@Test
	public void categoryCountTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "foo", "bar", "bar", "foo",
				"foo", null }), null, Integer.MAX_VALUE);
		Map<String, Integer> m = lp.categoryCount();
		assertEquals(m.get("foo"),new Integer(3));
		assertEquals(m.get("bar"), new Integer(2));
		testDescendingOrder(m);

		lp.setMaxNumberOfTextResults(2);
		m = lp.categoryCount();
		assertEquals(m.get("foo"),new Integer(3));
		assertEquals(m.get("bar"),new Integer(2));
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
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }), null, -1);
		assertEquals(lp.medianFloat(),(Float)2.5f);
		
		lp.setList(Arrays.asList(new String[] { "1.0", "2.0", "3.0"}));
		assertEquals(lp.medianFloat(),(Float)2.0f);
		
		lp.setList( new ArrayList<String>() );
		assertNull( lp.medianFloat());
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertNull( lp.medianFloat());
	}
	
	@Test
	public void isFloatTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1.0", "2.0", "foo", "bar", "bizz" }), null, -1);
		assertTrue(lp.isFloat());
		
		lp.setList(Arrays.asList(new String[] { "1.0", "foo", "bar", "bizz" }));
		assertFalse(lp.isFloat());
	}
	
	@Test
	public void isIntegerTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "foo", "bar", "bizz" }), null, -1);
		assertTrue(lp.isInteger());
		
		lp.setList(Arrays.asList(new String[] { "1", "foo", "bar", "bizz" }));
		assertFalse(lp.isInteger());
		
		lp.setList( new ArrayList<String>() );
		assertFalse( lp.isInteger() );
	}
	
	@Test
	public void maxFloatTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }), null, -1);
		assertEquals(lp.maxFloat() , (Float) 4.0f);
		
		lp.setList( new ArrayList<String>() );
		assertNull( lp.maxFloat());
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertNull( lp.maxFloat());
	}
	
	@Test
	public void maxIntegerTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3", "4"}), null, -1);
		assertEquals(lp.maxInteger(), new Integer(4));
		
		lp.setList( new ArrayList<String>() );
		assertNull( lp.maxInteger());
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertNull( lp.maxInteger());
	}
	
	@Test
	public void minFloatTest() {
		SummarizeList lp = new SummarizeList( Arrays.asList(new String[] { "1.0", "2.0", "3.0", "4.0" }), null, -1);
		assertEquals(lp.minFloat(),(Float) 1.0f);
		
		lp.setList( new ArrayList<String>() );
		assertNull(lp.maxFloat());
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertNull(lp.maxFloat());
	}
	
	@Test
	public void minIntegerTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3", "4"}), null, -1);
		assertEquals(lp.minInteger(), new Integer(1));
		
		lp.setList( new ArrayList<String>() );
		assertNull( lp.minInteger());
		
		lp.setList( Arrays.asList( new String[] { "foo", "bar" } ) );
		assertNull( lp.minInteger());
	}

	@Test
	public void setIgnoreListTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3" }), null, -1);
		lp.setIgnoreList(Arrays.asList(new String[] { "1" }));
		assertFalse(lp.asInteger().contains(1));
	}
	
	@Test
	public void getSummaryTest() {
		SummarizeList lp = new SummarizeList(Arrays.asList(new String[] { "1", "2", "3" }), null, 5);
		assertTrue(lp.getSummary().contains("Numeric"));
		
		lp.setList(Arrays.asList(new String[] { "ab-c", "de-f", "gh-i" }));
		assertTrue(lp.getSummary().contains("Identifier"));
		
		lp.setList(Arrays.asList(new String[] { "ab-c", "ab-c", "de-f" }));
		assertTrue(lp.getSummary().contains("Categorical"));
	
	}
	
	//Testing new version of summary list
	@Test
	public void getSummaryTextShouldReturnNumericInt(){
		List<String> values = Arrays.asList(new String[] { "1", "2", "3","1" });
		List<String> ignored = Arrays.asList(new String[] {});
		SummarizeList sl = new SummarizeList(values, ignored,5);
		assertEquals("Numeric: {avg=1.75, mean=1.75, max=3.0, min=1.0, median=2.5}", sl.getSummary());
	}
	
	@Test
	public void getSummaryShouldReturnACountOfTheNumberOfUniqueIdentifiersIfListIsUnique(){
		List<String> values = Arrays.asList(new String[]{"a","b","c","d"});
		SummarizeList sl = new SummarizeList(values, null,5);
		assertEquals("Identifier: {# of Unique ID's = 4}", sl.getSummary());
	}
	
	@Test
	public void getSummaryShouldReturnACountOfTheNumberOfTimesAStringAppearsInAList(){
		List<String> values = Arrays.asList(new String[]{"a","b","c","d","a","a","e","e","d"});
		SummarizeList sl = new SummarizeList(values, null,3);
		assertEquals("Categorical: {a=3, d=2, e=2}", sl.getSummary());
	}
}

