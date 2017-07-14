package com.cyb.reflect;

import com.cyb.reflect.Reflector;

public class Main {
      public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
    	  Bean bean = new Bean("23","chenyb");
    	  System.out.println(Reflector.object2Map(bean));
    	  System.out.println(Reflector.getFieldValueByName("id", bean).toString()+","+Reflector.getAnnotionedId(bean));
    	  System.out.println(Reflector.saveSql(bean));
    	  System.out.println(Reflector.updateSql(bean));
    	  System.out.println(Reflector.deleteSql(bean));
	}
}