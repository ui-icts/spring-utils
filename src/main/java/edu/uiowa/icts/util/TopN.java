package edu.uiowa.icts.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TopN<T> {
	private static final Log log = LogFactory.getLog( TopN.class );

	LinkedList<Double> topValues = new LinkedList<Double>();

	List<T> theList = new LinkedList<T>();

	int count;

	public TopN( int count ) {
		this.count = count;
		reset();
	}

	public TopN() {
		this.count = 100;
		reset();
	}

	public static void main( String args[] ) {
		TopN<Double> top10 = new TopN<Double>( 10 );
		Random r = new Random();
		for ( int i = 0; i < 10000; i++ ) {
			double d = r.nextDouble();
			int pos = top10.inTopN( d );
			if ( pos > 0 ) {
				log.debug( i + ":  Rank: " + pos + " is in top 10: " + d );
			}
		}

		top10.print();
		log.debug( "size: " + top10.size() );
		log.debug( "max: " + top10.max() );

	}

	public void reset() {
		topValues = new LinkedList<Double>();
		theList = new ArrayList<T>();
		for ( @SuppressWarnings( "unused" )
		int i = 0; topValues.size() < count; i++ ) {
			topValues.push( 0d );
			theList.add( null );
		}
	}

	public int inTopN( Double current ) {
		return inTopN( current, null );
	}

	public int inTopN( Double current, T obj ) {
		int i = 0;
		for ( Double d : topValues ) {

			if ( current > d ) {
				insert( current, i, obj );
				return i;
			}
			i++;
		}
		return -1;
	}

	public int inBottomN( Double current, T obj ) {
		// TODO Auto-generated method stub
		int i = 0;
		for ( Double d : topValues ) {

			if ( current < d ) {
				insert( current, i, obj );
				return i;
			}
			i++;
		}
		return -1;
	}

	public int size() {
		return topValues.size();
	}

	public void print() {
		log.debug( "List: " + topValues.size() );
		int i = 0;
		for ( Double d : topValues ) {
			log.debug( "print i: " + i++ + ": " + d );
		}
	}

	public Double max() {

		return topValues.get( 0 );
	}

	public List<T> getList() {
		return theList;
	}

	private void insert( Double current, int pos, T obj ) {
		if ( pos < count ) {
			double oldVal = topValues.get( pos );
			this.topValues.set( pos, current );

			T oldObj = theList.get( pos );
			this.theList.set( pos, obj );

			insert( oldVal, pos + 1, oldObj );
		}

	}

}
