package com.app.codefactory.infor;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 作者 : iechenyb<br>
 * 类描述: auth模板的信息<br>
 * 创建时间: 2018年9月11日
 */
public class AuthFtlInfor {
	public static String ftlPath = "D:\\data\\codefactory\\auth\\ftl\\";// 模板目录
	public static String controllerFtl = "controller.ftl";
	public static String ServiceFtl = "service.ftl";
	public static String DaoFtl = "dao.ftl";
	public static String MapperFtl = ftlPath + "controller.ftl";
	public static String srcPath = "D:\\data\\codefactory\\auth\\src";// 代码输出目录

	public static String modelName = "Jyfl";
	//0-模块名 1 是dao po 等
	public static String ModulePackagePath ="com.cyb.web.{0}.{1}";//role.controller
	public static String ModulePackageImportName ="com.cyb.web.{0}.{0}{1}";//com.cyb.web.user.userController
	/*public static String ControllerPackagePath = moduleBasePackagePath + ".controller";
	public static String ServicePackagePath = moduleBasePackagePath + ".service";
	public static String MapperPackagePath = moduleBasePackagePath + ".po";
	public static String DaoPackagePath = moduleBasePackagePath + ".dao";
	public static String PoPackagePath = moduleBasePackagePath + ".po";
	public static MessageFormat controllerMF = new MessageFormat(AuthFtlInfor.ControllerPackagePath, Locale.US);
	public static MessageFormat serviceMF = new MessageFormat(AuthFtlInfor.ServicePackagePath, Locale.US);
	public static MessageFormat daoMF = new MessageFormat(AuthFtlInfor.DaoPackagePath, Locale.US);
	public static MessageFormat mapperMF = new MessageFormat(AuthFtlInfor.MapperPackagePath, Locale.US);
	public static MessageFormat poMF = new MessageFormat(AuthFtlInfor.PoPackagePath, Locale.US);
    */
	public static MessageFormat CodeMF = new MessageFormat(AuthFtlInfor.ModulePackagePath, Locale.US);
	public static MessageFormat ServiceImportMF = new MessageFormat(AuthFtlInfor.ModulePackageImportName, Locale.US);
}
