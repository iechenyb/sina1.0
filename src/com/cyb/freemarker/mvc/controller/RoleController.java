package com.cyb.freemarker.mvc.controller;
import com.cyb.freemarker.mvc.service.RoleService;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *  @author iechenyb
 *  @date 2017-07-13 16:48:37
 */
@Controller
@RequestMapping("rest/test")
public class RoleController extends BaseController {
   @Resource(name="roleService")
   RoleService roleService;
}