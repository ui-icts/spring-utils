package edu.uiowa.icts.el;

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

import java.util.Set;

/**
 * <p>SetContains class.</p>
 *
 * @author rrlorent
 * @date May 14, 2014
 * @version $Id: $
 */
public class SetContains {
	
	/**
	 * <p>setContains.</p>
	 *
	 * @param set a {@link java.util.Set} object.
	 * @param object a {@link java.lang.Object} object.
	 * @return a {@link java.lang.Boolean} object.
	 */
	@SuppressWarnings( "rawtypes" )
	public static Boolean setContains( Set set, Object object ){
		if( set == null || object == null ){
			return false;
		} else {
			return set.contains( object );
		}
	}
	
}
