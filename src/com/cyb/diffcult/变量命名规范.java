package com.cyb.diffcult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年8月17日
 *1、首字母是英文字母、$和下划线，由字母、数字和下划线组成。  ［很常规］
  2、变量的命名遵循见名知义的原则。  ［很重要，比如名字就用 name ，而不是用a、b、c这样的命名，不然又要找找找］
  3、用驼峰命名法命名多个单词组成的变量名。  ［比如： sumScore ］
  4、变量名［方法名］首字母建议不用大写字母。   ［首字母大写一般是用来标识类名的］  －－ 看起来更规范而已
  5、变量名不要使用Java关键字
 */
public class 变量命名规范 {
	Log log = LogFactory.getLog(变量命名规范.class);
	String $123;
	//String 123$456;//数字开头不可以
	String onlyen;
	String qwe$12￥3;
	String _546kkd;
	//String 1523;//纯数字不可以
	//String case;//关键字不可以
}
