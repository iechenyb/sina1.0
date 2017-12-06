package com.cyb.reflect.rpc;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 */
public class RpcTest {
	public static Map<String,Class<?>> register = new HashMap<String,Class<?>>();
	static{
		register.put("com.cyb.reflect.rpc.SayService", PersonSayImpl.class);
		//register.put("com.cyb.reflect.rpc.SayService", AnimalSayImpl.class);
	}
	Log log = LogFactory.getLog(RpcTest.class);
	public static void main(String[] args)  {
		try{
			/*String mName="say";
			Class<?> clss = SayService.class;
			//如果根据接口调用获取指定的方法 类型；
			Method method = SayService.class.getMethod(mName,	CallMethod.getParamTypes(mName,clss));
			Object obj2 = method.invoke(person, "sdf");//使用参数数组（直接指定数组）
			System.out.println("ret ="+obj2);*/
			/* SayService person = new PersonSayImpl();
			 * person = (SayService) new DynamicProxyHandler().bind(person);
			person.say("heall");
			person.say("iechenyb", "你好！");*/
			/*SayService helloWorldService = (SayService)RPCProxyClient.getProxy(SayService.class);
	        helloWorldService.say("test");*/
			 CglibProxy proxy = new CglibProxy();  
			 //通过生成子类的方式创建代理类  
			 SayService proxyImp = (SayService)proxy.getProxy(SayService.class);  
			 proxyImp.say("cjsdf"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
