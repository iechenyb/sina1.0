package com.cyb.collection.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月28日
 */
public class JAVA7 {
	Log log = LogFactory.getLog(JAVA7.class);

	@SuppressWarnings("unused")
	public static void main(String[] args) throws NumberFormatException, FileNotFoundException, IOException {
		/*
		 * List<String> list = ["item"]; String item = list[0]; Set<String> set
		 * = {"item"};
		 * 
		 * Map<String, Integer> map = {"key":1}; int value = map["key"];
		 */
		/*Set<String> set = {"item"};    
		Map<String, Integer> map = {"key":1}; int value = map["key"];*/
		try (BufferedReader br = new BufferedReader(new FileReader(""))) {
			br.readLine();
		}
		Map<String, List<String>> anagrams = new HashMap<>();
		int number = 12_123_456;
		int binary = 0b1001_1001;
		byte aByte = (byte) 0b001;
		short aShort = (short) 0b010;
		String s = "456";
		switch (s) {
		case "quux":
			// fall-through
		case "foo":
		case "bar":
			break;
		case "baz":
			// fall-through
		default:
			break;
		}
		try {
			Integer.parseInt("Hello");
		} catch (RuntimeException e) { // 使用'|'切割，多个类型，一个对象e

		}
		try (InputStream input = new FileInputStream(""); 
			 OutputStream output = new FileOutputStream("")) {
			byte[] buffer = new byte[8192];
			int len = -1;
			while ((len = input.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
		}
	}
}
