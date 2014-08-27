package edu.uiowa.icts.util;

import java.util.StringTokenizer;

/**
 * @author rrlorent
 */
public class IctsStringUtils {

	public String relabel( String sqllabel, boolean leadingCapital ) {
		StringBuffer result = new StringBuffer();
		StringTokenizer theTokenizer = new StringTokenizer( sqllabel, "_-" );
		while ( theTokenizer.hasMoreTokens() ) {
			String theToken = theTokenizer.nextToken().toLowerCase();
			result.append( Character.toUpperCase( theToken.charAt( 0 ) ) );
			result.append( theToken.substring( 1 ) );
		}
		if ( !leadingCapital ) {
			result.setCharAt( 0, Character.toLowerCase( result.charAt( 0 ) ) );
		}
		return result.toString();
	}

}
