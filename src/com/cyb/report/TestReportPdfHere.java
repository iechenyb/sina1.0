package com.cyb.report;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月31日
 */
public class TestReportPdfHere {
	Log log = LogFactory.getLog(TestReportPdfHere.class);
	public static void main(String[] args) {  
        Map parameters=new HashMap();  
        ByteArrayOutputStream outPut=new ByteArrayOutputStream();  
        FileOutputStream outputStream=null;  
        File file=new File("F:/Temp/report.xls");  
        String reportModelFile="C:/Users/jack/report2.jasper";  
          
        try {  
            JasperPrint jasperPrint=JasperFillManager.fillReport(reportModelFile,  
                    parameters,new ReportDataSource("男"));  
            JRAbstractExporter exporter=new JRPdfExporter();  
              
            //创建jasperPrint  
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
            //生成输出流  
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);  
            //屏蔽copy功能  
            exporter.setParameter(JRPdfExporterParameter.IS_ENCRYPTED,Boolean.TRUE);  
            //加密  
            exporter.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY,Boolean.TRUE);  
            exporter.exportReport();  
            outputStream=new FileOutputStream(file);  
            outputStream.write(outPut.toByteArray());  
        } catch (JRException e) {  
            e.printStackTrace();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                outPut.flush();  
                outPut.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
