package com.cyb.spring.componentscan;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.spring.Axe;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年12月12日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// 获取特定包下所有的类(包括接口和类)
		List<Class<?>> clsList = 
				ClassUtil.getAllClassByPackageName
				(Axe.class.getPackage());
		System.out.println(clsList.size());
		//输出所有使用了特定注解的类的注解值
		AnnotationUtil.validAnnotation(clsList);
	}
}
