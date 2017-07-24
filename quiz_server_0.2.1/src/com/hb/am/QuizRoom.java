package com.hb.am;

import java.util.ArrayList;

public class QuizRoom {
	// 방 이름
	String roomName;
	
	// 참여자
	ArrayList<QuizPlayer> qp_list;
	
	public QuizRoom() {}
	public QuizRoom(String rName) {
		this.roomName = rName;
	}
	
	public String getRoomName(){
		return roomName;
	}
}
