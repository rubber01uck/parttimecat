package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class insert extends JFrame{

	JButton b1=new JButton("알바");
	JButton b2=new JButton("광고");
	
	JLabel bt=new JLabel("업로드", JLabel.CENTER);
	
	JLabel img=new JLabel("브랜드를 선택해주세요.", JLabel.CENTER);
	
	JComboBox cb1=new JComboBox<>();
	JComboBox cb2=new JComboBox<>();
	
	JPanel p=new JPanel(new GridLayout(4, 2, 20, 10));
	JLabel lb1=new JLabel("근무 조건");
	JLabel lb2=new JLabel("모집 조건");
	JTextArea ta1=new JTextArea();
	JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	JTextField tf3=new JTextField();
	JTextField tf4=new JTextField();
	JComboBox c1=new JComboBox<>();
	JComboBox c2=new JComboBox<>();
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public insert () {
		setLayout(null);
		
		// 업로드 버튼 모양 삼각형으로 그리는 부분
//		bt=new JLabel("업로드", JLabel.CENTER) {
//			@Override
//			protected void paintComponent(Graphics g) {
//				// TODO Auto-generated method stub
//				g.setColor(Color.red);
//				g.fillPolygon(new int[] {0,getWidth()/2, getWidth()}, new int[] {getHeight(), 0, getHeight()}, 3);
//				super.paintComponent(g);
//			}
//		};
		
		// 업로드 버튼 삼각형으로 그릴거면 아래 두 줄 지우기
		bt.setOpaque(true);
		bt.setBackground(Color.red);
		
		img.setBorder(new LineBorder(Color.black));
		img.setForeground(Color.gray);
		ta1.setForeground(Color.gray);
		b1.setBorder(new LineBorder(new Color(255,120,50)));
		b2.setBorder(new LineBorder(new Color(255,120,50)));
		b1.setFont(new Font("맑은 고딕", 0, 12));
		b2.setFont(new Font("맑은 고딕", 0, 12));
		b1.setForeground(Color.white);
		b1.setBackground(new Color(255,120,50));
		b2.setBackground(Color.white);
		b2.setForeground(new Color(255,120,50));
		cb1.setFont(new Font("맑은 고딕", 0, 12));
		cb2.setFont(new Font("맑은 고딕", 0, 12));
		c1.setFont(new Font("맑은 고딕", 0, 12));
		c2.setFont(new Font("맑은 고딕", 0, 12));
		
		
		img.setHorizontalAlignment(JLabel.CENTER);
		img.setForeground(Color.gray);
		img.setFont(new Font("맑은 고딕", 0, 12));
		
		cb1.addItem("카테고리");
		cb2.addItem("브랜드");
		cb2.setEnabled(false);
		mgr.sql="select * from category;";
		list=mgr.a2();
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			cb1.addItem(bean.getB());
		}
		
		lb1.setFont(new Font("맑은 고딕", 1, 14));
		lb2.setFont(new Font("맑은 고딕", 1, 14));
		p.setOpaque(false);
		cb1.setFont(new Font("맑은 고딕", 1, 12));
		cb2.setFont(new Font("맑은 고딕", 1, 12));
		c1.setFont(new Font("맑은 고딕", 1, 12));
		c2.setFont(new Font("맑은 고딕", 1, 12));
		bt.setFont(new Font("맑은 고딕", 1, 12));
		
		tf1.setFont(new Font("맑은 고딕",0,12));
		tf2.setFont(new Font("맑은 고딕",0,12));
		tf3.setFont(new Font("맑은 고딕",0,12));
		tf4.setFont(new Font("맑은 고딕",0,12));
		
		ta1.setBorder(new LineBorder(Color.black));
		bt.setForeground(Color.white);
		
		cb1.setBackground(Color.white);
		cb2.setBackground(Color.white);
		c1.setBackground(Color.white);
		c2.setBackground(Color.white);
		
		b1.setBounds(10, 10, 100, 30);
		b2.setBounds(10+110, 10, 100, 30);
		bt.setBounds(610-150, 0, 150, 40);
		img.setBounds(10, 50, 300, 250);
		cb1.setBounds(10, 310, 120, 25);
		cb2.setBounds(10+130, 310, 310-140, 25);
		ta1.setBounds(320, 50, 300, 60);
		p.setBounds(320, 120, 300, 180);
		
		c1.addItem("고용 형태");
		c1.addItem("정규직");
		c1.addItem("계약직");
		c2.addItem("지원 자격");
		c2.addItem("무관");
		c2.addItem("대학");
		c2.addItem("고등");
		
		p.add(lb1);
		p.add(lb2);
		p.add(tf1);
		p.add(tf2);
		p.add(tf3);
		p.add(c1);
		p.add(tf4);
		p.add(c2);
		add(p);
		add(b1);
		add(b2);
		add(bt);
		add(img);
		add(cb1);
		add(cb2);
		add(ta1);
		
		setVisible(true);
		setTitle("등록하기");
		setSize(650, 400);
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new amain();
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new insert();
	}

}