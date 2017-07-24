package com.hb.dm;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
	ServerSocket ss;
	Socket s;
	ArrayList<Client_list> list = new ArrayList<>();
	public Server() {
		try {
			ss = new ServerSocket(7777);
			System.out.println("서버 대기중...");
			new Thread(this).start();
		} catch (Exception e) {
			System.out.println(1);
		}
	}
	@Override
	public void run() {
		try {
			while(true){
				s = ss.accept();
				Client_list c_list = new Client_list(s, this);
				list.add(c_list);
				new Thread(c_list).start();
			}
		} catch (Exception e) {
			System.out.println(2);
		}
		
	}
	// 메세지를 받아서 전체에게 전달하는 역할
	public void sendMsg(Protocol p){
		try {
			for(Client_list k : list){
				k.oos.writeObject(p);
				k.oos.flush();
			}
		} catch (Exception e) {
			System.out.println();
		}
	}
	// 해당 메세지를 삭제
	public void del(Client_list s){
		try {
			list.remove(s);
			Protocol p3 = new Protocol(200,s.name+"님 퇴장");
			for(Client_list k : list){
				k.oos.writeObject(p3);
				k.oos.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String[] args) {
		new Server();
	}
}
