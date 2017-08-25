package com.cyb.log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class MyLogUtil {
		static Log log =LogFactory.getLog(MyLogUtil.class);
		public static void main(String[] args) throws Exception {
			log.debug("debug级别的日志输出");  
	        log.debug("debug级别的日志输出1");  
	        log.debug("debug级别的日志输出2");  
	        log.debug("debug级别的日志输出3"); 
	        throw new Exception("ERROR ");
		} 
}
