package com.cyb.collection.utils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 集合排序 {
	static List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
	static List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
	static List<String> list = new ArrayList<String>();

	static void init() {
		Map<String, String> data0 = new HashMap<String, String>();
		Map<String, String> data1 = new HashMap<String, String>();
		Map<String, String> data2 = new HashMap<String, String>();
		Map<String, String> data3 = new HashMap<String, String>();
		data0.put("BEGIN_DATA", "2014-01-02");
		data1.put("BEGIN_DATA", "2014-01-05");
		data2.put("BEGIN_DATA", "2014-01-01");
		data3.put("BEGIN_DATA", "2014-01-08");

		data0.put("JYMC", "IC1605 中证500指数");
		data1.put("JYMC", "IC 中证500指数");
		data2.put("JYMC", "AAAAAA");
		data3.put("JYMC", "IC1707 测试");
		list1.add(data0);
		list1.add(data1);
		list1.add(data2);
		list1.add(data3);

		/////////////////////////////////////////////////////

		Map<String, Object> data00 = new HashMap<String, Object>();
		Map<String, Object> data11 = new HashMap<String, Object>();
		Map<String, Object> data22 = new HashMap<String, Object>();
		Map<String, Object> data33 = new HashMap<String, Object>();
		data00.put("BEGIN_DATA", "2014-01-02");
		data11.put("BEGIN_DATA", "2014-01-05");
		data22.put("BEGIN_DATA", "2014-01-01");
		data33.put("BEGIN_DATA", "2014-01-08");

		data00.put("JYMC", "IC1605 中证500指数");
		data11.put("JYMC", "IC 中证500指数");
		data22.put("JYMC", "AAAAAA");
		data33.put("JYMC", "IC1707 测试");
		list2.add(data00);
		list2.add(data11);
		list2.add(data22);
		list2.add(data33);

		list.add("王硕");
		list.add("李明");
		list.add("刘迪");
		list.add("刘布");

	}

	public static void main(String[] args) {
		init();
		// 排序前
		System.out.println("Before sort.");
		for (Map<String, String> m : list1) {
			System.out.println(m);
		}
		// 排序
		// 注意：是根据的汉字的拼音的字母排序的，而不是根据汉字一般的排序方法
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("==================我>啊" + ("我".compareTo("啊")));
		System.out.println("==================我<啊" + ("啊".compareTo("我")));
		System.out.println("==================我=我" + ("我".compareTo("我")));
		Collections.sort(list, Collator.getInstance(java.util.Locale.CHINA));
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// Collections.sort(list, new MapComparator("BEGIN_DATA"));
		Collections.sort(list1, new Comparator<Map<String, String>>() {

			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				String b1 = o1.get("JYMC");
				String b2 = o2.get("JYMC");
				if (b2 != null) {
					return b1.compareTo(b2);
				}
				return 0;
			}

		});
		// 排序后
		System.out.println("After sort.");
		for (Map<String, String> m : list1) {
			System.out.println(m);
		}

		// 排序前
		System.out.println("Before sort.");
		for (Map<String, Object> m : list2) {
			System.out.println(m);
		}
		// 排序
		// Collections.sort(list, new MapComparator("BEGIN_DATA"));
		Collections.sort(list2, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				String b1 = o1.get("JYMC").toString();
				String b2 = o2.get("JYMC").toString();
				if (b2 != null) {
					return b1.compareTo(b2);
				}
				return 0;
			}

		});
		// 排序后
		System.out.println("After sort.");
		for (Map<String, Object> m : list2) {
			System.out.println(m);
		}
	}

	public static class MapComparator implements Comparator<Map<String, String>> {
		private String order;

		public MapComparator(String order) {
			this.order = order;
		}

		@Override
		public int compare(Map<String, String> o1, Map<String, String> o2) {
			String b1 = o1.get(order);
			String b2 = o2.get(order);
			if (b2 != null) {
				return b1.compareTo(b2);
			}
			return 0;
		}
	}
}
