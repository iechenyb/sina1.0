package com.cyb.reflect.mvc;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyb.collection.common.CollectionFactory;
import com.cyb.collection.po.User;
import com.cyb.reflect.invoke.CallMethod;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年2月2日
 */
class Handler {
	String reqHandler;
	String methodName;
	@SuppressWarnings("rawtypes")
	Class[] paramTypes;
	@SuppressWarnings("rawtypes")
	Class returnType;

	public String getReqHandler() {
		return reqHandler;
	}

	public void setReqHandler(String reqHandler) {
		this.reqHandler = reqHandler;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	@SuppressWarnings("rawtypes")
	public Class[] getParamTypes() {
		return paramTypes;
	}

	@SuppressWarnings("rawtypes")
	public void setParamTypes(Class[] paramTypes) {
		this.paramTypes = paramTypes;
	}

	@SuppressWarnings("rawtypes")
	public Class getReturnType() {
		return returnType;
	}

	@SuppressWarnings("rawtypes")
	public void setReturnType(Class returnType) {
		this.returnType = returnType;
	}

	public String toString() {
		String types = "";
		if (paramTypes != null) {
			for (Class<?> cur : paramTypes) {
				types = types + " " + cur.getName() + " ";
			}
		}
		return reqHandler + ",[" + methodName + "(" + types + ")]," + returnType;
	}

}

public class ControllerUtils {
	Log log = LogFactory.getLog(ControllerUtils.class);
	public static List<Handler> reqs;
	public static boolean allowRedfine = false;
	public static Map<String, String> map;
	public static Map<String, Handler> reqMaps;
	public static Class<?> controller = CallMethod.class;

	public static void main(String[] args) throws Exception {
		CollectionFactory.build(10);
		reqs = new ArrayList<Handler>();
		map = new HashMap<String, String>();
		reqMaps = new HashMap<String, Handler>();
		initRequest(CallMethod.class);
		for (Handler h : reqs) {
			System.out.println("**************");
			System.out.println(h);
		}

		// 根据指定方面名和参数类型列表，找到对应的方法
		String reqUri = "returnStringMethod2";
		Handler handler = reqMaps.get(reqUri);
		// 如何动态的封装参数！已知get的参数名字和值
		showParamsNames(controller, handler.getMethodName(), handler.getParamTypes());
		Method method = controller.getMethod(handler.getMethodName(), handler.getParamTypes());
		method.invoke(controller.newInstance(), new Object[] { "多态方法测试！" });
		// 动态的封装参数执行controller方法
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("param", "我是传递的参数值！");
		param.put("para1", "我是传递的参数值1！");
		param.put("para2", 2);
		param.put("throwException", false);//Boolean.valueOf(true)
		// method.invoke(controller.newInstance(),wrapperParams(handler,param));
		User user = new User();
		user.setName("iechenyb");
		param.put("user", user);
		param.put("name", "chenyuanbao");
		param.put("map", param);
		param.put("list", CollectionFactory.getList());
		param.put("file",new File("d://data//log"));
		System.out.println("====================");
		httpClient("voidMethod", param);
		System.out.println("1====================");
		httpClient("twoParamMethod", param);
		System.out.println("2====================");
		httpClient("returnStringMethod1", param);
		System.out.println("3====================");
		httpClient("returnStringMethod2", param);
		System.out.println("4====================");
		httpClient("returnUserMethod1", param);
		System.out.println("5====================");
		httpClient("returnUserMethod2", param);
		System.out.println("6====================");
		httpClient("mapTest", param);
		System.out.println("7====================");
		httpClient("listTest", param);
		System.out.println("8====================");
		
	}

	public static void httpClient(String uri, Map<String, Object> param)
			throws Exception {
		// 获取处理器
		Handler handler = reqMaps.get(uri);
		// 获取处理方法
		Method method = controller.getMethod(handler.getMethodName(), handler.getParamTypes());
		// 执行方法
		Object ret = null;
	    ret = method.invoke(controller.newInstance(),wrapperParams(handler, param));	
		System.out.println("请求的返回值 " + ret);
	}

