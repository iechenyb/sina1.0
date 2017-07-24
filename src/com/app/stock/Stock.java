package com.app.stock;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Table;
@Table(name="stock")
public class Stock implements Serializable{
	/**
	 * 
	 */
	@Column(name="id")
	public String id;
	@Column(name="name")
	public String name;
	@Column(name="code")
	public String code;
	@Column(name="exchange")
	public String exchange;
	@Column(name="classify")
	public String classify;//分类  农业 金融等
	@Column(name="province")
	public String province;//省份
	@Column(name="industry")
	public String industry;//A股 B股等
	@Column(name="timeofmarket")
	public String timeOfMarket;//上市时间
	//A股总股本,A股流通股本
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getTimeOfMarket() {
		return timeOfMarket;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public void setTimeOfMarket(String timeOfMarket) {
		this.timeOfMarket = timeOfMarket;
	}

	public Stock mapRow(ResultSet rs, int arg1) throws SQLException {
		    Stock userInfo = new Stock();
	        userInfo.setId(rs.getString("id"));
	        userInfo.setName(rs.getString("name"));
	        userInfo.setCode(rs.getString("code"));
	        userInfo.setExchange(rs.getString("exchange3"));
	        return userInfo;
	}
	public String toString(){
		return this.code+","+this.name+", "+this.industry+","+this.exchange;
	}
}
