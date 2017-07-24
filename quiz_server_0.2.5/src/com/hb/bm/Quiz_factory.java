package com.hb.bm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Quiz_factory {
	private int[] quiz;
	private ArrayList<Quiz_VO> qvo;
	public Quiz_factory() {
		Quiz_DAO qd = new Quiz_DAO();
		qvo = qd.selectAll();
		int correct = 0;
		
		quiz = new int[5];
		Set<Integer> quizSet = new HashSet<>();
		
		Scanner scan = new Scanner(System.in);
//		System.out.println("ƒ˚¡Ó∞‘¿”!");
		for (int i = 0; i < quiz.length; i++) {
			quiz[i] = (int)(Math.random()*qvo.size());
			if (!quizSet.add(quiz[i])) i--;
		}
	}
	public int[] getQuiz() {
		return quiz;
	}
	public ArrayList<Quiz_VO> getQvo() {
		return qvo;
	}
}
