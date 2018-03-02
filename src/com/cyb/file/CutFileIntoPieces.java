package com.cyb.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年3月2日
 */
public class CutFileIntoPieces {
	Log log = LogFactory.getLog(CutFileIntoPieces.class);

	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		// 声明输入输出流对象
		int num = 0;// 生成文本的序号从0开始
		try {
			int temp = 0;// 初始化temp
			fis = new FileInputStream("f:/src/from.txt");
			// 产生输入流对象，并传入小说来源路径
			int m = 1 * 1024 * 1024;
			// 将文本分为1兆一份
			byte[] buffer = new byte[m];

			while (true) {
				for (num = 0;; num++) {
					String path = "f:/src/to" + num + ".txt";
					// 指定分割后文件的名称
					fos = new FileOutputStream(path);
					// 生成输出流对象

					temp = fis.read(buffer, 0, buffer.length);
					// 调用输入流的read()方法，三个参数分别代表
					// 读出来的数据存在buffer中，偏移量为0,一次读取多少byte位
					if (temp == -1) {
						break;
					} // 所有字节都读取完成后temp=-1,这里不判断temp 会越界
					fos.write(buffer, 0, temp);
					// 对象进行读写操作
				}
				if (temp == -1) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				fis.close();
				fos.close();
				// 关闭接口

				// 最后会生成一个空的txt文件 在这里把那个空的txt文件删除
				String path1 = "f:/src/to" + num + ".txt";
				File f = new File(path1);
				if (f.exists()) {
					f.delete();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
