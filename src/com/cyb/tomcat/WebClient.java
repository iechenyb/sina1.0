package com.cyb.tomcat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.url.SpiderHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月22日
 */
public class WebClient {
	Log log = LogFactory.getLog(WebClient.class);
	public static void main(String[] args) throws Exception {
		SpiderHttpClient.doPost("http://localhost:8080");//查看参数 post请求参数
		
		//SpiderHttpClient.doGet("http://localhost:8080/myreq?name=chenyb");//查看get请求参数
		//SpiderHttpClient.doPostWithFile("http://localhost:8080/", "‪C:/Users/j/Pictures/more.jpg");
		//SpiderHttpClient.postBody("http://localhost:8080/postdata", "{name:'chenyb'}");//
		System.exit(0);
	}
}
