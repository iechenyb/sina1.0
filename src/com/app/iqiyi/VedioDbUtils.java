package com.app.iqiyi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.comconection.ConnectionExUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;

public class VedioDbUtils {
	private static String tcpPrix = "jdbc:h2:tcp://localhost/";//tcp访问
	private static String dbPath = "chenyb/app/db/";
	//private static String dbPath = "d:/data/";
	private static Log log = LogFactory.getLog(VedioDbUtils.class);
	public static ConnectionExUtils<Vedio> dbUtils = null;
	private static boolean createTable = true; 
    public VedioDbUtils(String dbName){
    	Connection conn = H2DBUtil.getConnectionByPath(tcpPrix+dbPath+dbName);
		dbUtils = new ConnectionExUtils<Vedio>(conn);
    }
    public static void save(Vedio p){
    	try {
			dbUtils.save(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void initConnection(String dbName) {
		try {
			Connection conn = H2DBUtil.getConnection(dbName);
			dbUtils = new ConnectionExUtils<Vedio>(conn);
			log.info(dbUtils.queryForMap("select 1+1 from dual", Map.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    public static List<Vedio> getVedio(String type) throws SQLException{
    	return dbUtils.queryForList("select * from vedio where type='"+type+"'", Vedio.class);
    }
	public static void exeDLL() {
		if (createTable) {
			String Vedio = " create table Vedio(name varchar(50),url varchar(100),type varchar(30))";
			try {
				dbUtils.update(Vedio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		try {
			H2Manager.start();
			initConnection("vedio");
			/*exeDLL();*/

			/*log.info(dbUtils.update("delete from Vedio"));
			Vedio obj = new Vedio();
			obj.setIp("127.0.0.2");
			obj.setPort(80);
			 dbUtils.save(obj); 

			log.info("queryForList:"
					+ dbUtils.queryForList("select * from Vedio", Vedio.class));
			log.info("queryForMap:"
					+ dbUtils.queryForMap("select * from Vedio", Map.class));
			log.info("queryForMap:"
					+ dbUtils.update("update Vedio set port=8085"));*/
			/*String sql = "select ip,port from Vedio";
			List<Vedio> users = dbUtils.queryForList(sql, Vedio.class);
			for (int i = 0; i < users.size(); i++) {
				Vedio p = users.get(i);
				log.info("ip:" + p.getIp() + ",port:" + p.getPort());
			}*/
			/*sql = "select ip,port from Vedio where ip='127.0.0.1'";
			Vedio user = dbUtils.queryForObject(sql, Vedio.class);
			log.info("queryForObject:" + user);*/
			dbUtils.close();
			H2Manager.stop();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
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
}
