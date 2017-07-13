package com.cyb.freemarker.mvc.controller;
import com.cyb.freemarker.mvc.service.TestService;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *  @author iechenyb
 *  @date 2017-07-13 16:37:57
 */
@Controller
@RequestMapping("rest/test")
public class TestController extends BaseController {
   @Resource(name="testService")
   TestService testService;
}