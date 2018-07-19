package com.cyb.concurrent.sevice;

/**
 *作者 : iechenyb<br>
 *类描述: 安全<br>
 *创建时间: 2018年7月6日
 */

public class MySingleton2 extends BaseService{
 
    private static MySingleton2 mySingleton = null;
    private static Object obj = new Object();
     
    //双重锁检查机制
    public static MySingleton2 getInstance() throws Exception{
        if(mySingleton == null){
            //System.out.println(System.currentTimeMillis());//1530859593633
            synchronized (obj){
                if(mySingleton == null){
                    mySingleton = new MySingleton2();
                    objCount.incrementAndGet();
                    if(objCount.get()>1){
                    	throw new Exception("出现多次初始化对象操作，创建次数"+objCount.get());
                    }
                }
            }
        }
        return mySingleton;
    }
}
