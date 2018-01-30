package com.cyb.diffcult;

public class 接口测试  implements 接口{
	    public int  x=2;//子类定义与接口同名的参数，类型不一样，合法
	    public String yInter ="iechenyb";//定义与接口同名的参数，覆盖接口参数值，并隐藏接口同名参数，在子类中不可见。
		@Override
		public void jkprint() {
			//xInter = 5;yInter=5;//默认为final,不能被修改
			x=1;yInter="sdf";
			System.out.println("print,the value of x in interface is "+x);
			System.out.println("print,the value of y in interface is "+yInter);//子类同名,同类的属性覆盖接口中的同名属性
		}
		public static void main(String[] args) {
			new 接口测试().jkprint();
			//?如何访问接口中的相同属性名的参数值？？？
		}
		@Override
		public void jkprint1() {
			jkprint();
		}
	} 
