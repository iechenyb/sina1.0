package com.cyb.reflect.sql;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月21日
 */
public class UserDao {
	Log log = LogFactory.getLog(UserDao.class);
	 public static void main(String[] args) {  
	        User u1 = new User();  
	        u1.setId("10");     //根据ID查询用户信息  
	          
	        User u2 = new User();  
	        u2.setUserName("nsh");  //根据用户名查询  
	        u2.setTel("139658658");  
	       /* Student s = new Student();  
	        s.setId("2");*/  
	          
	          
	        String sql1 =query(u1);  
	        String sql2 =query(u2);  
	        //String sql3 =query(s);  
	        System.out.println("sql1="+sql1);  
	        System.out.println("sql2="+sql2);  
	        //System.out.println("sql3="+sql3);  
	    }  
	  
	    @SuppressWarnings("unchecked")
		private static String query(Object u) {  
	        StringBuffer sb = new StringBuffer();  
	        //1.获取class  
	        Class<? extends Object> c =u.getClass();  
	        //2.获取tableName  
	        //判断是否有table这个注解  
	        boolean isExist =c.isAnnotationPresent(MyTable.class);  
	        if(isExist){  
	            //获取到table这个注解，并取得表名  
	            MyTable mt =(MyTable) c.getAnnotation(MyTable.class);  
	            String tableName =mt.value();  
	            sb.append("select * from ").append(tableName).append(" where 1=1");  
	        }  
	        //3遍历所有字段  
	        Field[] fArray= c.getDeclaredFields();  
	        for(Field f:fArray){  
	            //拿到字段后与实体类中的属性匹配，并得到其get方法，用来获取他的属性值  
	            String getMethodName ="";  
	            boolean isCExist =f.isAnnotationPresent(MyColome.class);  
	            if(isCExist){  
	                MyColome mc =f.getAnnotation(MyColome.class);  
	                String columeName =mc.value();  //字段对应数据库名字  
	                String name =f.getName();       //字段名字  
	                String value=null;              //字段值  
	                getMethodName="get"+name.substring(0,1).toUpperCase()+name.substring(1);//拼接属性的get方法  
	                try {  
	                    Method m =c.getMethod(getMethodName);  
	                    value =(String)m.invoke(u);     //拿到属性的值  
	                    if(value == null || "".equals(value)){  //如果属性没值，不拼接sql  
	                        continue;  
	                    }  
	                    else if(value instanceof String){  
	                        value ="'"+value+"'";  
	                    }  
	                } catch (Exception e) {  
	                    // TODO Auto-generated catch block  
	                    e.printStackTrace();  
	                }  
	                sb.append(" and ").append(columeName).append(" = ").append(value);  
	            }  
	        }  
	        return sb.toString();  
	    }  
}
