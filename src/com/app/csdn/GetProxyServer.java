package com.app.csdn;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.cmd.CmdUtils;
import com.cyb.file.FileUtils;

public class GetProxyServer {
	public static void main(String[] args) {
		 try {
			//getProxyIpChina();
			 //initProxy();
			 checkProxy();
		} catch (Exception e) {
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
	public static Map<Integer, String> proxys = new LinkedHashMap<Integer, String>();
	public static Integer proxysNums;
	static String url = "http://www.ip3366.net/?";
	public static void initProxy(){
		proxysNums = 0;
		try {
			for(int type=1 ;type<=4;type++){
				for(int i=1;i<10;i++){
					getProxyIpGet(type,i);
				}
			}
			//proxys=GetProxyServer.getProxyIpChina();
			System.out.println(proxys);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	static List<String> proxyList;
	public static void readProxy(){
		proxyList = FileUtils.readFileToList(GetProxyServer.proxyListFile);
		
		proxyList.addAll(FileUtils.readFileToList(GetProxyServer3.filePath));//System.out.println(proxys);
	}
	
	public static void checkProxy(){
		proxyList = FileUtils.readFileToList(GetProxyServer.proxyListFile);
		System.out.println("代理总数："+proxyList.size());
		int i=0;
		for(String str:proxyList){
			++i;
			boolean rs = CmdUtils.telnet(str.split("#")[0], Integer.valueOf(str.split("#")[1]));
			if(!rs){
				System.out.println(i+","+str+"不可用!");
			}else{
				System.out.println(i+","+str+"*可用*");
			}
		}
	}
	static String  proxyListFile = "D:\\data\\proxy\\1\\list.txt";
	public static String getProxyIpGet(int type,int page) throws IOException{
		Document doc = Jsoup.connect(url+"stype="+type+"&page="+page).get();
		Elements links = doc.select("tbody>tr");
		String linkText = "";
		for (Element link : links) {
			linkText = link.text();
			proxys.put(proxysNums++,linkText.split(" ")[0]+"#"+linkText.split(" ")[1]);
		    FileUtils.append(proxyListFile, linkText.split(" ")[0]+"#"+linkText.split(" ")[1]+"\n");
		    System.out.println(linkText.split(" ")[0]+"#"+linkText.split(" ")[1]);
		}
		return linkText.replaceAll(" ", "");
	}
	public static void getProxyIp(){
		String url = "http://www.66ip.cn/nmtq.php";
		try {
			  // 另外一种是post方式			
			  Document doc = Jsoup.connect(url)
					  .data("getnum","800")
					  .data("isp","0")
					  .data("anonymoustype","0")
					  .data("area","0")
					  .data("api","66ip")
					  .data("proxytype","2")
					  .userAgent("I am jsoup")
					  .cookie("auth","token") .timeout(10000) .post(); 
			  Elements  links_Post = doc.select("body"); 
			  int i=0;
			  for(Element link:links_Post){
				  i++;
				  String linkText = link.text(); 
				  System.out.println(i+","+linkText);
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
