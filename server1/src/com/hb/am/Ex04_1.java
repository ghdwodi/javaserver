package com.hb.am;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex04_1 {
	ServerSocket ss;
	Socket s;
	public Ex04_1() {
		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while(true){
						try {
							s = ss.accept();
							// 클라이언트의 byte스트림을 입력
							// 클라이언트가 byte스트림을 출력했다면 서버도 똑같이 byte스트림으로 입력해야 한다.
							InputStream ins = s.getInputStream();
							BufferedInputStream bis = new BufferedInputStream(ins);
							
							// int로 처리(한글은 처리 불가)
//							int b = 0;
//							b = bis.read();
//							System.out.println("출력 : "+(char)b);
//							
//							while((b=bis.read())!=-1){
//								System.out.print((char)b);
//							}
							
							// byte 배열을 사용하는 경우
							byte[] b = new byte[1024];
							bis.read(b);
							String msg = new String(b);
							System.out.println("출력 : "+msg);
							
						} catch (Exception e) {
						}
					}
				}
			}).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		new Ex04_1();
	}
}
