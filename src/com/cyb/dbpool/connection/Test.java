package com.cyb.dbpool.connection;

import java.sql.DriverManager;

public class Test {
public static void main(String[] args) {
	String url="";
	String username="root";
	String password="111111";
	Class.forName("com.mysql.jdbc.Driver") ; 
	DriverManager.getConnection(url , username , password ) ;
}
}
