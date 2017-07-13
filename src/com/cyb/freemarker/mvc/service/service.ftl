package ${packageName};
import javax.annotation.Resource;
import com.cyb.freemarker.mvc.base.BaseService;
import ${poPackageName};
import ${daoPackageName};
/**
 *  @author ${author}
 * 	@date ${date}
 */
public class ${modelName}Service extends BaseService<${po}> {
   @Resource
   private ${modelName}Dao ${modelName?uncap_first};
}