package com.cyb.file;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年3月2日
 */
public class CutMerginTest {
	Log log = LogFactory.getLog(CutMerginTest.class);

	public static void main(String[] args) throws IOException, InterruptedException {
		String curPath = "d://data/tmp/";
		System.out.println(curPath);

		StringBuilder sb = new StringBuilder("");

		long originFileSize = 1024 * 1024 * 100;// 10M
		int blockFileSize = 1024 * 1024 * 15;// 1M

		// 生成一个大文件
		for (int i = 0; i < originFileSize; i++) {
			sb.append("A");
		}

		String fileName = curPath + "origin.myfile";
		System.out.println(fileName);
		System.out.println(FileUtils.write(fileName, sb.toString()));

		// 追加内容
		sb.setLength(0);
		sb.append("0123456789");
		FileUtils.append(fileName, sb.toString());

		FileUtils fileUtil = new FileUtils();
		// 将origin.myfile拆分
		fileUtil.splitBySize(fileName, blockFileSize);
		Thread.sleep(5000);// 稍等10秒，等前面的小文件全都写完
		// 合并成新文件
		fileUtil.mergePartFiles(curPath, ".part", blockFileSize,
				curPath + "new.myfile");
	}
}
