package com.cyb.reflect;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.collection.common.CollectionFactory;
import com.cyb.reflect.bean.Bean;
import com.cyb.reflect.bean.BeanVo;
import com.cyb.reflect.bean.UserBean;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月6日
 */
public class VisitorObject {
	Log log = LogFactory.getLog(VisitorObject.class);
	public static void main(String[] args) throws InvocationTargetException {
		CollectionFactory.build(10);
		
		UserBean bean = new UserBean();
		bean.setId(1000);
		bean.setAddress("武汉");
		bean.setAge(100);
		bean.setName("chenyb");
		
		UserBean bean1 = new UserBean();
		bean1.setId(1000);
		bean1.setAddress("武汉");
		bean1.setAge(100);
		bean1.setName("iechenyb");
		
		ReflectUtils.show(bean);
		
		ReflectUtils.show(CollectionFactory.getMap());
		
		Bean po = new Bean("","");
		BeanVo vo = new BeanVo("1","chenyb");
		
		ReflectUtils.setFieldValueByName("name",po,"who am i");
		ReflectUtils.show(po);
		
		System.out.println("************************");
		String[] arr = {"id","Address","age","name"};
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]+"="+ReflectUtils.getFieldValueByName(arr[i],bean));
		}
		System.out.println("************************");
		ReflectUtils.compareObject(bean,bean1);
		//ReflectUtils.copy(vo, po);
	}
}
