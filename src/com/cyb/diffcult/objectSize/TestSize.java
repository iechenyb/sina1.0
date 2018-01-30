package com.cyb.diffcult.objectSize;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//http://blog.csdn.net/aaa1117a8w5s6d/article/details/8254922
public class TestSize {
	Log log = LogFactory.getLog(TestSize.class);
	public static void main(String[] args) {
		System.out.println("Interger size:" + MySizeOf.sizeOf(new Integer(1)));
		System.out.println("String size:" + MySizeOf.sizeOf(new String("a")));
		System.out.println("String fullsize:" + MySizeOf.fullSizeOf(new String("a")));
		System.out.println("char size:" + MySizeOf.sizeOf(new char[1]));
		System.out.println("ArrayList size:" + MySizeOf.sizeOf(new ArrayList<>()));
		System.out.println("Object size:" + MySizeOf.sizeOf(new Object()));
		System.out.println("Long size :" + MySizeOf.sizeOf(new Long(10000000000L)));
	}
}
