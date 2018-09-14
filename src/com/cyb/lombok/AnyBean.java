package com.cyb.lombok;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年9月5日
 */

@Data
public class AnyBean {
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String age;
	public static void main(String[] args) {
		AnyBean ab = new AnyBean();
	}
}
