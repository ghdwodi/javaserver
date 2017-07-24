package com.hb.am;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChatMember_DAO {
	Connection conn = null;
	PreparedStatement ptmt = null;
	ResultSet resSet = null;
	int result = 0;
	
	ArrayList<ChatMember_VO> cmvo = new ArrayList<>();
	ArrayList<String> idList = new ArrayList<>();
	ChatMember_VO cvo;
	int idx;
	
	public ChatMember_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "hjlee";
			String password = "23579";
			
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 회원정보입력
	
	public int join(String id, String pw, String name, String subject, String content, String attach,
			String regdate){
		try {
			String sql = "insert into sitemember "
					+ "values(siteseq.nextval,?,?,?,?,?,?,?)";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, id);
			ptmt.setString(2, pw);
			ptmt.setString(3, name);
			ptmt.setString(4, subject);
			ptmt.setString(5, content);
			ptmt.setString(6, attach);
			ptmt.setString(7, regdate);
			result = ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	// 회원 전체조회
	
	public ArrayList<ChatMember_VO> select(){
		try {
			String sql = "select * from sitemember";
			ptmt = conn.prepareStatement(sql);
			resSet = ptmt.executeQuery();
			
			while(resSet.next()){
				ChatMember_VO sm = new ChatMember_VO();
				sm.setIdx(resSet.getInt(1));
				sm.setId(resSet.getString(2));
				sm.setName(resSet.getString(3));
				sm.setPw(resSet.getString(4));
				sm.setSubject(resSet.getString(5));
				sm.setContent(resSet.getString(6));
				sm.setAttach(resSet.getString(7));
				sm.setRegdate(resSet.getString(8));
				cmvo.add(sm);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cmvo;
	}
	
	// id전체 조회
	public ArrayList<String> selectID(){
		try {
			String sql = "select id from sitemember";
			ptmt = conn.prepareStatement(sql);
			resSet = ptmt.executeQuery();
			
			while(resSet.next()){
				idList.add(resSet.getString("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return idList;
	}
	
	// 로그인
	
	public int login(String id, String pw){
		try {
			String sql = "select * from sitemember where id=? and password=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, id);
			ptmt.setString(2, pw);
			resSet = ptmt.executeQuery();
			if (resSet.next()){
				idx = resSet.getInt("idx");
			} else {
				idx = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return idx;
	}
	
	// 회원정보 조회
	
	public ChatMember_VO info(int idx){
		try {
			String sql = "select * from sitemember where idx=?";
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, idx);
			resSet = ptmt.executeQuery();
			while(resSet.next()){
				cvo = new ChatMember_VO();
				cvo.setIdx(resSet.getInt(1));
				cvo.setId(resSet.getString(2));
				cvo.setName(resSet.getString(3));
				cvo.setPw(resSet.getString(4));
				cvo.setSubject(resSet.getString(5));
				cvo.setContent(resSet.getString(6));
				cvo.setAttach(resSet.getString(7));
				cvo.setRegdate(resSet.getString(8));
			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cvo;
	}
	
	// DB 닫기
	public void closeConn(){
		try {
			resSet.close();
			ptmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
