package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class binfo extends JFrame{

	static int no=0;
	static int h=0;
	
	JLabel logo=new JLabel();
	JLabel logo1=new JLabel();
	JLabel logo2=new JLabel("", JLabel.CENTER);
	
	JLabel map=new JLabel(new ImageIcon(new ImageIcon("./datafiles/지도.png").getImage().getScaledInstance(300, 275, 0)));
	JLabel dot=new JLabel("●");
	
	JLabel t=new JLabel();
	int jno[]=new int[999];
	JScrollPane jsp=new JScrollPane();
	JTable table=new JTable();
	DefaultTableModel model=new DefaultTableModel();
	String co[]= {""};
	String ro[][];
	
	JPanel p=new JPanel(null);
	JLabel img=new JLabel();
	JLabel lb=new JLabel();
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public binfo () {
		setLayout(null);
		
		logo.setBorder(new LineBorder(new Color(255,120,50)));
		logo1.setFont(new Font("맑은 고딕", 1, 14));
		logo2.setFont(new Font("맑은 고딕", 1, 18));
		p.setBackground(Color.black);
		lb.setForeground(Color.white);
		lb.setFont(new Font("맑은 고딕", 2, 14));
		t.setForeground(new Color(255,120,50));
		t.setFont(new Font("맑은 고딕", 1, 12));
		
		dot.setForeground(Color.red);
		
		mgr.sql="select bname, cname, bxx, byy, jno, jname \r\n"
				+ " from job j join brand b on b.bno=j.bno  join category c on c.cno=b.cno  \r\n"
				+ "  where b.bno="+no+" ;";
		list=mgr.a6();
		bean=list.get(0);
		
		logo.setIcon(new ImageIcon(new ImageIcon("./datafiles/brand/"+no+".png").getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
		logo1.setText("["+bean.getB()+"]");
		logo2.setText(bean.getA());
		co[0]=bean.getA()+" 알바 목록";
		
		int x=Integer.parseInt(bean.getC())/2;
		int y=Integer.parseInt(bean.getD())/2;
		
		dot.setBounds(x,y,10,10);
		
		t.setText("총 "+list.size()+"건의 알바 목록이 있어요!");
		
		model=new DefaultTableModel(ro,co) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			// jno를 따로 저장
			jno[i]=Integer.parseInt(bean.getE());
			model.addRow(new Object[] {"<html>"+bean.getF()});
		}
		
		table=new JTable(model);
		
		// html 때문에 내용이 세로 가운데 정렬되어 세로 위 정렬해줌
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setVerticalAlignment(JLabel.TOP);
		table.getColumn(co[0]).setCellRenderer(dtc);
		
		table.setRowHeight(50);
		table.setFont(new Font("맑은 고딕", 0,10));
		table.getTableHeader().setBackground(new Color(255,120,50));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("맑은 고딕", 1, 12));
		// 선택했을 때의 글자색 변경
		table.setSelectionForeground(Color.black);
		// 선택했을 때의 배경색 변경
		table.setSelectionBackground(Color.orange);
		
		jsp.setViewportView(table);
		

		logo.setBounds(10, 10, 130, 130);
		logo1.setBounds(150, 10, 450-150, 30);
		logo2.setBounds(150, 10, 450-150, 130);
		map.setBounds(10, 150,300,275);
		t.setBounds(320, 150-20, 160, 20);
		jsp.setBounds(320, 150, 160, 275);
		p.setBounds(0, 510-70, 485, 75);
		
		add(logo);
		add(logo1);
		add(logo2);
		add(map);
		map.add(dot);
		add(t);
		add(jsp);
		add(p);
		
		img.setBounds(5, 5, 100, 60);
		lb.setBounds(120, 5, 470-120, 60);
		p.add(lb);
		p.add(img);

		setVisible(true);
		setTitle("브랜드 정보");
		setSize(500, 550);
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		// 광고 랜덤으로 바뀌게 하는 부분
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mgr.sql="select * from advertise order by rand();";
				list=mgr.a2();
				bean=list.get(0);
				img.setName(bean.getA());
				img.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+bean.getA()+"-1.jpg").getImage().getScaledInstance(100,60, Image.SCALE_SMOOTH)));
				lb.setText("<html>"+bean.getB());
				
			}
		};
		timer.schedule(task, 0, 2000);
		
		// 알바 목록 더블 클릭 시 알바 정보 폼 이동
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				// 클릭 횟수가 두 번일 때 ≒ 더블 클릭 시
				if(e.getClickCount()==2) {
					info.h=2;
					info.no=jno[table.getSelectedRow()];
					dispose();
					new info();
				}
			}
		});
		
		// 광고 영역을 마우스로 클릭했을 때
		p.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				advertise.h=2;
				advertise.no=Integer.parseInt(bean.getA());
				new advertise();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				if(h==0) {
					new info();
				}
				if(h==1) {
					new brand();
				}
				if(h==2) {
					new find();
				}
				if(h==3) {
					new mypage();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new binfo();
	}

}

