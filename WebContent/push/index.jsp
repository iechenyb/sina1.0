<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>netty-socketio-case</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<script src="socket.io.js"></script>
    <script src="jquery-1.7.2.min.js"></script>
	<script>
		$(function(){
		   //pushUrl = "http://"+ip+":"+port;
			var socket =  io.connect(pushUrl);
			 socket.on("pushpoint", function(data){
			 	//����pushpoint�¼�
				$('#x').text(data.x);
				$('#y').text(data.y);
			});
			    socket.on('connect', function() {
					console.log("���ӳɹ�");		
					//�״ν���ҳ�����ӳɹ���ӷ�������������ȡһ�����ݡ�			
					//socket.emit('getmsg', "�¼�����");					
				});
				socket.on("connecting", function() {
					console.log("��������");
				});
				socket.on('disconnect', function() {
					console.log("�Ͽ�����");
				});
				socket.on('connect_failed', function() {
					console.log("����ʧ��");
				});
				socket.on('error', function(data) {
					console.log("���Ӵ���" + data);
				});
				socket.on('message', function(data) {
					console.log("��Ϣ" + data);
				});
				socket.on('anything', function(data) {
					console.log("������Ϣ" + data);
				});
				socket.on('reconnect_failed', function() {
					console.log("����ʧ��");
				});
				socket.on('reconnect', function() {
					console.log("�ɹ�����");
				});
				socket.on('reconnecting', function(data) {
					console.log("��������" + data);
				});
		});
	</script>
</head>
<body>
	<div id="display" style="height:50px;background-color:gray;">
		<font size=50 >x=<span id="x">0</span>, y=<span id="y">0</span></font>
	</div>
</body>
</html>
