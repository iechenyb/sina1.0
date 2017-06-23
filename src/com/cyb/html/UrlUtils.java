package com.cyb.html;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class UrlUtils {
	 public static Log log = LogFactory.getLog(UrlUtils.class);
	/** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
     */  
    public static boolean  downLoadFromUrl(String urlStr,String host,String port){  
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
			getData = readInputStream(inputStream);   
			System.out.println("["+host+":"+port+"] result="+new String(getData));
			return true;
		} catch (Exception e) {
			System.out.println("代理不可用："+host+":"+port);
			return false;
		} 
       
    }  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
    public static InputStream getStream(String address){
		InputStream is = null;
		try {
			URL url = new URL(address);
			HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
			is = urlcon.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return is;
	}
    public static void main(String[] args) {  
        try{  
        	String path = "d:/resource/images/diaodiao/country/1";
        	//FileUtils.genFile(path);
        	String url = "http://www.7879123.com/gw_getplandata.html?r=0.1945637173485011";
        	url ="http://www.7879123.com/cqssc/cqssc.html?r=0.32960804319009185";
            downLoadFromUrl(url,  
                    "百度.jpg",path);  
        }catch (Exception e) {
        	//e.printStackTrace();
        }  
    }  
}
