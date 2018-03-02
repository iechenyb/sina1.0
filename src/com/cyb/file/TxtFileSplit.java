package com.cyb.file;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年3月2日
 */
public class TxtFileSplit {
	Log log = LogFactory.getLog(TxtFileSplit.class);
	public static void splitFileByLines(String fileReadName, String textSize) {
        File file = new File(fileReadName);
        BufferedReader bf = null;
        try {
            System.out.println("begin");
            long stime = System.currentTimeMillis();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            bf = new BufferedReader(isr);
            String tempString = null;
            // 行数
            BigInteger line = new BigInteger("1");
            // 分割后的每个文件的行数
            BigInteger txtSize = new BigInteger(textSize);
            String fileWriteName = null;
            while ((tempString = bf.readLine()) != null) {
                // 当行数整除每个文件的数量之后就是文件的序号
                BigInteger txtNo = line.divide(txtSize).add(new BigInteger("1"));
                // 根据文件的序号来命名分割后的文件
                fileWriteName = "D:\\SQLiteData\\parts\\fileSplit\\" + txtNo + ".txt";
                FileWriter writer = new FileWriter(fileWriteName, true);
                writer.write(tempString);
                writer.write("\n");
                writer.close();
                // 读取一行就给行号加一
                line = line.add(new BigInteger("1"));
            }
            bf.close();
            long etime = System.currentTimeMillis();
            // 计算总共所用时间
            long spendTime = (etime - stime) / 60 / 1000;
            System.out.println("花费时间为" + spendTime + "分");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static void main(String[] args) {
        // 这是源文件
        String fileReadName = "D:\\SQLiteData\\parts\\MES_LT_TP_ALL.sql";
        // 定义每个文件的行数，这里是80万行一个文件
        String textSize = "800000";
        splitFileByLines(fileReadName, textSize);
    }
}
