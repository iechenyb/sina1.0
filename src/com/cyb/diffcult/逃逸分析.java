package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月3日
 *https://www.jianshu.com/p/20bd2e9b1f03
 */
public class 逃逸分析 {
	Log log = LogFactory.getLog(逃逸分析.class);
	public static Object obj;
    public void globalVariableEscape() {  // 给全局变量赋值，发生逃逸
        obj = new Object();
    }
    public Object methodEscape() {  // 方法返回值，发生逃逸
        return new Object();
    }
    public void instanceEscape() {  // 实例引用发生逃逸
        test(this); 
    }
    
    public void test(Object obj){
    	
    }
    public void methodNoEscape(){
    	String fi = "aaaaa";
    	System.out.println(fi);
    }
}
