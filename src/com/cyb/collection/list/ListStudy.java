package com.cyb.collection.list;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.cyb.collection.common.CollectionFactory;
import com.cyb.collection.po.User;
import com.cyb.reflect.ReflectUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class ListStudy {
	public static void main(String[] args) {
		testUserAfterAddToList();
	}

	public static void testUserAfterAddToList() {
		ArrayList<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setName("chenyb");
		users.add(user);
		try {
			ReflectUtils.show(user);
			System.out.println("--------1--------");
			ReflectUtils.show(users.get(0));
			System.out.println("--------2--------");
			user.setName("iechenyb");//修改user
			ReflectUtils.show(users.get(0));//users中的值也跟着修改了
			System.out.println("--------3--------");
			user = null;
			ReflectUtils.show(users.get(0));//将user释放后，list里的user值还在！
			System.out.println("--------4--------");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void testStudy() {
		CollectionFactory.build(100);
		LinkedList<String> ll = new LinkedList<String>();
		ll.addFirst("123");
		ll.addFirst("456");
		ll.addLast("last1");
		ll.addLast("last2");
		ll.add("xxx");
		ll.add("yyyy");
		ll.add(5, "element");
		Collections.sort(CollectionFactory.list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);// 正确的方式
			}
		});
		System.out.println(CollectionFactory.list);
		System.out.println(ll);
		ArrayList<String> al = new ArrayList<String>();
		al.add("1");
		al.add("2");
		al.add("3");
		al.add("4ha");
		al.add("4ha");
		al.add("6");
		System.out.println(al.indexOf("4ha"));// 返回第一个value所在的索引值
		System.out.println("before remove:" + al);
		System.out.println("remove 4ha:" + al.remove("4ha"));// 移除第一个
		System.out.println("after 4ha:" + al);// 移除后需要重新调整list
		// 创建list的方法
		List<String> strList = new ArrayList<String>();
		Collections.addAll(strList, "1", "2", "3");
		System.out.println(strList);
		// 使用Guava创建list
		List<String> strList1 = Lists.newArrayList("1", "2", "3");
		System.out.println(strList1);
		List<String> strList2 = Collections.singletonList("1");// 返回一个只包含指定对象的不可变列表
		System.out.println(strList2);
		List<String> strList3 = Collections.emptyList();// 返回空的列表（不可变的）
		System.out.println(strList3);
		try {
			strList3.add("xx");
			System.out.println(strList3);// 报错，不可变
		} catch (Exception e) {
			System.out.println(strList3 + "不可修改！");
		}
		// 创建strList的视图
		List<String> unmodifiableList = Collections.unmodifiableList(strList);
		System.out.println(unmodifiableList);
		try {
			unmodifiableList.add("xx");
			System.out.println("unmodifiableList:" + unmodifiableList);// 报错，不可变
		} catch (Exception e) {
			System.out.println(strList3 + "unmodifiableList不可修改！");
		}
		// JDK中还可以创建一种只能修改，但不能增加和删除的List
		String[] strArray = new String[] { "1", "2", "3" };
		List<String> strList4 = Arrays.asList(strArray);
		try {
			strList4.add("5");
		} catch (Exception e) {
			System.out.println(strList4 + "strList4不可修改！");
		}
		List<String> strList5 = ImmutableList.of("1", "2", "3");
		System.out.println(strList5);
		strList5.add("xxx");// 不可修改，否则报错
	}

}
