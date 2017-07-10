package com.app.hcjh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.app.utils.ParamsUtil;
import com.cyb.file.ObjectFileUtils;


public class DrawData {
	private static String ppath = ParamsUtil.get("dbPath");
	public static void main(String[] args) throws IOException {
		initPlan();
		@SuppressWarnings("unchecked")
		Map<String,String> data1= (Map<String, String>) ObjectFileUtils.readObjectFromFile(ppath+"plan.data");
		System.out.println(data1);
		initCode();
		@SuppressWarnings("unchecked")
		Map<String,String> data2= (Map<String, String>) ObjectFileUtils.readObjectFromFile(ppath+"code.data");
		System.out.println(data2);
		initPlanBtn();
		Map<String,String> plans = (Map<String, String>) ObjectFileUtils.readObjectFromFile(ppath+"plans.data");
		System.out.println(plans);
	}
	public static String initPlan() throws IOException{
		Document doc = Jsoup.connect("http://www.cxin88.com/Plan.aspx").get();
		Elements links = doc.select("div[class=floatdiv-item]> ul li a");
		String linkText = "";
		Map<String,String> plans = new LinkedHashMap<String,String>();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> tmp;
		int i=1;
		for (Element link : links) {
			tmp = new HashMap<String,String>();
			linkText = link.text();
			if(linkText.contains("计划")&&!linkText.equals("在线观看计划")&&!linkText.equals("下载计划客户端")){
				plans.put(String.valueOf(i++), linkText);
				tmp.put("idx", String.valueOf(i));
				tmp.put("val", linkText);
				lst.add(tmp);
			}
		}
		ObjectFileUtils.writeObjectToFile(plans,ppath+"plan.data");
		ObjectFileUtils.writeObjectToFile(lst,ppath+"planl.data");
		return "";
	}
	public static String initCode() throws IOException{
		Document doc = Jsoup.connect("http://www.cxin88.com/Plan.aspx").get();
		Elements links = doc.select("input[name=radioname]");
		String linkText = "";
		Map<String,String> plans = new LinkedHashMap<String,String>();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> tmp;
		int i=0;
		for (Element link : links) {
			linkText = link.attr("value");
			tmp = new HashMap<String,String>();
			tmp.put("idx", String.valueOf(i));
			tmp.put("val", linkText);
			plans.put(String.valueOf(i++), linkText);
			lst.add(tmp);
		}
		ObjectFileUtils.writeObjectToFile(plans,ppath+"code.data");
		ObjectFileUtils.writeObjectToFile(lst,ppath+"codel.data");
		return "";
	}
	public static String initPlanBtn() throws IOException{
		Document doc = Jsoup.connect("http://www.cxin88.com/Plan.aspx").get();
		Elements links = doc.select("input[onclick=PlanClick(this)]");
		String linkText = "";
		Map<String,String> plans = new LinkedHashMap<String,String>();
		List<Map<String,String>> lst = new ArrayList<Map<String,String>>();
		Map<String,String> tmp;
		for (Element link : links) {
			linkText = link.attr("value");
			plans.put(link.attr("name"), linkText);
			tmp = new HashMap<String,String>();
			tmp.put("idx", link.attr("name"));
			tmp.put("val", linkText);
			lst.add(tmp);
		}
		ObjectFileUtils.writeObjectToFile(plans,ppath+"plans.data");
		ObjectFileUtils.writeObjectToFile(lst,ppath+"plansl.data");
		return "";
	}
}
