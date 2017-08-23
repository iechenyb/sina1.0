package com.cyb.diffcult;
/*private interface AA{};//error
protected  interface BB{};//error
static interface CC{};//error
*/
abstract interface DD{};
interface Inter1{};interface Inter2{};interface Inter3{};
//一个接口可以继承多个接口
public interface 接口 extends Inter1 ,Inter2,Inter3 {
  public static String name="我在接口里";	
  public String x="1";
  public String y="2";
  public static 静态语句块 静态语句块1= new 静态语句块("静态语句块在接口中");	
  /*static{
	  System.out.println(name);
  }*/
  public void jkprint();
  public abstract void jkprint1();//也必须得实现在派生类中
}
