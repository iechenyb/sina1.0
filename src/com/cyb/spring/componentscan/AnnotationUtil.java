package com.cyb.spring.componentscan;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年12月12日
 */
public class AnnotationUtil {
	Log log = LogFactory.getLog(AnnotationUtil.class);
	public static void validAnnotation(List<Class<?>> clsList){
		if (clsList != null && clsList.size() > 0) {
			for (Class<?> cls : clsList) {
				//获取类中的所有的方法
				Method[] methods = cls.getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					for (Method method : methods) {
						PeopleAnnotion peopleAnnotion = (PeopleAnnotion) method.getAnnotation(PeopleAnnotion.class);
						if (peopleAnnotion != null) {
							//可以做权限验证
							System.out.println(peopleAnnotion.say());
						}
					}
				}
			}
		}
	}
}
