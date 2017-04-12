package com.cyb.html;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtils {
	public static void main(String[] args) {
		// getParByString();
		//getHrefByNetGet("http://www.cffex.com.cn/xwgg/jysgg/index.html");
		getHrefByNetPost("http://www.cnblogs.com/zhangfei/p/");
	}

	// 直接从字符串中获取
	public static void getParByString() {
		// String html = "<html><head><title> 这里是字符串内容</title></head"+
		// ">"+"<body><p class='p1'> 这里是 jsoup 作用的相关演示</p><p class='p2'> 这里是 jsoup 作用的相关演示</p></body></html>";
		String html = "<p class='p1'> 这里是 jsoup 作用的相关演示1</p><p class='p2'> 这里是 jsoup 作用的相关演示2</p>";
		Document doc = Jsoup.parse(html);
		Elements links = doc.select("p[class='p2']");
		for (Element link : links) {
			String linkclass = link.className();
			String linkText = link.text();
			System.out.println(linkText);
			System.out.println(linkclass);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getHrefByNetGet(String url) {
		HashMap hm = new HashMap();
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(url).get();
			String title = doc.title();
			System.out.println("网页标题：" + title);
			Elements links = doc.select("a[class='list_a_text']");
			for (Element link : links) {
				String linkHref = link.attr("href");
				String linkText = link.text();
				System.out.println(linkText + "," + linkHref);
			}
		} catch (IOException e) {
			e.printStackTrace();
			hm.put("加载失败", "error");
		}
		return hm;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getHrefByNetPost(String url) {
		//http://www.cnblogs.com/zhangfei/p/?page=5 原始请求
		HashMap hm = new HashMap();
		try {
			  // 另外一种是post方式			
			  Document doc = Jsoup.connect(url)
					  .data("page","3")
					  .userAgent("I am jsoup")
					  .cookie("auth","token") .timeout(10000) .post(); 
			  Elements  links_Post = doc.select("div[class='postTitl2']"); 
			  for(Element link:links_Post){
				  String linkHref = link.attr("href"); 
				  String linkText = link.text(); 
				  System.out.println(linkText+","+linkHref);
			  }
		} catch (IOException e) {
			e.printStackTrace();
			hm.put("加载失败", "error");
		}

		return hm;
	}
	 public static String httpGet(String url,String cookie) throws IOException{
	        //获取请求连接
	        Connection con = Jsoup.connect(url);
	        //请求头设置，特别是cookie设置
	        con.header("Accept", "text/html, application/xhtml+xml, */*"); 
	        con.header("Content-Type", "application/x-www-form-urlencoded");
	        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
	        con.header("Cookie", cookie);
	        //解析请求结果
	        Document doc=con.get(); 
	        //获取标题
	        System.out.println(doc.title());
	        //返回内容
	        return doc.toString();
	    }
	 public static String httpGetHeader(String url,String cook,String header) throws IOException{
	        //获取请求连接
	        Connection con = Jsoup.connect(url);
	        //请求头设置，特别是cookie设置
	        con.header("Accept", "text/html, application/xhtml+xml, */*"); 
	        con.header("Content-Type", "application/x-www-form-urlencoded");
	        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
	        con.header("Cookie", cook);
	        //发送请求
	        Response resp=con.method(Method.GET).execute();        
	        //获取cookie名称为__bsi的值
	        String cookieValue = resp.cookie("__bsi");
	        System.out.println("cookie  __bsi值：  "+cookieValue);
	        //获取返回cookie所值
	        Map<String,String> cookies = resp.cookies();
	        System.out.println("所有cookie值：  "+cookies);
	        //获取返回头文件值
	        String headerValue = resp.header(header);
	        System.out.println("头文件"+header+"的值："+headerValue);
	        //获取所有头文件值
	        Map<String,String> headersOne =resp.headers();
	        System.out.println("所有头文件值："+headersOne);
	        return headerValue; 
	    }
}
