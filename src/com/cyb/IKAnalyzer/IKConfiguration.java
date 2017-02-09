package com.cyb.IKAnalyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.InvalidPropertiesFormatException;  
import java.util.List;  
import java.util.Properties;  
  




import org.wltea.analyzer.cfg.Configuration;  
  
/** 
 * 描述:IKAnalyzer配置文件.需要实现Configuration接口 
 * 主要提供各种词典的文件路径 
 * 单例模式 
 * @author xuzengqiang 
 * @since 2015-1-14 13:53:56 
 */  
public class IKConfiguration implements Configuration {  
  
    /** 
     * 描述：主词典目录 
     */  
    private static final String PATH_DIC_MAIN = "com/cyb/analyzer/dic/main2012.dic";  
    /** 
     * 描述：量词词典目录 
     */  
    private static final String PATH_DIC_QUANTIFIER = "com/cyb/analyzer/dic/quantifier.dic";  
    /** 
     * 描述：配置文件目录com/cyb/IKAnalyzer/ 
     */  
    private static final String CONFIG_FILE_NAME = "IKAnalyzer.cfg.xml";  
    /** 
     * 描述：扩展词典key名称 
     */  
    private static final String EXT_DICT = "ext_dict";  
    /** 
     * 描述：禁用词典的key名称 
     */  
    private static final String EXT_STOP = "ext_stopwords";  
    /** 
     * 描述：配置文件属性对象 
     */  
    private Properties props;  
    /** 
     * 描述：是否使用智能分词 
     */  
    private boolean useSmart;  
    /** 
     * 描述：是否使用扩展停止词典(默认使用) 
     */  
    private boolean useStopword = Boolean.TRUE;  
    /** 
     * 描述：是否使用扩展词典(默认使用) 
     * @return 
     */  
    private boolean useDict = Boolean.TRUE;  
  
    /** 
     * 描述：提供外部接口 
     * @return 
     * @throws FileNotFoundException 
     */  
    public static IKConfiguration getInstance() throws FileNotFoundException {  
        return new IKConfiguration();  
    }  
  
    /** 
     * 描述：配置文件读取 
     * @throws FileNotFoundException 
     */  
	private IKConfiguration() throws FileNotFoundException {  
        this.props = new Properties();  
        InputStream input = IKConfiguration.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);  
        if (input == null)  {
        	System.out.println("主配置文件为空！");
            return;
        }  
        try {  
            this.props.loadFromXML(input);  
        } catch (InvalidPropertiesFormatException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  public static void main(String[] args) {
	try {
		new IKConfiguration();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
  }
    public boolean useSmart() {  
        return this.useSmart;  
    }  
  
    public void setUseSmart(boolean useSmart) {  
        this.useSmart = useSmart;  
    }  
  
    public boolean isUseDict() {  
        return useDict;  
    }  
  
    public void setUseDict(boolean useDict) {  
        this.useDict = useDict;  
    }  
  
    public boolean isUseStopword() {  
        return useStopword;  
    }  
  
    public void setUseStopword(boolean useStopword) {  
        this.useStopword = useStopword;  
    }  
      
    /** 
     * 描述：获取主词典目录 
     */  
    public String getMainDictionary() {  
        return PATH_DIC_MAIN;  
    }  
  
    /** 
     * 描述：获取量词词典目录 
     */  
    public String getQuantifierDicionary() {  
        return PATH_DIC_QUANTIFIER;  
    }  
  
    /** 
     * 描述：获取扩展词典的路径,当不需要使用扩展词典,返回空 
     */  
    public List<String> getExtDictionarys() {  
        if (!isUseDict()) {  
            return null;  
        }  
        List<String> extDictFiles = new ArrayList<String>(2);  
        String extDictCfg = this.props.getProperty(EXT_DICT);  
        if (extDictCfg != null) {  
            String[] filePaths = extDictCfg.split(";");  
            if (filePaths != null) {  
                for (String filePath : filePaths) {  
                    if ((filePath != null) && (!("".equals(filePath.trim())))) {  
                        extDictFiles.add(filePath.trim());  
                    }  
                }  
            }  
        }  
        return extDictFiles;  
    }  
  
    /** 
     * 描述：获取禁止词典的路径.当不需要使用时返回空 
     */  
    public List<String> getExtStopWordDictionarys() {  
        if (!isUseStopword()) {  
            return null;  
        }  
        List<String> extStopWordDictFiles = new ArrayList<String>(2);  
        String extStopWordDictCfg = this.props.getProperty(EXT_STOP);  
        if (extStopWordDictCfg != null) {  
            String[] filePaths = extStopWordDictCfg.split(";");  
            if (filePaths != null) {  
                for (String filePath : filePaths) {  
                    if ((filePath != null) && (!("".equals(filePath.trim())))) {  
                        extStopWordDictFiles.add(filePath.trim());  
                    }  
                }  
            }  
        }  
        return extStopWordDictFiles;  
    }  
}  
