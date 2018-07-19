package com.cyb.password;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.jiami.MD5Util;

/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年10月16日
 */
public class PasswordUtils {
	Log log = LogFactory.getLog(PasswordUtils.class);
	private static final int ISVALID = 0;//密码合法
	private static final int ISNULL = 1;//为空
	private static final int LENGTH_ERROR=2;//长度超出指定边界
	private static final int LESS_THAN_THREE_CHAR=3;//必须包含三种字符
	private static final int ISNOTVALID = 4;//只能包含三类字符。
	private static final int MIN_LENGTH = 8;//最小长度
	private static final int MAX_LENGTH = 20;//最大长度
	private static final String useAbleLetChars = "[a-zA-Z]";//可允许的字符
	private static final String useAbleNUMChars = "[0-9]";
	private static final String useAbleSPEChars = "[#@-_!]";
	private static StringBuffer regEx = new StringBuffer("^[A-Za-z0-9!_-]{8,20}$");
	private static boolean beginByLetter = false;
	private static Map<Integer,String> ERR_MESSAGE = new HashMap<Integer,String>();
	static {
		//字母+数字+字符
		if(beginByLetter){
			regEx.insert(1,useAbleLetChars);//字母开头
		}
		ERR_MESSAGE.put(ISVALID, "密码合法！");
		ERR_MESSAGE.put(ISNULL, "密码不能为空！");
		ERR_MESSAGE.put(LENGTH_ERROR, "密码长度不合法，最小长度为"+MIN_LENGTH+",最大长度为"+MAX_LENGTH+"！");
		ERR_MESSAGE.put(LESS_THAN_THREE_CHAR, "密码必须同时包含字母、特殊字符("+useAbleSPEChars.substring(1, useAbleSPEChars.length()-1)+")和数字三种字符！");
		ERR_MESSAGE.put(ISNOTVALID, "必须同时且只能包含三类字符！最小长度为"+MIN_LENGTH+","+"最大长度为"+MAX_LENGTH+"!");
	}
			
	/**
	 * 
	 *作者 : iechenyb<br>
	 *方法描述: 验证规则至少包含字母、
	 *数字和特殊字符三类数字<br>
	 *创建时间: 2017年7月15日
	 *@param password
	 *@return
	 */
    public static int isValidatePWD(String password){
    	if(StringUtils.isEmpty(password)){
    		return ISNULL;//密码不能为空
    	}else{
	    	int len = password.length();
	    	//长度超出指定边界
	    	if(len<MIN_LENGTH||len>MAX_LENGTH){
	    		return LENGTH_ERROR;
	    	}
	    	if(!containDigit(password)||!containLetter(password)||!containSpecialCharacter(password)){
	    		return LESS_THAN_THREE_CHAR;//没有包含其中一种字符
	    	}
	    	Pattern pattern = Pattern.compile(regEx.toString());
	    	Matcher matcher = pattern.matcher(password);
	    	// 字符串是否与正则表达式相匹配
	    	if(matcher.matches()){
	    		return ISVALID;
	    	}else{
	    		return ISNOTVALID;
	    	}
    	}
    }
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 是否包字母<br>
     *创建时间: 2017年7月15日
     *@param password
     *@return
     */
    public static boolean containLetter(String password){
    	Pattern pattern = Pattern.compile(useAbleLetChars);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.find();
    }
    
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 是否包含数字<br>
     *创建时间: 2017年7月15日
     *@param password
     *@return
     */
    public static boolean containDigit(String password){
    	Pattern pattern = Pattern.compile(useAbleNUMChars);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.find();
    }
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 是否包含指定的特殊字符<br>
     *创建时间: 2017年7月15日
     *@param password
     *@return
     */
    public static boolean containSpecialCharacter(String password){
    	Pattern pattern = Pattern.compile(useAbleSPEChars);
    	Matcher matcher = pattern.matcher(password);
    	return matcher.find();
    }
    
    public static void checkPassword(String password){
    	System.out.println(password+"\t"+ERR_MESSAGE.get(isValidatePWD(password)));
    }
    
	public static void main(String[] args) {
		System.out.println(regEx.toString());
		checkPassword("12ab123456789123456789!_");//密码合法，长度不够
		checkPassword("12ab!_");//密码合法，长度不够
		checkPassword("12345678");//纯数字弱密码 不允许
		checkPassword("abcdefgh");//纯字母弱密码 不允许
		checkPassword("abcdefgh45!,");//不允许的字符不能出现
		checkPassword("abcdefgh_5");//字母开头不允许
		checkPassword("5abcdefgh_");//数字开头，不允许
		checkPassword("!abcdefgh5");
		checkPassword("abcdefgh5_");
		checkPassword("ABC!DEFGH1_");
		checkPassword("ABCDEFGH!");
		/*String testStr = "A1212B_!";
		testStr = "aa";
		testStr = "11";
		testStr="-!#";
		System.out.println(containDigit(testStr));
		System.out.println(containSpecialCharacter(testStr));
		System.out.println(containLetter(testStr));#@-_*/
		try {
			System.out.println(MD5Util.md5Encode("chen0601#"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
