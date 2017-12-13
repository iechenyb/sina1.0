package com.cyb.reflect;

import com.app.webeye.NetResult;

public class GenSqlTest {
      public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
    	  //Bean bean = new Bean("23","chenyb");
    	  //System.out.println(Reflector.object2Map(bean));
    	  NetResult bean = new NetResult();
    	  //System.out.println(Reflector.getFieldValueByName("id", bean).toString()+","+Reflector.getAnnotionedId(bean));
    	  System.out.println(DDLUtils.saveSql(bean));
    	  System.out.println(DDLUtils.updateSql(bean));
    	  System.out.println(DDLUtils.deleteSql(bean));
    	  System.out.println(DDLUtils.createSql(bean));
	}
}