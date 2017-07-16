package com.cyb.hibernate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.CollectionUtils;

import com.cyb.freemarker.mvc.base.BaseDao;
import com.cyb.freemarker.mvc.po.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年7月15日
 */
public class HibernateCriteriaBase extends BaseDao<User>{
	Log log = LogFactory.getLog(HibernateCriteriaBase.class);
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 单表整表查询<br>
	 *创建时间: 2017年7月15日hj12
	 *@return
	 */
	@SuppressWarnings("unchecked")
	public List<User> getList(){
		Criteria cr = this.getSession().createCriteria(User.class);
		// To get records having salary more than 2000
		cr.add(Restrictions.gt("username", 2000));
		 
		// To sort records in descening order
		cr.addOrder(Order.desc("username"));
		 
		// To sort records in ascending order
		cr.addOrder(Order.asc("username"));

		cr.add(Restrictions.eq("username", "iechenyb"));
		// To get records having id more than 2000
		cr.add(Restrictions.gt("id", 2000));
		 
		// To get records having id less than 2000
		cr.add(Restrictions.lt("id", 2000));
		 
		// To get records having fistName starting with zara
		cr.add(Restrictions.like("firstName", "zara%"));
		 
		// Case sensitive form of the above restriction.
		cr.add(Restrictions.ilike("firstName", "zara%"));
		 
		// To get records having id in between 1000 and 2000
		cr.add(Restrictions.between("id", 1000, 2000));
		 
		// To check if the given property is null
		cr.add(Restrictions.isNull("id"));
		 
		// To check if the given property is not null
		cr.add(Restrictions.isNotNull("id"));
		 
		// To check if the given property is empty
		cr.add(Restrictions.isEmpty("id"));
		 
		// To check if the given property is not empty
		cr.add(Restrictions.isNotEmpty("id"));
		
		// To get total row count.
		cr.setProjection(Projections.rowCount());
		 
		// To get average of a property.
		cr.setProjection(Projections.avg("salary"));
		 
		// To get distinct count of a property.
		cr.setProjection(Projections.countDistinct("firstName"));
		 
		// To get maximum of a property.
		cr.setProjection(Projections.max("salary"));
		 
		// To get minimum of a property.
		cr.setProjection(Projections.min("salary"));
		 
		// To get sum of a property.
		cr.setProjection(Projections.sum("salary"));

		Criterion condi1 = Restrictions.gt("salary", 2000);
		Criterion condi2 = Restrictions.ilike("firstNname","zara%");

		// To get records matching with OR condistions
		LogicalExpression orExp = Restrictions.or(condi1, condi2);
		cr.add( orExp );
		 
		 
		// To get records matching with AND condistions
		LogicalExpression andExp = Restrictions.and(condi1, condi2);
		cr.add( andExp );
		List<User> list = cr.list();
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
	public void copyTableData(){
		String hql = "INSERT INTO User(firstName, lastName, salary)" + 
			       "SELECT firstName, lastName, salary FROM old_user";
		Query query = this.getSession().createQuery(hql);
		int result = query.executeUpdate();//更新记录数
	}
}
