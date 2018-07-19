package com.cyb.json.bean;

import java.lang.reflect.InvocationTargetException;

import com.alibaba.fastjson.JSON;
import com.cyb.reflect.ReflectUtils;

public class Weibo {
	  	private String id;  
	    private String city;  
	    private String ABCD;
	    private String QDate;//语义与qDate重复
	    private String qDate;
	    private String aBcDe;
	  
	    public Weibo(String id, String city) {  
	        this.id = id;  
	        this.city = city;  
	    }  
	  
	    public Weibo() {  
	    }  
	  
	    public String getId() {  
	        return id;  
	    }  
	  
	    public void setId(String id) {  
	        this.id = id;  
	    }  
	  
	    public String getCity() {  
	        return city;  
	    }  
	  
	    public void setCity(String city) {  
	        this.city = city;  
	    }

		public String getABCD() {
			return ABCD;
		}

		public void setABCD(String aBCD) {
			ABCD = aBCD;
		}

		public String getQDate() {
			return QDate;
		}

		public void setQDate(String qDate) {
			QDate = qDate;
		}

		public String getaBcDe() {
			return aBcDe;
		}

		public void setaBcDe(String aBcDe) {
			this.aBcDe = aBcDe;
		}  
	    
		public String getqDate() {
			return qDate;
		}

		public void setqDate(String qDate) {
			this.qDate = qDate;
		}

		/**
		 * 
		 *作者 : iechenyb<br>
		 *方法描述: 大小写无关，但是一个bean存在相同语义的bean时，如何set值，
		 *比如 QDate和qDate<br>
		 *创建时间: 2017年7月15日
		 *@param args
		 *@throws InvocationTargetException
		 */
	  public static void main(String[] args) throws InvocationTargetException {
		String json1="{\"id\":\"0377\",\"city\":\"南阳\",\"aBCD\":\"南阳111\",\"QDate\":\"南阳222\",\"qDate\":\"南阳333\",\"aBcDe\":\"南阳333\"}";
		String json2="{\"id\":\"0377\",\"city\":\"南阳\",\"abcd\":\"南阳\",\"qdate\":\"南阳\",\"QDate\":\"南阳333\",\"aBcDe\":\"南阳\"}";
		Weibo a = JSON.parseObject(json1, Weibo.class);
	    ReflectUtils.show(a);
	    System.out.println("=======================");
	    Weibo b = JSON.parseObject(json2, Weibo.class);
	    ReflectUtils.show(b);
	  }  
}
