package ${packageName};
import java.util.List;
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseService;
import ${poPackageName};
import ${daoPackageName};
/**
 * 作者 : ${author}
 * 功能描述: 说点啥
 * 创建时间: ${date}
 */
public class ${modelName}Service extends BaseService<${po}> {
   @Resource
   private ${modelName}Dao ${modelName?uncap_first};
   /**
   * 作者 : ${author}
   * 功能描述: 说点啥
   * 创建时间: ${date}
   */
   public List<${po}> getList(){
   	 return ${modelName?uncap_first}.getList();
   }
}