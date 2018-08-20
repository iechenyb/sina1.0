package com.app.oauth2;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.url.fina.FinalHttpClient;

import net.sf.json.JSONObject;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月2日
 */
public class PasswordGrantUtils extends OauthBase {
	Log log = LogFactory.getLog(PasswordGrantUtils.class);
	static String sourceUri="api/delete";//user 无限制角色 dbTrustedClent 限制角色 authTrustedClent 报403
	static boolean stop= true;
	public static void main(String[] args) throws InterruptedException {
		//根据用户名和密码 获取token post 请求
		Map<String,String> para = new HashMap<>();
		para.put("username",username );
		para.put("password", password);
		para.put("client_id",clientId );
		para.put("client_secret", clientSecret);
		para.put("grant_type", grant_type);
		String result = FinalHttpClient.doPost(server+"oauth/token", para);
		System.out.println("password方式获取token的结果："+result);
		JSONObject tokenResult = JSONObject.fromObject(result);
		System.out.println("提取参数：\t\n access_token="+tokenResult.getString("access_token")+"\t\n refresh_token="+
				tokenResult.getString("refresh_token"));
		System.out.println("====================检查token的合法性========================");
		System.out.println("校验token \t\n"+FinalHttpClient.doGet(server+"oauth/check_token?token="+tokenResult.getString("access_token")));
		if(stop) return ;
		//校验token正常的返回结果：
	/*	{"exp":1533177910,"user_name":"cyb","scope":["read","write","trust"],
			"authorities":["ROLE_TRUSTED_CLIENT","ROLE_CLIENT"],
			"client_id":"my-trusted-client"}*/
		for(int i=0;i<10;i++){
			Thread.sleep(500);
			result = FinalHttpClient.doGet(server+sourceUri+"?access_token="+tokenResult.getString("access_token"));
			System.out.println("第一次获取的token访问：\t\n"+result);
		}
		//刷新token
		/*oauth/token
		?grant_type=refresh_token&refresh_token=?
				&client_id=my-trusted-client
				&client_secret=secret*/
		Map<String,String> para1 = new HashMap<>();
		para1.put("client_id",clientId );
		para1.put("client_secret", clientSecret);
		para1.put("grant_type", "refresh_token");
		para1.put("refresh_token", tokenResult.getString("refresh_token"));
		//利用第一次访问获取的refresh_token刷新token并访问n次资源。
		for(int i=0;i<37;i++){
			Thread.sleep(1000);
			result = FinalHttpClient.doPost(server+"oauth/token", para1);
			System.out.println("刷新token:\t\n"+result);
			String newToken = JSONObject.fromObject(result).getString("access_token");
			//使用刷新token访问资源
			result = FinalHttpClient.doGet(server+sourceUri+"?access_token="+newToken);
			System.out.println("第"+(i+1)+"次刷新的token访问资源：\t\n"+result);
		}
	}
}
