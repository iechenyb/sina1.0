package com.cyb.image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月5日
 */
public class ImageIoStudy {
	Log log = LogFactory.getLog(ImageIoStudy.class);
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		File file = new File("d:\\data\\copy.jpg");
		ImageIO.setUseCache(false);
		BufferedImage bufferedImage = null;
		while (true) {
			bufferedImage = ImageIO.read(new FileInputStream(file));
			System.out.println(bufferedImage);
			Thread.sleep(100);
			bufferedImage.flush();
			bufferedImage  = null;
		}
		
	}
}
