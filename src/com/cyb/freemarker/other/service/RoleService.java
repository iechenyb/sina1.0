package com.cyb.freemarker.other.service;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseService;
import com.cyb.freemarker.other.po.Role;
import com.cyb.freemarker.other.dao.RoleDao;
/**
 *  @author iechenyb
 * 	@date 2017-07-13 17:06:53
 */
public class RoleService extends BaseService<Role> {
   @Resource
   private RoleDao role;
}