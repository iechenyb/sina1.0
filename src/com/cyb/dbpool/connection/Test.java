package com.cyb.dbpool.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
public static void main(String[] args) throws SQLException, ClassNotFoundException {
	String url="";
	String username="root";
	String password="111111";
	Class.forName("com.mysql.jdbc.Driver") ; 
	DriverManager.getConnection(url , username , password ) ;
}
}
