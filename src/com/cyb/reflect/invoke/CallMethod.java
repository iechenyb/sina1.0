package com.cyb.reflect.invoke;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.cyb.collection.po.User;
import com.cyb.reflect.MethodUtils;

//http://blog.csdn.net/qq_14996421/article/details/51598611
public class CallMethod {
	@SuppressWarnings({ "rawtypes", "unused" })
	public static Class[] getParamTypes(String methodName,Class clazz) {
		Class<?>[] paramsClass = null;
		Method[] methods = clazz.getDeclaredMethods(); // 取得这个类的所有方法
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (methodName.equals(method.getName())) { // 取得本方法，这个方法是test，所以就用test比较
					paramsClass = method.getParameterTypes(); // 取得参数列表的所有类
					if (paramsClass != null) {
						for (Class<?> class1 : paramsClass) {
							//System.out.println(class1.getName());
						}
					}
					break;
				}
			}
		}
		return paramsClass;
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		CallMethod call = new CallMethod();
		try {
			Class[] paramsArray  = getParamTypes("voidMethod",CallMethod.class);
			System.out.println("参数个数："+paramsArray.length);
			Method method1 = CallMethod.class.getMethod("voidMethod",(Class[]) null);
			Object obj1 = method1.invoke(call, (Object[]) null);
			System.out.println("无参数无返回值的返回值："+obj1);// null
			getParamTypes("twoParamMethod",CallMethod.class);//无参数调用
			//Method method2 = CallMethod.class.getMethod("twoParamMethod",String.class, int.class);
			//（string,boolean）
			//获取参数列表
			paramsArray  = getParamTypes("twoParamMethod",CallMethod.class);
			System.out.println("参数列表："+paramsArray[0]+","+paramsArray[1]);
			Method method2 = CallMethod.class.getMethod("twoParamMethod",paramsArray);
			
			Map<String,Object> paramsHttp = new HashMap<String,Object>();
			paramsHttp.put("para1", "chenybslsl");
			paramsHttp.put("para2", 78965);
			paramsHttp.put("throwException", false);//测试参数传递正确否
			Object[] params = new Object[paramsHttp.size()];
			//根据map组装参数数组* 如何解决多态问题
			String[] paramNames1 = MethodUtils.getMethodParamNames(CallMethod.class,"twoParamMethod");
			for(int i=0;i<paramNames1.length;i++){//根据http请求封装参数
				params[i]= paramsHttp.get(paramNames1[i]); 
			}
			//Object obj2 = method2.invoke(call, "smile", 7);//指定参数值
			Object obj2 = method2.invoke(call, params);//使用参数数组（直接指定数组）
			System.out.println("twoParamMethod return is "+obj2);// null
			
			Method method3 = CallMethod.class.getMethod("returnStringMethod",(Class[]) null);
			Object returnValue = (String) method3.invoke(call, (Object[]) null);
			System.out.println("returnStringMethod return is "+returnValue);
			Method method4 = CallMethod.class.getMethod("returnUserMethod",	String.class);
			Object returnValue1 = (User) method4.invoke(call, "chenyb");
			System.out.println("return calssname: " + returnValue1.getClass());
			System.out.println("return canonicalName: "
					+ returnValue1.getClass().getCanonicalName());
			if (returnValue1 instanceof com.cyb.collection.po.User) {
				System.out.println("returnStringMethod return user.name is "+((com.cyb.collection.po.User) returnValue1)
						.getName());
			}
			if (returnValue1.getClass().getCanonicalName()
					.equals("com.cyb.collection.User")) {
				System.out.println("returnStringMethod return user.name is "+((com.cyb.collection.po.User) returnValue1)
						.getName());
			}
			
			//传递自定义User参数
			paramsArray  = getParamTypes("returnUserMethod",CallMethod.class);
			System.out.println(paramsArray[0]);//如何获取指定参数列表的同名方法
			//获取指定参数的同名方法
			Method method5 = CallMethod.class.getMethod("returnUserMethod",new Class[]{User.class});
			User user = new User();
			user.setName("chenyb");
			method5.invoke(call,new Object[]{user});
			
			Method method6 = CallMethod.class.getMethod("returnUserMethod",new Class[]{String.class});
			method6.invoke(call,new Object[]{"多态方法测试！"});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 无参无返回值
	 */
	@RequestMapping("voidMethod")
	public void voidMethod() {
		System.out.println("无返回值，无参数！");
	}

	/**
	 * 带参数
	 * 自动拆装箱变量  如int写成Integer都可以。
	 * @param s
	 * @param i
	 * @throws Exception 
	 */
	@RequestMapping("twoParamMethod")
	public void twoParamMethod(String para1, Integer para2,boolean throwException) throws Exception {
		if(throwException){
			throw new Exception("我自己抛出的异常!");
		}
		System.out.println("twoParamMethod para1=" + para1);
		System.out.println("twoParamMethod para2=" + para2);
		System.out.println("twoParamMethod para3=" + throwException);
	}

	/**
	 * 带返回值的
	 * 
	 * @return
	 */
	@RequestMapping("returnStringMethod1")
	public String returnStringMethod() {
		System.out.println("no param !");
		return "well";
	}
	@RequestMapping("returnStringMethod2")
	public String returnStringMethod(String param) {
		System.out.println("param is "+param);
		return "well"+param;
	}

	/**
	 * 带返回值的
	 * 
	 * @return
	 */
	@RequestMapping("returnUserMethod1")
	public User returnUserMethod(String name) {
		System.out.println("returnUserMethod(string) "+name);
		User user = new User();
		user.setName(name);
		return user;
	}
	
	/**
	 * 带返回值的
	 * 
	 * @return
	 */
	@RequestMapping("returnUserMethod2")
	public void returnUserMethod(User user) {
		System.out.println(" para user.name is "+user.getName());
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("mapTest")
	public void mapTest(Map map) {
		System.out.println(" map param test "+map);
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping("listTest")
	public void ListTest(List list,File file) {
		System.out.println(" List param test "+list);
		System.out.println(" File param test "+file);
	}
}
