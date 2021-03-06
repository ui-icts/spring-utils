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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>TopN class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class TopN<T> {
	private static final Log log = LogFactory.getLog( TopN.class );

	LinkedList<Double> topValues = new LinkedList<Double>();

	List<T> theList = new LinkedList<T>();

	int count;

	/**
	 * <p>Constructor for TopN.</p>
	 *
	 * @param count a int.
	 */
	public TopN( int count ) {
		this.count = count;
		reset();
	}

	/**
	 * <p>Constructor for TopN.</p>
	 */
	public TopN() {
		this.count = 100;
		reset();
	}

	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link java.lang.String} objects.
	 */
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

	/**
	 * <p>reset.</p>
	 */
	public void reset() {
		topValues = new LinkedList<Double>();
		theList = new ArrayList<T>();
		for ( @SuppressWarnings( "unused" )
		int i = 0; topValues.size() < count; i++ ) {
			topValues.push( 0d );
			theList.add( null );
		}
	}

	/**
	 * <p>inTopN.</p>
	 *
	 * @param current a {@link java.lang.Double} object.
	 * @return a int.
	 */
	public int inTopN( Double current ) {
		return inTopN( current, null );
	}

	/**
	 * <p>inTopN.</p>
	 *
	 * @param current a {@link java.lang.Double} object.
	 * @param obj a T object.
	 * @return a int.
	 */
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

	/**
	 * <p>inBottomN.</p>
	 *
	 * @param current a {@link java.lang.Double} object.
	 * @param obj a T object.
	 * @return a int.
	 */
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

	/**
	 * <p>size.</p>
	 *
	 * @return a int.
	 */
	public int size() {
		return topValues.size();
	}

	/**
	 * <p>print.</p>
	 */
	public void print() {
		log.debug( "List: " + topValues.size() );
		int i = 0;
		for ( Double d : topValues ) {
			log.debug( "print i: " + i++ + ": " + d );
		}
	}

	/**
	 * <p>max.</p>
	 *
	 * @return a {@link java.lang.Double} object.
	 */
	public Double max() {

		return topValues.get( 0 );
	}

	/**
	 * <p>getList.</p>
	 *
	 * @return a {@link java.util.List} object.
	 */
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
