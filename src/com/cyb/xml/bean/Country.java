package com.cyb.xml.bean;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年3月19日
 */
@XmlRootElement
public class Country {
	private String province;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public static void main(String[] args) throws JAXBException {

		Country country = new Country();
		country.setProvince("zhejiang");

		JAXBContext context = JAXBContext.newInstance(Country.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter writer = new StringWriter();
		marshaller.marshal(country, writer);
		System.out.println(writer.toString());

		Country1 country1 = new Country1();
		List<String> province = new ArrayList<String>();
		province.add("zhejiang");
		province.add("jiangsu");
		country1.setProvince(province);
		context = JAXBContext.newInstance(Country1.class);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		writer = new StringWriter();
		marshaller.marshal(country1, writer);
		System.out.println(writer.toString());
		
		Country2 country2 = new Country2();
		province = new ArrayList<String>();
		province.add("zhejiang");
		province.add("jiangsu");
		country2.setProvince(province);
		context = JAXBContext.newInstance(Country2.class);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		writer = new StringWriter();
		marshaller.marshal(country2, writer);
		System.out.println(writer.toString());
	}
}

@XmlRootElement
class Country1 {
	private List<String> province;

	public List<String> getProvince() {
		return province;
	}

	public void setProvince(List<String> province) {
		this.province = province;
	}

}

@XmlRootElement
class Country2
{
    @XmlElementWrapper(name="provinceList")
    private List<String> province;

	public List<String> getProvinceList() {
		return province;
	}

	public void setProvince(List<String> province) {
		this.province = province;
	}
}
