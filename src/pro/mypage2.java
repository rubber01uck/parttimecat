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

public class mypage2 extends JFrame{
	
	int di=0;
	
	int jno[]=new int[999];
	int bno[]=new int[999];
	
	JButton b[]=new JButton[3];
	String bb[]= {"내가 좋아한 알바","대기중일 알바","합격한 알바"};
	
	JScrollPane jsp=new JScrollPane();
	JTable table=new JTable();
	DefaultTableModel model=new DefaultTableModel();
	
	String co[];
	String ro[][];
	
	String co1[]= {"번호","카테고리","브랜드","브랜드명","알바","좋아요 한 날짜"};
	String co2[]= {"번호","지원 번호","브랜드","알바","지원 날짜","지원 자격"};
	String co3[]= {"번호","지원 번호","브랜드","알바","지원 날짜","금액"};
	
	String[][] c= {co1, co2, co3};
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public mypage2() {
		setLayout(null);
	
		String bb[]= {"내가 좋아한 알바","대기중일 알바","합격한 알바"};
		for (int i = 0; i < b.length; i++) {
			b[i]=new JButton(bb[i]);
			b[i].setBorder(new LineBorder(new Color(255,120,50)));
			b[i].setFont(new Font("맑은 고딕",0, 12 ));
			b[i].setBounds(10+220*i,10,210,30);
			
			add(b[i]);
			
			int n=i;
			b[n].addActionListener((e)->{
				di=n;
				set();
			});
		}
		
		
		jsp.setBounds(10,50,650,400);
		
		co=co1;
		
		set();
		
		add(jsp);
		
		setVisible(true);
		setSize(700,500);
		setTitle("마이페이지");
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
	}
	
	void set() {
		for (int i = 0; i < b.length; i++) {
			b[i].setBackground(Color.white);
			b[i].setForeground(new Color(255,120,50));
		}
		b[di].setForeground(Color.white);
		b[di].setBackground(new Color(255,120,50));
		co=c[di];
		
		if(di==0) {
			//첫번쨰 테이블
			mgr.sql="select j.jno, cname, b.bno, bname, jname, ldate\r\n"
					+ " from job j join likes l on l.jno=j.jno join brand b on b.bno=j.bno  join category c on c.cno=b.cno  \r\n"
					+ "  where uno="+main.no+" order by ldate;\r\n"
					+ "";
			co=co1;
		}else if(di==1) {
			//두번쨰 테이블
			mgr.sql="select j.jno, apno, b.bno, jname, apdate, case when jgrade=0 then '무관' when jgrade=1 then '대학' else '고등' end\r\n"
					+ " from job j join apply l on l.jno=j.jno join brand b on b.bno=j.bno  join category c on c.cno=b.cno  \r\n"
					+ "  where uno="+main.no+" and apok=0 order by apdate;\r\n"
					+ "";
			co=co2;
		}else if(di==2) {
			//세번쨰 테이블
			mgr.sql="select j.jno, apno, b.bno, jname, apdate, format(jmoney,0)\r\n"
					+ " from job j join apply l on l.jno=j.jno join brand b on b.bno=j.bno  join category c on c.cno=b.cno  \r\n"
					+ "  where uno="+main.no+" and apok=1 order by apdate;\r\n"
					+ "";
			co=co3;
		}
		model=new DefaultTableModel(ro,co) {
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
			
			if(di==0) {
				model.addRow(new Object[] {(i+1),  "<html>"+bean.getB()+"<br><br><br><br><br>", icon ,bean.getD() ,  "<html>"+bean.getE()+"<br><br><br><br><br>", bean.getF()});
			} else {
				model.addRow(new Object[] {(i+1),  bean.getB(), icon , "<html>"+bean.getD()+"<br><br><br><br><br>", bean.getE(), bean.getF()});
			}
		}
		
		table=new JTable(model);

		// 행 높이 조절
		table.setRowHeight(100);
		
		table.getTableHeader().setBackground(new Color(255,120,50));
		table.getTableHeader().setForeground(Color.white);
		table.setFont(new Font("맑은 고딕",0,13));
		table.getTableHeader().setForeground(Color.white);
		
		
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(JLabel.CENTER);
		
		for (int i = 0; i < co.length; i++) {
			if(di==0) {
				int w1[]= {30,40,120,50,200,80};
				if(i==0||i==3||i==5) {
					table.getColumn(co[i]).setCellRenderer(dtc);
				}
				table.getColumn(co[i]).setPreferredWidth(w1[i]);
			}else {
				int w2[]= {30,40,120,200,80,50};
				if(i==0||i==1||i==4||i==5) {
					table.getColumn(co[i]).setCellRenderer(dtc);
				}
				table.getColumn(co[i]).setPreferredWidth(w2[i]);
			}
		}
		
		
		
		jsp.setViewportView(table);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mypage2();
	}

}
