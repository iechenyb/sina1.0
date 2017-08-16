package com.cyb.image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月16日
 */
public class RecognizeImage2 {
	Log log = LogFactory.getLog(RecognizeImage2.class);
	 public static void main(String[] args) throws IOException, URISyntaxException {  
		 File imageFile = new File("d:\\tmp\\veriCode.png");  
	       Tesseract tessreact = new Tesseract();  
	       tessreact.setDatapath("C:/tessdata");  
	       try {  
	           String result = tessreact.doOCR(imageFile);  
	           System.out.println("\n---- RESULTS: ------- \n" + result);  
	       } catch (TesseractException e) {  
	           System.err.println(e.getMessage());  
	       }  
	   }   
	       
}
