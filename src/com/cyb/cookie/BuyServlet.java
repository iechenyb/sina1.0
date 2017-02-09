package com.cyb.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BuyServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String basePath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/";
		//�����������  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
      
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ǵ���������Ʒ��(����鿴����)<br/>");  
          
        //�õ������ݿ⡰����  
        Map<String, MyObject> map = DB.getAll();  
          
        //չʾ��Ʒ������Ʒidͨ�����Ӵ���ShowObject  
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=========================<br/>");  
        for (int i = 1; i < map.size()+1; i++){  
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+basePath+"ShowObject?id="+map.get(i+"").getId()+"'>"+map.get(i+"").getName()+"</a><br/>");  
        }  
          
        //��ʾ�����������Ʒ  
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���������������Ʒ��<br/>");  
          
        //�õ��û�������cookieֵ������ֵ��cookie����  
        Cookie[] cookies = request.getCookies();  
        for (int i = 0; cookies != null && i < cookies.length; i++){  
              
            //�ҵ�������Ҫ��cookie  
            if (cookies[i].getName().equals("historyCookie")){  
                String[] ids = cookies[i].getValue().split("\\,");  
                  
                //�õ�cookie�д��ڵ�id��չ�����������Ʒ  
                for (String id : ids){  
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+basePath+"ShowObject?id="+id+"'>"+map.get(id).getName()+"</a><br/>");  
                }  
            }  
        }  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response); 
	}

}
class DB{  
    private static LinkedHashMap<String, MyObject> map = new LinkedHashMap<String, MyObject>();  
    static{  
        map.put("1", new MyObject("1", "����", "12.0", "����Ʊ��Ӿ��Ǻã�"));  
        map.put("2", new MyObject("2", "ë��", "5.0", "��ˮ�Ըոյģ�"));  
        map.put("3", new MyObject("3", "����", "8.0", "�ݻ��󣬿ɷ�����࣡"));  
        map.put("4", new MyObject("4", "����", "6.0", "ǿ��ȥ������ǿ��"));  
        map.put("5", new MyObject("5", "ůƿ", "18.0", "�������˻���"));  
    }  
      
    public static Map<String, MyObject> getAll(){  
        return map;  
    }  
}  
class MyObject{  
    
    private String id;  
    private String name;  
    private String price;  
    private String describe;  
      
    public MyObject(String id, String name, String price, String describe) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.price = price;  
        this.describe = describe;  
    }  
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getPrice() {  
        return price;  
    }  
    public void setPrice(String price) {  
        this.price = price;  
    }  
    public String getDescribe() {  
        return describe;  
    }  
    public void setDescribe(String describe) {  
        this.describe = describe;  
    }  
}  
