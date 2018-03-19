package com.app.iqiyi.vip;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月15日
 */
public class DianShiJu {
	Log log = LogFactory.getLog(DianShiJu.class);
	
	public static List<DianShiJuBean> getVipDianshijuList() throws IOException{
		Document doc = Jsoup.parse("<html>");//直接解析html内容
		/**
		 * a[class=site-piclist_pic_link]
		 * a
		 */
		doc = Jsoup.connect(VipConstants.DIANSHIJU).get();
		Elements aList = doc.select("a[class=site-piclist_pic_link]");
		int num = aList.size();
		List<DianShiJuBean> ls = new ArrayList<>();
		DianShiJuBean vo = null;
		for(int i=0;i<num;i++){
			vo = new DianShiJuBean();
			vo.setName(aList.get(i).attr("title"));
			vo.setUrl(aList.get(i).attr("href"));
			ls.add(vo);
			//System.out.println(i+"------> titile="+aList.get(i).attr("title")+",href="+aList.get(i).attr("href"));
		}
		return ls;
	}
	public static void main(String[] args) throws IOException {
		List<DianShiJuBean> ls = getVipDianshijuList();
		//根据列表解析电视剧剧集
		for(DianShiJuBean bean:ls){
			getDetails(bean);
		}
	}
	public static void getDetails(DianShiJuBean bean) throws IOException{
		/**
		 * a[class=site-piclist_pic_link]
		 * a
		 */
		Document doc = Jsoup.connect(bean.getUrl()).get();
		Elements aList = doc.select("li[data-albumlist-elem=playItem] div[class=site-piclist_info] p[class=site-piclist_info_title] a");
		int num = aList.size();
		List<DianShiJuDetailsBean> ls = new ArrayList<>();
		DianShiJuDetailsBean vo = null;
		for(int i=0;i<num;i++){
			vo = new DianShiJuDetailsBean();
			vo.setIdx(aList.get(i).html());
			vo.setUrl(aList.get(i).attr("href"));
			Elements node = aList.get(i).parent().parent().select("p[class=site-piclist_info_describe] a");//div p a 
			vo.setDesc(node.get(0).text());
			vo.setName(bean.getName());
			ls.add(vo);
			System.out.println(node.get(0).text()+"------> titile="+aList.get(i).html()+","+aList.get(i).attr("href"));
		}
	}
}
