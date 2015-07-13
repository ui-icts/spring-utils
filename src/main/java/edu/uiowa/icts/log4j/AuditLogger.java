package edu.uiowa.icts.log4j;

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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;

/**
 * <p>AuditLogger class.</p>
 *
 * @author schappetj
 * @version $Id: $
 */
public class AuditLogger {

	/**
	 * <p>main.</p>
	 *
	 * @param args an array of {@link java.lang.String} objects.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static final Log log = LogFactory.getLog("audit");
    /**
     * <p>info.</p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param userid a {@link java.lang.String} object.
     * @param activity a {@link java.lang.String} object.
     * @param activityDetail a {@link java.lang.String} object.
     * @param activityDetail a {@link java.lang.String} object.
     */
    public static void info (String sessionId, String userid, String activity, String activityDetail) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
	    log.info(activityDetail);
    	MDC.clear();
	              
	}
    
    /**
     * <p>change.</p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param userid a {@link java.lang.String} object.
     * @param activity a {@link java.lang.String} object.
     * @param oldValue a {@link java.lang.String} object.
     * @param newValue a {@link java.lang.String} object.
     */
    public static void change(String sessionId, String userid, String activity, String oldValue, String newValue) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
	    log.info(" from: " + oldValue + " to: " + newValue );
    	MDC.clear();
	              
	}
    
    /**
     * <p>info.</p>
     *
     * @param sessionId a {@link java.lang.String} object.
     * @param userid a {@link java.lang.String} object.
     * @param activity a {@link java.lang.String} object.
     * @param listItems a {@link java.util.List} object.
     */
    public static void info (String sessionId, String userid, String activity, List<String> listItems) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
    	for(String s : listItems) 
    		log.info(s);
    	MDC.clear();
    }
	              

}
