package com.cyb.poi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年10月26日
 */

class ExcelHead{
	String colname;//"name";
	String colShowName;//"姓名";
	List<ExcelHead> children;
	public ExcelHead(){
		
	}
	public ExcelHead(String colname,String colShowName){
		this.colname = colname;
		this.colShowName = colShowName;
		children = new ArrayList<>();
	}
	public ExcelHead(String colname,String colShowName,List<ExcelHead> children){
		this.colname = colname;
		this.colShowName = colShowName;
		this.children = children;
	}
	public String getColname() {
		return colname;
	}
	public void setColname(String colname) {
		this.colname = colname;
	}
	public String getColShowName() {
		return colShowName;
	}
	public void setColShowName(String colShowName) {
		this.colShowName = colShowName;
	}
	public List<ExcelHead> getChildren() {
		return children;
	}
	public void setChildren(List<ExcelHead> children) {
		this.children = children;
	}
	
}
public class AutoExcel {
	Log log = LogFactory.getLog(AutoExcel.class);
	
	//跨两行 当存在children不为空的时候
	static ExcelHead head0 = new ExcelHead("name","姓名");
	
	//根据children的长度，动态的合并单元格
	static ExcelHead head1 = new ExcelHead("fknl","风控能力");
	public static void main(String[] args) throws IOException {
		List<ExcelHead> head1Childs = new ArrayList<ExcelHead>();
		head1Childs.add(new ExcelHead("1","平均风险率"));
		head1Childs.add(new ExcelHead("2","追保发生率"));
		head1Childs.add(new ExcelHead("3","强平发生率"));
		head1Childs.add(new ExcelHead("4","穿仓次数"));
		head1.setChildren(head1Childs);
		
		List<ExcelHead> headData = new ArrayList<ExcelHead>();
		headData.add(head0);
		//headData.add(head1);
	   HSSFWorkbook workbook = new HSSFWorkbook();
	   HSSFSheet sheet = workbook.createSheet("1");
       generateHeader(workbook,sheet,headData);
       FileOutputStream fileOut = 
    		   new FileOutputStream("d:/data/poi/test.xls"); 
       workbook.write(fileOut); 
       fileOut.close(); 
	}
	public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook,boolean isHeader){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        if (isHeader) {
            //style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
        }        
        return style;
    }
	public static  void generateHeader(HSSFWorkbook workbook,HSSFSheet sheet,
			List<ExcelHead> headData){
        HSSFCellStyle style = getCellStyle(workbook,true);
        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);
        for(int i=0;i<headData.size();i++){
            Cell cell = row.createCell(i);
            sheet.setColumnWidth(i, 100);
            cell.setCellValue(headData.get(i).getColShowName());
            cell.setCellStyle(style);
        }
       
    }  
	
	
}
