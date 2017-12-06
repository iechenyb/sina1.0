package com.cyb.reflect.rpc;

import java.lang.reflect.Method;

import com.cyb.reflect.invoke.CallMethod;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * https://yq.aliyun.com/articles/6668
 * 创建时间: 2017年12月6日
 */
public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();
    private Class<?> clss ;
	@SuppressWarnings("rawtypes")
	public Object getProxy(Class clazz) {
		// 设置需要创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		this.clss = clazz;
		// 通过字节码技术动态创建子类实例
		return enhancer.create();
	}

	// 实现MethodInterceptor接口方法
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println(clss.getName()+"前置代理"+","+method.getName()+","+args[0]);
		Object object  = RpcTest.register.get(clss.getName()).newInstance();
		// 通过代理类调用父类中的方法
		Method m = SayService.class.getMethod(method.getName(),	CallMethod.getParamTypes(method.getName(),clss));
		
		Object result = method.invoke(object,args);
		//Object result = proxy.invokeSuper(object, args);
		
		//Object result = object.invokeSuper(obj, args);
		System.out.println("后置代理");
		return result;
	}
}
