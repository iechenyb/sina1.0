package com.cyb.h2;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2DBUtil {
	static Logger log = LoggerFactory.getLogger(H2DBUtil.class);
	private static String embedPrix = "jdbc:h2:";
	private static String tcpPrix = "jdbc:h2:tcp://localhost/";//tcp访问
	private static String memPrix = "jdbc:h2:tcp://localhost/mem:";
	private static String dbPath = "d:/data/";//  /root/data/trade d:/data/
	static {
		H2Manager.start();
	}
	public static void main(String[] args) {
		H2Manager.start();H2Manager.start();
		H2DBUtil.testFileConnection("test0");
		H2DBUtil.testTCPConnection();
		H2DBUtil.testMemConnection();
		System.exit(0);
	}
   public static void testFileConnection(String dbName){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(embedPrix+dbPath+dbName, "sa", "");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("Ƕ��ģʽ��1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB File test Success!");
	} catch (Exception e) {
		System.out.println("tcp File test err!");
	}
   }
   public static Connection getConnection(String dbName){
	   try {
		   //Class.forName("org.h2.Driver");
		   System.out.println(tcpPrix+dbPath+dbName);
		   Connection conn = DriverManager.getConnection(tcpPrix+dbPath+dbName, "sa", "");
		   return conn;
	   } catch (Exception e) {
			System.out.println("tcp File test err!");
			return null;
		}
	  
   }
   public static Connection getConnectionByPath(String path){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(path, "sa", "");
		   return conn;
	   } catch (Exception e) {
			System.out.println("tcp File test err!");
			return null;
		}
	  
   }
   public static Connection getConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(embedPrix+dbPath+"test1", "sa", "");
		   return conn;
	   } catch (Exception e) {
			System.out.println("tcp File test err!");
			return null;
		}
	  
   }
   public static void testTCPConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(tcpPrix+dbPath+"test2", "sa", "");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("serverģʽ��1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB TCP test Success!");
		} catch (Exception e) {
			log.info("tcp TCP test err!");
		} 
   }
   public static void testTCPConnection(String dbName){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(tcpPrix+dbPath+dbName, "sa", "");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("server1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB TCP test Success!");
		} catch (Exception e) {
			log.info("tcp TCP test err!");
		} 
   }
   public static void testMemConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(memPrix+dbPath+"test3", "sa", "");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("Mem1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB Mem test Success!");
		} catch (Exception e) {
			log.info("tcp Mem test err!");
		} 
   }
   
   public static boolean isExist(Connection conn,String tableName){
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet rs = meta.getTables(null, null, tableName.toUpperCase(), null);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
   }
}