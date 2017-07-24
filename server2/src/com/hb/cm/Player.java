package com.hb.cm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Player extends Thread {
	Socket s;
	Server server;
	String msg, name;
	ObjectInputStream ois;
	BufferedWriter bw;
	public Player() {}
	public Player(Socket s, Server server) {
		super();
		this.s = s;
		this.server = server;
		try {
			ois = new ObjectInputStream(s.getInputStream());
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	@Override
	public void run() {
		while(true){
			try {
				Protocol protocol = (Protocol)ois.readObject();
				if (protocol.getCmd()==100){
					name = protocol.getMsg();
				}
				server.sendMsg(protocol);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
