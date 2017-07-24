package com.hb.am;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 스레드 + 무한루프
public class Ex03_1 implements Runnable {
	ServerSocket ss;
	Socket s;
	public Ex03_1() {
		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			try {
				s = ss.accept();
				System.out.println("ip : "+s.getInetAddress().getHostAddress());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Ex03_1()).start();
	}

	
}
