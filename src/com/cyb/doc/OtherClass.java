package com.cyb.doc;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月27日
 */
public class OtherClass {
	Log log = LogFactory.getLog(OtherClass.class);
	/** 
	* show 方法的简述. 
	* <p>show 方法的详细说明第一行<br> 
	* show 方法的详细说明第二行 	
	* @param b true 表示显示，false 表示隐藏 
	* @return 整数 
	*/ 
	public int show(boolean b) { 
		System.out.println(b);
		return 0;
	} 
	/** 
	 * The doGet method of the servlet. 
	 * This method is called when a form has its tag value method equals to get. 
	 * 
	 * @param request 
	 * the request send by the client to the server 
	 * @param response 
	 * the response send by the server to the client 
	 * @throws ServletException 
	 * if an error occurred 
	 * @throws IOException 
	 * if an error occurred 
	 */ 
	public void doGet (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
	} 
}
