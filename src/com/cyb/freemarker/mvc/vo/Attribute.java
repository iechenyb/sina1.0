package com.cyb.freemarker.mvc.vo;

public class Attribute {
	private String name;  
    private String type;  
    private int len;
    public Attribute() {  
    }  
   
    public Attribute(String name, String type,int len) {  
        this.name = name;  
        this.type = type;  
        this.len = len;
    }  
   
    public String getName() {  
        return name;  
    }  
   
    public void setName(String name) {  
        this.name = name;  
    }  
   
    public String getType() {  
        return type;  
    }  
   
    public void setType(String type) {  
        this.type = type;  
    }

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}  
    
}
