package com.app.heike;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.cyb.url.SpiderHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月26日
 */
public class MessageTest {
	Log log = LogFactory.getLog(MessageTest.class);
	public static void main(String[] args) {
		for(int i=0;i<=1;i++){
			String url="https://www.cesfutures.com/RESTfull/user/sendCode.do?iszh=true&phone=13938469072";
			doGet(url);
		}
	}
	@SuppressWarnings("resource")
	public static String doGet(String uri) {
		String result = "";
		// HttpGet httpRequst = new HttpGet(URI uri);
		// HttpGet httpRequst = new HttpGet(String uri);
		// 创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
		HttpGet httpRequst = new HttpGet(uri);

		// new DefaultHttpClient().execute(HttpUriRequst requst);
		try {
			// 使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);// 其中HttpGet是HttpUriRequst的子类
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity);// 取出应答字符串
				// 一般来说都要删除多余的字符
				result.replaceAll("\r", "");// 去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
			} else {

			}
			httpRequst.abort();
			httpRequst.releaseConnection();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		} catch (IOException e) {
			e.printStackTrace();
			result = e.getMessage().toString();
		}
		return result;
	}
}
