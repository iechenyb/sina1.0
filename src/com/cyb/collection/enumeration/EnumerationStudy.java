package com.cyb.collection.enumeration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: Enumeration迭代器只能遍历Vector、Hashtable这种古老的集合， 因此通常不要使用它，
 * 除非在某些极端情况下，不得不使用Enumeration， 否则都应该选择Iterator迭代器<br>
 * 创建时间: 2018年2月9日
 */
public class EnumerationStudy {
	Log log = LogFactory.getLog(EnumerationStudy.class);
	enum Priority { High, Normal, Low};
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Priority [] a=new Priority[]{Priority.High,Priority.Normal,Priority.Low};
		System.out.println(Priority.High);
		Enumeration<String> days;
		Vector<String> dayNames = new Vector<String>();
		dayNames.add("Sunday");
		dayNames.add("Monday");
		dayNames.add("Tuesday");
		dayNames.add("Wednesday");
		dayNames.add("Thursday");
		dayNames.add("Friday");
		dayNames.add("Saturday");
		days = dayNames.elements();
		while (days.hasMoreElements()) {
			System.out.println(days.nextElement());
		}
		List arrlist = new ArrayList();
		// populate the list
		arrlist.add("A");
		arrlist.add("B");
		arrlist.add("C");
		// create Enumeration
		Enumeration e = Collections.enumeration(arrlist);
		System.out.println("Print the enumeration");
		while (e.hasMoreElements()) {
			System.out.println("Value is: " + e.nextElement());
		}
		Hashtable table = new Hashtable();
		table.put("语文", 99);
		table.put("数学", 90);
		Set<Map.Entry<String, Integer>> set = table.entrySet();
		Iterator<Entry<String, Integer>> iter = set.iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> m = iter.next();
			System.out.println(m.getKey()+"="+m.getValue());
		}
	}
}
