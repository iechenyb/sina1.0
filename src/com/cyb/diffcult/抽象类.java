package com.cyb.diffcult;

public abstract class 抽象类 implements 接口 {
  public String name="我在抽象类里";	
  public String x="11";
  public String y="22";
  public 抽象类(){
	  System.out.println("抽象类的构造方法！");
  }
  public void print(){
	  System.out.println("abstract cls.name="+name);
  }
  public void print1(){};//空实现
  
  public abstract void print2();//抽象方法，没有方法体（可以没有抽象方法，但是如果有，则其派生类不必是抽象类）
 
  public static void main(String[] args) {

 }
}
