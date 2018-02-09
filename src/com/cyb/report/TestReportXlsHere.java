package com.cyb.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;

import com.cyb.date.DateUtil;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 * 作者 : iechenyb<br>
 * 类描述: 但数据源测试<br>
 * 创建时间: 2018年1月31日
 */
public class TestReportXlsHere {
	Log log = LogFactory.getLog(TestReportXlsHere.class);

	public static void main(String[] args) {
		Map parameters = new HashMap();
		String path="D:\\data\\report\\";
		ByteArrayOutputStream outPut = new ByteArrayOutputStream();
		FileOutputStream outputStream = null;
		File file = new File(path+"gen\\"+DateUtil.descTimeToSec()+".xls");
		String reportModelFile = path+"first.jasper";
		HSSFCellStyle x;
		HSSFColor y;
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(reportModelFile, parameters,
					new ReportDataSource("男"));

			JRAbstractExporter exporter = new JRXlsExporter();
			// 创建jasperPrint
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			// 生成输出流
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outPut);
			// 去除两行之前的空白
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			// 设置所有页只打印到一个Sheet中
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			// 设置Excel表格的背景颜色为默认的白色
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.exportReport();
			outputStream = new FileOutputStream(file);
			outputStream.write(outPut.toByteArray());
			System.out.println("数据生成成功！");
			System.out.println("请查看文件："+file);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outPut.flush();
				outPut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
//http://blog.csdn.net/acmman/article/details/51814243
//http://blog.csdn.net/acmman/article/details/51828558
