package com.cyb.jvm;

import java.util.ArrayList;
import java.util.List;

import com.cyb.collection.po.User;

public class 堆溢出 {
	//-Xmx20m -Xms5m  “-XX:OnOutOfMemoryError=D:/tools/jdk1.7_40/bin/printstack.bat %p”  
	//-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/a.dump
	//java -verbose:gc -Xmn10M -Xms20M -Xmx20M -XX:+PrintGC OOMTest_1  
public static void main(String[] args) {
	int i = 0 ;
	List<User> users= new ArrayList<User>();
	while(true){//java.lang.OutOfMemoryError: Java heap space
		User user = new User();
		user.setId(i++);
		users.add(user);
	}
}
}
