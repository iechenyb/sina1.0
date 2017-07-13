package ${packageName};
import ${servicePackageName};
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *  @author ${author}
 *  @date ${date}
 */
@Controller
@RequestMapping("${basePath}")
public class ${modelName}Controller extends BaseController {
   @Resource(name="${modelName?uncap_first}Service")
   ${modelName}Service ${modelName?uncap_first}Service;
}