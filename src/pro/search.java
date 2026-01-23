package pro;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
public class search extends JFrame{

	// 카테고리 변경 시
	// cno가 0보다 크다면 / cno는 1부터 시작하므로 카테고리 전체를 의미
	// 카테고리 변경 시에도, 검색 시에도 where을 써야 함
	// 하지만 한 구문에 where을 두 번 쓸 수는 없고 and 로 조건을 연결해주어야 하는데
	// 각각 동작할 때마다 다른 조건이 있는지 없는지 판별하는 조건문이 길기 때문에
	// 카테고리 변경 문구는 무조건 where로 시작, 검색 문구는 무조건 and로 시작하게 할 것임
	// 때문에 카테고리 전체를 나타내는 기본 값으로 'where cno > 0' 지정
	String w="where cno > 0";
	// 정렬 변경 시
	// 기본 값 = 기본순, jno 오름차순으로 정렬
	String o="order by j.jno";
	
	JLabel lb1=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/cat.png").getImage().getScaledInstance(120, 60, 0)));
	JTextField tf1=new JTextField();
	JLabel lb2=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/search.png").getImage().getScaledInstance(50, 50, 0)));
	
	JComboBox cb1=new JComboBox();
	
	String b[] ={"기본순", "인기순", "급여높은순"};
	JComboBox cb2=new JComboBox(b);
	
	JScrollPane jsp1=new JScrollPane();
	JPanel all=new JPanel();
	
	JLabel ad[]=new JLabel[999];
	JLabel job[]=new JLabel[999];
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public search() {
		setLayout(null);

		// '전체'는 category 테이블에 없으므로 따로 먼저 추가
		cb1.addItem("전체");
		mgr.sql="select * from category;";
		list=mgr.a2();
		// list의 길이 만큼 ≒ 결과 테이블의 행 개수 만큼 돌도록
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			cb1.addItem(bean.getB());
		}
		
		cb1.setFont(new Font("맑은 고딕",0,13));
		cb2.setFont(new Font("맑은 고딕",0,13));
		tf1.setFont(new Font("맑은 고딕",0,13));
		
		cb1.setBackground(Color.white);
		cb2.setBackground(Color.white);   

		tf1.setBorder(new LineBorder(new Color(255,120,50)));
		jsp1.setBorder(new LineBorder(new Color(255,120,50)));

		lb1.setBounds(20,20,120,60);
		tf1.setBounds(140,50,350,30);
		lb2.setBounds(500,30,50,50);
		cb1.setBounds(20,100,200,30);
		cb2.setBounds(465,100,100,30);
		jsp1.setBounds(20,140,545,400);
		
		jsp1.setViewportView(all);
		
		set();
		
		add(lb1);
		add(tf1);
		add(lb2);
		add(cb1);
		add(cb2);
		add(jsp1);
		
		setVisible(true);
		setSize(600,600);
		setTitle("채용");
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
		
		lb1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				new main();
			}
		});
		
		cb1.addItemListener(new ItemListener() {
			@Override
			// 콤보박스 선택 값이 바뀔 때
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				// 선택 값이 0번째 목록 ≒ '전체'라면
				if(cb1.getSelectedIndex()==0) {
					// where을 쓰지 않고 전체 검색
					w="where cno > 0";
				}else {
					w="where cno="+cb1.getSelectedIndex();
				}
				
				// 다시 배치
				set();
			}
		});
		
		cb2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(cb2.getSelectedIndex()==0) {
					o="order by j.jno";
				}else if(cb2.getSelectedIndex()==1) {
					o="order by count(*) desc";
				}else {
					o="order by jmoney desc";
				}
				set();
				
			}
		});
		
		lb2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(tf1.getText().equals("")) {
					w="where cno>0";
					o="order by j.jno";
				}else {
					w=" and jname like '%"+tf1.getText()+"%'";
				}
				set();
			}
		});
		
		
	}
	
	void set() {
		// 검색 또는 콤보박스 변경 후, 재배치할 때 기존에 배치한 객체를 모두 지움
		all.removeAll();
		
		all.setLayout(null);
		all.setBackground(Color.white);
		
		int y=10;
		
		// w와 o 사이에 띄어쓰기 잊지 말기
		// 인기순 정렬 시, 좋아요 횟수를 알아야 하므로 likes를 씀
		// 하지만 likes 기준으로 작성하면 likes에 없는
		// 즉, 좋아요를 받지 못한 알바 정보는 나타나지 않아 버리기 때문에
		// right join을 사용하여 job 기준으로 연결
		mgr.sql="select j.jno, jname from likes as i right join job as j on i.jno=j.jno join brand as b on j.bno=b.bno "+w+" group by j.jno "+o+";";
		list=mgr.a2();
		for (int i = 0; i < list.size() ; i++) {
			bean=list.get(i);
			job[i]=new JLabel("<html> <p style=text-align:center>"+bean.getB());
			// jno 저장
			job[i].setName(bean.getA());
			job[i].setHorizontalAlignment(JLabel.CENTER);
			job[i].setFont(new Font("맑은 고딕",1,15));
			job[i].setBounds(10,y,500,60);
			job[i].setBorder(new LineBorder(Color.black));
			all.add(job[i]);
			
			int n=i;
			job[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					dispose();
					info.h=1;
					// 클릭된 job[]의 Name. 즉, 클릭한 알바 정보의 jno가 info에 저장됨.
					info.no=Integer.parseInt(job[n].getName());
					new info();
				}
			});
			y+=70;
		}
		
		if(w.contains("and")) {
			mgr.sql="select * from advertise where aname like '%"+tf1.getText()+"%' order by rand();";
		}else {
			mgr.sql="select * from advertise order by rand();";
		}
		list=mgr.a3();
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			// <p style=text-align:center> <- html 구문을 쓰는 부분을 가운데 정렬 해줌
			ad[i]=new JLabel("<html> <p style=text-align:center>"+bean.getB());
			ad[i].setName(bean.getA());
			ad[i].setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+bean.getA()+"-1.jpg").getImage().getScaledInstance(120, 60, 0)));
			ad[i].setFont(new Font("맑은 고딕",1,15));
			ad[i].setBounds(10,y,500,60);
			ad[i].setBorder(new LineBorder(Color.black));
			all.add(ad[i]);
			y+=70;
			
			int n=i;
			ad[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					dispose();
					advertise.h=1;
					advertise.no=Integer.parseInt(ad[n].getName());
					new advertise();
				}
			});
		}
		
		all.setPreferredSize(new Dimension(0,y));
		// all 새로고침
		all.updateUI();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new search();
	}

}
