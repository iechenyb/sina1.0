package com.cyb.xml;
import java.io.File;
import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.cyb.file.FileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月29日
 */
public class JSoupTest {
	Log log = LogFactory.getLog(JSoupTest.class);
	public static void main(String[] args) throws IOException, XPathExpressionException {
		String path = FileUtils.getAbsolutePathAtClass(JDOMTest.class);
		Document doc = Jsoup.parse(new File(path+"books.xml"), "utf-8");
		/*Elements eles = doc.getElementsByTag("bookstore");
		System.out.println(eles.html());*/
		 for(Element el:doc.select("book")){  
	          System.out.println("标题："+el.select("name").text()+"  价格： "+el.select("price").text());  
	           
	     }  
		//Jaxp解析文档  
        /*DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();  
        factory.setNamespaceAware(true);  
        DocumentBuilder builder=factory.newDocumentBuilder();  
        Document doc=builder.parse("a.xml"); */ 
        /*XPathFactory xft=XPathFactory.newInstance();  
        XPath xpath=xft.newXPath();  
        XPathExpression expr=xpath.compile("//book/title//text() | //book/price//text()");//某个元素下的所有元素  
        Object result=expr.evaluate(doc, XPathConstants.NODESET);  
        NodeList nodes=(NodeList)result;  
        for(int i=0;i<nodes.getLength();i++){  
            Node item = nodes.item(i);  
            System.out.println("  节点名：   "+item.getNodeName()+"  节点值： "+item.getNodeValue());  
        }  */
	}
}
