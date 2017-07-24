package com.hb.am;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player extends Thread {
	Socket s;
	Server server;
	BufferedReader br;
	BufferedWriter bw;
	String msg;
	String ip;
	public Player() {}
	
	public Player(Socket s, Server server) {
		this.s = s;
		this.server = server;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			ip = s.getInetAddress().getHostAddress();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				msg = br.readLine();
				if (msg.equalsIgnoreCase("exit")){
					String str = "bye~~"+System.getProperty("line.separator");
					bw.write(str);
					bw.flush();
					server.delPlayer(this);
					break;
				} else {
					server.sendMsg(ip+" : "+msg);
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("2 :"+e);
				break;
			}
		}
	}
}