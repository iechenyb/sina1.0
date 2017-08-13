package com.cyb.collection.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cyb.collection.po.Bean;
import com.cyb.collection.po.User;

public class CollectionFactory {
	public static List<Integer> list = new ArrayList<Integer>();
	public static List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
	public static List<User> users = new ArrayList<User>();
	public static List<Bean> beans = new ArrayList<Bean>();
	public static Set<Integer> set = new HashSet<Integer>();
	public static Map<String, String> map = new HashMap<String, String>();
	public static User user = new User();

	public static void build(int nums) {
		for (int i = 0; i < nums; i++) {
			User user = new User();
			Bean bean  = new Bean("iechenyb"+i,"password"+i);
			user.setId(i);
			user.setName("iechenyb" + i);
			user.setPwd("don't tell you!" + i);
			list.add(i);
			set.add(i);
			map.put("k" + i, "val" + i);
			listMap.add(map);
			beans.add(bean);
			users.add(user);
		}
		user.setName("iechenyb");
		user.setPwd("don't tell you!");
	}
	public static void buildBeans(int nums) {
		for (int i = 0; i < nums; i++) {
			Bean bean  = new Bean("iechenyb"+i,"password"+i);
			beans.add(bean);
		}
	}
	public static List<Integer> getList() {
		return list;
	}

	public static Set<Integer> getSet() {
		return set;
	}

	public static Map<String, String> getMap() {
		return map;
	}

	public static User getUser() {
		return user;
	}

	public static List<Map<String, String>> getListMap() {
		return listMap;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static List<Bean> getBeans() {
		return beans;
	}

	public static void setBeans(List<Bean> beans) {
		CollectionFactory.beans = beans;
	}

	public static void setList(List<Integer> list) {
		CollectionFactory.list = list;
	}

	public static void setListMap(List<Map<String, String>> listMap) {
		CollectionFactory.listMap = listMap;
	}

	public static void setUsers(List<User> users) {
		CollectionFactory.users = users;
	}

	public static void setSet(Set<Integer> set) {
		CollectionFactory.set = set;
	}

	public static void setMap(Map<String, String> map) {
		CollectionFactory.map = map;
	}

	public static void setUser(User user) {
		CollectionFactory.user = user;
	}

}
