package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class mypage extends JFrame{

	JButton bt1=new JButton("내가 좋아한 알바");
	JButton bt2=new JButton("대기중인 알바");
	JButton bt3=new JButton("합격한 알바");
	
	JScrollPane jsp=new JScrollPane();
	JTable table=new JTable();
	DefaultTableModel model=new DefaultTableModel();
	
	int jno[]=new int[999];
	int bno[]=new int[999];
	
	//column 열
	String co[];
	//row 행
	String ro[][];
	
	// table을 세 개 만드는 대신 table 초기화하여 co과 ro를 선택한 버튼에 따라 바뀌게 할 것임
	String co1[]= {"번호","카테고리","브랜드","브랜드명","알바","좋아요 한 날짜"};
	String co2[]= {"번호","지원 번호","브랜드","알바","지원 날짜","지원 자격"};
	String co3[]= {"번호","지원 번호","브랜드","알바","지원 날짜","금액"};
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public mypage() {
		setLayout(null);
		
		bt1.setFont(new Font("맑은 고딕",0,13));
		bt2.setFont(new Font("맑은 고딕",0,13));
		bt3.setFont(new Font("맑은 고딕",0,13));
		
		bt1.setForeground(Color.white);
		bt1.setBackground(new Color(255,120,50));
		bt1.setBorder(new LineBorder(new Color(255,120,50)));
		bt2.setForeground(new Color(255,120,50));
		bt2.setBackground(Color.white);
		bt2.setBorder(new LineBorder(new Color(255,120,50)));
		bt3.setForeground(new Color(255,120,50));
		bt3.setBackground(Color.white);
		bt3.setBorder(new LineBorder(new Color(255,120,50)));
		
		bt1.setBounds(10,10,210,30);
		bt2.setBounds(230,10,210,30);
		bt3.setBounds(450,10,210,30);
		
		jsp.setBounds(10,50,650,400);
		
		co=co1;
		mgr.sql="select j.jno, cname, b.bno, bname, jname, ldate from likes as l join job as j on l.jno=j.jno join brand as b on j.bno=b.bno join category as c on b.cno=c.cno where uno="+main.no+";";
		set();
		
		add(bt1);
		add(bt2);
		add(bt3);
		add(jsp);
		
		setVisible(true);
		setSize(700,500);
		setTitle("마이페이지");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);

		bt1.addActionListener((e)->{
			bt1.setForeground(Color.white);
			bt1.setBackground(new Color(255,120,50));
			bt1.setBorder(new LineBorder(new Color(255,120,50)));
			bt2.setForeground(new Color(255,120,50));
			bt2.setBackground(Color.white);
			bt2.setBorder(new LineBorder(new Color(255,120,50)));
			bt3.setForeground(new Color(255,120,50));
			bt3.setBackground(Color.white);
			bt3.setBorder(new LineBorder(new Color(255,120,50)));
			co=co1;
			mgr.sql="select j.jno, cname, b.bno, bname, jname, ldate from likes as l join job as j on l.jno=j.jno join brand as b on j.bno=b.bno join category as c on b.cno=c.cno where uno="+main.no+";";
			set();
		});
		bt2.addActionListener((e)->{
			bt2.setForeground(Color.white);
			bt2.setBackground(new Color(255,120,50));
			bt2.setBorder(new LineBorder(new Color(255,120,50)));
			bt1.setForeground(new Color(255,120,50));
			bt1.setBackground(Color.white);
			bt1.setBorder(new LineBorder(new Color(255,120,50)));
			bt3.setForeground(new Color(255,120,50));
			bt3.setBackground(Color.white);
			bt3.setBorder(new LineBorder(new Color(255,120,50)));
			mgr.sql="select j.jno, apno, b.bno, jname, apdate, if(jgrade=0,'무관',if(jgrade=1,'대학','고등')) from apply as a join job as j on a.jno=j.jno join brand as b on j.bno=b.bno where uno="+main.no+" and apok=0;";
			co=co2;
			set();
		});
		bt3.addActionListener((e)->{
			bt3.setForeground(Color.white);
			bt3.setBackground(new Color(255,120,50));
			bt3.setBorder(new LineBorder(new Color(255,120,50)));
			bt2.setForeground(new Color(255,120,50));
			bt2.setBackground(Color.white);
			bt2.setBorder(new LineBorder(new Color(255,120,50)));
			bt1.setForeground(new Color(255,120,50));
			bt1.setBackground(Color.white);
			bt1.setBorder(new LineBorder(new Color(255,120,50)));
			mgr.sql="select j.jno, apno, b.bno, jname, apdate, if(jgrade=0,'무관',if(jgrade=1,'대학','고등')) from apply as a join job as j on a.jno=j.jno join brand as b on j.bno=b.bno where uno="+main.no+" and apok=1;";
			co=co3;
			set();
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
	
	void set() {
		
		model=new DefaultTableModel(ro,co) {
			// table column에 이미지 넣기 전 설정
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
				if(getRowCount()>0) {
					Object value=getValueAt(0, columnIndex);
					if(value!=null) {
						return getValueAt(0, columnIndex).getClass();
					}
				}
				return super.getColumnClass(columnIndex);
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		list=mgr.a6();
		for (int i = 0; i < list.size(); i++) {
			bean=list.get(i);
			
			// try catch => try 안 구문에 오류가 생기면 프로그램이 멈추지 않고 catch 안 구문이 작동함
			// ImageIO 사용 시, 읽는(.read()) 파일이 존재하지 않는 경우를 대비해 try catch를 사용하게 되어있음. try catch 미사용 시 오류남
			
			Image img=null;
			ImageIcon icon=null;
			
			try {
				img=ImageIO.read(new File("./datafiles/brand/"+bean.getC()+".png"));
				icon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			jno[i]=Integer.parseInt(bean.getA());
			bno[i]=Integer.parseInt(bean.getC());
			
			if(co==co1) {
				model.addRow(new Object[] {(i+1),  "<html>"+bean.getB(),  icon ,bean.getD() ,  "<html>"+bean.getE(), bean.getF()});
			} else {
				model.addRow(new Object[] {(i+1),  bean.getB(), icon , "<html>"+bean.getD(), bean.getE(), bean.getF()});
			}
		}
		
		table=new JTable(model);
		

		// 행 높이 조절
		table.setRowHeight(100);
		
		table.getTableHeader().setBackground(new Color(255,120,50));
		table.getTableHeader().setForeground(Color.white);
		table.setFont(new Font("맑은 고딕",0,13));
		table.getTableHeader().setForeground(Color.white);
		
		// 테이블 글자 정렬
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(JLabel.CENTER);
		
		DefaultTableCellRenderer dtc2=new DefaultTableCellRenderer();
		dtc2.setVerticalAlignment(JLabel.TOP);
		
		
		for (int i = 0; i < co.length; i++) {
			if(co==co1) {
				// 열 넓이 임의 크기
				int w1[]= {30,40,120,50,200,80};
				if(i==0||i==3||i==5) {
					// 열 글자 정렬 적용
					table.getColumn(co[i]).setCellRenderer(dtc);
				}else if(i==1||i==4) {
					table.getColumn(co[i]).setCellRenderer(dtc2);
				}
				// 열 넓이 적용
				table.getColumn(co[i]).setPreferredWidth(w1[i]);
			}else {
				int w2[]= {30,40,120,200,80,50};
				if(i==0||i==1||i==4||i==5) {
					table.getColumn(co[i]).setCellRenderer(dtc);
				}else if(i==3) {
					table.getColumn(co[i]).setCellRenderer(dtc2);
				}
				table.getColumn(co[i]).setPreferredWidth(w2[i]);
			}
		}
		
		jsp.setViewportView(table);
		
		// set()이 실행될 떼마다 table이 재생성 됨
		// 따라서 table이 재생성 되는 set() 안에 table = new table(model); 아래에 액션을 적어줌
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				if(e.getClickCount()==2) {
					// 이미지 열 클릭 시
					if(table.getSelectedColumn()==2) {
						binfo.no=bno[table.getSelectedRow()];
						dispose();
						binfo.h=3;
						new binfo();
					// 알바(제목) 열 클릭 시
					}else if(co==co1&&table.getSelectedColumn()==4) {
						info.no=jno[table.getSelectedRow()];
						info.h=3;
						dispose();
						new info();
					// 알바(제목) 열 클릭 시
					}else if(co!=co1&&table.getSelectedColumn()==3) {
						info.no=jno[table.getSelectedRow()];
						info.h=3;
						dispose();
						new info();
					}
				}
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mypage();
	}

}
