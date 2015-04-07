package edu.uiowa.icts.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SummarizeList {

	//three new values
	private List<String> values;
	private List<String> ignored;
	private int maxNumberOfTextResults;
	
	public SummarizeList() {}

	public SummarizeList(List<String> values, List<String> ignored,
			int maxNumberOfTextResults) {
		this.values = values;
		this.ignored = ignored;
		this.maxNumberOfTextResults = maxNumberOfTextResults;
		
	}

	// Define list of strings to be processed
	public void setList( List<String> values ) {
		this.values = values;
	}

	// Define list of values which should be ignored in all processing steps
	public void setIgnoreList( List<String> ignore ) {
		this.ignored = ignore;
	}

	// For the given list, if 40% of the values are integers, the list is an Integer List
	public boolean isInteger() {
		int total = 0;
		for ( String i : this.getValuesWithIgnoredRemoved() ) {
				try {
					Integer.parseInt( i );
					total++;
				} catch ( Exception e ) {	
			}
		}
		return ( (float) total ) / ( this.getValuesWithIgnoredRemoved().size() ) >= .4;
	}

	// Return list as Integers, skipping null and ignore
	public List<Integer> asInteger() {
		List<Integer> r = new ArrayList<Integer>();
		for ( String i : this.getValuesWithIgnoredRemoved() ) {
				try {
					r.add( Integer.parseInt( i ) );
				} catch ( Exception e ) {
			}
		}
		return r;
	}

	// For the given list, if 40% of the values are Float, the list is an Float List
	public boolean isFloat() {
		int total = 0;
		for (String i : this.getValuesWithIgnoredRemoved()) {
			try {
				Float.parseFloat( i );
				total++;
			} catch ( Exception e ) {
				}
		}
		return ( (float) total ) / (this.getValuesWithIgnoredRemoved().size() ) >= .4;
	}

	// Return list as Float, skipping null and ignore
	public List<Float> asFloat() {
		List<Float> r = new ArrayList<Float>();
		for ( String i : this.getValuesWithIgnoredRemoved() ) {
				try {
					r.add( Float.parseFloat( i ) );
				} catch ( Exception e ) {
			}
		}
		return r;
	}

	// for each string in list get count of that value
	// in(foo,bar,bar,foo,foo,null)
	// out(foo 3, bar 2, null 1)

	// Only return Top n categories
	public HashMap<String, Integer> categoryCount() {
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		for ( String i : this.getValuesWithIgnoredRemoved() ) {
				Integer prev = h.get( i );
				h.put( i, prev == null ? 1 : prev + 1 );
		}
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
			h.entrySet() );

		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare( Map.Entry<String, Integer> m1,
				Map.Entry<String, Integer> m2 ) {
				return ( m2.getValue() ).compareTo( m1.getValue() );
			}
		} );

		HashMap<String, Integer> r = new LinkedHashMap<String, Integer>();
		for ( int i = 0; i < this.maxNumberOfTextResults && i < list.size(); i++ ) {
			Map.Entry<String, Integer> e = list.get( i );
			r.put( e.getKey(), e.getValue() );
		}
		return r;
	}

	// Min values of list
	public Float minFloat() {
		List<Float> l = asFloat();
		if ( l.size() == 0 )
			return null;
		Collections.sort( l );
		return l.get( 0 );

	}

	// Min values of list
	Integer minInteger() {
		List<Integer> l = asInteger();
		if ( l.size() == 0 )
			return null;
		Collections.sort( l );
		return l.get( 0 );
	}

	// Max values of list
	public Float maxFloat() {
		List<Float> l = asFloat();
		if ( l.size() == 0 )
			return null;
		Collections.sort( l );
		return l.get( l.size() - 1 );
	}

	// Max values of list
	public Integer maxInteger() {
		List<Integer> l = asInteger();
		if ( l.size() == 0 )
			return null;
		Collections.sort( l );
		return l.get( l.size() - 1 );
	}

	// Avg values of list
	public Float avgFloat() {
		List<Float> l = asFloat();
		if ( l.size() > 0 ){
			float total = 0;
			for ( Float f : l ) {
				total += f;
			}
			return total / l.size();
		}
		return null;
	}

	// avg values of list
	Integer avgInteger() {
		List<Integer> l = asInteger();
		if ( l.size() == 0 )
			return null;
		int total = 0;
		for ( Integer i : l ) {
			total += i;
		}
		return total / l.size();
	}

	public Float medianFloat() {
		List<Float> l = asFloat();
		if ( l.size() == 0 )
			return null;
		return l.size() % 2 == 0 ? ( l.get( l.size() / 2 ) + l
			.get( l.size() / 2 - 1 ) ) / 2.0f : l.get( l.size() / 2 );
	}

	// Return summary of the list
	// min, max, avg, median, mean
	public HashMap<String, Float> summaryFloat() {
		HashMap<String, Float> h = new LinkedHashMap<String, Float>();
		h.put( "avg", avgFloat() );
		h.put( "mean", avgFloat() );
		h.put( "max", maxFloat() );
		h.put( "min", minFloat() );
		h.put( "median", medianFloat() );
		return h;
	}

	public String numericSummary() {
		HashMap<String, Float> ret = summaryFloat();
		StringBuffer sb = new StringBuffer();
		for ( String s : ret.keySet() ) {
			sb.append( (( sb.length() > 1) ? "\n" : ("" + s + ": " + ret.get( s ) )) );
		}
		return ret.toString();

	}

	public String getSummary() {
		if ( isFloat() || isInteger() )
			return "Numeric: " + numericSummary();
		else {
			Map<String, Integer> catCount = categoryCount();
			return (catCount.size() == this.getValuesWithIgnoredRemoved().size()) ? "Identifier: {# of Unique ID's = "
				+ this.getValuesWithIgnoredRemoved().size() + "}"
				: "Categorical: " + catCount;
		}
	}

	public List<String> getValuesWithIgnoredRemoved(){
		List<String> values = new ArrayList<String>();
		for(String s : this.values){
			if(this.ignored== null){values.add(s);}
			else if(!this.ignored.contains(s)){
				values.add(s);
			}
		}
		return values;
	}

	public int getMaxNumberOfTextResults() {
		return maxNumberOfTextResults;
	}

	public void setMaxNumberOfTextResults(int maxNumberOfTextResults) {
		this.maxNumberOfTextResults = maxNumberOfTextResults;
	}
	
	
}
