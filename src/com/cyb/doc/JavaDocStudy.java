package com.cyb.doc;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥，注释多行测试，
 *类头上多个多行注释上，会保留最后一个！<br>
 *创建时间: 2018年2月27日
 */
/*注释若干行 http://blog.csdn.net/michelle__/article/details/52523635*/ 
/**-encoding utf-8 -charset utf-8**/
/**注释若干行，并写入javadoc文档*/ 
/**
 * 类注释，随便写点啥！
 * @author iechenyb
 * @see OtherClass
 * @version 测试版本 1.10 2018年2月27日13:48:06<br>
 * This is first line. <br> 
 ***** This is second line. <br> 
 This is third line. 
 * 
 */
public class JavaDocStudy {
	Object object;
	/**日志对象*/ 
	Log log = LogFactory.getLog(JavaDocStudy.class);
	OtherClass obj;
	/** 
	* show 方法的简述. 
	* <p>show 方法的详细说明第一行<br> 
	* show 方法的详细说明第二行 	
	* @param b true 表示显示，false 表示隐藏 
	* @return 整数 
	*/ 
	public int show(boolean b) { 
		System.out.println(b);
		obj.show(true);
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
		obj.doGet(request, response);
	} 
}
