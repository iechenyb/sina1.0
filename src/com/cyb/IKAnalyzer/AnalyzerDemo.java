package com.cyb.IKAnalyzer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * IKAnalyzer.cfg.xml

IKAnalyzer2012.jar

lucene-core-3.6.0.jar

stopword.dic
 * @author DHUser
 *
 */
@SuppressWarnings("deprecation")
public class AnalyzerDemo {
	public static void main(String[] args) throws IOException {  
		try {
			analy2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 try {
			//Analyzer analyzer3 = new StandardAnalyzer(Version.LUCENE_30);   //单字分中文
			//Analyzer analyzer02 = new PaodingAnalyzer();
			@SuppressWarnings("deprecation")
			Analyzer analyzer2 = new WhitespaceAnalyzer();                  //空格分词  
			String indexString1 = "中国建设银行 深发银行 广东发展银行";  
			String indexString2 = "这是一届创造奇迹、超越梦想的奥运会.......";  
			String indexString3 = "中国建设银行深发银行广东发展银行";  
			//showAnalyzerResult(analyzer3,indexString3);  
			showAnalyzerResult(analyzer2,indexString1);  
			//showAnalyzerResult(analyzer3,indexString1);  
			//showAnalyzerResult(analyzer2,indexString2);  
			//showAnalyzerResult(analyzer3,indexString2); 
			//showAnalyzerResult(analyzer2,indexString3);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	   try {
			analy2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	   String str = "最希望从企业得到的是独家的内容或销售信息，获得打折或促销信息等；"
	   		+ "最不希望企业进行消息或广告轰炸及访问用户的个人信息等。"
	   		+ "这值得使用社会化媒体的企业研究";
	   IKAnalysis(str);
    }  
	//使用(lucene)实现:
	public static void analy1() throws IOException{
		String text="期货开户怎么开呢？";//"基于java语言开发的轻量级的中文分词工具包";  
        StringReader sr=new StringReader(text);  
        IKSegmenter ik=new IKSegmenter(sr, true); //ik分词 
        Lexeme lex=null;  
        while((lex=ik.next())!=null){  
            System.out.print(lex.getLexemeText()+"|");  
        }  
        System.out.println();
	}
	//示例代码如下(使用IK Analyzer): 
	public static void analy2() throws IOException{
		String text="基于java语言开发的轻量级的中文分词工具包";  
        //创建分词对象  
        @SuppressWarnings("resource")
		Analyzer anal1=new IKAnalyzer(true);   
        Analyzer anal = new StandardAnalyzer(Version.LUCENE_36);
        StringReader reader=new StringReader(text);  
        //分词  
        TokenStream ts=anal.tokenStream("", reader);  
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
        //遍历分词数据  
        while(ts.incrementToken()){  
            System.out.print(term.toString()+"|");  
        }  
        reader.close();  
        System.out.println();
	}
	  @SuppressWarnings("deprecation")
	public static void showAnalyzerResult(Analyzer analyzer, String str) throws Exception {  
	        System.out.println("\n" + analyzer.getClass().getSimpleName());  
	        StringReader reader = new StringReader(str);   
	        TokenStream ts = analyzer.tokenStream(str, reader);  
	        TermAttribute termAttribute =ts.getAttribute(TermAttribute.class);   
	        while (ts.incrementToken()) {   
	               System.out.println(termAttribute.term());                                            
	        }   
	          
	    }  
	  public static String IKAnalysis(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			 //InputStream in = new FileInputStream(str);//
			IKConfiguration config = IKConfiguration.getInstance();  
	        config.setUseDict(true);  
	        config.setUseStopword(true);  
			byte[] bt = str.getBytes();// str
			InputStream ip = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(ip);
			IKSegmenter iks = new IKSegmenter(read, config);
			Lexeme t;
			while ((t = iks.next()) != null) {
				sb.append(t.getLexemeText() + " , ");

			}
			sb.delete(sb.length() - 1, sb.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
