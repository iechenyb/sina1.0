package com.cyb.freemarker.mvc.vo;

import java.util.Map;

public class Attribute {
	private String name;  
    private String type;  
    private int len;
    private String comment;
    private boolean nullable;
    public Attribute() {  
    }  
   
    public Attribute(String name, String type,int len,String comment) {  
        this.name = name;  
        this.type = type;  
        this.len = len;
        this.comment = comment;
        this.nullable=false;
    }  
    public Attribute(Map<String,Object> map) {  
        this.name = map.get("name").toString();  
        this.type = map.get("type").toString();  
        this.len =  Integer.valueOf(map.get("length").toString());  
        this.comment = map.get("comment").toString(); 
        this.nullable = Boolean.valueOf(map.get("nullable").toString()); 
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}  
    
	
}
