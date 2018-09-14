package com.app.codefactory.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.app.codefactory.infor.AuthFtlInfor;
import com.app.codefactory.infor.PublicInfor;
import com.cyb.date.DateUtil;
import com.cyb.file.FileUtils;
import com.cyb.freemarker.mvc.Contants;
import com.cyb.freemarker.mvc.controller.ControllerGenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class AuthControllerGenerator {
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		try {
			new AuthControllerGenerator().gen();
			//gen("login","controller.ftl",);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 作者 : iechenyb<br>
	 * 方法描述: 根据模块名定义出对应的类名<br>
	 * 创建时间: 2017年7月15日
	 * 
	 * @throws IOException
	 * @throws TemplateException
	 */
	// public static Map<>
	class ClassInfor {
		private String controllerName;
		private String serviceName;
		private String mapperName;
		private String daoName;
		private String poName;
		private String controllerPackageName;// 当前的controller所在的包名
		private String servicePackageName;// 当前的controller所在的包名
		private String poPackageName;// 当前的po所在的包名
		private String daoPackageName;// 当前的dao所在的包名
		private String mapperPackageName;// 当前的mapper所在的包名
		private String requestMapping;// 请求路径 /rest/user
		private String author = PublicInfor.author;// 作者
		private String date;

		public ClassInfor(String module) {
			String newModule = toUpperCaseFirstOne(module);
			poName = requestMapping = module;
			controllerName = newModule + "Controller";// RoleController
			serviceName = newModule + "Service";// RoleService
			mapperName = newModule + "Mapper";// RoleMapper
			daoName = newModule + "Dao";// RoleDao
			poName = newModule;// Role
			controllerPackageName = AuthFtlInfor.CodeMF.format(new Object[] { module, "controller" });
			servicePackageName = AuthFtlInfor.CodeMF.format(new Object[] { module, "service" });
			daoPackageName = AuthFtlInfor.CodeMF.format(new Object[] { module, "dao" });
			mapperPackageName = AuthFtlInfor.CodeMF.format(new Object[] { module, "mapper" });
			poPackageName = AuthFtlInfor.CodeMF.format(new Object[] { module, "po" });
			date = DateUtil.timeToSec(new Date()).toString();
		}

		public String getControllerName() {
			return controllerName;
		}

		public void setControllerName(String controllerName) {
			this.controllerName = controllerName;
		}

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}

		public String getMapperName() {
			return mapperName;
		}

		public void setMapperName(String mapperName) {
			this.mapperName = mapperName;
		}

		public String getDaoName() {
			return daoName;
		}

		public void setDaoName(String daoName) {
			this.daoName = daoName;
		}

		public String getPoName() {
			return poName;
		}

		public void setPoName(String poName) {
			this.poName = poName;
		}

		public String getControllerPackageName() {
			return controllerPackageName;
		}

		public void setControllerPackageName(String controllerPackageName) {
			this.controllerPackageName = controllerPackageName;
		}

		public String getServicePackageName() {
			return servicePackageName;
		}

		public void setServicePackageName(String servicePackageName) {
			this.servicePackageName = servicePackageName;
		}

		public String getPoPackageName() {
			return poPackageName;
		}

		public void setPoPackageName(String poPackageName) {
			this.poPackageName = poPackageName;
		}

		public String getDaoPackageName() {
			return daoPackageName;
		}

		public void setDaoPackageName(String daoPackageName) {
			this.daoPackageName = daoPackageName;
		}

		public String getMapperPackageName() {
			return mapperPackageName;
		}

		public void setMapperPackageName(String mapperPackageName) {
			this.mapperPackageName = mapperPackageName;
		}

		public String getRequestMapping() {
			return requestMapping;
		}

		public void setRequestMapping(String requestMapping) {
			this.requestMapping = requestMapping;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}
	}

	/**
	 * 方法说明：对象转化为Map
	 * 
	 * @param object
	 * @return
	 */
	public Map<?, ?> objectToMap(Object object) {
		return convertBean(object, Map.class);
	}

	/**
	 * 方法说明：将bean转化为另一种bean实体
	 * 
	 * @param object
	 * @param entityClass
	 * @return
	 */
	public static <T> T convertBean(Object object, Class<T> entityClass) {
		if (null == object) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(object), entityClass);
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 说点啥<br>
	 *创建时间: 2017年7月15日
	 *@param module user
	 *@param ftl controller.ftl
	 *@param className UserController.java  UserService.java
	 *@throws IOException
	 *@throws TemplateException
	 */
	public  void gen(String module,String ftl,String type,ClassInfor mobanInfor) throws IOException, TemplateException{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(AuthFtlInfor.ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template temp = cfg.getTemplate(ftl);
		String dir = AuthFtlInfor.srcPath + File.separator
				+ mobanInfor.getControllerPackageName().replace(".", File.separator);
		FileUtils.genFileDir(dir);
		System.out.println(dir);
		OutputStream fos = new FileOutputStream(
				new File(dir, toUpperCaseFirstOne(mobanInfor.getControllerName()+".java")));
		Writer out = new OutputStreamWriter(fos);
		temp.process(objectToMap(mobanInfor), out);
		fos.flush();
		fos.close();
	}
	public void gen() throws IOException, TemplateException, IllegalAccessException, InvocationTargetException {
		String module = "user";// 模块名替换
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(AuthFtlInfor.ftlPath));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		Template temp = cfg.getTemplate(AuthFtlInfor.controllerFtl);
		ClassInfor moban = new ClassInfor(module);
		String dir = AuthFtlInfor.srcPath + File.separator
				+ moban.getControllerPackageName().replace(".", File.separator);
		FileUtils.genFileDir(dir);
		System.out.println(dir);
		OutputStream fos = new FileOutputStream(
				new File(dir, toUpperCaseFirstOne(moban.getControllerName() + ".java")));
		Writer out = new OutputStreamWriter(fos);
		temp.process(objectToMap(moban), out);
		fos.flush();
		fos.close();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
}