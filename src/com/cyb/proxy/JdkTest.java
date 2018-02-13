package com.cyb.proxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.reflect.invoke.CallMethod;
import com.cyb.reflect.rpc.jdk.JdkDynamicProxy;
import com.cyb.reflect.rpc.service.AnimalSayImpl;
import com.cyb.reflect.rpc.service.PersonSayImpl;
import com.cyb.reflect.rpc.service.SayService;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年12月6日
 */
public class JdkTest {
	public static Map<String, Class<?>> register = new HashMap<String, Class<?>>();

	static {
		// register.put("com.cyb.reflect.rpc.SayService", PersonSayImpl.class);
		// register.put("com.cyb.reflect.rpc.SayService", AnimalSayImpl.class);
	}

	Log log = LogFactory.getLog(JdkTest.class);

	public static void main(String[] args)  {
		try{
			SayService person = new PersonSayImpl();
			SayService animal = new AnimalSayImpl();
			String mName="say";
			Class<?> clss = SayService.class;
			//如果根据接口调用获取指定的方法 类型；
			Method method = SayService.class.getMethod(mName,CallMethod.getParamTypes(mName,clss));
			Object obj2 = method.invoke(person, "sdf");//反射调用
			System.out.println("ret ="+obj2);
			
			//实现类代理
			person = (SayService) new JdkDynamicProxy().getProxy(person);
			person.say("heall");
			person.say("iechenyb", "你好！");
			
			//实现类代理
			person = (SayService) new JdkDynamicProxy().getProxy(new PersonSayImpl());
			person.say("heall");
			person.say("iechenyb", "你好！");
			
			animal = (SayService) new JdkDynamicProxy().getProxy(animal);
			animal.say("heall");
			animal.say("iechenyb", "你好！");
			
			animal = (SayService) new JdkDynamicProxy().getProxy(new AnimalSayImpl());
			animal.say("heall");
			animal.say("iechenyb", "你好！");
			
			animal = (SayService) new JdkDynamicProxy().getProxy(SayService.class.newInstance());
			animal.say("heall");
			animal.say("iechenyb", "你好！");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
