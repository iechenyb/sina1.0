package com.cyb.report;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月31日
 */
public class DateSourceBaseFactory {
	Log log = LogFactory.getLog(DateSourceBaseFactory.class);
	public static List createBeanCollection(String sex) {  
        
        int num=0;  
        if(sex.equals("男")){  
            num=1;  
        }else{  
            num=2;  
        }  
          
        ResultSet rs=null;  
        Statement st=null;  
        Connection con=null;  
        List datas=new ArrayList();  
          
        try {  
            con=JDBCConnection.getConnection();  
            st=con.createStatement();  
            rs=st.executeQuery("select * from t_user");  
            while(rs.next()){  
                Map attris=new HashMap();  
                attris.put("id", rs.getLong("id"));  
                attris.put("account", rs.getString("account"));  
                attris.put("password", rs.getString("password"));  
                attris.put("valid", rs.getBoolean("valid"));  
                datas.add(attris);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
                try {  
                    if(rs!=null) rs.close();  
                    if(st!=null) st.close();  
                    if(con!=null) con.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
        }  
          
        return datas;  
    } 
}
