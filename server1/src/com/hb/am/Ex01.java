package com.hb.am;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 자바 통신은 소켓 통신이다.
// 서버는 ServerSocket 클래스 사용
// 클라이언트는 Socket 클래스 사용
public class Ex01 {
	public Ex01() {
		try {
			ServerSocket ss = new ServerSocket(7777);	// 포트를 이용해 생성
			System.out.println("서버 대기중...");
			// 클라이언트가 접속할때까지 기다린다.
			// 클라이언트가 접속하면 대리 소켓 생성
			Socket s = ss.accept();
			
			// Socket s에는 접속자의 모든 정보가 들어 있다.
			String name = s.getInetAddress().getHostName();
			String ip = s.getInetAddress().getHostAddress();
			System.out.println("ip : "+ip+", name : "+name);
			System.out.println("수고하셨습니다.");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Ex01();
	}
}