package com.hb.am;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	ServerSocket ss;
	Socket s;
	
	// 여러 접속자의 정보를 받아 처리하기 위해 컬렉션 사용
	// Player 클래스를 생성
	ArrayList<Player> list;
	public Server() {
		try {
			list = new ArrayList<>();
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중...");

			new Thread(this).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(true){
			try {
				s = ss.accept();
				Player player = new Player(s,this);
				list.add(player);
				player.start();
			} catch (Exception e) {
			}
		}
	}
	
	// 받은 메시지를 가지고 list 안에 존재하는 클라이언트들에게 각각 보낸다.
	public void sendMsg (String msg){
		try {
			msg = msg+System.getProperty("line.separator");
			for (Player p : list) {
				p.bw.write(msg);
				p.bw.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 탈퇴한 클라이언트 삭제
	public void delPlayer(Player player){
		list.remove(player);
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}
