package ${packageName};
import ${poPackageName};
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import com.cyb.freemarker.mvc.base.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
/**
 * 作者 : ${author}
 * 功能描述: 说点啥
 * 创建时间: ${date}
 */
@Repository("${modelName?uncap_first}Dao")
public class ${modelName}Dao extends BaseDao<${po}>{
    @SuppressWarnings("unchecked")
    /**
	 * 作者 : ${author}
	 * 功能描述: 说点啥
	 * 创建时间: ${date}
	 */
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