package com.app.stock;

import java.io.IOException;

import com.cyb.UUIDUtils;
import com.cyb.file.FileUtils;
import com.cyb.h2.H2Manager;

public class StockNewsUtils {
	public static void main(String[] args) throws Exception {
		H2Manager.start();
		new StockDbUtils("app");
		for(int i=1;i<999999;i++){
			int len = String.valueOf(i).length();
			String code = getZeros(6-len)+i;
			String orginStr = FileUtils.getJsonFromNet("http://hq.sinajs.cn/list=sh"+code, null,"gbk");//.replaceAll("\"", "").replaceAll(";", "");
			String left=orginStr.split("=")[0];
			String right = orginStr.split("=")[1];
			if(right.length()>4){
		      Stock stock =new Stock();
		      stock.setId(UUIDUtils.getUUID());
		      stock.setName(left.split("=")[0].substring(left.length()-6, left.length()));
		      stock.setCode(right.split(",")[0].replace("\"", ""));
		      stock.setExchange("sh");
		      StockDbUtils.dbUtils.update("delete from stock where code='"+stock.getCode()+"' and exchange='"+stock.getExchange()+"'");
		      StockDbUtils.dbUtils.save(stock);
		      System.out.println(left.split("=")[0].substring(left.length()-6, left.length())+","+right.split(",")[0].replace("\"", ""));
			}			
		}
		H2Manager.stop();
		System.exit(0);
	}
	
	public static  String getZeros(int len){
		StringBuffer str = new StringBuffer();
		for(int i=1;i<=len;i++){
			str.append("0");
		}
		return str.toString();
	}
}
