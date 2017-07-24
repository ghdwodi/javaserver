package com.hb.am;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer implements Runnable {
	ServerSocket ss;
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ChatMember_VO cv1,cv2;
	ChatMember_DAO cd = new ChatMember_DAO();
	Byte_IO pi = new Byte_IO();
	IO_methods textIO = new IO_methods();
	Protocol pro1,pro2;
	ArrayList<String> idList;
	ArrayList<Player> list = new ArrayList<>();
	int chk=0;
	int res=0;
	public ChatServer() {
		try {
			ss = new ServerSocket(7779);
			System.out.println("서버 대기중...");
			new Thread(this).start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				s = ss.accept();
				ois = new ObjectInputStream(s.getInputStream());
				pro1 = (Protocol) ois.readObject();
				pro2 = new Protocol();
				
				switch (pro1.getCmd()) {
				case 10:
					idList = cd.selectID();
					String newId = pro1.getMsg().trim();
					if (idList.contains(newId)){
						chk=1;
					} else {
						chk=0;
					}
					pro2.setCmd(chk);
					break;
				case 20:
					cv1 = pro1.getCv();
					System.out.println(cv1.getId());
					res = cd.join(cv1.getId(),
							cv1.getPw(),
							cv1.getName(),
							cv1.getSubject(),
							cv1.getContent(),
							cv1.getAttach(),
							cv1.getRegdate());
					byte[] pht = cv1.getPhoto();
					File file = new File("c:/util/sitemember/"+cv1.getId());
					file.mkdirs();
					String path = "c:/util/sitemember/"+cv1.getId()+"/"+cv1.getId()+"_자기소개_"+cv1.getSubject()+".txt";
					String content = cv1.getContent().replace("\n", "\r\n");
					textIO.textsave(path, content);
					String path2 = "c:/util/sitemember/"+cv1.getId()+"/"+cv1.getId()+"_"+cv1.getAttach();
					pi.photoSave(path2, pht);
					break;
				case 100:
					cv1 = pro1.getCv();
					String id = cv1.getId();
					String pw = cv1.getPw();
					int idx = cd.login(id, pw);
					System.out.println(idx);
					cv2 = cd.info(idx);
					String path3 = "c:/util/sitemember/"+cv2.getId()+"/"+cv2.getId()+"_"+cv2.getAttach();
					int size = pi.fileSize(path3.trim());
					byte[] photo = pi.photoUpload(path3.trim(), size);
					cv2.setPhoto(photo);
					System.out.println(size);
					pro2 = new Protocol(idx, cv2);
					break;
				}
				
				System.out.println(pro2.getCmd());
				
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(pro2);
				oos.flush();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("4 : "+e);
		}
	}
	public static void main(String[] args) {
		new ChatServer();
	}
}
