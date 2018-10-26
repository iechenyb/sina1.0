package com.cyb.io;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月20日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		OutputStream out=new FileOutputStream(new File(""));
		

		OutputStreamWriter osw=new OutputStreamWriter(out,"utf-8");
		
		BufferedWriter bfw=new BufferedWriter(osw);
	}
}
