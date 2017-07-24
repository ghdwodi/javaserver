package com.hb.am;

public class ListMk_methods {
	Quiz_server quizServer2;
	public ListMk_methods(Quiz_server q) {
//		System.out.println(q.p_list.size());
		this.quizServer2 = q;
	}
	// 대기실에 있는 사람들의 이름을 반환하는 메소드
	public String[] getUsers(){
		String[] arr = new String[quizServer2.p_list.size()];
		int i = 0;
		for (QuizPlayer k : quizServer2.p_list) {
			arr[i] = k.getNickname();
			i++;
		}
		return arr;
	}
	
	// 대기실에 있는 사람들에게 프로토콜 전달
	public void sendMsg(Protocol p){
		try {
			for (QuizPlayer k : quizServer2.p_list) {
				k.oos.writeObject(p);
				k.oos.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	// 자기 자신을 대기실에서 삭제
//	public void del(QuizPlayer player){
//		list.remove(player);
//	}
}
