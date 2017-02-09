package com.cyb.socket.many2many;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import net.sf.json.JSONObject;

public class HandlerMessageThread implements Runnable {
	public ServerSocket server = null;
	public Socket client = null;
	BufferedReader br = null;

	public HandlerMessageThread(ServerSocket server, Socket client) throws IOException {
		this.server = server;
		this.client = client;
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

	public void run() {
			String tmpMsg = "";	
			PrintWriter pw = null;
			try {
			
			System.out.println("�����Ϊ" + client + "��������Ϣ�����̣߳�");
			while (true) {
				Socket to = null;
				// ���ڷ��ͷ�����Ϣ,���Բ���Ҫװ����ô��io��ʹ�û�����ʱ��������Ҫע�����.flush()����
				String msg = br.readLine();
				if(!"".equals(msg)&&msg!=null){
					JSONObject msgObject = JSONObject.fromObject(msg);
					tmpMsg = msgObject.toString();
					to = Server.clients_map.get(msgObject.get("toSocket"));//��ȡ������ϢĿ�����
					pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(to.getOutputStream())), true);
					System.out.println(" ����������ת����:"+msgObject.get("fromSocket")+"���͸� "+msgObject.get("toSocket")+"����Ϣ��" + msgObject.get("content"));
					pw.println(new Date().toString()+msgObject.get("content"));
					pw.flush();
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("��Ϣ "+tmpMsg+" �����쳣������ͨ��ͨ���Ƿ�������");
			}
	}

}
