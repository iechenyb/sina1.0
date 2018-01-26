package com.cyb.servelt;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月26日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*使用普通方式取出存储在request对象中的数据：
		<h3 style="color:red;"><%=(String)request.getAttribute("data")%></h3>
		使用EL表达式取出存储在request对象中的数据：
		<h3 style="color:red;">${data}</h3>*/
		HttpServletRequest hsr;//接口
		ServletRequest sq;//接口
		HttpServletRequestWrapper hsrw;//类 集成ServletRequestWrapper HttpServletRequest
		//cas
		HttpServletRequestWrapperFilter hsrwf;//
		
		/*请求重定向和请求转发的区别
		一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理，称之为请求转发/307。
		一个web资源收到客户端请求后，通知浏览器去访问另外一个web资源进行处理，称之为请求重定向/302。
		*/
	}
}
