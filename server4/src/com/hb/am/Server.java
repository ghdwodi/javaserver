package com.hb.am;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable {
	ServerSocket ss;
	Socket s;
	
	// 대기실 리스트
	ArrayList<Player> list;
	
	// 채팅방 리스트
	ArrayList<Room> r_list;
	
	public Server() {
		r_list = new ArrayList<>();
		list = new ArrayList<>();
		try {
			ss = new ServerSocket(8989);
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
				Player player = new Player(s, this);
				list.add(player);
				player.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	// 대기실에 있는 사람들의 이름을 반환하는 메소드
	public String[] getUsers(){
		String[] arr = new String[list.size()];
		int i = 0;
		for (Player k : list) {
			arr[i] = k.getNickname();
			i++;
		}
		return arr;
	}
	
	// 현재 대기실에 있는 방의 제목을 반환하는 메소드
	public String[] getRooms(){
		String[] arr = new String[r_list.size()];
		int i = 0;
		for (Room k : r_list) {
			arr[i] = k.getRooms();
			i++;
		}
		return arr;
	}

	// 대기실에 있는 사람들에게 프로토콜 전달
	public void sendMsg(Protocol p){
		try {
			for (Player k : list) {
				k.oos.writeObject(p);
				k.oos.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 만들어진 방을 r_list에 추가
	public void addRoom(Room room){
		r_list.add(room);
	}
	
	// 자기 자신을 대기실에서 삭제
	public void del(Player player){
		list.remove(player);
	}
	
	// 방 리스트의 인첵스를 받아서 해당 방을 리턴
	public Room changeRoom(int index){
		return r_list.get(index);
	}
	
	// 방 지우기
	public void delRoom(Room room){
		r_list.remove(room);
	}

	public static void main(String[] args) {
		new Server();
	}
}
