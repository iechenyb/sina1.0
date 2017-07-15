package com.cyb.freemarker.mvc.base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.file.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class MvcGenerator {
	private static Log log = LogFactory.getLog(MvcGenerator.class);
    public static void gen(Map<String, Object> root,String name) throws IOException, TemplateException{
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File( FileUtils.getAbsolutePathAtClass(MvcGenerator.class)));   
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template temp = cfg.getTemplate(name+".ftl");
        String dir = System.getProperty("user.dir")+File.separator+"src"+File.separator+root.get("packageName").toString().replace(".", File.separator);
        FileUtils.genFileDir(dir);
        OutputStream fos = new  FileOutputStream( new File(dir, root.get("fileName")+".java"));
        Writer out = new OutputStreamWriter(fos);
        temp.process(root, out);
        fos.flush();  
        fos.close();
        log.info("gen "+".java"+" code success!");
    }
}