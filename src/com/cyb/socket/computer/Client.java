package com.cyb.socket.computer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		try {
			for (int i = 0; i < 10000; i++) {
				Socket socket = null;
				BufferedReader br = null;
				PrintWriter pw = null;
				socket = new Socket("localhost", Server.PORT);
				System.out.println("Socket=" + socket);
				br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
						socket.getOutputStream())));//{name:chenyb,age:20,address:shanghai}
				String str0 =  "index" + i;
				pw.println(str0);
				pw.flush();
				String str = br.readLine();
				System.out.println("from server:"+str+"=="+str0);
				pw.println("END");
				pw.flush();
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
