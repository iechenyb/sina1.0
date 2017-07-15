package com.cyb.xml.usecase.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cyb.date.DateUtil;
import com.cyb.freemarker.mvc.base.MvcGenerator;

import freemarker.template.TemplateException;
/**
 * 作者 : iechenyb
 * 功能描述: 说点啥
 * 创建时间: 2017年7月15日13:15:52
 */
public class MvcXmlUtils {
	private static Log log = LogFactory.getLog(MvcXmlUtils.class);
	public static String author = "iechenyb";
	public static String basePackagePath = "";
	static List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

	@SuppressWarnings("unchecked")
	public static void readXml(String src) throws Exception {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(src));
		Element root = document.getRootElement();
		List<Element> mvcs = root.elements("mvc");
		basePackagePath = root.attribute("basePackagePath").getValue();
		log.info("mvc包根路径：" + basePackagePath);
		for (Element mvc : mvcs) {
			boolean update = Boolean.valueOf(mvc.attribute("update").getValue());
			if (update) {
				try {
					anaMvc(mvc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void anaMvc(Element e) throws IOException, TemplateException {
		String modelName = e.attribute("modelName").getValue();
		String packagePath = e.attribute("packagePath").getValue();
		log.info("模块路径："+basePackagePath +"."+ packagePath);
		String ControllerPackagePath = basePackagePath +"."+ packagePath+ ".controller";
		String ServicePackagePath = basePackagePath +"."+ packagePath+  ".service";
		String DaoPackagePath = basePackagePath +"."+ packagePath+  ".dao";
		String PoPackagePath = basePackagePath +"."+ packagePath+  ".po";
		String controllerName = modelName + "Controller";
		String serviceName = modelName + "Service";
		String daoName = modelName + "Dao";
		String poName= e.attribute("poname").getValue();
		String controllerBaseUri = e.attribute("baseuri").getValue();
		Map<String, Object> rootController = new HashMap<String, Object>();
		Map<String, Object> rootService = new HashMap<String, Object>();
		Map<String, Object> rootDao = new HashMap<String, Object>();
		Map<String, Object> rootPub = new HashMap<String, Object>();
		rootPub.put("author", author);
		rootPub.put("date", DateUtil.descTimeToSec());
		{
			rootController.put("fileName",controllerName);
			rootController.put("packageName",ControllerPackagePath);
			rootController.put("modelName", modelName);
			rootController.put("servicePackageName", ServicePackagePath+"."+serviceName);
			rootController.put("po", poName);
			rootController.put("poPackageName", PoPackagePath+"."+poName);
			rootController.put("serviceName", serviceName);
			rootController.put("basePath", controllerBaseUri);
			rootController.putAll(rootPub);
			MvcGenerator.gen(rootController, "controller");
		}
		{
			rootService.put("fileName",serviceName);
			rootService.put("packageName",ServicePackagePath);
			rootService.put("modelName", modelName);
			rootService.put("po", poName);
			rootService.put("poPackageName", PoPackagePath+"."+poName);
			rootService.put("daoPackageName", DaoPackagePath+"."+daoName);
			rootService.putAll(rootPub);
			MvcGenerator.gen(rootService, "service");
		}
		{
			rootDao.put("fileName",daoName);
			rootDao.put("packageName", DaoPackagePath);
			rootDao.put("modelName", modelName);
			rootDao.put("po", poName);
			rootDao.put("poPackageName", PoPackagePath+"."+poName);
			rootDao.putAll(rootPub);
			MvcGenerator.gen(rootDao, "dao");
		}
	}
	public static void main(String[] args) throws Exception {
		String basePath = System.getProperty("user.dir") + "/src/com/cyb/xml/usecase/mvc/";
		MvcXmlUtils.readXml(basePath + "ProjectNameMvc.xml");
	}
}
