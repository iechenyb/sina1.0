package com.cyb.clone;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.collection.common.CollectionFactory;
import com.cyb.collection.po.User;
import com.cyb.reflect.ReflectUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 浅复制<br>
 *创建时间: 2017年7月20日
 */
public class CloneTest {
	Log log = LogFactory.getLog(CloneTest.class);
	public static void main(String[] args) throws CloneNotSupportedException, InvocationTargetException, ClassNotFoundException, IOException {
		CollectionFactory.build(10);
		User user = CollectionFactory.getUser();
		User user2 = (User) user.clone();
		ReflectUtils.show(user);
		System.out.println(user+"--------1-------"+user2);
		ReflectUtils.show(user2);
		System.out.println(user+"--------2-------"+user2);
		user2.setName("new Name");
		ReflectUtils.show(user);
		System.out.println(user+"--------3-------"+user2);
		//set新值后地址发生变化，被克隆对象内容不变
		ReflectUtils.show(user2);
		System.out.println(user+"--------4-------"+user2);
		
		User user3  = (User) user.deepClone();
		ReflectUtils.show(user3);
		System.out.println(user+"--------4-------"+user3);
	}
}
