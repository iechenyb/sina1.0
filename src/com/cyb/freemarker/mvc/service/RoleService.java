package com.cyb.freemarker.mvc.service;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseService;
import com.cyb.freemarker.mvc.po.Role;
import com.cyb.freemarker.mvc.dao.RoleDao;
/**
 *  @author iechenyb
 * 	@date 2017-07-13 16:48:37
 */
public class RoleService extends BaseService<Role> {
   @Resource
   private RoleDao role;
}