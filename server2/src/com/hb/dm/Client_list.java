package com.hb.dm;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client_list implements Runnable{
	Socket s;
	Server server;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	String name;
	public Client_list() {}
	public Client_list(Socket s, Server server) {
		this.s = s ;
		this.server = server;
		try {
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (Exception e) {
			System.out.println(4);
		}
	}
	@Override
	public void run() {
		try {
			while(true){
			// 객체직렬화된 정보가 들어옴 => 역직렬화
			Protocol p = (Protocol)ois.readObject();
			switch (p.getCmd()) {
			// 대화명 받기
			case 100: 
				name = p.getMsg();
				// 바로 클라이언트가 채팅할수 있도록 200으로 셋팅변경
				Protocol p2 = new Protocol();
				p2.setCmd(200);
				p2.setMsg(name+"님 입장");
				server.sendMsg(p2);
				break;
			// 채팅하기	
			case 200:
				 // String msg = p.getMsg();
				 server.sendMsg(p);
				break;
			// 종료하기	
			case 300:
				server.del(this);
				break;
			}
			}
		} catch (Exception e) {
		}
	}
}