package com.cyb.freemarker.mvc.service;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseService;
import com.cyb.freemarker.mvc.po.User;
import com.cyb.freemarker.mvc.dao.TestDao;
/**
 *  @author iechenyb
 * 	@date 2017-07-13 16:31:34
 */
public class TestService extends BaseService<User> {
   @Resource
   private TestDao test;
}