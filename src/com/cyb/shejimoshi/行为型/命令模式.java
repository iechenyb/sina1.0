package com.cyb.shejimoshi.行为型;

interface Command {
	public void exe();
}

class MyCommand implements Command {

	private Receiver receiver;

	public MyCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void exe() {
		receiver.action();
	}
}

class Receiver {
	public void action() {
		System.out.println("command received!");
	}
}

class Invoker {

	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	public void action() {
		command.exe();
	}
}

public class 命令模式 {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command cmd = new MyCommand(receiver);
		Invoker invoker = new Invoker(cmd);
		invoker.action(); // 可以连接多个命令接收者
	}
}
