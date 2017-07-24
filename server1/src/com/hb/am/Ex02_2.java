package com.hb.am;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 서버 스레드 처리
public class Ex02_2 implements Runnable {
	ServerSocket ss;
	Socket s;
	public Ex02_2() {
		try {
			// 서버 소켓 생성
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중");
			new Thread(this).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		try {
			// 클라이언트가 접속할때까지 기다린다.
			// 클라이언트가 접속하면 대리 소켓 생성
			s = ss.accept();
			
			// Socket s에는 접속자의 모든 정보가 들어 있다.
			System.out.println(s.getInetAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Ex02_2();
	}

	
}
