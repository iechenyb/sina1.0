package com.cyb.freemarker.mvc.controller;
import java.util.List;
import java.util.Map;

import com.cyb.freemarker.mvc.service.TestService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cyb.freemarker.mvc.base.BaseController;
import com.cyb.freemarker.other.po.Role;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *  @author iechenyb
 *  @date 2017-07-13 16:37:57
 */
@Controller
@RequestMapping("rest/test")
public class TestController extends BaseController {
   Log log = LogFactory.getLog(TestController.class);
   @Resource(name="testService")
   TestService testService;
   /**
    * 增加方法
    * @param req
    * @return
    */
   @ResponseBody
   @RequestMapping("add")
   public Map<String,Object> add(HttpServletRequest req){
	   return msgMap;
   }
   /**
    * 更新方法
    * @param req
    * @return
    */
   @ResponseBody
   @RequestMapping("update")
   public Map<String,Object> update(HttpServletRequest req){
	   return msgMap;
   }
   /**
    * 删除数据
    * @param req
    * @return
    */
   @ResponseBody
   @RequestMapping("del")
   public Map<String,Object> delete(String id ,HttpServletRequest req){
	   return msgMap;
   }
   /**
    * 数据列表
    * @return
    */
   @ResponseBody
   @RequestMapping("list")
   public List<Role> list(){
	   return null;
   }
   
}