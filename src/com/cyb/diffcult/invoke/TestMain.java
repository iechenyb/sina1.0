package com.cyb.diffcult.invoke;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月31日
 */
public class TestMain {
	Log log = LogFactory.getLog(TestMain.class);
	public static void main(String[] args){
        //创建控制器对象，将提供给他的回调对象传入
        Employee employee=new Employee();
        employee.addLisenter(new BossListener());
        //启动控制器对象运行
        employee.doWork();
        employee.doWork_1();
    }
}
