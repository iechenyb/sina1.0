package ${packageName};
import ${poPackageName};
import com.cyb.freemarker.mvc.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 *  @author ${author}
 *  @date ${date}
 */
@Repository("${modelName?uncap_first}Dao")
public class ${modelName}Dao extends BaseDao<${po}>{
   
}