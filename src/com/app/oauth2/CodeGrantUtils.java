package com.app.oauth2;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.url.fina.FinalHttpClient;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年8月2日
 */
public class CodeGrantUtils extends OauthBase{
	Log log = LogFactory.getLog(CodeGrantUtils.class);
	public static void main(String[] args) {
		
		/*第一步 输入授权客户端信息，请求并重定向到指定的uri上。
		 * 第一次会进入登录页面！！！后边只返回code值
		 * http://localhost:1130/oauth/authorize?response_type=code&
			client_id=my-trusted-client&
			redirect_uri=http://baidu.com&state=123*/
		String code="Mtbo6a";//第一步，在浏览器中输入用户名及密码获取code
		//根据code换取token
		/*http://localhost:1130/oauth/token?
			client_id=my-trusted-client&client_secret=secret
			&grant_type=authorization_code
			&redirect_uri=http://baidu.com&code=GoqHzH*/
		grant_type = "authorization_code";
		Map<String,String> para = new HashMap<>();
		para.put("username",username );
		para.put("password", password);
		para.put("client_id",clientId );
		para.put("client_secret", clientSecret);
		para.put("grant_type", grant_type);
		para.put("code", code);//************
		para.put("redirect_uri","http://www.baidu.com");
	    String result = FinalHttpClient.doPost(server+"oauth/token", para);
	    System.out.println(result);
	}
}
