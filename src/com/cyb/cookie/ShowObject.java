package com.cyb.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowObject")
public class ShowObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowObject() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
    	 super.init(config); 
    	/* <init-param>
    	 <param-name>SSOServiceURL</param-name>
    	 <param-value>http://wangyu.prc.sun.com:8080/SSOAuth/SSOAuth</param-value>
    	 </init-param>*/
    	 //config.getInitParameter("SSOServiceURL");//��ȡxml���õ�keyֵ
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //�����������  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
          
        //�õ�Url�е�id  
        String id = request.getParameter("id");  
          
        //��ȡ�����ݿ⡰������  
        Map<String, MyObject> map = DB.getAll();  
        //����id�õ��������Ʒ  
        MyObject myobj = (MyObject) map.get(id);  
          
        out.write("��Ҫ������Ʒ�ǣ�"+myobj.getName()+"<br/>");  
        out.write("�۸�"+myobj.getPrice()+"<br/>");  
        out.write("�Ը���Ʒ�������ǣ�<br/>");  
        out.write(myobj.getDescribe()+"<br/>");  
        out.write("<a href='index.jsp'>������ҳ</a><br/>");  
        //���������дcookie  
        String cookieValue = buildCookie(id, request);//������Ҫ��cookie�е�ֵ  
        Cookie cookie = new Cookie("historyCookie", cookieValue);  
        cookie.setMaxAge(1*24*3600);  
        System.out.println(request.getContextPath());
        cookie.setPath(request.getContextPath());  
        response.addCookie(cookie);  
	}
	private String buildCookie(String id, HttpServletRequest request) {  
        
        String historyCookie = null;  
          
        //�õ������д�����cookieֵ  
        Cookie[] cookies = request.getCookies();  
        for (int i = 0; cookies != null && i < cookies.length; i++){  
            if (cookies[i].getName().equals("historyCookie") ){  
                historyCookie = cookies[i].getValue();  
            }  
        }  
          
        //���Ϊ�շ��ص�ǰ��Ʒ��id  
        if (historyCookie == null){  
            return id;  
        }  
          
        LinkedList<String> list = new LinkedList<String>( Arrays.asList((historyCookie.split("\\,"))));  
          
        //�Բ�ͬ��������з�������id��ֵ  
        if (list.contains(id)){  
            list.remove(id);  
        }else{  
            if (list.size() >= 5){  
                list.removeLast();  
            }  
        }  
        list.addFirst(id);  
          
        StringBuffer sb = new StringBuffer();  
        for (String sid : list){  
            sb.append(sid + ",");  
        }  
        sb.deleteCharAt(sb.length()-1);  
          
        return sb.toString();  
    }  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response); 
	}

}
