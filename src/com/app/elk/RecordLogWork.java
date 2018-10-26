package com.app.elk;
import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.factory.DataRandomFactory;
import com.cyb.file.FileUtils;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月18日
 */
public class RecordLogWork {
	Log log = LogFactory.getLog(RecordLogWork.class);
	public static void main(String[] args) throws IOException {
		String file= "d:/data/logs/elkbase.log";
		File f = new File(file);
		if(!f.exists()){
			f.createNewFile();
		}
		for(int i=0;i<100;i++){
			try {
				String name = DataRandomFactory.getTel();
				FileUtils.append(file, name+"\n");
				System.out.println(name);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
