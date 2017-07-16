package com.cyb.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.cyb.freemarker.mvc.base.BaseDao;
import com.cyb.freemarker.mvc.po.Role;
import com.cyb.freemarker.mvc.po.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月15日
 */
public class HibernateQueryBase extends BaseDao<User>{
	Log log = LogFactory.getLog(HibernateQueryBase.class);
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 单表整表查询<br>
	 *创建时间: 2017年7月15日hj12
	 *@return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getList(){
		String hql = "FROM User";
		hql = "FROM com.cyb.freemarker.mvc.po.User";
		hql = "FROM User AS user";
		hql="select s.id,s.name,sc.score from Student as s,Score as sc where s.id = sc.userId";//联合查询，查询结果特征：每行记录不在是一个对象 而是一个数组
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(1);//分页
		query.setMaxResults(10);
		List<User> list = query.list();
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<User>();
		}
		return list;
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 只查询某个字段<br>
	 *创建时间: 2017年7月15日hj12
	 *@return
	 */
	public List<?> getUserList(){
		String hql = "SELECT user.username FROM User user";
		hql = "FROM User E WHERE E.id > 10 ORDER BY E.username DESC";
		hql = "SELECT SUM(E.salary), E.firtName FROM User E GROUP BY E.firstName";//分组

		Query query = this.getSession().createQuery(hql);
		List<?> list = query.list();
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<Object>();
		}
		return list;
	}
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 查询带参数<br>
	 *创建时间: 2017年7月15日hj12
	 *@return
	 */
	public List<?> getListByParam(String id){
		String hql = "FROM User E WHERE E.id = :id_param";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("id_param",10);
		List<?> list = query.list();
		if(CollectionUtils.isEmpty(list)){
			return new ArrayList<Object>();
		}
		return list;
	}
	@SuppressWarnings("unused")
	public void copyTableData(){
		String hql = "INSERT INTO User(firstName, lastName, salary)" + 
			       "SELECT firstName, lastName, salary FROM old_user";
		Query query = this.getSession().createQuery(hql);
		int result = query.executeUpdate();//更新记录数
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public void findTTable(){
		
		String sql = "select {c.*}, {s.*} from User c , Role s where s.class_id = c.id   ";  
		List<Object[]> clazzs = (List<Object[]>) this.getSession().createSQLQuery(sql)  
		                                 .addEntity("c", User.class)  
		                                 .addEntity("s", Role.class).list();  
		String sql1 = "select {c.*}, {s.*} from User c , Role s where s.class_id = c.id   ";
		List<Object[]> clazzs1 = (List<Object[]>) this.getSession().createSQLQuery(sql1)
		.addEntity("c", User.class)
		.addEntity("s", Role.class).list();//这个查询会返回一个Object的List,每个Object包含两个对象一个User,一个Role  
		 for(int i=0;i<clazzs1.size();i++){  
	            System.out.println((User)clazzs1.get(i)[0]);
	            System.out.println((Role)clazzs1.get(i)[1]);
	        }  
	}
}
