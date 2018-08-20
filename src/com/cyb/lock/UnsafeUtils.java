package com.cyb.lock;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.poi.User;

import sun.misc.Unsafe;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年8月14日
 */
public class UnsafeUtils {
	static Log log = LogFactory.getLog(UnsafeUtils.class);
	private static sun.misc.Unsafe UNSAFE;
	private static int byteArrayBaseOffset;

	@SuppressWarnings({ "deprecation" })
	public static void modifyArrTest()
			throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
		System.out.println(UNSAFE);

		byte[] data = new byte[10];
		System.out.println(Arrays.toString(data));
		byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);

		System.out.println(byteArrayBaseOffset);
		UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
		UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
		System.out.println(Arrays.toString(data));
		long parkBlockerOffset = 
		UNSAFE.objectFieldOffset
        (java.lang.Thread.class.getDeclaredField("parkBlocker"));
		//获取某个线程的阻塞对象
		UNSAFE.putObject(Thread.currentThread(), parkBlockerOffset, new User());
		Object obj=UNSAFE.getObjectVolatile(Thread.currentThread(), parkBlockerOffset);
		System.out.println("阻塞对象："+obj);
	}

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, InstantiationException,
			IllegalArgumentException, IllegalAccessException {
		final Unsafe unsafe = UnsafeUtils.getInstance();
		modifyArrTest();
		System.out.println("unsafe object is " + unsafe);
		long valueOffset = unsafe.objectFieldOffset(AtomicBoolean.class.getDeclaredField("value"));
		System.out.println("valueOffset:" + valueOffset);// 显示value字段在内存的地址偏移量
		User user = new User();
		Field field = user.getClass().getDeclaredField("name");
		// 获取对象属性在内存中相对对象的偏移地址
		User instance = (User) unsafe.allocateInstance(User.class);
		System.out.println("直接创建对象:" + instance);
		/*
		 * 第一个参数是是要修改的对象，第二个参数是相对对象的内存偏移地址， 第三个参数是程序员预期的值，就是当前值，第四个参数是程序员想改成的值
		 */
		// 原子更新， 直接根据内存地址修改*/
		long nameOffset = unsafe.objectFieldOffset(field);
		System.out.println("nameOffset:" + nameOffset);
		boolean rs = unsafe.compareAndSwapObject(user, nameOffset, "old", "new");
		System.out.println(rs + " 修改后的值：" + user.getName());// 失败
		// User类的一个属性名，将175改成10 不用枷锁
		user.setName("chenyb");
		UnsafeUtils.update(user, "name", "chenyb", "chenyuanbao");
		System.out.println("修改后的值：" + user.getName());
		// unsafe.freeMemory(nameOffset);

		unsafe.putOrderedObject(user, nameOffset, "zhangsan");
		System.out.println("修改后的值：" + user.getName());
		int scale = unsafe.arrayIndexScale(long[].class);// 8获取一个元素的大小
		System.out.println("long scale :" + scale);
		scale = unsafe.arrayIndexScale(User[].class);// 获取一个元素的大小
		System.out.println("user scale :" + scale);
		scale = unsafe.arrayIndexScale(int[].class);// 获取一个元素的大小
		System.out.println("int scale :" + scale);
		scale = unsafe.arrayIndexScale(byte[].class);// 获取一个元素的大小
		System.out.println("byte scale :" + scale);
		scale = unsafe.arrayIndexScale(char[].class);// 获取一个元素的大小
		System.out.println("char scale :" + scale);
		scale = unsafe.arrayIndexScale(short[].class);// 获取一个元素的大小
		System.out.println("short scale :" + scale);
		scale = unsafe.arrayIndexScale(double[].class);// 获取一个元素的大小
		System.out.println("double scale :" + scale);
		int a = Integer.numberOfLeadingZeros(8);// 返回最高位0的个数，参数必须是2的指数
		System.out.println(a);
		/**
		 * 1-31 2-30 4-29 8-28 ... 判断一个数是2的指数方式 scale & (scale - 1)
		 */
		System.out.println(1 << 3);// 1*2(3)
		System.out.println(2 << 3);// 2*2(3)
		System.out.println(3 << 3);// 3*2(3)
		System.out.println(4 << 3);// 4*2(3)
		int shift = 31 - Integer.numberOfLeadingZeros(scale);
		System.out.println(shift);// scale对应2的指数
		/**
		 * 比如 scale=1 则shift=0 比如 scale=2 则shift=1 比如 scale=4 则shift=2 比如
		 * scale=8 则shift=3 ...
		 */

		Thread currThread = Thread.currentThread();

		unsafe.unpark(currThread);
		/*
		 * unsafe.unpark(currThread);//多次调用unpark的效果和调用一次unpark的效果一样
		 * unsafe.unpark(currThread);//必须先unpark，然后park
		 * 
		 * unsafe.park(false, 0);//park方法用于Block current thread
		 * unsafe.unpark(currThread);//没有用
		 * 
		 */ unsafe.park(false, 0);// park方法用于Block current thread
		System.out.println("SUCCESS!!!");
		// 相对时间后面的参数单位是纳秒
		unsafe.park(false, 5000000000l);// 阻塞5秒返回
		System.out.println("SUCCESS!!!");
		long time = System.currentTimeMillis() + 3000;
		// 绝对时间后面的参数单位是毫秒
		unsafe.park(true, time);
	}

	public static Unsafe getInstance() {
		Field f = null;
		sun.misc.Unsafe unsafe = null;
		try {
			// theUnsafe是unsafe 内部一个属性名
			f = Unsafe.class.getDeclaredField("theUnsafe");
			f.setAccessible(true);
			// 得到Unsafe类的实例
			unsafe = (Unsafe) f.get(null);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UNSAFE = unsafe;
		return unsafe;
	}

	public static boolean update(Object obj, String FieldName, Object old, Object news) {
		Unsafe unsafe = UNSAFE;
		Field field;
		try {
			field = obj.getClass().getDeclaredField(FieldName);
			long sexOffset = unsafe.objectFieldOffset(field);

			if (old instanceof Integer) {

				int aa = (Integer) old;
				int bb = (Integer) news;

				if (unsafe.compareAndSwapInt(obj, sexOffset, aa, bb)) {
					log.info("更改成功！");
					return true;

				} else {
					log.info("更改失败！");
					return false;
				}
			}

			if (old instanceof Long) {

				long cc = (Long) old;
				long dd = (Long) news;

				if (unsafe.compareAndSwapLong(obj, sexOffset, cc, dd)) {
					log.info("更改成功！");
					return true;
				} else {
					log.info("更改失败！");
					return false;
				}
			}

			if (old instanceof String) {
				if (unsafe.compareAndSwapObject(obj, sexOffset, old, news)) {
					log.info("更改成功！");
					return true;
				} else {
					log.info("更改失败！");
					return false;
				}
			}

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
