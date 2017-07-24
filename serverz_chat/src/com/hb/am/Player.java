package com.hb.am;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Player extends Thread {
	Socket s;
	ChatServer server;
	String msg, name;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	public Player() {}
	public Player(Socket s, ChatServer server) {
		super();
		this.s = s;
		this.server = server;
		try {
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
