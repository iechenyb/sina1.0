package com.app.jwtclient;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.cyb.url.fina.FinalHttpClient;

import net.sf.json.JSONObject;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月13日
 */
public class JwtClient {
	Log log = LogFactory.getLog(JwtClient.class);
	public static String basePath = "http://localhost:8088/";
	public static String loginPath = basePath+"auth/login";
	public static String refeshPath = "auth/refresh";
	public static void main(String[] args) {
		String token = "12346";
		//token=login("query");
		//token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1cGQiLCJjcmVhdGVkIjoxNTM2ODAwNzEyMDE4LCJleHAiOjE1MzY4MDE3MTJ9.xugPqEGawDj8uJyGkvAzhjhn4itXUjW1z4A5GjS6TuX7nfk0jbR73ESPwwmuuJFLysCDmsX5mVdlqFVlOOm5fw";
		refeshToken(token,refeshPath);
		getDataByApi(token,"api/add");
		getDataByApi(token,"api/update");
		getDataByApi(token,"api/delete");
		getDataByApi(token,"api/query");
		getDataByApi(token,"api/free");//没有做对应的角色控制 ，但是也得登录之后才能访问！
	}
	
	public static String login(String name) {
		Map<String,String> para = new HashMap<>();
		para.put("username", name);
		para.put("password", name);
		String data = FinalHttpClient.doPost(loginPath, para);
		System.out.println("data:"+data);
		String token = JSONObject.fromObject(data).get("d").toString();
		System.out.println(token);
		return token;
	}
	public static void refeshToken(String token,String uri) {
		getDataByApi(token,uri);
	}
	
	public static void getDataByApi(String token,String uri) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(basePath+uri);
			httpget.addHeader("Authorization",
					"Bearer "+token);
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态
				System.out.println(response.getStatusLine().getStatusCode()
						+ "," + response.getStatusLine().getProtocolVersion());
				if (entity != null) {
					// 打印响应内容长度
					System.out.println("Response content length: "
							+ entity.getContentLength());
					// 打印响应内容
					System.out.println("Response content: "
							+ EntityUtils.toString(entity));
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
