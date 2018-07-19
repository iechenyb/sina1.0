package com.cyb.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年5月31日
 */
public class H2Demo {
	private Server server;
	private String port = "9080";
	//linux   ~/tomcat/webapps/data/cashDb
	private String dbDir = "d://data/sample;CACHE_SIZE=32384;MAX_LOG_SIZE=32384;mv_store=false";// ./h2db/
	private String user = "sa";
	private String password = "";

	public void startServer() {
		try {
			System.out.println("正在启动h2...");
			server = Server.createTcpServer(new String[] { "-tcp", "-tcpAllowOthers", "-tcpPort", port }).start();
		} catch (SQLException e) {
			System.out.println("启动h2出错：" + e.toString());
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void stopServer() {
		if (server != null) {
			System.out.println("正在关闭h2...");
			server.stop();
			System.out.println("关闭成功.");
		}
	}

	public void useH2() {
		try {
			Class.forName("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://192.168.16.211:" + port + "/" + dbDir, user,
					password);
			Statement stat = conn.createStatement();
			// insert data
			stat.execute("DROP TABLE IF EXISTS TEST");
			stat.execute("CREATE TABLE TEST(NAME VARCHAR)");
			stat.execute("INSERT INTO TEST VALUES('Hello World')");
			// use data
			ResultSet result = stat.executeQuery("select name from test ");
			int i = 1;
			while (result.next()) {
				System.out.println(i++ + ":" + result.getString("name"));
			}
			result.close();
			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("*********start***********");
		H2Demo h2 = new H2Demo();
		h2.startServer();//调用远端服务时，不需要开启server
		h2.useH2();
		// h2.stopServer();
		System.out.println("*********end***********");
	}
}
