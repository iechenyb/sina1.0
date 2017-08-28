package com.cyb.file;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月13日 -Xms1024M -Xmx1024M
 */
public class FileReadRaipyTest {
	static Log log = LogFactory.getLog(FileReadRaipyTest.class);
	static String filePath = "d:/data/jsonlist80.txt";
	public static void main(String[] args) {
		log.info(readFileByLines(filePath));
		//new FileReadRaipyTest().readTimeComPare();
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
    
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static StringBuffer readFileByLines(String path) {
    	StringBuffer content = new StringBuffer("");
        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                content.append(tempString.trim()+",");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
}
