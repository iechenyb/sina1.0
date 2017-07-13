package com.cyb.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class PoiTest {
    
     /*excel column formate:column_#_width, excel涓瘡锟�?鍒楃殑鍚嶇О*/
    public static final String[] RECORES_COLUMNS = new String[]{
            "User Name_#_3000",
            "Address_#_7000"           
            };
    /*the column will display on xls files. must the same as the entity fields.瀵瑰簲涓婇潰鐨勫瓧锟�?.*/
    public static final String[] RECORES_FIELDS = new String[]{
        "name","address"
    };
    
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
    	generXls();
    }
     public static void generXls(){
	   try{
		   List<User> users = new ArrayList<User>();
	       for(int i=0; i<10;i++){
	           User u = new User();
	           u.setAddress("address :" + i);
	           u.setName("name: "+ i);
	           u.setAge(i);
	           users.add(u);
	       }
	       //瀹為檯椤圭洰涓紝杩欎釜list 浼拌鏄粠鏁版嵁搴撲腑寰楀埌锟�?
	       HSSFWorkbook workbook = new HSSFWorkbook();
	       GenerExcelUtil<User> userSheet = new GenerExcelUtil<User>();
	       userSheet.creatAuditSheet(workbook, "user sheet xls",users, RECORES_COLUMNS, RECORES_FIELDS);
	       FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir")+"/src/com/cyb/poi/user.xls"); 
	       System.out.println("gener path of file is "+System.getProperty("user.dir")+"/src/com/cyb/poi/user.xls");
	       workbook.write(fileOut); 
	       fileOut.close(); 
	       
	       
	       GenerExcelUtil<User> userSheet1 = new GenerExcelUtil<User>();
	       userSheet1.creatAuditSheet(workbook, "user sheet xls2",users, RECORES_COLUMNS, RECORES_FIELDS);
	       FileOutputStream fileOut1 = new FileOutputStream(System.getProperty("user.dir")+"/src/com/cyb/poi/user.xls"); 
	       System.out.println("gener path of file is "+System.getProperty("user.dir")+"/src/com/cyb/poi/user.xls");
	       workbook.write(fileOut1); 
	       fileOut1.close(); 
	       
	       
	       String path = System.getProperty("user.dir")+"/src/com/cyb/poi/user.xls";
	       ReadExcelUtils.displayExcel(path);
	       System.out.println("*******************************");
	       FileInputStream is = new FileInputStream(new File(path));
	       ReadExcelUtils.readExcelContent(is);
       }catch(Exception e){
    	   e.printStackTrace();
       }
   }
}
