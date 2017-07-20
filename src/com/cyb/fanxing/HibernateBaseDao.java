package com.cyb.fanxing;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
//implements BaseDao<T,T1>
public abstract class HibernateBaseDao<T,T1>  {  
    private Class<T> entityClass;  
    private Class<T1> entityClass1;  
  
    /** 
     * 这个通常也是<a href="http://lib.csdn.net/base/javaee" class='replace_word' title="Java EE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>hibernate</a>的取得子类class的方法 
     *  
     * @author "yangk" 
     * @date 2010-4-11 下午01:51:28 
     */  
	@SuppressWarnings("unchecked")
	public HibernateBaseDao() {  
        Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class<T>) params[0];
        entityClass1 = (Class<T1>) params[1];
        System.out.println("entityClass="+entityClass);
        System.out.println("entityClass1="+entityClass1);
        
    }  
  
    public T get(String id) {  
        try {  
            return entityClass.newInstance();  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    public T1 getT1(String id) {  
        try {  
            return entityClass1.newInstance();  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    
    public Class<T> getT1(){
    	return entityClass;
    }
    public Class<T1> getT2(){
    	return entityClass1;
    }
  
}  
