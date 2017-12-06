package com.cyb.reflect.rpc;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 */
public class RPCProxyClient implements java.lang.reflect.InvocationHandler{
    private Object obj;
    public RPCProxyClient(Object obj){
        this.obj=obj;
    }
    /**
     * 得到被代理对象;
     */
    public static Object getProxy(Object obj){
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new RPCProxyClient(obj));
    }

    /**
     * 调用此方法执行
     */
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //结果参数;
        Object result = new Object();
        // ...执行通信相关逻辑
        System.out.println("hhhh");
        //method.invoke(obj, args);
        // ...
        return result;
    }
}
