package com.cyb.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectFileUtils {
	 public static void writeObjectToFile(Object obj)
	    {
	        File file =new File("test.dat");
	        FileOutputStream out;
	        try {
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut=new ObjectOutputStream(out);
	            objOut.writeObject(obj);
	            objOut.flush();
	            objOut.close();
	            System.out.println("write object success!");
	        } catch (IOException e) {
	            System.out.println("write object failed");
	            e.printStackTrace();
	        }
	    }
	 public static Object readObjectFromFile()
	    {
	        Object temp=null;
	        File file =new File("test.dat");
	        FileInputStream in;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            temp=objIn.readObject();
	            objIn.close();
	            System.out.println("read object success!");
	        } catch (IOException e) {
	            System.out.println("read object failed");
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return temp;
	    }
	 public static void writeObjectToFile(Object obj,String path) throws IOException
	    {
	        File file =new File(path);
	        if(!file.exists()) file.createNewFile();
	        FileOutputStream out;
	        try {
	            out = new FileOutputStream(file);
	            ObjectOutputStream objOut=new ObjectOutputStream(out);
	            objOut.writeObject(obj);
	            objOut.flush();
	            objOut.close();
	        } catch (IOException e) {
	            System.out.println("write object failed");
	            e.printStackTrace();
	        }
	    }
	 public static Object readObjectFromFile(String path)
	    {
	        Object temp=null;
	        File file =new File(path);
	        FileInputStream in;
	        try {
	            in = new FileInputStream(file);
	            ObjectInputStream objIn=new ObjectInputStream(in);
	            temp=objIn.readObject();
	            objIn.close();
	        } catch (IOException e) {
	            System.out.println("read object failed");
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return temp;
	    }
}
