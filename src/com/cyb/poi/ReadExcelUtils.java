package com.cyb.poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ReadExcelUtils {
  
    private static POIFSFileSystem fs;      
    private static HSSFWorkbook wb;      
    private static HSSFSheet sheet;      
    private static HSSFRow row;      
    /**    
     * 读取Excel表格表头的内容    
     * @param InputStream    
     * @return String 表头内容的数组    
     *     
     */     
    public static String[] readExcelTitle(InputStream is) {      
        try {      
            fs = new POIFSFileSystem(is);      
            wb = new HSSFWorkbook(fs);      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        sheet = wb.getSheetAt(0);      
        row = sheet.getRow(0);      
        //标题总列数      
        int colNum = row.getPhysicalNumberOfCells();      
        String[] title = new String[colNum];      
        for (int i=0; i<colNum; i++) {      
            title[i] = getStringCellValue(row.getCell((short) i));      
        }      
        return title;      
    }      
          
    /**    
     * 读取Excel数据内容    
     * @param InputStream    
     * @return Map 包含单元格数据内容的Map对象    
     */     
    public static Map<Integer,String> readExcelContent(InputStream is) {      
        Map<Integer,String> content = new LinkedHashMap<Integer,String>();      
        String str = "";      
        try {      
            fs = new POIFSFileSystem(is);      
            wb = new HSSFWorkbook(fs);      
        } catch (IOException e) {      
            e.printStackTrace();      
        }      
        sheet = wb.getSheetAt(0);      
        //得到总行数      
        int rowNum = sheet.getLastRowNum();      
        row = sheet.getRow(0);      
        int colNum = row.getPhysicalNumberOfCells();      
        //正文内容应该从第二行开始,第一行为表头的标题      
        for (int i = 1; i <= rowNum; i++) {      
            row = sheet.getRow(i);      
            int j = 0;      
            while (j<colNum) {      
        //每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据      
        //也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean      
                //str += "col="+j+",cell="+getStringCellValue(row.getCell((short) j)).trim() + "#";
            	str += getStringCellValue(row.getCell((short) j)).trim() + "#"; 
            	System.out.println("("+i+","+j+")"+getStringCellValue(row.getCell((short) j)).trim());
                j ++;  
                
            }  
            
            content.put(i, str);      
            str = "";      
        }      
        return content;      
    }    
          
    /**    
     * 获取单元格数据内容为字符串类型的数据    
     * @param cell Excel单元格    
     * @return String 单元格数据内容    
     */     
    public static String getStringCellValue(HSSFCell cell) {      
        String strCell = "";      
        try {
			switch (cell.getCellType()) {      
			case HSSFCell.CELL_TYPE_STRING:      
			    strCell = cell.getStringCellValue();      
			    break;      
			case HSSFCell.CELL_TYPE_NUMERIC:      
			    strCell = String.valueOf(cell.getNumericCellValue());      
			    break;      
			case HSSFCell.CELL_TYPE_BOOLEAN:      
			    strCell = String.valueOf(cell.getBooleanCellValue());      
			    break;      
			case HSSFCell.CELL_TYPE_BLANK:      
			    strCell = "";      
			    break;      
			default:      
			    strCell = "";      
			    break;      
			}      
			if (strCell.equals("") || strCell == null) {      
			    return "";      
			}      
			if (cell == null) {      
			    return "";      
			}
		} catch (Exception e) {
			return "";
		}      
        return strCell;      
    }      
          
    /**    
     * 获取单元格数据内容为日期类型的数据    
     * @param cell Excel单元格    
     * @return String 单元格数据内容    
     */     
    private String getDateCellValue(HSSFCell cell) {      
        String result = "";      
        try {      
            int cellType = cell.getCellType();      
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {      
                Date date = cell.getDateCellValue();      
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)       
                + "-" + date.getDate();      
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {      
                String date = getStringCellValue(cell);      
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();      
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {      
                result = "";      
            }      
        } catch (Exception e) {      
            System.out.println("日期格式不正确!");      
            e.printStackTrace();      
        }      
        return result;      
    }      
    public void createExcel() throws IOException{}
    
    public static void displayExcel(String src){
    	 try {      
             //对读取Excel表格标题测试      
             InputStream is = new FileInputStream(src);      
             String[] title = ReadExcelUtils.readExcelTitle(is);
            
             System.out.println("获得Excel表格的标题:");      
             for (String s : title) {      
                 System.out.print(s + " ");      
             }      
             System.out.println();     
             //对读取Excel表格内容测试      
             InputStream is2 = new FileInputStream(src);      
             Map<Integer,String> map = ReadExcelUtils.readExcelContent(is2);      
             System.out.println("获得Excel表格的内容:");      
             for (int i=1; i<=map.size(); i++) {      
                 System.out.println(map.get(i));      
             }      
         } catch (FileNotFoundException e) {      
             System.out.println("未找到指定路径的文件!");      
             e.printStackTrace();      
         }  
    }
    public static void main(String[] args) {      
    	//String path = System.getProperty("user.dir")+"/src/com/cyb/poi/gnqd.xlsx";
    	String path="D:\\chenyb\\1BMS系统\\bms功能分拆方案1.0.xlsx";
    	displayExcel(path);
    }      
}   