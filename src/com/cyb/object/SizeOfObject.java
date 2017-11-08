package com.cyb.object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.collection.po.User;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2017年11月8日
 */
public class SizeOfObject {
	Log log = LogFactory.getLog(SizeOfObject.class);
	static Instrumentation inst;

	public static void main(String[] args) throws IllegalAccessException, IOException {
		User object = new User();
		int i=0;
		long size = size(i);
		System.out.println(size);
	}

	public static void premain(String args, Instrumentation instP) {
		inst = instP;
	}

	/**
	 * 直接计算当前对象占用空间大小，包括当前类及超类的基本类型实例字段大小、<br>
	 * </br>
	 * 引用类型实例字段引用大小、实例基本类型数组总占用空间、实例引用类型数组引用本身占用空间大小;<br>
	 * </br>
	 * 但是不包括超类继承下来的和当前类声明的实例引用字段的对象本身的大小、实例引用数组引用的对象本身的大小 <br>
	 * </br>
	 *
	 * @param obj
	 * @return
	 */
	public static long sizeOf(Object obj) {
		return inst.getObjectSize(obj);
	}

	/**
	 * 递归计算当前对象占用空间总大小， 包括当前类和超类的实例字段 大小以及实例字段引用对象大小
	 *
	 * @param objP
	 * @return
	 * @throws IllegalAccessException
	 */
	public static long fullSizeOf(Object objP) throws IllegalAccessException {
		Set<Object> visited = new HashSet<Object>();
		Deque<Object> toBeQueue = new ArrayDeque<>();
		toBeQueue.add(objP);
		long size = 0L;
		while (toBeQueue.size() > 0) {
			Object obj = toBeQueue.poll();
			// sizeOf的时候已经计基本类型和引用的长度，包括数组
			size += skipObject(visited, obj) ? 0L : sizeOf(obj);
			Class<?> tmpObjClass = obj.getClass();
			if (tmpObjClass.isArray()) {
				// [I , [F 基本类型名字长度是2
				if (tmpObjClass.getName().length() > 2) {
					for (int i = 0, len = Array.getLength(obj); i < len; i++) {
						Object tmp = Array.get(obj, i);
						if (tmp != null) {
							// 非基本类型需要深度遍历其对象
							toBeQueue.add(Array.get(obj, i));
						}
					}
				}
			} else {
				while (tmpObjClass != null) {
					Field[] fields = tmpObjClass.getDeclaredFields();
					for (Field field : fields) {
						if (Modifier.isStatic(field.getModifiers()) // 静态不计
								|| field.getType().isPrimitive()) { // 基本类型不重复计
							continue;
						}

						field.setAccessible(true);
						Object fieldValue = field.get(obj);
						if (fieldValue == null) {
							continue;
						}
						toBeQueue.add(fieldValue);
					}
					tmpObjClass = tmpObjClass.getSuperclass();
				}
			}
		}
		return size;
	}

	/**
	 * String.intern的对象不计；计算过的不计，也避免死循环
	 *
	 * @param visited
	 * @param obj
	 * @return
	 */
	static boolean skipObject(Set<Object> visited, Object obj) {
		if (obj instanceof String && obj == ((String) obj).intern()) {
			return true;
		}
		return visited.contains(obj);
	}

	/**
	 * 计算一个对象所占字节数
	 * 
	 * @param o对象，该对象必须继承Serializable接口即可序列化
	 * @return
	 * @throws IOException
	 */
	public static int size(final Object o) throws IOException {
		int max = 100*1024*1024;//100兆大对象
		if (o == null) {
			return 0;
		}
		ByteArrayOutputStream buf = new ByteArrayOutputStream(max);
		ObjectOutputStream out = new ObjectOutputStream(buf);
		out.writeObject(o);
		out.flush();
		buf.close();
		return buf.size();
	}

	/**
	 * 赋值对象，返回对象的引用，如果参数o为符合对象，则内部每一个对象必须可序列化
	 * 
	 * @param o对象，该对象必须继承Serializable接口即可序列化
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object copy(final Object o) throws IOException, ClassNotFoundException {
		if (o == null) {
			return null;
		}
		ByteArrayOutputStream outbuf = new ByteArrayOutputStream(4096);
		ObjectOutput out = new ObjectOutputStream(outbuf);
		out.writeObject(o);
		out.flush();
		outbuf.close();
		ByteArrayInputStream inbuf = new ByteArrayInputStream(outbuf.toByteArray());
		ObjectInput in = new ObjectInputStream(inbuf);
		return in.readObject();
	}
}
