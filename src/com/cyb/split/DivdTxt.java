package com.cyb.split;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cyb.file.FileUtils;

public class DivdTxt {
	static String dataFile = FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"data.txt" ;
	public static void main(String[] args) throws IOException {
//		ReadData();
		splitTxt(4);
		 File file = new File(dataFile);
		 split(file);
	}
	public static void split(File file) throws IOException{
		  File dir = new File(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"temp");
		  dir.mkdir();
		  
		  // 计算每一份的大小
		  long partLen = (file.length() + 4) / 5;  // (10 + 4) / 5 = 2,  (14 + 4) / 5 = 3
		  int fileNum = 1;
		  long len = 0;
		  
		  BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		  BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(dir, fileNum + "")));
		  
		  int b;
		  while((b = bis.read()) != -1) {
		   if(len++ == partLen){
		    bos.close();
		    bos = new BufferedOutputStream(new FileOutputStream(new File(dir, ++fileNum + "")));
		    len = 0;
		   }
		   bos.write(b);
		  }
		  bis.close();
		  bos.close();
		  
		  System.out.println(file.delete());
		  dir.renameTo(file);
	}

	public static void ReadData() {  
        try {  
            FileReader read = new FileReader(dataFile);  
            BufferedReader br = new BufferedReader(read);  
            String row;  
            int rownum = 1;  
            FileWriter fw1 = new FileWriter(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"/text1.txt");  
            FileWriter fw2 = new FileWriter(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"/text2.txt");  
            FileWriter fw3 = new FileWriter(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"/text3.txt");  
            FileWriter fw4 = new FileWriter(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"/text4.txt");  
            while ((row = br.readLine()) != null) {  
                if (rownum % 4 == 1) {  
                    fw1.append(row + "\r\n");  
                } else if (rownum % 4 == 2) {  
                    fw2.append(row + "\r\n");  
                } else if (rownum % 4 == 3) {  
                    fw3.append(row + "\r\n");  
                } else if (rownum % 4 == 0) {  
                    fw4.append(row + "\r\n");  
                }  
                rownum++;  
            }  
            fw1.close();  
            fw2.close();  
            fw3.close();  
            fw4.close();  
  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	public static void splitTxt(int count) {  
        try {  
            FileReader read = new FileReader(dataFile);  
            BufferedReader br = new BufferedReader(read);  
            String row;  
            List<FileWriter> flist = new ArrayList<FileWriter>();  
            for (int i = 0; i < count; i++) {  
                flist.add(new FileWriter(FileUtils.getAbsolutePathAtClass(DivdTxt.class)+"text" + i + ".txt"));  
            }  
            int rownum = 1;// 计数器  
            while ((row = br.readLine()) != null) {  
                flist.get(rownum % count).append(row + "\r\n");  
                rownum++;  
            }  
            for (int i = 0; i < flist.size(); i++) {  
                flist.get(i).close();  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
}
