package com.cyb.url;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月10日
 */
public class HttpSimulation {
	Log log = LogFactory.getLog(HttpSimulation.class);
	public static void main(String[] args) {
		downJsonFile();
	}
	
	public static void downJson(){
		 try{         
	        	long s = System.currentTimeMillis();
	        	String path = "http://localhost:3000/cyb/users.json";
	        	UrlUtils.downLoadFromUrl(path,null,null);  
	        	long e = System.currentTimeMillis();
	        	System.out.println((e-s)/1000+"s");
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }  
	}
	
	public static void downJsonFile(){
		 try{         
	        	long s = System.currentTimeMillis();
	        	String path = "http://localhost:3000/cyb/usersObjList.json";
	        	UrlUtils.downLoadFromUrl(path,"userObjList-1.json","d:/data/");  
	        	long e = System.currentTimeMillis();
	        	System.out.println((e-s)/1000+"s");
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }  
		
	}
	
	public static void downJsonZipFile(){
		
	}
}
