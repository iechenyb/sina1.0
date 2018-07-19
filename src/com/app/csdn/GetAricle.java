package com.app.csdn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.date.DateUtil;
import com.cyb.file.FileUtils;
import com.cyb.url.UrlUtils;

public class GetAricle {
	static Log log = LogFactory.getLog(GetAricle.class);
	// 直接从字符串中获取
	private static String html = "http://blog.csdn.net/iechenyb/article/list/";
	private static String index = "http://blog.csdn.net/iechenyb/";
	private static Map<Integer, String> data = new LinkedHashMap<Integer, String>();
	public static List<Integer> randmons;

	private static int nums = 1;
	public static String rankFilePath =  "d:/data/csdn/rank.txt";

	public static void init() {
		try {
			nums = 1;
			for (int i = 1; i <= 10; i++) {
				getHrefByNetGet(html + 1);
			}
			visitorAricle();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		//init();
		recordRank();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getHrefByNetGet(String url) {
		HashMap hm = new HashMap();
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("h4[class='text-truncate']>a");
			for (Element link : links) {
				String linkHref = link.attr("href");
				data.put(nums, linkHref);// iechenyb/article/details/52946445
				System.out.println("第" + nums + "篇文章：" + linkHref);
				nums++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			hm.put("加载失败", "error");
		}
		return hm;
	}

	public static String recordRank() {
		String rankStr = "";
		try {
			// 这是get方式得到的
			Document doc = Jsoup.connect(index).get();
			Elements links = doc.select("div[class='grade-box clearfix']");
			for (Element link : links) {
				rankStr = DateUtil.timeToMilis(new Date()) + " " + link.text() ;
			}
			System.out.println("排名路径："+rankFilePath);
			System.out.println(rankStr);
			FileUtils.appendString2File(rankStr+ "\n", rankFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rankStr;
	}

	public static List<Integer> genRandomsNum() {
		int max = data.keySet().size();
		int min = 1;
		randmons = new ArrayList<Integer>();
		for (int i = 0; i < data.size(); i++) {
			randmons.add(random(min, max));
		}
		return randmons;
	}

	public static int random(int min, int max) {
		Random random = new Random();
		if (max <= min) {
			return 1;
		}
		return random.nextInt(max) % (max - min + 1) + min;
	}

	private static String prix = "http://blog.csdn.net";

	public static void visitorAricle() throws IOException {
		List<Integer> nums = genRandomsNum();
		System.out.println("当前访问序号："+nums);
		for (int num : nums) {
			if (data.get(num) != null) {
				try {
					UrlUtils.downLoadFromUrl(prix + data.get(num), null, null);
				} catch (Exception e) {
				}
			}
		}
	}
	public static void visitorAricleRandom() throws IOException {
		int idx= random(0,data.size());
			if (data.get(idx) != null) {
				try {
					log.info("序号："+idx+",artid="+data.get(idx).substring(data.get(idx).length()-8));
					UrlUtils.downLoadFromUrl(data.get(idx), null, null);
				} catch (Exception e) {
				}
			}
	}
}
