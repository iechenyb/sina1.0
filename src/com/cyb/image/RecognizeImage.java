package com.cyb.image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asprise.util.ocr.OCR;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月16日
 */
public class RecognizeImage {
	Log log = LogFactory.getLog(RecognizeImage.class);
	 public static void main(String[] args) throws IOException, URISyntaxException {  
	        if(("1.4").compareTo(System.getProperty("java.vm.version")) > 0) {  
	            System.err.println("Warining: \n\nYou need Java version 1.4 or above for ImageIO to run this demo.");  
	            System.err.println("Your current Java version is: " + System.getProperty("java.vm.version"));  
	            System.err.println("\nSolutions: \n");  
	            System.err.println("(1) Download JRE/JDK version 1.4 or above; OR \n");  
	            System.err.println("(2) Run DemoUI, which can run on your current Java virtual machine.");  
	            System.err.println("    Double click the 'runDemoUI' to invoke it.\n");  
	            return;  
	        }  
	        String path = "d:\\tmp\\veriCode.png";        
	        File file = new File(path);  
	        BufferedImage image = ImageIO.read(file);  
	        String s = new OCR().recognizeEverything(image);  
	        System.out.println("\n---- RESULTS: ------- \n" + s);  
	    }  
}
