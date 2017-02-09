package com.cyb.IKAnalyzer;

import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.StringReader;  
  

import org.wltea.analyzer.core.IKSegmenter;  
import org.wltea.analyzer.core.Lexeme;  
  
/** 
 * 描述：IKAnalyzer 
 * 采用了特有的“正向迭代最细粒度切分算法“，具有60万字/秒的高速处理能力。
采用了多子处理器分析模式，支持：英文字母（IP地址、Email、URL）、
数字（日期，常用中文数量词，罗马数字，科学计数法），中文词汇（姓名、地名处理）等分词处理。
 * @author iechenyb 
 */  
public class KAnalyzerExtendDemo {  
  
    public static void main(String[] args) throws FileNotFoundException {  
    	String words = "我要开户啊！";//"这是许林或许增强或陈远豹或者何亚飞的杯子";  
    	analyzer(words);
    }  
    public static  String analyzer(String sentence) throws FileNotFoundException{
        StringReader reader = new StringReader(sentence);  
        IKConfiguration config = IKConfiguration.getInstance();  
        config.setUseDict(true);  
        config.setUseStopword(true); 
        System.out.println("xx="+config.getExtDictionarys());
        System.out.println(config.getExtStopWordDictionarys());
       /* Object obj[] = config.getExtStopWordDictionarys().toArray();
        for(int i=0;i<obj.length;i++){
        	System.out.println(obj[i].toString());
        }*/
        IKSegmenter segmenter = new IKSegmenter(reader, true);//config  
        Lexeme lexeme = null;  
        StringBuffer sb = new StringBuffer("");
        try {  
            while ((lexeme = segmenter.next()) != null) {  
                System.out.print(lexeme.getLexemeText() + "|"); 
                sb.append(lexeme.getLexemeText() + "|");
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        System.out.println();  
        return sb.toString();
    }
}  
