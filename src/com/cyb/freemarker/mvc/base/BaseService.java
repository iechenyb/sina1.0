package com.cyb.freemarker.mvc.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;


public class BaseService<T> {
	@Resource(name="baseDao")
	BaseDao<T> baseDao;
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseService() {
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            clazz = (Class<T>)p[0];
        }
	}
	/**
	 * 
	 * @作者:iechenyb
	 * @功能描述：持久化一个实体类
	 * @创建时间：2016年12月27日下午3:07:33
	 */
	public void save(T t){
		baseDao.save(t);
	}
	
	public void update(T t){
		baseDao.update(t);
	}
	
	public void delete(T t){
		baseDao.delete(t);
	}
	
	public Object get(String id){
		return baseDao.get(clazz, id);
	}
	public Object getAll(){
		return baseDao.getAll();
	}
	public Object getAll(String entityName){
		return baseDao.getAll(entityName);
	}
	public Object load(String id){
		return baseDao.load(clazz, id);
	}
	public void evict(Object t){
		   this.baseDao.evict(t);
	}
}
