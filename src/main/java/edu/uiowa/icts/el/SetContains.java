package edu.uiowa.icts.el;

import java.util.Set;

/**
 * @author rrlorent
 * @date May 14, 2014
 */
public class SetContains {
	
	@SuppressWarnings( "rawtypes" )
	public static Boolean setContains( Set set, Object object ){
		if( set == null || object == null ){
			return false;
		} else {
			return set.contains( object );
		}
	}
	
}