package com.cyb.xml;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cyb.file.FileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月29日
 */
public class DOM4JTest {
	Log log = LogFactory.getLog(DOM4JTest.class);
	 private static ArrayList<Book> bookList = new ArrayList<Book>();
	    /**
	     * @param args
	     */
	    @SuppressWarnings("rawtypes")
		public static void main(String[] args) {
	        // 解析books.xml文件
	        // 创建SAXReader的对象reader
	        SAXReader reader = new SAXReader();
	        try {
	        	 String path = FileUtils.getAbsolutePathAtClass(JDOMTest.class);
	            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
	            Document document = reader.read(new File(path+"books.xml"));
	            // 通过document对象获取根节点bookstore
	            Element bookStore = document.getRootElement();
	            // 通过element对象的elementIterator方法获取迭代器
	            Iterator it = bookStore.elementIterator();
	            // 遍历迭代器，获取根节点中的信息（书籍）
	            while (it.hasNext()) {
	                System.out.println("=====开始遍历某一本书=====");
	                Element book = (Element) it.next();
	                // 获取book的属性名以及 属性值
	                List<Attribute> bookAttrs = book.attributes();
	                for (Attribute attr : bookAttrs) {
	                    System.out.println("属性名：" + attr.getName() + "--属性值："
	                            + attr.getValue());
	                }
	                Iterator itt = book.elementIterator();
	                while (itt.hasNext()) {
	                    Element bookChild = (Element) itt.next();
	                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
	                }
	                System.out.println("=====结束遍历某一本书=====");
	            }
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
	    }
}
