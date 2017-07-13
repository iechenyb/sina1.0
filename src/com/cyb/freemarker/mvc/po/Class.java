package com.cyb.freemarker.mvc.po;
public class Class {
	private String packagename;  
    private String classname;  
    private String[] imports;  
    private String tableName;
    public Class() {}  
    public Class(String classname, String[] imports,String packagename,String tableName) {  
        super();  
        this.classname = classname;  
        this.imports = imports;  
        this.packagename=packagename;  
        this.tableName = tableName;
    }  
   
    public String getClassname() {  
        return classname;  
    }  
   
    public void setClassname(String classname) {  
        this.classname = classname;  
    }  
   
    public String[] getImports() {  
        return imports;  
    }  
   
    public void setImports(String[] imports) {  
        this.imports = imports;  
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
