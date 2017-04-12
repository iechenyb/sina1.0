package com.cyb.mvc.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *  @author iechenyb
 */
@Controller
@RequestMapping("restfull/test"); 
public class TestController extends BaseController {
   @Resource(name="testService")
   TestService testService;
}