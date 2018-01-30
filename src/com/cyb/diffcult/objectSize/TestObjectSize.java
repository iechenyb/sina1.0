package com.cyb.diffcult.objectSize;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * http://blog.csdn.net/tianqishu11/article/details/78823179
 * 作者 : iechenyb<br>
 * -Xms1m -Xmx1m -XX:+PrintGCDetails
 * 类描述: 测量一个object的大小<br>
 * 创建时间: 2018年1月30日
 */
public class TestObjectSize {
	Log log = LogFactory.getLog(TestObjectSize.class);
	int i = 0;
	public  void testObjectSize() {
		List<Object> list = new ArrayList<>();
		try {
			while (true) {
				list.add(new Object());
				i++;
			}
		} catch (Exception e) {
			System.out.println(i);
			e.printStackTrace();
		} finally {
			System.out.println(i);
		}
	}
	public static void main(String[] args) {
		//代理计算出的object大小为16字节
		//new TestObjectSize().testObjectSize();
		System.out.println("一个object的大小为"+1024*1024*5/240097);//21
		//5m = 1024*1024*5
	}
}
