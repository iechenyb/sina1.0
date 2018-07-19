package com.app.baidu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月9日
 */
public class Seacher {
	Log log = LogFactory.getLog(Seacher.class);
	public static void main(String[] args) {  
        try {  
            String key = "房地产"; //查询关键字  
            key = URLEncoder.encode(key, "utf-8");  
            URL u = new URL("http://www.baidu.com.cn/s?wd=" + key + "&cl=3");  
            URLConnection conn = u.openConnection();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream(), "utf-8"));  
            String str = reader.readLine();  
            StringBuffer html = new StringBuffer(str);
            while (str != null) {  
                //System.out.println(str);  
                html.append(str);
                str = reader.readLine();  
            }  
           Elements as =  Jsoup.parse(html.toString()).getElementsByTag("a");
           for(int i=0;i<as.size();i++){
        	   if(!"".equals(as.get(i).text())&&as.get(i).text().contains(key))
        	   System.out.println(as.get(i).text()+","+as.get(i).absUrl("href"));
           }
            reader.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  

        }
	}
}
