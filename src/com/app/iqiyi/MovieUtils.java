package com.app.iqiyi;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cyb.h2.H2Manager;

public class MovieUtils {
	public static void getMovies() throws Exception{
		//VedioDbUtils.exeDLL();
		Document doc = Jsoup.connect("http://www.iqiyi.com/dianying/vip.html?fc=a3289cd48dbb67aa").get();
		Elements links = doc.select("p[class='site-piclist_info_title'] a[target='iqiyiplay']");
		Vedio dio = null;
		for (Element link : links) {
			dio = new Vedio();
			dio.setName(link.attr("title"));
			dio.setUrl(link.attr("href"));
			dio.setType("movie");
//			System.out.println(	link.attr("href")+","+link.attr("title"));
			VedioDbUtils.dbUtils.update("delete from vedio where name='"+dio.getName()+"' and url='"+dio.getUrl()+"'");
			VedioDbUtils.dbUtils.save(dio);
		}
	}
	public static void getVediosDir() throws Exception{
		
		Document doc = Jsoup.connect("http://list.iqiyi.com/www/2/----------2---4-1-1-iqiyi--.html").get();
		Elements links = doc.select("p[class='site-piclist_info_title'] a[data-searchpingback-elem='link']");
		Vedio dio = null;
		for (Element link : links) {
			dio = new Vedio();
			dio.setName(link.attr("title"));
			dio.setUrl(link.attr("href"));
			dio.setType("vedio");
			System.out.println(	link.attr("href")+","+link.attr("title"));
			VedioDbUtils.dbUtils.update("delete from vedio where name='"+dio.getName()+"' and url='"+dio.getUrl()+"'");
			VedioDbUtils.dbUtils.save(dio);
		}
	}
	public static List<Vedio> getVedios(String dir) throws Exception{
		//VedioDbUtils.exeDLL();
		List<Vedio> data = new ArrayList<Vedio>();
		Document doc = Jsoup.connect(dir).get();
		Elements links = doc.select("div[class='site-piclist_pic'] a[class='site-piclist_pic_link']");
		Vedio dio = null;
		for (Element link : links) {
			dio = new Vedio();
			dio.setName(link.attr("title"));
			dio.setUrl(link.attr("href"));
			dio.setType("vedio");
			data.add(dio);
			System.out.println(	link.attr("href")+","+link.attr("title"));
			//VedioDbUtils.dbUtils.update("delete from vedio where name='"+dio.getName()+"' and url='"+dio.getUrl()+"'");
			//VedioDbUtils.dbUtils.save(dio);
		}
		return data;
	}
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			new  VedioDbUtils("vedio");
			VedioDbUtils.exeDLL();
			getMovies();
			getVediosDir();
		    getVedios("http://www.iqiyi.com/a_19rrh8y4al.html#vfrm=2-4-0-1");
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
