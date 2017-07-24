package com.hb.bm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Quiz_JFrame extends JFrame {
	JPanel jpNorth, jpCenter, jpSouth;
	JPanel jp1, jp2, jp3, jp4;
	JLabel jlBig, jl1, jl2, jl3, jl4;
	JButton jb1, jb2, jb3, jb4;
	Quiz_factory qf = new Quiz_factory();
	int[] quizs;
	int i;
	int chk;
	private ArrayList<Quiz_VO> qvo;
	public Quiz_JFrame() {
		setTitle("퀴즈");
		
		// ��� ����
		jpNorth = new JPanel();
		jpNorth.add(new JLabel("퀴이_즈"));
		
		// ����
		jpCenter = new JPanel();
		jlBig = new JLabel();
		
		// ����
		jp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jb1 = new JButton("1");
		jl1 = new JLabel();
		jp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jb2 = new JButton("2");
		jl2 = new JLabel();
		jp3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jb3 = new JButton("3");
		jl3 = new JLabel();
		jp4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		jb4 = new JButton("4");
		jl4 = new JLabel();
		
		jp1.add(jb1);
		jp1.add(jl1);
		jp2.add(jb2);
		jp2.add(jl2);
		jp3.add(jb3);
		jp3.add(jl3);
		jp4.add(jb4);
		jp4.add(jl4);
		
		jpSouth = new JPanel();
		jpSouth.setLayout(new GridLayout(2, 2));
		
		jpSouth.add(jp1);
		jpSouth.add(jp2);
		jpSouth.add(jp3);
		jpSouth.add(jp4);
		
		add(jpNorth, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
		add(jpSouth, BorderLayout.SOUTH);
		
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(ds.width/2-150, ds.height/2-150, 300, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// ����
		quizs = qf.getQuiz();
		qvo = qf.getQvo();
		i = 0;
		chk = 0;
		
		jpCenter.add(jlBig);
		jlBig.setText(qvo.get(quizs[i]).getQuiz());
		jl1.setText(qvo.get(quizs[i]).getQuiz_item1());
		jl2.setText(qvo.get(quizs[i]).getQuiz_item2());
		jl3.setText(qvo.get(quizs[i]).getQuiz_item3());
		jl4.setText(qvo.get(quizs[i]).getQuiz_item4());
		
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i<4){
					if (qvo.get(quizs[i]).getQuiz_answer()==1){
						System.out.println("정답");
						chk++;
					}
					i++;
					jlBig.setText(qvo.get(quizs[i]).getQuiz());
					jl1.setText(qvo.get(quizs[i]).getQuiz_item1());
					jl2.setText(qvo.get(quizs[i]).getQuiz_item2());
					jl3.setText(qvo.get(quizs[i]).getQuiz_item3());
					jl4.setText(qvo.get(quizs[i]).getQuiz_item4());
				} else if (i==4) {
					if (qvo.get(quizs[i]).getQuiz_answer()==1){
						System.out.println("정답");
						chk++;
					}
					System.out.println("5문제 중 "+chk+"개 맞춤");
					System.out.println("끝");
				}
				
			}
		});
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i<4){
					if (qvo.get(quizs[i]).getQuiz_answer()==2){
						System.out.println("정답");
						chk++;
					}
					i++;
					jlBig.setText(qvo.get(quizs[i]).getQuiz());
					jl1.setText(qvo.get(quizs[i]).getQuiz_item1());
					jl2.setText(qvo.get(quizs[i]).getQuiz_item2());
					jl3.setText(qvo.get(quizs[i]).getQuiz_item3());
					jl4.setText(qvo.get(quizs[i]).getQuiz_item4());
				} else if (i==4) {
					if (qvo.get(quizs[i]).getQuiz_answer()==2){
						System.out.println("정답");
						chk++;
					}
					System.out.println("5문제 중 "+chk+"개 맞춤");
					System.out.println("끝");
				}
			}
		});
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i<4){
					if (qvo.get(quizs[i]).getQuiz_answer()==3){
						System.out.println("정답");
						chk++;
					}
					i++;
					jlBig.setText(qvo.get(quizs[i]).getQuiz());
					jl1.setText(qvo.get(quizs[i]).getQuiz_item1());
					jl2.setText(qvo.get(quizs[i]).getQuiz_item2());
					jl3.setText(qvo.get(quizs[i]).getQuiz_item3());
					jl4.setText(qvo.get(quizs[i]).getQuiz_item4());
				} else if (i==4) {
					if (qvo.get(quizs[i]).getQuiz_answer()==3){
						System.out.println("정답");
						chk++;
					}
					System.out.println("5문제 중 "+chk+"개 맞춤");
					System.out.println("끝");
				}
			}
		});
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i<4){
					if (qvo.get(quizs[i]).getQuiz_answer()==4){
						System.out.println("정답");
						chk++;
					}
					i++;
					jlBig.setText(qvo.get(quizs[i]).getQuiz());
					jl1.setText(qvo.get(quizs[i]).getQuiz_item1());
					jl2.setText(qvo.get(quizs[i]).getQuiz_item2());
					jl3.setText(qvo.get(quizs[i]).getQuiz_item3());
					jl4.setText(qvo.get(quizs[i]).getQuiz_item4());
				} else if (i==4) {
					if (qvo.get(quizs[i]).getQuiz_answer()==4){
						System.out.println("정답");
						chk++;
					}
					System.out.println("5문제 중 "+chk+"개 맞춤");
					System.out.println("끝");
				}
			}
		});
	}
}
