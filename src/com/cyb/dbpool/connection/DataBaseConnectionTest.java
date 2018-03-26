package com.cyb.dbpool.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//jdbc:mysql://localhost:3306/test com.mysql.jdbc.Driver root 111111
public class DataBaseConnectionTest {
	static String url="jdbc:mysql://localhost:3306/test";
	static String username="root";
	static String password="111111";
	static String driver = "com.mysql.jdbc.Driver";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println(args[0]+"--"+args[1]+"--"+args[2]+"--"+args[3]);
		checkLocal(args);
	}
	
	public static void checkLocal(String[] args) throws ClassNotFoundException, SQLException{
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    if(args.length>0){
		    url =args[0];
		    driver=args[1];
		    username=args[2];
		    password=args[3];
	    }
	    Class.forName(driver) ;
		conn = DriverManager.getConnection(url , username , password ) ;
		stmt = conn.prepareStatement(" select 1+1 from dual");
		rs = stmt.executeQuery();
		while(rs.next()){
			System.out.println("查询结果(1+1=?) "+rs.getString(1));
		}
		conn.close();
		stmt.close();
	}
}
