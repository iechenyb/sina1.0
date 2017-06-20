package com.app.csdn;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetProxyServer {
	public static void main(String[] args) {
		 try {
			getProxyIpChina();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Map<Integer, String> getProxyIpChina() throws IOException{
		int i=0;
		Map<Integer, String> proxys = new LinkedHashMap<Integer, String>();
		Document doc = Jsoup.connect("http://www.proxy360.cn/Region/China").get();
		Elements links = doc.select("div[name='list_proxy_ip']");
		String linkText = "";
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
//			System.out.println(linkText);
			proxys.put(i++,linkText);
		}
		return proxys;
	}
	public static Map<Integer, String> getProxyIpAmerica() throws IOException{
		int i=0;
		Map<Integer, String> proxys = new LinkedHashMap<Integer, String>();
		Document doc = Jsoup.connect("http://www.proxy360.cn/Region/America").get();
		Elements links = doc.select("div[name='list_proxy_ip']");
		String linkText = "";
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
//			System.out.println(linkText);
			proxys.put(i++,linkText);
		}
		return proxys;
	}
	public static Map<Integer, String> getProxyIpBrazil() throws IOException{
		int i=0;
		Map<Integer, String> proxys = new LinkedHashMap<Integer, String>();
		Document doc = Jsoup.connect("http://www.proxy360.cn/Region/China").get();
		Elements links = doc.select("div[name='list_proxy_ip']");
		String linkText = "";
		for (Element link : links) {
			linkText = link.text().replaceAll(" ", "#");
//			System.out.println(linkText);
			proxys.put(i++,linkText);
		}
		return proxys;
	}
}
