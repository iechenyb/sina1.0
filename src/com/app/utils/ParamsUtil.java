package com.app.utils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ParamsUtil {

	private static Properties p = null;
	static Log log = LogFactory.getLog(ParamsUtil.class);
	public synchronized static void initByPath() throws Exception {
		InputStream inputstream = null;
		try {
			if (p == null) 
			{
				String filePath = System.getProperty("user.dir")+"/config.properties";
				p = new Properties();
				log.info("初始化属性文件:"+filePath);
				inputstream = new FileInputStream(filePath);
				p.load(inputstream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputstream!=null){
				inputstream.close();
				inputstream = null;
			}
		}
	}

	public static String get(String key) {
		String result = "";
		try {
			if(p==null){
				initByPath();
			}
			result = p.getProperty(key+p.getProperty("etc"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}