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
	public static void main(String[] args) throws IOException {
		String url="http://localhost:8080/demo/2";
		httpGetHeader(url,null,null);
		// getParByString();
		//getHrefByNetGet("http://www.cffex.com.cn/xwgg/jysgg/index.html");
		//getHrefByNetPost("http://www.cnblogs.com/zhangfei/p/");
		/*for(int i=0;i<10;i++){
			new Thread(new JsoupUtils().new TaskProxy()).start();
		}*/
		/*try {
			for(int type=1 ;type<=4;type++){
				for(int i=1;i<10;i++){
					getProxyIpGet(type,i);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
   class TaskProxy implements Runnable{
   private ThreadLocal<Integer> type = new ThreadLocal<Integer>() {  
        @Override  
        protected Integer initialValue() {  
            return 1;  
        }  
    };  
	@Override
	public void run() {
		try {
			for(int type=1 ;type<=4;type++){
				for(int i=1;i<10;i++){
					getProxyIpGet(type,i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	   
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
			  Document doc = Jsoup.connect("http://www.ip3366.net/?stype=1&page=1")
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
	static String url = "http://www.ip3366.net/?";
	public static String getProxyIpGet(int type,int page) throws IOException{
		Document doc = Jsoup.connect(url+"stype="+type+"&page="+page).get();
//		String title = doc.title();
//		System.out.println("网页标题："+"type="+type+",page="+page +":"+ title);
		Elements links = doc.select("tbody>tr");
		String linkText = "";
		int num =0;
		for (Element link : links) {
			linkText = link.text();
			boolean rs =UrlUtils.downLoadFromUrl("http://120.26.75.28:8080/webLis/find?type=normal",linkText.split(" ")[0], linkText.split(" ")[1]);
			if(rs){
				System.out.println("type="+type+",page="+page+"("+(++num)+"):"+linkText);
			}
		}
		return linkText.replaceAll(" ", "#");
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
			  for(Element link:links_Post){
				  String linkText = link.text(); 
				  System.out.println(linkText);
			  }
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	        con.header("Accept", "text/html, application/xhtml+xml,*/*"); 
	        con.header("Content-Type", "application/x-www-form-urlencoded");
	        con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
	        //con.header("Cookie", cook);
	        con.header("Authorization","Bearer 44925e47-62d2-4c7d-809e-cca8269425a0");
	        //发送请求
	        Response resp=con.method(Method.GET).execute();   
	        System.out.println("body:"+resp.body());
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
