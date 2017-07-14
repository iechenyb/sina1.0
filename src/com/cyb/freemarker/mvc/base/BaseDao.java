package com.cyb.freemarker.mvc.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cyb.fanxing.HibernateBaseDao;

public class BaseDao<T> {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月19日下午3:17:28</br>
	 */
	Log log = LogFactory.getLog(HibernateBaseDao.class);
	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	protected Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseDao() {
		Type t = getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			clazz = (Class<T>) p[0];
		}
	}

	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			if (session == null) {
				session = sessionFactory.openSession();
			}
		} catch (Exception e) {
			return sessionFactory.openSession();
		}
		return session;
	}

	public void close(Session session) {
		if (session != null) {
			session.flush();
			session.close();
		}
	}

	public void save(T t) {
		this.getSession().save(t);
	}

	public void delete(T t) {
		this.getSession().delete(t);
	}

	public void update(T t) {
		this.getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	public T get(Class<?> clazz, String id) {
		T entity = null;
		try {
			entity = (T) this.getSession().get(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T load(Class<?> clazz, String id) {
		T entity = null;
		try {
			entity = (T) this.getSession().load(clazz, id);
		} catch (HibernateException sqle) {
			entity = null;
			log.error(sqle.toString());
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		Object obj = this.getSession()
				.createQuery("from " + clazz.getSimpleName() + " order by id")
				.setCacheable(true).list();
		if (obj != null) {
			return (List<T>) obj;
		} else {
			return new ArrayList<T>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(String entityName) {
		Object obj = this.getSession()
				.createQuery("from " + entityName + " order by id")
				.setCacheable(true).list();
		if (obj != null) {
			return (List<T>) obj;
		} else {
			return new ArrayList<T>();
		}
	}

	public void evict(Object t) {
		this.getSession().merge(t);
		this.getSession().evict(t);
	}
}
