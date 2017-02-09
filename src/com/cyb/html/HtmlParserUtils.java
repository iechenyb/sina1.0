package com.cyb.html;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Remark;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.RemarkNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.xml.sax.SAXException;


public class HtmlParserUtils {
	 static public void main(String[] args) throws SAXException, IOException{  
		 try {
			 NodeFilter filter = null;
			 filter = new HasChildFilter(null);
			 filter = new HasAttributeFilter( "id" );
			 filter = new HasAttributeFilter( "id","tt1");
			 filter = new HasAttributeFilter( "id","dataForm");//根据属性抓
			 filter = new HasAttributeFilter( "name","chenyb");
			 filter = new NodeClassFilter(RemarkNode.class);//注释标记
			 filter = new StringFilter("tt");//将文本内容包含tt的node查出来
			 filter = new LinkStringFilter("aaa");//这个Filter用于判断链接中是否包含某个特定的字符串，可以用来过滤出指向某个特定网站的链接。
			 filter = new TagNameFilter ("table");//根据标签名字抓			 
			 try {
			 //HTMLParser(filter);
				 anaNode("");
				//anaStr();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }  
	 /**
	  * AndFilter可以把两种Filter进行组合，只有同时满足条件的Node才会被过滤。
	  * NodeFilter filterID = new HasAttributeFilter( "id" );
		NodeFilter filterChild = new HasChildFilter(filterA);
		NodeFilter filter = new AndFilter(filterID, filterChild);
	  * @param filter
	  * @throws ParserException
	  */
	 public static void HTMLParser(NodeFilter filter) throws ParserException{
		 Parser parser = new Parser ("src/com/cyb/dom/test.html");//http://www.oschina.net/p/htmlparser
		 NodeList  list = parser.extractAllNodesThatMatch(filter);//extractAllNodesThatMatch(f) parse(f)
		 for(int i=0;i<list.size();i++){
			 System.out.println("$$$$:"+list.elementAt(i).toHtml());
		 }
	 }
	 public static void HTMLParserOnline(NodeFilter filter,String url) throws ParserException{
		 Parser parser = new Parser (url);//http://www.oschina.net/p/htmlparser
		 NodeList  list = parser.extractAllNodesThatMatch(filter);//extractAllNodesThatMatch(f) parse(f)
		 for(int i=0;i<list.size();i++){
			 System.out.println(i+":"+list.elementAt(i).toHtml());
		 }
	 }
	 /**
	  * 提取文字
	  * @throws Exception
	  */
	 public static void anaStr() throws Exception 
	{
		String sss = "<div class='title'>商品详细说明：</div><p style='word-break: break-all'>ESTEE LAUDER   Perfectly Clean Splash Away Foaming Cleanser<br/>为中性/混合性肌肤度身订制的清洁产品。 <br />";
  		Parser parser = new Parser(sss);
  		TextExtractingVisitor visitor = new TextExtractingVisitor();
  		parser.visitAllNodesWith(visitor);
  		System.out.println(visitor.getExtractedText());
 	}
	 //resource 可以是html内容也可以是http连接和本地文件路径
	 public static void anaNode(String resource){
		 try{
	            //Parser parser = new Parser( (HttpURLConnection) (new URL("src/com/cyb/dom/test.html")).openConnection() );
			    //Parser parser = new Parser ("src/com/cyb/dom/test.html");
			     Parser parser = new Parser (resource);
			    //因为我设置的 recurseChildren和recurseSelf都是false，所以Visitor没有访问子节点也没有访问根节点的内容
			    NodeVisitor visitor = new NodeVisitor( true, true ) {
	                public void visitTag(Tag tag) {
	                   System.out.println("This is Tag:"+tag.getText()+","+tag.getTagName());
	                }
	                public void visitStringNode (Text string)    {
	                     System.out.println("This is Text:"+string);
	                }
	                public void visitRemarkNode (Remark remark) {
	                     System.out.println("This is Remark:"+remark.getText());
	                }
	                public void beginParsing () {
	                    System.out.println("beginParsing");
	                }
	                public void visitEndTag (Tag tag){
	                    System.out.println("visitEndTag:"+tag.getText()+","+tag.getAttribute("name"));
	                }
	                public void finishedParsing () {
	                    System.out.println("finishedParsing");
	                }
	            };
	            parser.visitAllNodesWith(visitor);
	            parser.parse(null);
	        }
	        catch( Exception e ) {     
	            e.printStackTrace();
	        }
	 }
}
