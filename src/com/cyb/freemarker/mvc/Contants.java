package com.cyb.freemarker.mvc;

public class Contants {
  public static String controllerBaseUri="rest/test";
  public static String modelName="Jyfl";
  public static String poName="Jyfl";
  public static String tableName="tb_jyfl";
  public static String author="iechenyb";
  public static String basePackagePath="com.kiiik.webmanager.khzx";
  public static String ControllerPackagePath=basePackagePath+".controller";
  public static String ServicePackagePath=basePackagePath+".service";
  public static String DaoPackagePath=basePackagePath+".dao";
  public static String PoPackagePath=basePackagePath+".po";
  public static String controllerName=modelName+"Controller";
  public static String serviceName=modelName+"Service";
  public static String daoName=modelName+"Dao";
  public static String fields = "jyjysmc:String:50:交易所名称,jysbs:String:8:交易所标识,"
  		+ "hymc:String:30:合约名称,hydm:String:20:合约代码,kcss:String:20:开仓(手数),"
  		+ "kcje:String:20:开仓(金额),pcss:String:18:平仓(手数),pcje:String:20:开仓(金额),"
  		+ "pjcss:String:20:平今仓(手数),pjcje:String:20:平今仓(金额),czyid:String:40:操作员标识,czymc:String:50:(操作员名称),"
  		+ "czsj:String:14:操作时间,zt:int:0:(记录状态 -1删除非 -1正常)";
  //public static String genBasePath=System.getProperty("user.dir");
  public static String genBasePath="d:/data";
}
