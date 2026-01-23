package pro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

public class info extends JFrame{

	static int h=0;
	static int no=1;
	
	JLabel lb1=new JLabel();
	JLabel heart=new JLabel();
	
	JScrollPane jsp1=new JScrollPane();
	JPanel all=new JPanel();
	JLabel lb2=new JLabel();
	JLabel map=new JLabel(new ImageIcon(new ImageIcon("./datafiles/지도.png").getImage().getScaledInstance(300, 275, 0)));
	
	JButton bt1=new JButton("지원하기→");

	JLabel border=new JLabel();
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public info(){
		setLayout(null);

		// format() <- 형식 설정
		// format(형식을 지정할 열이름, 형식)
		// '#,###' <- 천 단위 구분 기호 형식
		mgr.sql="select jname, format(jmoney,'#,###'), jday, jtime, if(jwork=0,'정규직','계약직'), if(jgrade=0,'무관',if(jgrade=1,'대학','고등')), jpeople, bname from job as j join brand as b on j.bno=b.bno where jno="+no+";";
		list=mgr.a8();
		bean=list.get(0);
		lb1.setText("<html>"+bean.getA());
		lb2.setText("<html>[근무조건]<br>급여: "+bean.getB()+"원<br>근무요일: 주 "+bean.getC()+"회<br>근무시간: "+bean.getD()+"시간<br>고용형태: "+bean.getE()+"<br><br>[모집조건]<br>지원자격: "+bean.getF()+"<br>모집인원: "+bean.getG()+"명<br><br>[기업정보]<br>"+bean.getH()+"<br>▽위치보기");
		// 글 위 공백을 없애고 싶다면 글자 세로 정렬을 바꾸면 됨 (생략 가능)
		lb2.setVerticalAlignment(JLabel.TOP);
		
		
		lb1.setFont(new Font("맑은 고딕",1,20));
		
		heart.setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/하트2.png").getImage().getScaledInstance(30, 30, 0)));
		heart.setName("0");
		
		bt1.setFont(new Font("맑은 고딕",0,13));
		bt1.setBackground(new Color(255,120,50));
		bt1.setForeground(Color.white);
		
		lb1.setBounds(20,20,345,60);
		
		border.setOpaque(true);
		border.setBackground(new Color(255,120,50));
		
		border.setBounds(20,95,345,5);
		jsp1.setBounds(20,100,345,300);
		heart.setBounds(20,410,30,30);
		bt1.setBounds(230,410,130,30);
		
		lb2.setBounds(10,10,330,300);
		map.setBounds(10,310,300,275);
		
		add(lb1);
		add(heart);
		add(bt1);
		add(jsp1);
		add(border);
		
		lb2.setFont(new Font("맑은 고딕",0,13));
 
		all.setLayout(null);
		all.setBackground(Color.white);
		
		all.add(lb2);
		all.add(map);
		all.setPreferredSize(new Dimension(0,600));
		jsp1.setViewportView(all);
		
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		setVisible(true);
		setTitle("알바 정보");
		setSize(400,500);
		getContentPane().setBackground(Color.white);
		
		heart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(heart.getName().equals("0")) {
					heart.setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/하트1.png").getImage().getScaledInstance(30, 30, 0)));
					heart.setName("1");
				}else {
					heart.setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/하트2.png").getImage().getScaledInstance(30, 30, 0)));
					heart.setName("0");
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				if(h==0) {
					new main();
				}else if(h==1){
					new search();
				}else if(h==2){
					new binfo();
				}else {
					new mypage();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new info();
	}

}