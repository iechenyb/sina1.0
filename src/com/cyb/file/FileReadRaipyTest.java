package com.cyb.file;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月13日 -Xms1024M -Xmx1024M
 */
public class FileReadRaipyTest {
	Log log = LogFactory.getLog(FileReadRaipyTest.class);
	static String filePath = "d:/data/jsonList50.txt";
	public static void main(String[] args) {
		new FileReadRaipyTest().readTimeComPare();
	}
	public void readTimeComPare(){
		log.info("====start======");
        useFileIStream(filePath);
      /*  useBufferIStream();
        useBufferReader();*/
        log.info("=====end========");
        System.exit(0);
    }
    // 使用FileInputStream
    public static String useFileIStream(String filePath) {
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int cnt = 0;
            StringBuffer sb = new StringBuffer();
            while((cnt=fis.read(buffer)) != -1) {
              sb.append(new String(buffer, 0, cnt));
            }
            fis.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace(); 
            return "";
        }
       
    }
    // 使用BufferedInputStream
    public static String useBufferIStream(){

        try {
            File file  = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int cnt = 0;
            StringBuffer sb = new StringBuffer();
            while((cnt=bis.read(buffer)) != -1) {
            	 sb.append(new String(buffer, 0, cnt));
            }
            bis.close();
            return sb.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }


    }
    //使用BufferedReader 
    public static String useBufferReader(){

        try {
            File file  = new File(filePath);
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            int cnt = 0;
            char[] buffer = new char[1024];
            StringBuffer sb = new StringBuffer();
            while((cnt = bReader.read(buffer)) != -1) {
            	 sb.append(new String(buffer, 0, cnt));
            }
            bReader.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
