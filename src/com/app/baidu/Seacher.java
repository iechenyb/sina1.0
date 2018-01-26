package com.app.baidu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月9日
 */
public class Seacher {
	Log log = LogFactory.getLog(Seacher.class);
	public static void main(String[] args) {  
        try {  
            String key = "java"; //查询关键字  
            key = URLEncoder.encode(key, "utf-8");  
            URL u = new URL("http://www.baidu.com.cn/s?wd=" + key + "&cl=3");  
            URLConnection conn = u.openConnection();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), "utf-8"));  
            String str = reader.readLine();  
            while (str != null) {  
                System.out.println(str);  
                str = reader.readLine();  
            }  
            reader.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  

        }
	}
}
