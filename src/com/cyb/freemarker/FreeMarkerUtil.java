package com.cyb.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.cyb.collection.CollectionFactory;
import com.cyb.file.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {
	public static void main(String[] args) {
	    CollectionFactory.build(10);
	    Map<String,Object> root=new HashMap<String,Object>();
	    root.put("user", CollectionFactory.getUser());
	    root.put("nums", CollectionFactory.getList());
	    root.put("users", CollectionFactory.users);
	    root.put("map", CollectionFactory.map);
	    String templatesPath=FileUtils.getAbsolutePathAtClass(FreeMarkerUtil.class);  
	    String mobanName="user.ftl"; 
	    String htmlFile=templatesPath+"user.html"; 
	    String templateEncoding="UTF-8";
	     //这里的编码方式是为了以后可能还要输入韩文或者日文等信息为了灵活//也可以在工具类中写死一种
	    FreeMarkerUtil.analysisTemplate(templatesPath, mobanName,htmlFile,templateEncoding, root);
	    System.out.println("生成文件："+htmlFile);
	}
	@SuppressWarnings("deprecation")
	public static void analysisTemplate(String templatePath,
			String templateName, String fileName, String templateEncoding,
			Map<?, ?> root) {
		try {
			// 创建Configuration对象
			Configuration config = new Configuration();
			// 指定模板路径
			File file = new File(templatePath);
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(file);
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 获取模板,并设置编码方式，编码必须要与页面中的编码格式一致
			Template template = config.getTemplate(templateName,
					templateEncoding);
			// 合并数据模型与模板
			FileOutputStream fos = new FileOutputStream(fileName);
			// 输出到页面
			Writer out = new OutputStreamWriter(fos, templateEncoding);
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}