package com.cyb.xml.usecase.po;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import com.cyb.freemarker.mvc.po.Class;
import com.cyb.freemarker.mvc.po.PoGenerator;
import com.cyb.freemarker.mvc.vo.Attribute;

import freemarker.template.TemplateException;
public class PoXmlUtils {
	static String baseSrcPath="";
	static Log log = LogFactory.getLog(PoXmlUtils.class);
	static List<Map<String,Object>> data= new ArrayList<Map<String,Object>>();
	@SuppressWarnings("unchecked")
	public static void readXml(String src) throws Exception{
		SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(src));
        Element root = document.getRootElement();
        List<Element> pos = root.elements("po");
        baseSrcPath = root.attribute("basePackagePath").getValue();
        log.info("po包根路径："+baseSrcPath);
        PoGenerator.init();
        for (Element po:pos)
        {
        	boolean update = Boolean.valueOf(po.attribute("update").getValue());
        	if(update){
        		try {
					PoGenerator.write(anaPo( po ));
				} catch (TemplateException e) {
					e.printStackTrace();
				}
        	}
        }
	} 
	@SuppressWarnings("unchecked")
	public static Map<String, Object> anaPo( Element e ){
		Map<String, Object> root = new HashMap<String, Object>(); 
		Map<String,Object> tmpAttr = new HashMap<String,Object>();
		String packagename = baseSrcPath+"."+e.attribute("packagename").getValue();
		Class clazz = new Class(e.attribute("name").getValue(),packagename,e.attribute("tablename").getValue());
		List<Attribute> attributes = new ArrayList<Attribute>();  
		log.info(baseSrcPath+"."+packagename);
		List<Element> propertys = e.elements();
		for(Element property:propertys){
			List<Element> fields = property.elements();
			for(Element field:fields){
				 String attName = field.getName() ;
				 log.info("po Attr: " + attName+ "="+ field.getText());
				 tmpAttr.put(attName, field.getText());
			}
			attributes.add(new Attribute(tmpAttr));
		}
		root.put("clazz", clazz);
		root.put("attributes",attributes);
		return root;
		
	}

	 public static void main(String[] args) throws Exception
    {
		 String basePath=System.getProperty("user.dir")+"/src/com/cyb/xml/usecase/po/";
		 PoXmlUtils.readXml(basePath+"ProjectNamePo.xml");//生成xml
    }
}
