package com.app.csdn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.date.DateUtil;
import com.cyb.file.FileUtils;
import com.cyb.url.UrlUtils;

public class GetAricle {
	// 直接从字符串中获取
	private static String html = "http://blog.csdn.net/zzuchenyb/article/list/";
	private static String index = "http://blog.csdn.net/zzuchenyb/";
	private static Map<Integer, String> data = new LinkedHashMap<Integer, String>();
	public static List<Integer> randmons;
	public static Map<Integer, String> proxys = new LinkedHashMap<Integer, String>();
	public static Integer proxysNums;
	private static int nums = 1;
    public static String rankFilePath = System.getProperty("user.dir")+"/rank.out";
	public static void init() {
		try {
			nums = 1;
			for (int i = 1; i < 4; i++) {
				getHrefByNetGet(html + 1);
			}
			visitorAricle();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		/*init();
		visitorAricle();
		getProxyIp();
		initProxy();*/
		System.out.println(rankFilePath);
		File file = new File(rankFilePath);
		if(!file.exists()){ 
			file.createNewFile();
			System.out.println("成功创建文件"+file);
		}
		System.out.println();
		recordRank();
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
	public static void initProxy(){
		proxysNums = 0;
		try {
			/*for(int type=1 ;type<=4;type++){
				for(int i=1;i<10;i++){
					getProxyIpGet(type,i);
				}
			}*/
			proxys=GetProxyServer.getProxyIpChina();
			System.out.println(proxys);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	static String url = "http://www.ip3366.net/?";
	public static String getProxyIpGet(int type,int page) throws IOException{
		Document doc = Jsoup.connect(url+"stype="+type+"&page="+page).get();
		Elements links = doc.select("tbody>tr");
		String linkText = "";
		for (Element link : links) {
			linkText = link.text();
			proxys.put(proxysNums++,linkText.split(" ")[0]+"#"+linkText.split(" ")[1]);
		}
		return linkText.replaceAll(" ", "");
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getHrefByNetGet(String url) {
		HashMap hm = new HashMap();
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("span[class='link_view']>a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				data.put(nums, linkHref);// iechenyb/article/details/52946445
				nums++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			hm.put("加载失败", "error");
		}
		return hm;
	}

	public static String recordRank() {
		String rankStr="";
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(index).get();
			Elements links = doc.select("ul[id='blog_rank']");
			for (Element link : links) {
				rankStr = DateUtil.timeToMilis(new Date())+" "+link.text()+"\n";
			}
			FileUtils.appendString2File(rankStr, rankFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rankStr;
	}
	public static List<Integer> genRandomsNum() {
		int max = data.keySet().size();
		int min = 1;
		randmons = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			randmons.add( random(min,max));
		}
		return randmons;
	}
	
	public static int  random(int min,int max){
		Random random = new Random();
		if(max<=min){ return 1;}
		return random.nextInt(max) % (max - min + 1) + min;
	}

	private static String prix = "http://blog.csdn.net";

	public static void visitorAricle() throws IOException {
		List<Integer> nums = genRandomsNum();
		for (int num : nums) {
			//System.out.println("读取文章信息["+num+"]：" + prix + data.get(num));
			if(data.get(num)!=null){
				try{
					UrlUtils.downLoadFromUrl(prix + data.get(num), null, null);
				}catch(Exception e){}
			}
		}
	}
}
