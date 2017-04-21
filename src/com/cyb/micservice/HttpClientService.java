package com.cyb.micservice;

import io.netty.handler.codec.http.HttpResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpClientService {
	protected CloseableHttpClient httpclient;

	public static void main(String[] args) {
		// getInforByID(2);
		getToken();
	}

	/**
	 * get请求HttpClient返回实体或error
	 * 
	 * @param address
	 *            请求地址
	 * @return
	 */
	public static void getInforByID(int id) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet("http://localhost:8080/demo/" + id);
			httpget.addHeader("Authorization",
					"Bearer 195124a3-4d51-4126-a18a-0aa3d6ebe3a3");
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

	@SuppressWarnings({ "deprecation", "resource" })
	public static void getToken() {
		String result = "";
		try {
			String url = "http://localhost:8080/oauth/token";
			HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象Y2xpZW50Om1fdm9sdW50ZWVy
			httpRequst.addHeader("Authorization","Basic Y2xpZW50Om1fdm9sdW50ZWVy");
			//httpRequst.addHeader("Authorization","client:m_volunteer");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("grant_type", "password"));
			params.add(new BasicNameValuePair("username", "voltest"));
			params.add(new BasicNameValuePair("password", "voltest"));
			httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			org.apache.http.HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequst);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {}else{}
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity);// 取出应答字符串
			System.out.println("返回结果："+result);
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
	}
}
