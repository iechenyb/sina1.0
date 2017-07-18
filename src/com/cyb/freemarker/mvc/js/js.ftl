package ${packageName};
import ${poPackageName};
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.cyb.freemarker.mvc.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
/**
 *  @author ${author}
 *  @date ${date}
 */
@Repository("${modelName?uncap_first}Dao")
public class ${modelName}Dao extends BaseDao<${po}>{
    @SuppressWarnings("unchecked")
    public List<${po}> getList(){
	   String sql="from ${po} where zt!=-1";
	   Query query = this.getSession().createSQLQuery(sql).setCacheable(true);
	   List<${po}> list = query.list();
	   if(CollectionUtils.isEmpty(list)){
		   list = new ArrayList<${po}>();
	   }
	   return list;
   }
}