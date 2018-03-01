package com.cyb.String;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月28日
 */
public class 字符串占位符 {
	Log log = LogFactory.getLog(字符串占位符.class);
	public static void main(String[] args) {
		show1();
		show();
		show2();
		//MessageFormat.format("'{'{0}}", "X-rapido"); // {X-rapido}
	}
	//给字母加上单引号
	public static void show3(){
		String message = "oh, {0} is ''a'' pig";  
		Object[] array = new Object[]{"ZhangSan"};  
		String value = MessageFormat.format(message, array);  
		System.out.println(value); 
	}
	public static void show2(){
		String message = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}{10}{11}{12}{13}{14}{15}{16}";  
		Object[] array = new Object[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"};  
		String value = MessageFormat.format(message, array);  
		System.out.println(value); 
	}
	public static void show1() {
		//模式字符串
        String pattern = "On {0}, a hurricance destroyed {1} houses and caused {2} of damage.";
         //实例化MessageFormat对象，并装载相应的模式字符串
         MessageFormat format = new MessageFormat(pattern, Locale.CHINA);
         Object arr[] = {new Date(), 99, 100000000};
         //格式化模式字符串，参数数组中指定占位符相应的替换对象
         String result = format.format(arr);
         System.out.println(result);
	}
	public static void show() {
		 //模式字符串
         String pattern = "At {0, time, short} on {0, date}, a destroyed {1} houses and caused {2, number, currency} of damage.";
        //实例化MessageFormat对象，并装载相应的模式字符串
        MessageFormat format = new MessageFormat(pattern, Locale.US);
        Object arr[] = {new Date(), 99, 100000000};
         //格式化模式字符串，参数数组中指定占位符相应的替换对象
        String result = format.format(arr);
        System.out.println(result);
    }
	public static String format(String pattern, Object ... arguments)   
	{  
	    MessageFormat temp = new MessageFormat(pattern);  
	    return temp.format(arguments);  
	}  
	@SuppressWarnings("unused")
	public int insertToTest_tb(String createTime,String datefrom,String dateto,String name,String intranetid,String actualhour,String planhour,String status) throws Exception{
        StringBuilder sb=new StringBuilder();
        sb.append("    insert into test_tb (");
        sb.append("        createTime, ");
        sb.append("        datefrom, ");
        sb.append("        dateto, ");
        sb.append("        name, ");
        sb.append("        intranetid, ");
        sb.append("        actualhour, ");
        sb.append("        planhour, ");
        sb.append("        status");
        sb.append("    ) values (");
        sb.append("        ''{0}'',");
        sb.append("        ''{1}'',");
        sb.append("        ''{2}'',");
        sb.append("        ''{3}'',");
        sb.append("        ''{4}'',");
        sb.append("        ''{5}'',");
        sb.append("        ''{6}'',");
        sb.append("        ''{7}''");
        sb.append("    )");
        String result=sb.toString();
        Object[] arr={createTime,datefrom,dateto,name,intranetid,actualhour,planhour,status};
        String sql=MessageFormat.format(result, arr);
        //this.getJdbcTemplate().update(sql);
        return 1;
    }
}
//参考文献  http://blog.csdn.net/zhiweianran/article/details/8666992
//http://blog.csdn.net/xiaokui_wingfly/article/details/46124057
