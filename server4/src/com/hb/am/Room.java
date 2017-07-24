package com.hb.am;

import java.util.ArrayList;

// 대화방
public class Room extends Thread {
	String r_name;
	
	// 대화방 참여자 리스트
	ArrayList<Player> j_list = new ArrayList<>();
	
	public Room() {}
	public Room(String r_name) {
		this.r_name = r_name;
	}
	
	// 방 이름을 반환하는 메소드
	public String getRooms(){
		return r_name;
	}
	
	// 대기실에서 방으로 참여하는 메소드
	public void joinRoom(Player player){
		j_list.add(player);
		Protocol p = new Protocol();
		p.setCmd(500);
		p.setChat(getJoinUsers());
		p.setMsg(player.getNickname()+"님 입장");
		sendMessageRoom(p);
	}
	
	public String[] getJoinUsers(){
		String[] ar = new String[j_list.size()];
		int i =0;
		for (Player k : j_list) {
			ar[i++] = k.getNickname();
		}
		return ar;
	}
	
	// 방 참여자들에게 메세지 전달
	public void sendMessageRoom(Protocol protocol){
		for(Player k : j_list ){
			try {
				k.oos.writeObject(protocol);
				k.oos.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	// 방 나가기
	public void outRoom(Player player){
		j_list.remove(player);
		
		Protocol p = new Protocol();
		p.setChat(getJoinUsers());
		p.setMsg(player.getNickname()+"님 퇴장");
		
		sendMessageRoom(p);
	}
	
	// 방 인원 세기
	public int getJoinCount(){
		return j_list.size();
	}
}
