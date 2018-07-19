package com.app.vas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.alibaba.fastjson.JSONObject;
import com.cyb.file.FileUtils;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年7月6日
 */
public class Controller执行时间统计 {
	Log log = LogFactory.getLog(Controller执行时间统计.class);

	private static String xmlPath = "d:/data/vas/tests";
	static String httpBase = "http://192.168.108.224:8085/vasweb/";
	public static void main(String[] args) throws IOException {
		for(int i=1;i<=2;i++){
			testVasExecuteTime(i);
		}
	}
	
	public static void testVasExecuteTime(int i) throws IOException{
		List<RQ> rqs = getAllVasRqs(xmlPath);
		System.out.println("执行次数["+i+"]增值服务中心测试接口总数:" + rqs.size());
		System.out.println("执行状态\t接口名称\t\t\t请求总耗时\t服务执行时间");
		System.out.println("--------------------------------------------------------");
		for(RQ rq :rqs){
			RS rs = sendPost(rq.getUri(), rq.getBody());
			String controllerTime = JSON.parseObject(rs.getData()).getString("time");
			System.out.println(checkRq(rs)+"\t"+rq.getName()+"\t\t"+rs.getTime()+"\t"+controllerTime);
			//System.out.println("--------------------------------------------------------");
		}
	}
    public static String checkRq(RS rs){
    	JSONObject obj = JSON.parseObject(rs.getData());
		if ("0".equals(obj.get("ec"))) {
			return "成功";
		} else {
			return "失败";
		}
    }
	public static List<RQ> getAllVasRqs(String basePath) throws IOException {
		System.out.println("--------------------------------------------------------");
		List<RQ> allRqs = new ArrayList<>();
		List<File> paramsXml = FileUtils.getAllFiles(basePath);
		for (File file : paramsXml) {
			if (file.getName().endsWith(".xml")) {
				allRqs.addAll(xmlToRq(file));
			}
		}
		return allRqs;
	}

	public static List<RQ> xmlToRq(File file) throws IOException {
		List<RQ> rqsList = new ArrayList<>();
		Document doc = Jsoup.parse(file, "utf-8");
		Elements rqs = doc.select("rq");
		for (int i = 0, total = rqs.size(); i < total; i++) {
			if ("0".equals(rqs.get(i).attr("skip"))) {
				rqsList.add(new RQ(rqs.get(i).attr("name"), httpBase+rqs.get(i).attr("uri"), rqs.get(i).text()));
			}
		}
		return rqsList;
	}

	// 短连接
	public static RS sendPost(String url, String data) {
		long s = System.currentTimeMillis();
		String response = null;
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

class RQ {
	private String name;
	private String uri;
	private String body;

	public RQ(String name, String uri, String body) {
		if(name.length()<12){
			for(int i=0;i<20-name.length();i++){
				name+=" ";
			}
		}
		this.name = name;
		this.uri = uri;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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
