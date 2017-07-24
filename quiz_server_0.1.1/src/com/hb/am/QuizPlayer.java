package com.hb.am;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import oracle.net.aso.s;

public class QuizPlayer extends Thread {
	Socket socket;
	Quiz_server quizServer;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Protocol pro1, pro2;
	
	// 퀴즈VO
	Quiz_VO qvo;
	ArrayList<Quiz_VO> qvoList;
	Quiz_DAO qd;
	
	// 회원VO
	QuizMember_VO qmvo;
	ArrayList<QuizMember_VO> qmvoList;
	ArrayList<String> id_list;
	QuizMember_DAO qmd;
	
	// 체크
	int idCheck=0;
	int joinRes=0;
	
	// 파일 입출력
	Photo_IO pio;
	Text_IO tio;
	
	// 기타
	ListMk_methods lm;
	String nickname;
	
	
	public String getNickname() {
		return nickname;
	}

	public QuizPlayer(Quiz_server quiz_server) {
		try {
			quizServer = quiz_server;
			lm = new ListMk_methods(quizServer);
			socket = quizServer.s;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			qmvo = new QuizMember_VO();
			qvo = new Quiz_VO();
			qmd = new QuizMember_DAO();
			pio = new Photo_IO(); 
			tio = new Text_IO();
			qd = new Quiz_DAO();
		} catch (Exception e) {
		}
	}
	
	@Override
	public void run() {
		try {
			while (true){
				pro1 = (Protocol) ois.readObject();
				pro2 = new Protocol();
				System.out.println(pro1.getCmd());
				switch (pro1.getCmd()) {
					// ID 중복체크
					case 101:
						String idChk = pro1.getMsg();
						qmd = new QuizMember_DAO();
						id_list = qmd.selectID();
//						System.out.println(id_list);
						if (id_list.contains(idChk)){
							idCheck = 1;
						} else {
							idCheck = 0;
						}
						pro2.setCmd(idCheck);
//						oos.writeObject(pro2);
//						oos.flush();
						break;
	
					// 회원가입
					case 100:
						qmvo = pro1.getQuizMemVO();
						
						String newId = qmvo.getId().trim();
						String newPw = qmvo.getPw().trim();
						String newName = qmvo.getName().trim();
						String newNick = qmvo.getNickname().trim();
						String newIntro = qmvo.getIntroduction().trim();
						String newPhoto = qmvo.getPhoto().trim();
						byte[] photo = qmvo.getPhotoByte();
						
						joinRes = qmd.join(newId, newPw, newName, newNick, newIntro, newPhoto);
						
						File file = new File("c:/util/quizmember/"+newId);
						file.mkdirs();
						String introPath = "c:/util/quizmember/"+newId+"/"+newId+"_자기소개.txt";
						String introContent = qmvo.getIntroduction().replace("\n", "\r\n");
						tio.textsave(introPath, introContent);
						String photoPath = "c:/util/quizmember/"+newId+"/"+newId+"_"+newPhoto;
						pio.photoSave(photoPath, photo);
						
						pro2.setCmd(joinRes);
//						oos.writeObject(pro2);
//						oos.flush();
						break;

					// 로그인
					case 200:
						qmvo = pro1.getQuizMemVO();
						String id = qmvo.getId();
						String pw = qmvo.getPw();
//						System.out.println(id+pw);
						int loginIdx = qmd.login(id, pw);
//						System.out.println(loginIdx);
						if (loginIdx!=0){
							QuizMember_VO qmvo2 = qmd.info(loginIdx);
							String photoPath2 = "c:/util/quizmember/"+id+"/"+id+"_"+qmvo2.getPhoto().trim();
							int size2 = pio.fileSize(photoPath2);
							byte[] photo2 = pio.photoUpload(photoPath2, size2);
							qmvo2.setPhotoByte(photo2);
							pro2.setQuizMemVO(qmvo2);
						}
						pro2.setCmd(loginIdx);
//						oos.writeObject(pro2);
//						oos.flush();
						break;
						
					// 퀴즈 추가
					case 300:
						qvo = pro1.getQuizVO();
						String quiz = qvo.getQuiz();
						String item1 = qvo.getQuiz_item1();
						String item2 = qvo.getQuiz_item2();
						String item3 = qvo.getQuiz_item3();
						String item4 = qvo.getQuiz_item4();
						int quizAns = qvo.getQuiz_answer();
						int result = qd.addQuiz(quiz, item1, item2, item3, item4, quizAns);
						
						pro2.setCmd(result);
//						oos.writeObject(pro2);
//						oos.flush();
						break;
						
					// 모든 회원정보 조회
					case 400:
						qmvoList = qmd.selectAll();
						pro2.setMemberList(qmvoList);
						
						break;
						
					// 대기실 입장
					case 500:
						qmvo = pro1.getQuizMemVO();
						nickname = qmvo.getNickname();
						quizServer.p_list.add(this);
						pro2.setCmd(501);
						pro2.setUsers(lm.getUsers());
						lm.sendMsg(pro2);
						break;
								
					// 대기실 퇴장
					case 502:
						quizServer.p_list.remove(this);
						pro2.setCmd(501);
						pro2.setUsers(lm.getUsers());
						lm.sendMsg(pro2);
						break;

					// 방 만들기
					case 600:
						break;
					}
					if (pro1.getCmd()<500){
						oos.writeObject(pro2);
						oos.flush();
					}
				}
		} catch (Exception e) {
			System.out.println("2:"+e);
		} finally {
			try {
				oos.close();
				ois.close();
				socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
			
		
