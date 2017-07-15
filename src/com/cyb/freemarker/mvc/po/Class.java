package com.cyb.freemarker.mvc.po;

import java.util.Map;

public class Class {
	private String packagename;//com.kiiik.webmanager.user.po  
    private String classname; //Jyfl 
    private String tableName; //tb_name 
    public Class() {}  
    public Class(String classname,String packagename,String tableName) {  
        super();  
        this.classname = classname;  
        this.packagename=packagename;  
        this.tableName = tableName;
    }  
   
    public String getClassname() {  
        return classname;  
    }  
   
    public void setClassname(String classname) {  
        this.classname = classname;  
    }  
   
    public String getPackagename() {  
        return packagename;  
    }  
   
    public void setPackagename(String packagename) {  
        this.packagename = packagename;  
    }

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	} 
    
}
