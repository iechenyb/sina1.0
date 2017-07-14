package com.cyb.freemarker.mvc.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.cyb.freemarker.mvc.base.BaseDao;
import com.cyb.freemarker.mvc.po.Role;

/**
 *  @author iechenyb
 *  @date 2017-07-13 16:48:37
 */
@Repository("roleDao")
public class RoleDao extends BaseDao<Role>{
   public List<?> getList(){
	   String sql="from ? where zt!=-1";
	   Query query = this.getSession().createSQLQuery(sql).setCacheable(true);
	   List<?> list = query.list();
	   if(CollectionUtils.isEmpty(list)){
		   list = new ArrayList<>();
	   }
	   return list;
   }
}