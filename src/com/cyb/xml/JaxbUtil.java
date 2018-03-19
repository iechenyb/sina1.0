package com.cyb.xml;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月19日
 */
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cyb.xml.bean.Role;
import com.cyb.xml.bean.Student;  
  
/** 
 * Jaxb2工具类. 
 * 
 */  
public class JaxbUtil {  
    /** 
     * JavaBean转换成xml. 
     *  
     * @param obj 
     * @return 
     */  
    public static String convertToXml(Object obj) {  
        return convertToXml(obj, "UTF-8");  
    }  
  
    /** 
     * JavaBean转换成xml. 
     *  
     * @param obj 
     *            bean实体 
     * @param encoding 
     *            默认编码UTF-8 
     * @return 
     */  
    private static String convertToXml(Object obj, String encoding) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);  
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return result;  
    }  
  
    /** 
     * xml转成JavaBean. 
     *  
     * @param xml 
     * @param c 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T converyToJavaBean(String xml, Class<T> c) {  
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }  
  
    
    
    public static void main(String[] args) {
	  test1();
	  System.out.println("#####################");
	  test2();
	}
    /** 
     * bean转xml测试. 
     */  
    public static void test1() {  
        Student book = new Student();  
        book.setId(100);  
        book.setName("yangwenxue");  
        book.setBirthday(new Date());  
        book.setAge(25);  
  
        Role role = new Role();  
        role.setRoleId("1233");  
        role.setRoleName("管理员");  
        role.setMemo("管理用户权限");  
        book.setRole(role);  
  
        String str = JaxbUtil.convertToXml(book);  
        System.out.println(str);  
    }  
  
    /** 
     * xml转bean测试. 
     */  
    public static void test2() {  
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"  
                + "<student id=\"100\"><myname>yangwenxue</myname><age>25</age>"  
                + "<birthday>2016-12-22T13:29:42.662+08:00</birthday><role roleId=\"1233\">"  
                + "<roleName>管理员</roleName><memo>管理用户权限</memo></role></student>";  
        Student book = JaxbUtil.converyToJavaBean(str, Student.class);  
        System.out.println(book);  
    }  
  
}  
