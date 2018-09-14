package com.app.cas;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.cyb.url.HttpsClient;
import com.cyb.url.SpiderHttpClient;

/**
 * 作者 = iechenyb<br>
 * 类描述= 说点啥<br>
 * 创建时间= 2018年8月24日
 */

public class CasLogin {
	Log log = LogFactory.getLog(CasLogin.class);
	static String loginUrl = "http://cas.kiiik.com:8088";

	public static void main(String[] args) throws Exception {
		String username = "add";
		String password = "cj";
		String system = "iechenyb";
		String rememberMe = "true";
		String lt = GetLT();// "LT-16-nMdBzbO1Feuv0Js09c0DZJFiNdm23s-http=//cas.kiiik.com=8088";
		String execution = "e2s1";
		String _eventId = "submit";
		Map<String, String> para = new HashMap<String, String>();
		para.put("username", username);
		para.put("password", password);
		para.put("system", system);
		para.put("rememberMe", rememberMe);
		para.put("lt", lt);
		para.put("execution", execution);
		para.put("_eventId", _eventId);
		String rs = SpiderHttpClient.doPost(loginUrl + "/cas/login", para);
		System.out.println("登录结果：" + rs); // 重定向
		rs = SpiderHttpClient.doGet(loginUrl + rs);
		System.out.println("登录结果：" + rs);
	}

	public static String GetLT() throws Exception {
		Document doc = Jsoup.connect(loginUrl + "/cas/login").get();
		Elements info = doc.select("input[name = lt]");
		System.err.println(info.attr("name") + "," + info.attr("value"));
		return info.val();
	}

	public static  String aaa(){
		String CasLoginHttpClienturl="/cas/login?service=http%3A%2F%2F133.128.14.155"+
				"%3A8081%2Fportal%2FdoPortalLogin.do"+
				"&auto=true"+"&username="+"chenyb"+"&password="+"123456";
		//String CasLoginHttpClienturl="http://localhost:8080/cas/login?service=http%3A%2F%2Flocalhost%3A8812%2Fportal%2FdoPortalLogin.do&auto=true&username=10001&password=superwoman";   
		//response.sendRedirect(CasLoginHttpClienturl);
		System.out.println(CasLoginHttpClienturl);
		return null;
	}
}
