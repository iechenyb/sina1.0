package com.app.csdn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetProxyServer2 {
	static String url = "http://31f.cn/";
	public static void main(String[] args) {
		try {
			 //getAllCity();
			 getAllIpPortTr();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAllIpPortTr() throws MalformedURLException, IOException{
		Document doc = Jsoup.parse(new URL("http://31f.cn/city/北京/"), 20000);
		Elements as = doc.getElementsByTag("tr");
		for(int i=0;i<as.size();i++){
			System.out.println(as.get(i).html());
		}
	}
	/**
	 *  http://31f.cn/docs/
		http://31f.cn/downloads/
		http://31f.cn/docs/
		http://7n4.cn/
		http://9n4.cn/
		http://31f.cn/
		http://www.miibeian.gov.cn/
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日
	 *@throws MalformedURLException
	 *@throws IOException
	 */
	public static  void getAllCity() throws MalformedURLException, IOException{
		Document doc = Jsoup.parse(new URL(url), 20000);
		Elements as = doc.getElementsByTag("a");
		for(int i=0;i<as.size();i++){
			String href = as.get(i).absUrl("href");
			if(!"".equals(href)){
				if(href.contains("isp")||href.contains("region")||href.contains("city"))
				System.out.println(as.get(i).absUrl("href"));
			}
		}
	}
}
