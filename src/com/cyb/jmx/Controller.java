package com.cyb.jmx;

import java.io.FileNotFoundException;

import com.cyb.IKAnalyzer.KAnalyzerExtendDemo;

public class Controller implements ControllerMBean {  
	  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getAge() {  
        return this.age;  
    }  
    public void setAge(String age) {  
        this.age = age;  
    }  
  
    public String getName() {  
        return this.name;  
    }  
      
    private String name;  
    private String age;  
  
    public String status() {  
        return "this is a Controller MBean,name is " + this.name+",age is "+age;  
    }  
  
    public void start(String czy) { 
    	System.out.println("start by "+czy);
    }  
  
  
    public void stop(String czy) {  
    	System.out.println("stop by "+czy);
    }

	@Override
	public String analyzer(String sentence) {
		String res ="default";
		try {
			System.out.println(sentence);
			res = KAnalyzerExtendDemo.analyzer(sentence);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}  
    
}  
