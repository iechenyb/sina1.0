package com.cyb.diffcult;

public class 接口测试  implements 接口{
	    //public int  y=2;
	    public String y ="iechenyb";//定义与接口同名的参数，覆盖接口参数值，并隐藏接口同名参数，在子类中不可见。
		@Override
		public void jkprint() {
			//x = 5;y=5;//默认为final,不能被修改
			System.out.println("print,the value of x in interface is "+x);
			System.out.println("print,the value of y in interface is "+y);//子类同名,同类的属性覆盖接口中的同名属性
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
