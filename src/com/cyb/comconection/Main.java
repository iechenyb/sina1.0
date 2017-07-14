package com.cyb.comconection;

import com.cyb.reflect.Reflector;

public class Main {
      public static void main(String[] args) {
    	  ChildConnection conUtil = new ChildConnection(null);
    	  Bean bean = new Bean("23","chenyb");
    	  System.out.println(conUtil.object2Map(bean));
    	  System.out.println(Reflector.getFieldValueByName("id", bean));
	}
}
