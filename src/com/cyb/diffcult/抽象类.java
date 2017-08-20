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
  //private abstract void privateAbsMethod();//private的抽象方法不允许
  protected abstract void privateAbsMethod1();
  public abstract void privateAbsMethod12();
 // abstract void privateAbsMethod1();//default的抽象方法不允许
  public static void print2(){
	  System.out.println("abstract static method");
  }
  public void print1(){};//空实现
  
  public abstract void print3();//抽象方法，没有方法体（可以没有抽象方法，但是如果有，则其派生类不必是抽象类）
  //public static abstract void statMethod();不合法
  //public native abstract void nativeMethod();不合法
  public abstract  void statMethod();
  public static void main(String[] args) {
   print2();
 }
}
