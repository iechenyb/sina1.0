package com.cyb.diffcult;

interface 接口 {
  public static String name="我在接口里";	
  public String x="x value in interface";
  public String y=" y value in interface";
  public static 静态语句块 静态语句块1= new 静态语句块("静态语句块在接口中");	
  /*static{
	  System.out.println(name);
  }*/
  public void jkprint();
  public abstract void jkprint1();//也必须得实现在派生类中
}
