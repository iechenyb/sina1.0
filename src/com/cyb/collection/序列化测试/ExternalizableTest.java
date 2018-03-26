package com.cyb.collection.序列化测试;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * @descripiton Externalizable接口的使用
 * 
 * @author iechenyb
 * @date 2017-10-15
 *
 */
public class ExternalizableTest implements Externalizable {

	private transient String content = "是的，我将会被序列化，不管我是否被transient关键字修饰";

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(content);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		content = (String) in.readObject();
	}

	public static void main(String[] args) throws Exception {

		ExternalizableTest et = new ExternalizableTest();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File("c:/test")));
		out.writeObject(et);

		ObjectInput in = new ObjectInputStream(new FileInputStream(new File("c:/test")));
		et = (ExternalizableTest) in.readObject();
		System.out.println(et.content);

		out.close();
		in.close();
	}
}