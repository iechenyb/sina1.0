package com.cyb.collection.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cyb.collection.po.Person;
import com.cyb.collection.utils.IdCardGenerator;
import com.cyb.collection.utils.RandomUtils;
import com.cyb.file.FileUtils;

public class PersonFactory {
	public static List<Person> list;
	public static String dataFile = "d:/data/persons.txt";
    public PersonFactory() throws IOException{
    	build(100);
    } 
	public static void build() throws IOException {
		build(100);
	}
	public static void build(int nums) throws IOException {
		list = new ArrayList<Person>(nums);
		for (int i = 0; i < nums; i++) {
			Person p = new Person();
			p.setId(RandomUtils.createRandomInt());
			p.setName(RandomUtils.getChineaseName());
			p.setPingyin(RandomUtils.getPingYin(p.getName()));
			p.setIdcard(IdCardGenerator.generate());
			p.setEmail(RandomUtils.getEmail(8, 15));
			p.setAge(RandomUtils.getNum(25, 45));
			list.add(p);
		}
		File file = new File(dataFile);
		if(!file.exists()){
			file.createNewFile();
		}
		String data = JSON.toJSONString(list);
		FileUtils.overideString2File(data, dataFile);
		System.out.println();
	}
	
	public static void main(String[] args) {
		try {
			PersonFactory.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
