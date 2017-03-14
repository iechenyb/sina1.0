package com.cyb.jaas.p1;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;  
//需要在运行参数中指定配置文件路径 -Djava.security.auth.login.config=D:\chenyb\myproject\finally\sina1.0\src\com\cyb\jaas\p1\example.conf
/**
 *  -Djava.security.manager
	-Djava.security.policy==policy.jaas
	-Djava.security.auth.login.config==login.conf
	-Dapusic.home=%APUSIC_HOME% samples.CountFiles
 * @author DHUser
 *
 */
public class MyClient {  
  public static void main(String argv[]) {  
    LoginContext ctx = null;  
    try {  
      ctx = new LoginContext("WeatherLogin", new MyCallbackHandler());  
    } catch(LoginException le) {  
      System.err.println("LoginContext cannot be created.1 "+ le.getMessage());  
      System.exit(-1);  
    } catch(SecurityException se) {  
      System.err.println("LoginContext cannot be created.2 "+ se.getMessage());  
    }  
    try {  
      ctx.login();  
    } catch(LoginException le) {  
     System.out.println("Authentication failed. " + le.getMessage());  
     System.exit(-1);  
    }  
    System.out.println("Authentication succeeded.");  
    System.exit(-1);  
  }  
}  
