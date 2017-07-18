package com.cyb.collection.eunm;

public enum ColorMap {
	RED("红色", 1,1), GREEN("绿色", 2,2), BLANK("白色", 3,3), YELLO("黄色", 4,4);
	
	private String name ;
    private int index ;
    private int t; 
    private ColorMap( String name , int index,int t ){
        this.name = name ;
        this.index = index ;
        this.t = t;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}
    
}
