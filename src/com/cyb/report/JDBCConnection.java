package com.cyb.report;
import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月31日
 */
public class JDBCConnection {
	Log log = LogFactory.getLog(JDBCConnection.class);
		public static Connection getConnection(){  
	        try {  
	             String url = "jdbc:mysql://localhost:3306/test";  
	             Class.forName("com.mysql.jdbc.Driver");  
	             Connection con = DriverManager.getConnection(url, "root", "111111");  
	             return con;  
	         }catch(Exception e){  
	              e. printStackTrace();  
	         }  
	         return null;  
	    }  
}
