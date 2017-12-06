package com.cyb.reflect.rpc;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.reflect.rpc.cglib.CglibProxy;
import com.cyb.reflect.rpc.cglib.CglibProxyCls;
import com.cyb.reflect.rpc.service.AnimalSayImpl;
import com.cyb.reflect.rpc.service.PersonSayImpl;
import com.cyb.reflect.rpc.service.SayService;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 */
public class CglibTest {
	public static Map<String,Class<?>> register = new HashMap<String,Class<?>>();
	static{
		register.put("com.cyb.reflect.rpc.service.SayService", PersonSayImpl.class);
		register.put("com.cyb.reflect.rpc.service.SayService", AnimalSayImpl.class);
	}
	Log log = LogFactory.getLog(CglibTest.class);
	public static void main(String[] args)  {
		try{
			 //通过生成子类的方式创建代理类   接口代理
			 SayService proxyImp = (SayService)new CglibProxy().getProxy(SayService.class);  
			 proxyImp.say("cjsdf"); 
			 //通过生成子类的方式创建代理类   接口实现类代理
			 SayService personSayImpl = (SayService)new CglibProxyCls().getProxy(PersonSayImpl.class);  
			 personSayImpl.say("cjsdf"); 
			 //通过生成子类的方式创建代理类   接口实现类代理
			 SayService animalSayImpl = (SayService)new CglibProxyCls().getProxy(AnimalSayImpl.class);  
			 animalSayImpl.say("cjsdf"); 
			 SayService animalSayImpl_ = (SayService)new CglibProxyCls().getProxy(SayService.class);  
			 animalSayImpl_.say("cjsdf"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
