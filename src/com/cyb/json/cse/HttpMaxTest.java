package com.cyb.json.cse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.cyb.collection.common.CollectionFactory;
import com.cyb.collection.po.Bean;
import com.cyb.file.FileReadRaipyTest;
import com.cyb.file.FileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月13日 -Xms1024M -Xmx1024M
 */
public class HttpMaxTest {
	static Log log = LogFactory.getLog(HttpMaxTest.class);
	public static void main(String[] args) throws IOException {
		CollectionFactory.buildBeans(150*10000);//10 5m  50 20m 100 50m
	    FileUtils.overideString2File(com.alibaba.fastjson.JSONArray.toJSONString(CollectionFactory.getBeans()), "d:/data/jsonlist80.txt");	     
		/*writeBeanReadList("jsonlist5");
		log.info("----------------------------------");
		writeBeanReadList("jsonlist50");
		log.info("----------------------------------");
		writeBeanReadList("jsonlist80");*/
		log.info("download begin ...");
		//FileUtils.getJsonFromUrl("http://localhost:63342/cyb/data/jsonlist50.txt", "", "UTF-8");
		//InputStream in =FileUtils.getInstreamFromUrl("http://localhost:63342/cyb/data/jsonlist50.txt", "", "UTF-8");
		/*byte[] data = FileUtils.readInputStream(in);
		parseListBean(new String(data));*/
		//FileUtils.copyFileByStream(in, "d:/data/tmp/lst50.txt");
		log.info("download end ...");
	}
	public static void parseListBean(String jsonStr){
		 List<Bean> lst = JSON.parseArray(jsonStr, Bean.class);
	     log.info(lst.size()+",get json value :"+lst.get(6).getName());
	}
	public static void writeBeanReadList(String fileName) throws IOException{
		 log.info("read json file begin..."); 
		 String strList=FileReadRaipyTest.useFileIStream("d:/data/"+fileName+".txt").toString();
	     log.info("parse json data begin...");
	     List<Bean> lst = JSON.parseArray(strList, Bean.class);
	     log.info(lst.size()+",get json value :"+lst.get(6).getName());
	}
	public static void writeBeanReadBean() throws IOException{
		 log.info("read json file begin..."); 
	     FileUtils.overideString2File(com.alibaba.fastjson.JSONArray.toJSONString(new Bean("aaa","111")), "d:/data/jsonbean.txt");
	     String str=FileUtils.readContentFromFile("d:/data/json.txt").toString();
	     log.info("read json file begin..."); 
	     Bean bean = JSON.parseObject(str, Bean.class);
	     log.info("get json value :"+bean.getName());
	}
}
