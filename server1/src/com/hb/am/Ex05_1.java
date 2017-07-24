package com.hb.am;

// 에코 서버

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ex05_1 {
	ServerSocket ss;
	Socket s;
	public Ex05_1() {
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
							InputStreamReader isr = new InputStreamReader(in);
							BufferedReader br = new BufferedReader(isr);
							
							String msg = br.readLine();
							System.out.println("출력 : "+msg);
							
							// 받은 문자열 반사
							OutputStream out = s.getOutputStream();
							OutputStreamWriter osw = new OutputStreamWriter(out);
							BufferedWriter bw = new BufferedWriter(osw);
							bw.write(msg+System.getProperty("line.separator"));
							bw.flush();

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
		new Ex05_1();
	}
}
