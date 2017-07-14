package com.cyb.freemarker.mvc.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.cyb.freemarker.mvc.po.User;

public class BaseController {
  public static Map<String,Object> msgMap = new HashMap<String,Object>();
  public static final String SUCCESS="1";
  public static final String FAILURE="0";
  public static final String ERROR="-1";
  public void setMsgMap(String zt,String tip){
	  msgMap.put("zt", zt);
	  msgMap.put("msg",tip);
  }
  public User getUser(HttpServletRequest req){
  	User user = null ;
  	try{
  		user = (User) req.getSession().getAttribute("");
  	}catch(Exception e){
  		e.printStackTrace();
  	}
  	return user;
  }
  
  @ExceptionHandler
  @ResponseBody
  public Map<String, Object> doException(Exception e,HttpServletRequest request) throws Exception {
      if (e instanceof MaxUploadSizeExceededException) {
          long maxSize = ((MaxUploadSizeExceededException) e)
                  .getMaxUploadSize();
         setMsgMap(FAILURE, "上传文件太大，不能超过" + maxSize / 1024 + "k");
      }else if(e instanceof RuntimeException){
      	setMsgMap(FAILURE, "请求处理异常，请检查！");
      }else{
      	setMsgMap(FAILURE, "上传失败");
      }
      e.printStackTrace();
      return msgMap;
  }
}
