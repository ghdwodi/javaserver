package com.hb.am;

import java.net.ServerSocket;
import java.net.Socket;

public class Ex06_main {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		try {
			while (true){
				ss = new ServerSocket(7777);
				System.out.println("서버 대기중...");
				
				s = ss.accept();
				
				new Thread(new Ex06_input(s)).start();
				new Thread(new Ex06_output(s)).start();	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
