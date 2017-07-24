package com.hb.cm;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	ServerSocket ss;
	Socket s;
	ArrayList<Player> list;
	public Server() {
		try {
			ss = new ServerSocket(7777);
			list = new ArrayList<>();
			System.out.println("서버 대기중...");
			new Thread(this).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				s = ss.accept();
				Player player = new Player(s,this);
				list.add(player);
				new Thread(player).start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void sendMsg(Protocol p){
		try {
			int cmd = p.getCmd();
			if (cmd == 100){
				System.out.println("1");
				for (Player k : list) {
					k.bw.write(p.getMsg()+"님 입장"+System.getProperty("line.separator"));
					k.bw.flush();
				}
			} else if (cmd==200){
				System.out.println("2");
				for (Player k : list) {
					k.bw.write(k.name+":"+p.getMsg()+System.getProperty("line.separator"));
					k.bw.flush();
				}
			} else if (cmd==300){
				System.out.println("3");
				for (Player k : list) {
					k.bw.write(p.getMsg()+"님 퇴장"+System.getProperty("line.separator"));
					k.bw.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("3:"+e);
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
