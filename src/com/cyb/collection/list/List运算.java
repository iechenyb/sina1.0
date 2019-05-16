package com.cyb.collection.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者:iechenyb<br>
 * 类描述:说点啥<br>
 * 创建时间:2018年11月28日
 */
public class List运算 {
	Log log = LogFactory.getLog(List运算.class);

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();

		list1.add("1");

		list1.add("3");

		list1.add("5");
		System.out.println("1=" + list1);

		List<String> list2 = new ArrayList<String>();

		list2.add("2");

		list2.add("4");

		list2.add("5");

		System.out.println("2=" + list2);

		list1.addAll(list2);

		System.out.println("1+2=" + list1);// 输出结果为：[1,3,5,2,4,5]

		List<String> list3 = new ArrayList<String>();

		list3.add("2");

		list3.add("4");

		list3.add("5");
		System.out.println("3=" + list3);

		list1.removeAll(list3);

		System.out.println("1+2-3=" + list1);// 输出结果为：[1,3,5,2,4,5]
		
		//快速从list中找到比a大的数字所在位置
		for(String s:list3){
			if(Integer.valueOf(s)>=2){
				System.out.println(list3.indexOf(s));
			}
		}
		int  a = list1.indexOf("33");//为负值时 报错
		if(a>0){
			System.out.println(a+"list1:"+list1.subList(a, list1.size()));
		}
	}
}
