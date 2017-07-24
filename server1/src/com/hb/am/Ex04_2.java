package com.hb.am;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex04_2 {
	ServerSocket ss;
	Socket s;
	public Ex04_2() {
		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true){
						try {
							s = ss.accept();
							// 클라이언트에서 문자 스트림으로 전송
							// 서버도 문자 스트림으로 입력받는다
							InputStream in = s.getInputStream();
							InputStreamReader isr = new InputStreamReader(in);
							BufferedReader br = new BufferedReader(isr);
							
							String msg = br.readLine();
							System.out.println("출력 : "+msg);

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new Ex04_2();
	}
}
