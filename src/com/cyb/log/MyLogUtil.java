package com.cyb.log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class MyLogUtil {
		static Log log =LogFactory.getLog(MyLogUtil.class);
		public static void main(String[] args) throws Exception {
			for(int i=0;i<10000*10;i++){
				log.debug("debug级别的日志输出"+i); 
				log.info("debug级别的日志输出"+i); 
			}
	        //throw new Exception("ERROR ");
		} 
}
