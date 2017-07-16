package ${packageName};
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cyb.freemarker.mvc.base.BaseService;
import ${poPackageName};
import ${daoPackageName};
/**
 *  @author ${author}
 * 	@date ${date}
 */
 @Service("${modelName?uncap_first}")
public class ${modelName}Service extends BaseService<${po}> {
   @Resource
   private ${modelName}Dao ${modelName?uncap_first}Dao;
   public List<${po}> getList(){
   	   return ${modelName?uncap_first}.getList();
   }
}