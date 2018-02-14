package com.app.md5file;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyb.file.FileUtils;
import com.cyb.jiami.MD5Util;
/**
 *作者 : iechenyb<br>
 *类描述: 从指定目录递归查询指定md5码的文件信息<br>
 *创建时间: 2018年2月14日
 */
interface Finder{
	public boolean compare(String target,File from);
	public void setMd5Map(Map<String, String> md5Map);
} 
class MD5Finder implements Finder{
	Map<String,String> md5Map;
	@Override
	public boolean compare(String targetFileMD5, File from) {
		try {
			return md5Map.containsKey(MD5Util.getMd5ByFile(from));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	public Map<String, String> getMd5Map() {
		return md5Map;
	}
	public void setMd5Map(Map<String, String> md5Map) {
		this.md5Map = md5Map;
	}
	
}
public class FileMD5Finder {
	static Map<String,String> md5Map = new HashMap<String,String>();
	static int count =0;
	static Finder finder = new MD5Finder();
	static File log;
	static String logPath;
	static String resultPath;
	static String targetFilePath;
	static String findFilePath;
	public static void main(String[] args) {
		try {
			logPath=args[0];
			resultPath=args[1];
			targetFilePath = args[2];
			findFilePath=args[3];
			log = new File(logPath);
			if(!log.exists()){
				log.createNewFile();
			}
			count=0;
			initTarget();//读取目标md5码
			finder.setMd5Map(md5Map);
			findTargetFile(findFilePath);//是否写结果到文件
			if((count-1)>0){
				showLog("发现"+(count-1)+"项匹配！");
			}else{
				showLog("尚未发现匹配项！");
			}
			showLog("查找结束...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void showLog(String msg){
		System.out.println(msg);
		FileUtils.appendString2File(msg+"\n", log.getAbsolutePath());
	}
	private static List<File> findTargetFile(String fileDir) throws FileNotFoundException {  
        List<File> fileList = new ArrayList<File>();  
        File file = new File(fileDir);  
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹  
        if (files == null) {// 如果目录为空，直接退出  
            return null;
        }  
        // 遍历，目录下的所有文件  
        for (File f : files) {  
            if (f.isFile()) {  
                fileList.add(f); 
                showLog(f.getAbsolutePath()+"#"+MD5Util.getMd5ByFile(f));
            } else if (f.isDirectory()) {  
            	showLog(f.getAbsolutePath());  
                findTargetFile(f.getAbsolutePath());  
            }  
        }  
        File rsf = new File(resultPath);
        if(rsf.exists()){
        	rsf.delete();
        }
        try {
			rsf.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        List<String> finderRes = new ArrayList<String>();
        for (File f1 : fileList) {  
        	try {
        		if(f1.isDirectory()){continue;}
				if(md5Map.containsKey(MD5Util.getMd5ByFile(f1))){
					count++;
					finderRes.add(f1.getAbsolutePath()+"#"+MD5Util.getMd5ByFile(f1));
					FileUtils.appendString2File(f1.getAbsolutePath()+"#"+MD5Util.getMd5ByFile(f1)+"\n", rsf.getAbsolutePath());
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }  
        return fileList;
    } 
	public static void initTarget(){
		List<String> md5s = FileUtils.readFileToList(targetFilePath);
		showLog("md5s is "+md5s);
		for(String md5:md5s){
			md5Map.put(md5, md5);
			md5Map.put(md5.toUpperCase(), md5.toUpperCase());
		}
	}
}
