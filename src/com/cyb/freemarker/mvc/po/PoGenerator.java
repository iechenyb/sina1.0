package com.cyb.freemarker.mvc.po;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.cyb.date.DateUtil;
import com.cyb.file.FileUtils;
import com.cyb.freemarker.mvc.Contants;
import com.cyb.freemarker.mvc.vo.Attribute;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PoGenerator {
	private final static String TEMPLATE_NAME="po.ftl";  
    private static Configuration cfg;  
    private static Properties ploader;  
   /** 
     * 初始化工作 
     */  
    @SuppressWarnings("deprecation")
	public static void init() throws Exception {  
        // 负责管理的实例创建+设置模板文件所在的目录  
        cfg = new Configuration();  
        // load资源的实例创建  
        ploader = new Properties();  
        cfg.setDirectoryForTemplateLoading(new File(  
                FileUtils.getAbsolutePathAtClass(PoGenerator.class)));  
        String path = FileUtils.getAbsolutePathAtClass(PoGenerator.class);
        ploader.load(new FileInputStream(path+"bean.properties"));  
    }  
   
      
    public static void process() throws IOException, TemplateException {  
          
        //1.load信息  
        Class clazz=loadClass();  
        List<Attribute> attributes=loadAttr();  
        //2.向root中放入模版中所需信息  
        Map<String, Object> root = new HashMap<String, Object>();  
        root.put("clazz",clazz);  
        root.put("attributes",attributes);  
        //3.将模版进行指定文件的输出  
        write(root);  
          
    }  
    /* 
     * 执行入口 
     */  
    public static void main(String[] args) throws Exception {  
        PoGenerator.init();  
        PoGenerator.process();  
    }  
      
    /* 
     * 加载类信息：包名，类名，import 
     */  
    public static Class loadClass() {  
        String packagename=Contants.PoPackagePath;//ploader.getProperty("packagename");  
        String classname=Contants.poName;//ploader.getProperty("classname");  
        //String values=ploader.getProperty("imports");  
        String tableName=Contants.tableName;//ploader.getProperty("tableName");  
        //String[] imports=values.split(",");  
        Class clazz = new Class(classname,packagename,tableName);  
        return clazz;  
    }  
    /* 
     * 加载属性：属性名，属性类型 
     */  
    public static List<Attribute> loadAttr() {  
        List<Attribute> attributes = new ArrayList<Attribute>();  
        String values=Contants.fields;//ploader.getProperty("attribute");  
        String[] strs=values.split(",");  
        for (String s : strs) {  
            String[] sp=s.split(":");  
            attributes.add(new Attribute(sp[0],sp[1],Integer.valueOf(sp[2]),sp[3]));  
        }  
        return attributes;  
    }  
    /* 
     * 将模版进行指定文件的输出 
     */  
    public static void write(Map<String, Object> root) throws IOException, TemplateException{  
    	Class cls = (Class) root.get("clazz");
    	root.put("date", DateUtil.descTimeToSec());
    	root.put("author","iechenyb");
        Template t = cfg.getTemplate(TEMPLATE_NAME);  
        String dir = System.getProperty("user.dir")+File.separator+"src"+File.separator+cls.getPackagename().replace(".", File.separator);
        FileUtils.genFileDir(dir);
        System.out.println(dir);
        OutputStream out = new FileOutputStream(new File(dir,cls.getClassname()+".java"));  
        t.process(root, new OutputStreamWriter(out));  
    }  
}
