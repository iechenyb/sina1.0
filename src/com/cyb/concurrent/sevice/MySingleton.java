package com.cyb.concurrent.sevice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 不安全<br>
 *创建时间: 2018年7月6日
 */
public class MySingleton extends BaseService{
	Log log = LogFactory.getLog(MySingleton.class);
	private static   MySingleton mySingleton = null;
	 
    public static MySingleton getInstance() throws Exception{
        if(mySingleton == null){
             mySingleton = new MySingleton();
             objCount.incrementAndGet();
             if(objCount.get()>1){
             	throw new Exception("出现多次初始化对象操作，创建次数"+objCount.get());
             }
        }
        return mySingleton;
    }

}
