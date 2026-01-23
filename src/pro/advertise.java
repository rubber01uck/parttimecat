package pro;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
public class advertise extends JFrame{

	static int h=0;
	static int no=0;
	
	JLabel lb1=new JLabel();
	JLabel lb2=new JLabel();
	JLabel lb3=new JLabel();
	JLabel lb4=new JLabel();
	JPanel p1=new JPanel(null);
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public advertise() {
		setLayout(null);
		
		mgr.sql="select * from advertise where ano="+no+";";
		list=mgr.a3();
		bean=list.get(0);
		
		lb1.setText("<html>"+bean.getB());
		lb4.setText("<html>"+bean.getC());
		
		lb1.setFont(new Font("맑은 고딕",1,18));
		lb4.setFont(new Font("맑은 고딕",0,13));
		
		lb2.setBorder(new LineBorder(Color.black));
		lb3.setBorder(new LineBorder(Color.black));
		
		lb2.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-1.jpg").getImage().getScaledInstance(340, 150, 0)));
		lb3.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-2.jpg").getImage().getScaledInstance(340, 150, 0)));
		
		lb1.setBounds(20,0,340,100);
		p1.setBounds(20,100,340,150);
		lb4.setBounds(20,250,340,100);
		
		lb2.setBounds(0,0,340,150);
		lb3.setBounds(340,0,340,150);
		
		p1.setBackground(Color.white);
		p1.setBorder(new LineBorder(Color.black));
		
		set();
		
		add(lb1);
		p1.add(lb2);
		p1.add(lb3);
		add(lb4);
		add(p1);
		
		setVisible(true);
		setSize(400,400);
		setTitle("광고 정보");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				dispose();
				if(h==0) {
					new main();
				}else if(h==1) {
					new search();
				}else {
					new binfo();
				}
			}
		});
	}
	void set() {
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			// x값
			int x=0;
			// 메인 이미지 구분(0 = -1.jpg, 1 = -2.jpg)
			int n=0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					lb2.setBounds(x,0,340,150);
					lb3.setBounds(x+340,0,340,150);
					// 왼쪽으로 이동
					x--;
					
					// 이미지 폭만큼 왼쪽으로 이동했으면 ≒ 서브 이미지가 메인 이미지 위치에 왔으면
					if(x<-340) {
						// 메인 이미지가 -1.jpg 였으면
						if(n==0) {
							// 메인 이미지를 -2.jpg로
							// 서브 이미지를 -1.jpg로 변경
							lb2.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-2.jpg").getImage().getScaledInstance(340, 150, 0)));
							lb3.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-1.jpg").getImage().getScaledInstance(340, 150, 0)));
							// 메인 이미지 변경했으니 구분 값 변경
							n=1;
						}else {
							// 메인 이미지를 -1.jpg로
							// 서브 이미지를 -2.jpg로 변경
							lb2.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-1.jpg").getImage().getScaledInstance(340, 150, 0)));
							lb3.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+no+"-2.jpg").getImage().getScaledInstance(340, 150, 0)));
							// 메인 이미지 변경했으니 구분 값 변경
							n=0;
						}
						// 두 이미지 모두 원 위치
						// x=0 생략 시, x 값 -346으로 이동되어 올바르게 전환되지 않음
						x=0;
						lb2.setBounds(x,0,340,150);
						lb3.setBounds(x+340,0,340,150);
						
						// 이미지 0.5초 멈춤
						Thread.sleep(500);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		// 시작 이미지 보이게 0.5초 멈추고 시작, 약 5초 동안 이동되도록 15밀리초 마다 -1 x씩 이동
		timer.schedule(task, 500, 15);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new advertise();
	}

}