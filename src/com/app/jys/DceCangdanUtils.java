package com.app.jys;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.cyb.url.MyHttpClient;

public class DceCangdanUtils {
	public static void main(String[] args) {
		//1 下载数据
		String url = "http://www.dce.com.cn/publicweb/quotesdata/wbillWeeklyQuotes.html";
		Map<String,String> para = new HashMap<String,String>();
		para.put("year","2017" );
		para.put("month","11" );//实际月份+1
		para.put("day","8" );
		String html = "";
		html = MyHttpClient.doPost(url, para);
		//FileUtils.overideString2File(html, "d:/data/cangdan.html");
		org.jsoup.nodes.Document doc = Jsoup.parse(html);
		//html = doc.getElementsByTag("table").html();
		//System.out.println(html); 
		//org.jsoup.nodes.Document tr = Jsoup.parse(html);
		//FileUtils.overideString2File(html, "d:/data/cangdan.html");
		
		//System.out.println(tr.getElementsByTag("tr").html());
		/*Iterator<Element> iter = tr.getAllElements().iterator();
		while(iter.hasNext()){
			System.out.println(iter.next().html());
			System.out.println("#########");
		}*/
		/*Elements trs = tr.getElementsByTag("tr");
		for (Element link : trs) {
			  //String linkHref = link.attr("href");
			  String linkText = link.text();
			  System.out.println(linkText);
		}*/
		Elements trs = doc.select("table").select("tr"); 
        for(int i = 0;i<trs.size();i++){ 
            Elements tds = trs.get(i).select("td"); 
            for(int j = 0;j<tds.size();j++){ 
                String text = tds.get(j).text(); 
                if(tds.get(0).text().contains("总计")||!tds.get(0).text().contains("小计")){
                	break;
                }else{
	                if(j==0&&tds.get(0).text().contains("小计")){
	                	System.out.println(tds.get(j).text().replace("小计", ""));
	                }else if(j!=1){
	                	System.out.println(text); 
	                }
                }
            } 
        }
	}
}
