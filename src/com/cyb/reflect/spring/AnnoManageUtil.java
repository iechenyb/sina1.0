package com.cyb.reflect.spring;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *作者 : iechenyb<br>
 *类描述: 获取指定包名下的指定映射信息<br>
 *创建时间: 2018年7月24日
 *<dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>0.9.11</version>
  </dependency>
 */
public class AnnoManageUtil {
	Log log = LogFactory.getLog(AnnoManageUtil.class);
	 /**
     * 获取指定文件下面的RequestMapping方法保存在mapp中
     *
     * @param packageName
     * @return
     */
    public static Map<String, ExecutorBean> getRequestMappingMethod(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Controller.class);

        // 存放url和ExecutorBean的对应关系
        Map<String, ExecutorBean> mapp = new HashMap<String, ExecutorBean>();
        for (Class<?> classes : classesList) {
            //得到该类下面的所有方法
            Method[] methods = classes.getDeclaredMethods();

            for (Method method : methods) {
                //得到该类下面的RequestMapping注解
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if (null != requestMapping) {
                    ExecutorBean executorBean = new ExecutorBean();
                    try {
                        executorBean.setObject(classes.newInstance());//设置类对象
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    executorBean.setMethod(method);
                    mapp.put(requestMapping.value()[0], executorBean);

                }
            }
        }
        return mapp;
    }
    
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
       /* List<Class<?>> classesList = null;
        classesList = AnnoManageUtil.getPackageController("annotationTest.service", Controller.class);
        Map<String, ExecutorBean> mmap = new HashMap<String, ExecutorBean>();
        AnnoManageUtil.getRequestMappingMethod(classesList, mmap);
        ExecutorBean bean = mmap.get("/test1");
        try {
            bean.getMethod().invoke(bean.getObject());
            RequestMapping annotation = bean.getMethod().getAnnotation(RequestMapping.class);
            System.out.println("注解名称：" + annotation.name() + "\t注解描述：" + annotation.value()[0]);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
    	Map<String, ExecutorBean> set = getRequestMappingMethod("com.cyb.reflect.spring");
    	//get map -》 对象 post json-》object  
    	for(String key:set.keySet()){
	    	System.out.println(set.get(key).getMethod().getName()+","+set.get(key).getObject());
	    	//set.get(key).getMethod().invoke(set.get(key).getObject(), null);进行回调  参数如何封装
    	}
    }
}
