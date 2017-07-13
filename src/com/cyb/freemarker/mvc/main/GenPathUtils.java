package com.cyb.freemarker.mvc.main;

import java.io.File;

import com.cyb.file.FileUtils;
import com.cyb.freemarker.mvc.Contants;

public class GenPathUtils {
  public static void main(String[] args) {
	  Contants.basePackagePath="base.org.cn";
	  String sysPath = System.getProperty("user.dir")+File.separator+"src"+File.separator+Contants.basePackagePath.replace(".", File.separator);
	  System.out.println(sysPath);
	  FileUtils.genFileDir(sysPath);
  }
}
