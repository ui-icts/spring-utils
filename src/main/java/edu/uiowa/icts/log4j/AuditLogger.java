package edu.uiowa.icts.log4j;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;

public class AuditLogger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static final Log log = LogFactory.getLog("audit");
    public static void info (String sessionId, String userid, String activity, String activityDetail) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
	    log.info(activityDetail);
    	MDC.clear();
	              
	}
    
    public static void change(String sessionId, String userid, String activity, String oldValue, String newValue) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
	    log.info(" from: " + oldValue + " to: " + newValue );
    	MDC.clear();
	              
	}
    
    public static void info (String sessionId, String userid, String activity, List<String> listItems) {
    	MDC.put("userid", userid);
        MDC.put("activity", activity);
        MDC.put("sessionId", sessionId);
    	for(String s : listItems) 
    		log.info(s);
    	MDC.clear();
    }
	              

}
