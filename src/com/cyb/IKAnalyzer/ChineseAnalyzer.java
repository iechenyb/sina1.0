package com.cyb.IKAnalyzer;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * 常见的中文分词有 极易分词，庖丁分词，IKAnalyzer
       由于极易分词早就不更新了，最高支持到Lucene2.4版。
       极易分词不支持Lucene3 不作测试 
 * @author DHUser
 *
 */
public class ChineseAnalyzer {
	public static void main(String[] args) throws Exception{  
        
        String strZH = "核心提示：据媒体报道，" +  
                "美国正在计划大规模扩张在亚洲的导弹防御系统。" +  
                "8月23日，美国国务院回应称，此举意在抵御来自朝鲜的导弹威胁，" +  
                "而不是针对中国。美国表示，美国通过美中军事对话以及美中战略与" +  
                "经济对话等机制，已经就该导弹防御系统的意图与中国进行了广泛的对话。";  
        strZH="你别管我是谁，我就是要注册，还得开户！";  
        Analyzer analyzer01 = new StandardAnalyzer(Version.LUCENE_36); //当为true时，分词器进行智能切分  
        //Analyzer analyzer02 = new PaodingAnalyzer();  
          
        showAnalyzerResult(analyzer01, strZH);  
        //showAnalyzerResult(analyzer02, strZH);  
          
    }  
      
    @SuppressWarnings("deprecation")
	public static void showAnalyzerResult(Analyzer analyzer, String str) throws Exception {  
        long start = System.currentTimeMillis();  
        System.out.println("分词器：" + analyzer.getClass().getSimpleName());  
        StringReader reader = new StringReader(str);   
        TokenStream ts = analyzer.tokenStream("", reader);  
        TermAttribute termAttribute =ts.getAttribute(TermAttribute.class);             
        long end = System.currentTimeMillis();  
        long time = end - start;  
        System.out.println("耗时：" + time + "ms");  
        while (ts.incrementToken()) {   
               System.out.print(termAttribute.term() + "|");                                            
        }   
          
    }  
}
