package com.cyb.url;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年8月10日
 */
public class HttpSimulation {
	static Log log = LogFactory.getLog(HttpSimulation.class);
	static String httpBase = "http://192.168.108.224:8085/vasweb/";
	static String rFiles = "d:/data/vas/tests/";
	static String xml="base/ProductList.xml";//AmpList
	public static Elements getRqs(String file) throws IOException{
		Document doc = Jsoup.parse(new File(rFiles + file), "utf-8");
		return doc.select("rq");
	}
	static int times = 200;//单个请求执行200次
	static long totalTimes = 0;
	static long totalRQTimes = 0;
	public static void main(String[] args) throws IOException {
		/*for(int i=0;i<100;i++){
	        shortTest();
	        System.out.println("==============================");
			longTest();
			System.out.println("******************************");
		}*/
		
		//生成文件
		RS rs= readUrlRq("base/ProductList.xml");
		writeProducts(rs.getData());
	}
	static int count =0;
	public static void writeProducts(String data){
		JSONObject d = JSONObject.parseObject(data);
		/*System.out.println(d.getJSONObject("d"));
		System.out.println(d.getJSONObject("d").getJSONArray("mt"));
		System.out.println(d.getJSONObject("d").getJSONObject("pcl").get("CFFEX"));
		*/JSONArray exs = d.getJSONObject("d").getJSONArray("mt");
		for(int i =0;i<exs.size();i++){
			if(exs.get(i).toString().equals("INE")){
				continue;
			}
			JSONArray products= d.getJSONObject("d")
					.getJSONObject("pcl")
					.getJSONArray(exs.get(i).toString());
			for(int j=0;j<products.size();j++){
				JSONArray secuids = products.getJSONObject(j).getJSONArray("c");
				for(int z=0;z<secuids.size();z++){
					System.out.println(++count+","+exs.get(i)+","+products.getJSONObject(j).get("pc")+","+secuids.get(z));
				}
			}
		}
	}
	
	public static void shortTest() throws IOException{
		totalTimes =0;
		totalRQTimes =0;
		Elements rqs = getRqs(xml);
		for (int i = 0; i < rqs.size(); i++) {
			for (int j = 0; j < times; j++) {
				RS rs = sendPost(httpBase + rqs.get(i).attr("uri"), rqs.get(i).text());
				JSONObject obj = JSON.parseObject(rs.getData());
				if ("0".equals(obj.get("ec"))) {
					System.out.println(rqs.get(i).attr("name") + "  逻辑处理时间 " + obj.getByteValue("time")
							+ ",请求总时间 " + rs.getTime());
					totalTimes = totalTimes + Long.valueOf(obj.getString("time"));
					totalRQTimes = totalRQTimes +rs.getTime();
				} else {
					System.out.println(j + "执行失败！");
				}
			}
			System.out.println(rqs.get(i).attr("name") + " 平均程序处理耗时：" + totalTimes / times);
			System.out.println(rqs.get(i).attr("name") + " 平均请求耗时：" + totalRQTimes / times);
			System.out.println("网络传输平均时间："+(totalRQTimes / times-totalTimes / times));
		}
	}

	public static void downJson() {
		try {
			long s = System.currentTimeMillis();
			String path = "http://localhost:3000/cyb/users.json";
			UrlUtils.downLoadFromUrl(path, null, null);
			long e = System.currentTimeMillis();
			System.out.println((e - s) / 1000 + "s");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downJsonFile() {
		try {
			long s = System.currentTimeMillis();
			String path = "http://localhost:3000/cyb/usersObjList.json";
			UrlUtils.downLoadFromUrl(path, "userObjList-1.json", "d:/data/");
			long e = System.currentTimeMillis();
			System.out.println((e - s) / 1000 + "s");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void longTest() throws IOException {
		CloseableHttpClient httpclient = null;
		httpclient = HttpClients.createDefault();
		CloseableHttpResponse httpresponse = null;
		String response = null;
		totalTimes =0;
		totalRQTimes =0;
		try{
			Elements rqs = getRqs(xml);
			for (int i = 0; i < rqs.size(); i++) {
				for (int j = 0; j < times; j++) {
					long s = System.currentTimeMillis();
					HttpPost httppost = new HttpPost(httpBase + rqs.get(i).attr("uri"));
					StringEntity stringentity = new StringEntity(rqs.get(i).text(), ContentType.create("application/json", "UTF-8"));
					httppost.setEntity(stringentity);
					httpresponse = httpclient.execute(httppost);
					response = EntityUtils.toString(httpresponse.getEntity());
					long e = System.currentTimeMillis();
					JSONObject obj = JSON.parseObject(response);
					if ("0".equals(obj.get("ec"))) {
						System.out.println(rqs.get(i).attr("name") + "  逻辑处理时间 " + obj.getByteValue("time")
								+ ",请求总时间 " + (e-s));
						totalTimes = totalTimes + Long.valueOf(obj.getString("time"));
						totalRQTimes = totalRQTimes +(e-s);
					} else {
						System.out.println(j + "执行失败！");
					}
				}
				System.out.println(rqs.get(i).attr("name") + " 平均程序处理耗时：" + totalTimes / times);
				System.out.println(rqs.get(i).attr("name") + " 平均请求耗时：" + totalRQTimes / times);
				System.out.println("网络传输平均时间："+(totalRQTimes / times-totalTimes / times));
			}
		}finally {
			if (httpclient != null) {
				httpclient.close();
			}
			if (httpresponse != null) {
				httpresponse.close();
			}
		}
		
	}
	
	
	public static RS readUrlRq(String xml) throws IOException{
		Elements rqs = getRqs(xml);
		for (int i = 0; i < rqs.size(); i++) {
			return sendPost(httpBase+rqs.get(i).attr("uri"),rqs.get(i).text());
		}
		return null;
	}
	 
	//短连接
	public static RS sendPost(String url, String data) {
		long s = System.currentTimeMillis();
		String response = null;
		/*
		 * log.info("url: " + url); log.info("request: " + data);
		 */
		try {
			CloseableHttpClient httpclient = null;
			CloseableHttpResponse httpresponse = null;
			try {
				httpclient = HttpClients.createDefault();
				HttpPost httppost = new HttpPost(url);
				StringEntity stringentity = new StringEntity(data, ContentType.create("application/json", "UTF-8"));
				httppost.setEntity(stringentity);
				httpresponse = httpclient.execute(httppost);
				response = EntityUtils.toString(httpresponse.getEntity());
				/* log.info("response: " + response); */
			} finally {
				if (httpclient != null) {
					httpclient.close();
				}
				if (httpresponse != null) {
					httpresponse.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		return new RS(response, e - s);
	}
}

class RS {
	private String data;
	private long time;

	public RS(String data, long time) {
		this.data = data;
		this.time = time;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
