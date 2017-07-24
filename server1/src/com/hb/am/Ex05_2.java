package com.hb.am;

// 에코 서버

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex05_2 {
	ServerSocket ss;
	Socket s;
	public Ex05_2() {
		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중");
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true){	// 무한루프 탈출 시 서버가 닫혀버릴 수 있으므로 try-catch를 while 안에 둔다.
						try {
							s = ss.accept();
							// 클라이언트에서 문자 스트림으로 전송
							// 서버도 문자 스트림으로 입력받는다
							InputStream in = s.getInputStream();
							BufferedInputStream bis = new BufferedInputStream(in);
							byte[] b = new byte[1024];
							bis.read(b);
							String msg = new String(b);
							System.out.println("출력 : "+msg);
//							
							// 받은 문자열 반사
							OutputStream out = s.getOutputStream();
							BufferedOutputStream bos = new BufferedOutputStream(out);
							// getBytes <= String을 byte[]로 자동 변화
							bos.write(msg.getBytes());
							bos.flush();

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
		new Ex05_2();
	}
}
