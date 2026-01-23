package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class brand extends JFrame{
	int limit=0;

	JComboBox cb1=new JComboBox<>();
	
	JPanel all=new JPanel(new GridLayout(2, 3, 20,20));
	
	JLabel left=new JLabel("<", JLabel.CENTER);
	JLabel right=new JLabel(">", JLabel.CENTER);
	
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public brand () {
		setLayout(null);
		
		cb1.addItem("전체");
		mgr.sql="select * from category;";
		list=mgr.a2();
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			cb1.addItem(bean.getB());
		}
		cb1.addActionListener((e)->{
			limit=0;
			set();
		});

		
		left.setFont(new Font("맑은 고딕", 1, 24));
		right.setFont(new Font("맑은 고딕", 1, 24));
		all.setBackground(Color.white);
		
		cb1.setBounds(20, 10, 150, 25);
		left.setBounds(10, 100, 30, 260);
		all.setBounds(90, 100, 120*3+40, 260);
		right.setBounds(585-40, 100, 30, 260);
		
		add(cb1);
		add(left);
		add(right);
		add(all);
		
		
		set();
		
		setVisible(true);
		setTitle("브랜드");
		setSize(600, 500);
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new main();
			}
		});
		
		left.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// 왼쪽 화살표가 활성화되어있다면
				if(left.isEnabled()) {
					limit-=6;
					set();
				}
			}
		});
		right.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(right.isEnabled()) {
					limit+=6;
					set();
				}
			}
		});
	}
	
	
	void set() {
		all.removeAll();
		
		// 콤보박스 조건에 맞는 최댓값 가져오기
		if(cb1.getSelectedIndex()==0) {
			mgr.sql="select bno, bname from brand;";
		} else {
			mgr.sql="select bno, bname from brand where cno="+cb1.getSelectedIndex()+";";
		}
		list=mgr.a2();
		int max=list.size();
		
		// 왼쪽 오른쪽 화살표 비활성화 여부 지정
		left.setEnabled(true);
		right.setEnabled(true);
		// 현제 페이지의 첫 번째 값이 전체의 첫 번째 값이면
		if(limit==0) {
			// 왼쪽 화살표 비활성화
			left.setEnabled(false);
		} 
		// 현제 페이지에 전체의 마지막 번째 값이 포함되어있다면
		if(limit+6>=max) {
			// 오른쪽 화살표 비활성화
			right.setEnabled(false);
		}
		
		if(cb1.getSelectedIndex()==0) {
			// 브랜드는 6개씩 가져오므로 limit을 사용
			// limit 시작값(생략=0), 양
			// 예) limit 6,6 => 일곱 번째 행부터 6개 가져와라
			mgr.sql="select bno, bname from brand limit "+limit+", 6;";
		} else {
			mgr.sql="select bno, bname from brand where cno="+cb1.getSelectedIndex()+" limit "+limit+", 6;";
		}
		list=mgr.a2();
		
		JLabel a[]=new JLabel[6];
		
		for (int i = 0; i < a.length; i++) {
			a[i]=new JLabel();
			
			if(i<list.size()) {
				bean=list.get(i);
				
				a[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/brand/"+bean.getA()+".png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
				a[i].setBorder(new LineBorder(Color.black));
				a[i].setName(bean.getA());;
				
				int n=i;
				a[n].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						super.mouseClicked(e);
						binfo.no=Integer.parseInt(a[n].getName());
						binfo.h=1;
						dispose();
						new binfo();
					}
				});
			}
			
			all.add(a[i]);
		}
		
		
		all.updateUI();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new brand();
	}

}