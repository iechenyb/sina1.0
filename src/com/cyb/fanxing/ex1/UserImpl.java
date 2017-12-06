package com.cyb.fanxing.ex1;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.json.bean.User;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月1日
 */
public class UserImpl implements AnyTypeInterface<User>{
	Log log = LogFactory.getLog(UserImpl.class);
	
	public static void main(String[] args) {
		AnyTypeInterface<User> any = new UserImpl();
		System.out.println(any.getVar().getName());
	}

	@Override
	public User getVar() {
		User user = new User();
		user.setName("chenyb");
		return user;
	}
}