	public static Object[] wrapperParams(Handler handler, Map<String, Object> param) throws Exception {
		List<String> names = getParamterName(controller, handler.getMethodName(), handler.getParamTypes());
		if (!org.springframework.util.CollectionUtils.isEmpty(names)) {
			System.out.println(handler.getReqHandler()+"->"+handler.getMethodName()+"->" + names);
			Object[] reci = new Object[names.size()];
			@SuppressWarnings("rawtypes")
			Class[] types = handler.getParamTypes();
			if (!org.springframework.util.CollectionUtils.isEmpty(names)) {
				for (int i = 0; i < reci.length; i++) {
					reci[i] = param.get(names.get(i));//如何根据指定类型和值创建指定类型的对象
					System.out.println(types[i].getName()+","+param.get(names.get(i)).getClass().getName());
					//String sourceName = param.get(names.get(i)).getClass().getName();
					/*if(!types[i].getName().toLowerCase().equals(param.get(names.get(i)).getClass().getName().toLowerCase())){
						throw new Exception(sourceName+"不能转换成"+types[i].getName());
					}*/
				}
			}
			return reci;
		} else {
			return null;
		}
	}

	public static void initRequest(@SuppressWarnings("rawtypes") Class clazz) throws Exception {
		Class<?>[] paramsClass = null;
		Method[] methods = clazz.getDeclaredMethods(); // 取得这个类的所有方法
		if (methods != null) {
			Handler handler = null;
			for (int i = 0; i < methods.length; i++) {
				handler = new Handler();
				Method method = methods[i];
				handler.setMethodName(method.getName());
				paramsClass = method.getParameterTypes(); // 取得参数列表的所有类
				handler.setParamTypes(paramsClass);
				handler.setReturnType(method.getReturnType());
				if (method.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping cls = method.getAnnotation(RequestMapping.class);
					handler.setReqHandler(cls.value()[0]);
					if (!reqMaps.containsKey(handler.getReqHandler())) {
						reqs.add(handler);
						reqMaps.put(cls.value()[0], handler);
					} else {
						if (allowRedfine) {
							reqs.add(handler);
							reqMaps.put(cls.value()[0], handler);
						}
						throw new Exception(handler.getReqHandler() + "请求已经定义过了！");
					}
				}
				handler = null;
			}
		}
	}

	/**
	 * 
	 * 作者 : iechenyb<br>
	 * 方法描述: 多态如何处理？？？<br>
	 * 创建时间: 2017年7月15日
	 * 
	 * @param clazz
	 * @param methodName
	 */
	@SuppressWarnings("rawtypes")
	public static void showParamsNames(Class clazz, String methodName, Class[] paramTypes) {
		try {
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get(clazz.getName());
			CtMethod cm = cc.getDeclaredMethod(methodName);
			// 使用javaassist的反射方法获取方法的参数名
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);
			if (attr == null) {
				// exception
			}
			String[] paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++)
				paramNames[i] = attr.variableName(i + pos);
			// paramNames即参数名
			for (int i = 0; i < paramNames.length; i++) {
				System.out.println(paramNames[i]);
			}

		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static List<String> getParamterName(Class clazz, String methodName, Class[] targetParamTypes) {
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals("returnStringMethod")) {
				System.out.println();
			}
			Class[] curMethodParams = method.getParameterTypes();
			if (methodName.equals(method.getName()) && compareClassArray(curMethodParams, targetParamTypes)) {
				String[] params = u.getParameterNames(method);
				return Arrays.asList(params);
			}
		}
		return null;
	}

	public static boolean compareClassArray(Class[] from, Class[] to) {
		if (from == null && to == null) {
			return true;
		}
		boolean eq = true;
		if (from != null && to != null) {
			if (from.length == to.length) {
				for (int i = 0; i < from.length; i++) {
					if (from[i] == to[i]) {
						continue;
					} else {
						eq = false;
						break;
					}
				}
			}else{
				return false;
			}
		}
		return eq;
	}

	@SuppressWarnings("rawtypes")
	public static List<String> getParamterName(Class clazz, String methodName) {
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (methodName.equals(method.getName())) {
				String[] params = u.getParameterNames(method);
				return Arrays.asList(params);
			}
		}
		return null;
	}
	/*
	 * jdk1.8 public static List<String> getParameterNameJava8(Class clazz,
	 * String methodName){ List<String> paramterList = new ArrayList<>();
	 * Method[] methods = clazz.getDeclaredMethods(); for (Method method :
	 * methods) { if (methodName.equals(method.getName())) { Parameter[] params
	 * = method.getParameters(); for(Parameter parameter : params){
	 * paramterList.add(parameter.getName()); }
	 * 
	 * } } return paramterList; }
	 */
}
