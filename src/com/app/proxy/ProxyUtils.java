package com.app.proxy;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import com.cyb.file.FileUtils;

public class ProxyUtils {
	public static boolean  isUseable(String urlStr,String host,String port){  
        //获取自己数组  
		byte[] getData;
		try {
			URL url = new URL(urlStr);    
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.valueOf(port)));  
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(proxy);    
			        //设置超时间为3秒  
			conn.setConnectTimeout(3*1000);  
			//防止屏蔽程序抓取而返回403错误  
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
			//得到输入流  
			InputStream inputStream = conn.getInputStream();    
			getData = FileUtils.readInputStream(inputStream);   
			System.out.println("["+host+":"+port+"] result="+new String(getData));
			return true;
		} catch (Exception e) {
			//System.out.println("代理不可用："+host+":"+port);
			return false;
		} 
    }
}
