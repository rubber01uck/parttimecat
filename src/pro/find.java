package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class find extends JFrame{

	JLabel map=new JLabel(new ImageIcon(new ImageIcon("./datafiles/지도.png").getImage().getScaledInstance(600,550,0)));

	JScrollPane jsp=new JScrollPane();
	JTable table=new JTable();
	DefaultTableModel model=new DefaultTableModel();
	String co[]= {"직종 카테고리"};
	String ro[][];
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public find () {
		setLayout(null);
		
		mgr.sql="select * from category;";
		list=mgr.a2();
	
		JLabel logo=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/cat.png").getImage().getScaledInstance(160, 80, Image.SCALE_SMOOTH)));
		
		model=new DefaultTableModel(ro,co) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		model.addRow(new Object[] {"전체"});
		
		mgr.sql="select * from category;";
		list=mgr.a2();
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			model.addRow(new Object[] {bean.getB()});
		}
		
		table=new JTable(model);
		table.setFont(new Font("맑은 고딕",0,12));
		table.getTableHeader().setFont(new Font("맑은 고딕",1,12));
		table.getTableHeader().setBackground(Color.white);
		table.setSelectionBackground(Color.red);
		table.setSelectionForeground(Color.white);
		
		// 행 다중 선택 가능
		// ListSelectionModel.MULTIPLE_INTERVAL_SELECTION = 2
//		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		≒ table.setSelectionMode(2);
		
		table.setRowHeight(35);
		
		// 0번째 행부터 0번째 행까지 선택시키기 ≒ '전체' 선택하기
		table.setRowSelectionInterval(0, 0);
		
		jsp.setViewportView(table);
		
		map.setBounds(0, 0, 600, 550);
		jsp.setBounds(620, 0, 160, 410);
		logo.setBounds(610, 450, 170, 80);
		
		add(map);
		add(jsp);
		add(logo);
		
		set();
		
		setVisible(true);
		setTitle("찾기");
		setSize(800, 590);
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				new main();
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				set();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new main();
			}
		});
	}
	
	// 조건에 맞도록 패널 디자인
	void set() {
		map.removeAll();
		
		if(table.getSelectedRow()==0) {
			mgr.sql="select * From brand;";
		} else {
			// 다중 선택 제외 기준
			mgr.sql="select * From brand where cno="+table.getSelectedRow()+";";
			
		}
		list=mgr.a5();
		
		JLabel[] a=new JLabel[list.size()];
		
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			
			
			a[i]=new JLabel("●");
			a[i].setName(bean.getA());
			// 마우스 오버 시, 브랜드 이름 툴팁으로 나타내기
			a[i].setToolTipText(bean.getB());
			a[i].setForeground(Color.red);
			
			int x=Integer.parseInt(bean.getD());
			int y=Integer.parseInt(bean.getE());
			
			a[i].setBounds(x, y, 20, 20);
			
			map.add(a[i]);
			
			int n=i;
			a[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					if(e.getClickCount()==2) {
						dispose();
						binfo.h=2;
						binfo.no=Integer.parseInt(a[n].getName());
						new binfo();
					}
				}
			});
			
		}
		
		map.updateUI();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new find();
	}

}