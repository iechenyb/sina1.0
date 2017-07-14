package ${packageName};
import ${servicePackageName};
import ${poPackageName};
import java.util.List;
import java.util.Map;
import com.cyb.date.DateUtil;
import com.cyb.freemarker.mvc.po.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.cyb.freemarker.mvc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *  作者 : ${author}
 *  功能描述: 说点啥
 *  创建时间: ${date}
 */
@Controller
@RequestMapping("${basePath}")
public class ${modelName}Controller extends BaseController {
   Log log = LogFactory.getLog(${modelName}Controller.class);
   @Resource(name="${modelName?uncap_first}Service")
   ${modelName}Service ${modelName?uncap_first}Service;
   
   /**
    * 功能描述: 说点啥
    * 作者 : ${author}
 	* 创建时间: ${date}
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("add")
   public Map<String,Object> add(${po} ${po?uncap_first},HttpServletRequest req){
       setMsgMap(SUCCESS, "信息添加成功");
	   try{
	       User user = getUser(req);
		   ${po?uncap_first}.setCzyid(user.getId());
		   ${po?uncap_first}.setCzymc(user.getUsername());
		   ${po?uncap_first}.setCzsj(DateUtil.date2long14());
		   ${modelName?uncap_first}Service.save(${po?uncap_first});
		   msgMap.put("t",  ${po?uncap_first});
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息添加失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 更新方法
    * 作者 : ${author}
 	* 创建时间: ${date}
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("update")
   public Map<String,Object> update(${po} ${po?uncap_first},HttpServletRequest req){
   	   setMsgMap(SUCCESS, "信息更新成功");
	   try{
	   	   User user = getUser(req);
		   ${po?uncap_first}.setCzyid(user.getId());
		   ${po?uncap_first}.setCzymc(user.getUsername());
		   ${po?uncap_first}.setCzsj(DateUtil.date2long14());
		   ${modelName?uncap_first}Service.update(${po?uncap_first});
		   msgMap.put("t",  ${po?uncap_first});
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息更新失败！");
	   }
	   return msgMap;
   }
  /**
    * 功能描述: 物理删除数据
    * 作者 : ${author}
 	* 创建时间: ${date}
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("del")
   public Map<String,Object> delete(String id ,HttpServletRequest req){
       setMsgMap(SUCCESS, "信息删除成功");
	   try{
		   ${po} ${po?uncap_first} = new ${po}();
		   ${po?uncap_first}.setId(id);
		   ${modelName?uncap_first}Service.delete(${po?uncap_first});
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息删除失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 逻辑删除数据
    * 作者 : ${author}
 	* 创建时间: ${date}
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("del1")
   public Map<String,Object> delete1(String id ,HttpServletRequest req){
       setMsgMap(SUCCESS, "信息删除成功");
	   try{ 
		   User user = getUser(req);
		   ${po} ${po?uncap_first} = (${po}) jyflService.load(id);
		   ${po?uncap_first}.setCzyid(user.getId());
		   ${po?uncap_first}.setCzymc(user.getUsername());
		   ${po?uncap_first}.setCzsj(DateUtil.date2long14());
		   ${po?uncap_first}.setZt(-1);
		   jyflService.update(${po?uncap_first});
	   }catch(Exception e){
		   e.printStackTrace();
		   setMsgMap(FAILURE, "信息删除失败！");
	   }
	   return msgMap;
   }
    /**
    * 功能描述: 数据列表
    * 作者 : ${author}
 	* 创建时间: ${date}
 	* 参数说明：
    * 返回值说明：
    */
   @ResponseBody
   @RequestMapping("list")
   public List<${po}> list(){
	   return ${modelName?uncap_first}Service.getList();
   }
   
}