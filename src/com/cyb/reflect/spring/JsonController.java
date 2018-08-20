package com.cyb.reflect.spring;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyb.collection.po.User;

import net.sf.json.JSONException;

/**
 * 作者 : iechenyb<br>
 * 类描述:get 请求没有 request body 所以 json请求只能用post<br>
 * 创建时间: 2018年5月15日
 */
@Controller
public class JsonController {
	
	Log log = LogFactory.getLog(JsonController.class);

	/**
	 * [ {"name":"test0","password":"gz0"} ,{"name":"test1","password":"gz1"} ]
	 * 作者 : iechenyb<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日hj12
	 * 
	 * @param list
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value = "recListFegin")
	public Object recListFegin(@RequestBody List<User> list) throws JSONException {
		return null;
	}
	/**
	 * {"name":"test0","password":"gz0"} 作者 : iechenyb<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日hj12
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "addUser")
	public User addUser(@RequestBody User user) {
		return null;
	}

	/**
	 * ["a","b"] 作者 : iechenyb<br>
	 * 方法描述: 说点啥<br>
	 * 创建时间: 2017年7月15日hj12
	 * 
	 * @param arrStr
	 * @return
	 */
	@RequestMapping(value = "arrStr")
	public List<String> addUser(@RequestBody List<String> arrStr) {
		return null;
	}

}
