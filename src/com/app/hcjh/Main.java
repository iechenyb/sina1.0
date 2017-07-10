package com.app.hcjh;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Main {
	public static void main(String[] args) {
		getString();
	}
	@SuppressWarnings({ "deprecation", "resource" })
	public static void getString() {
		String result = "";
		try {
			String url = "http://www.cxin88.com/HandlerAjax.ashx";
			HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象Y2xpZW50Om1fdm9sdW50ZWVy
			//httpRequst.addHeader("Authorization","Basic Y2xpZW50Om1fdm9sdW50ZWVy");
			//httpRequst.addHeader("Authorization","client:m_volunteer");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Color", "官方重庆计划"));
			params.add(new BasicNameValuePair("MethodId", "6"));
			params.add(new BasicNameValuePair("MethodName", "彩宝"));
			params.add(new BasicNameValuePair("Radio", "定码 前二胆码"));
			params.add(new BasicNameValuePair("CodeNumber", "1"));
			params.add(new BasicNameValuePair("PlanCycle", "2"));
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
