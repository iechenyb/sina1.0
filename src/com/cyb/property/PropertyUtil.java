package com.cyb.property;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.file.FileUtils;
public class PropertyUtil {

	private static Properties p = null;
	static Log log = LogFactory.getLog(PropertyUtil.class);
	public synchronized static void initByPath() throws Exception {
		InputStream inputstream = null;
		try {
			if (p == null) 
			{
				String filePath = System.getProperty("user.dir")+"/src/com/cyb/property/config.properties";
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
	@SuppressWarnings("unused")
	public synchronized static Properties initByPath(String configFilePath) throws Exception {
		InputStream inputstream = null;
		Properties p = new Properties();
		try {
			
				p = new Properties();
				log.info("初始化属性文件:"+configFilePath);
				inputstream = new FileInputStream(configFilePath);
				p.load(inputstream);
			
		} catch (Exception e) {
			e.printStackTrace();
			return p;
		}finally{
			if(inputstream!=null){
				inputstream.close();
				inputstream = null;
			}
		}
		return p;
	}
	public synchronized static void initByPackage() throws Exception {
		InputStream inputstream = null;
		try {
			if (p == null) 
			{
				String filePath = "com/cyb/property/config.properties";//System.getProperty("user.dir")+
				p = new Properties();
				inputstream = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath);
				log.info("初始化属性文件:"+filePath);
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

   public static String getValueByKey(String propertyName, String key) {
		String result = "";
		try {
			if(p==null){
				initByPath();
			}
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String get(String key) {
		String result = "";
		try {
			if(p==null){
				initByPath();
			}
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] s) {
		 try {
//			PropertyUtil.initByPackage();
//			PropertyUtil.initByPath();
			writeVaule();
			removeKey("newkey");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(PropertyUtil.get("name"));
	}
	
	public static void writeVaule()
	{
		Properties pro=new Properties();
		String filePath = "com/cyb/property/test.properties";
		String filePath2 = System.getProperty("user.dir")+"/src/com/cyb/property/test.properties";
		InputStream in=PropertyUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			pro.load(in);
			pro.setProperty("testkey", "sdf");
			pro.setProperty("password", "3345");
			pro.setProperty("newkey", "3345");
			pro.remove("");
			OutputStream os = null;
			FileUtils.copyFileByStream(in, filePath2);
			os = new FileOutputStream(filePath2);
			pro.store(os, "modify by iechenyb");
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeKey(String key)
	{
		Properties pro=new Properties();
		String filePath = "com/cyb/property/test.properties";
		String filePath2 = System.getProperty("user.dir")+"/src/com/cyb/property/test.properties";
		InputStream in=PropertyUtil.class.getClassLoader().getResourceAsStream(filePath);
		try {
			pro.load(in);
			pro.remove(key);
			OutputStream os = null;
			FileUtils.copyFileByStream(in, filePath2);
			os = new FileOutputStream(filePath2);
			pro.store(os, "modify by iechenyb12312");
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Properties load(String dbFileName){
		if(!dbFileName.endsWith("properties")){
    		return loadPropertiesFile("config\\"+dbFileName+".properties");
    	}else{
    		return loadPropertiesFile("config\\"+dbFileName);
    	}
	}
	/**
     * @param string 配置文件名
     * @return Properties对象
     */
    private static Properties loadPropertiesFile(String fileName) {
        String webRootPath = System.getProperty("user.dir");//工程名
        if (null == fileName || fileName.equals("")){
            throw new IllegalArgumentException("Properties file path can not be null" + fileName);
        }
        InputStream inputStream = null;
        Properties p = null;
        try {
            inputStream = new FileInputStream(new File(webRootPath + "\\" + fileName));
            p = new Properties();
            p.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream){
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }
	
}