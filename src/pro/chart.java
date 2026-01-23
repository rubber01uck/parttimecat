package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class chart extends JFrame{

	String a[]= {"내가 좋아하는 알바","인기 많은 브랜드"};
	JComboBox cb1=new JComboBox(a);
	
//	JPanel pp=new JPanel();
	
//	JPanel all=new JPanel(null);
	
//	int v[]=new int[5];
//	int ang[]=new int[5];
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public chart() {
		setLayout(null);

		cb1.setBackground(Color.white);
		cb1.setFont(new Font("맑은 고딕",0,13));
		
		cb1.setBounds(10,10,200,30);
//		all.setBounds(40, 410, 500, 20);
		
//		set();
		
		add(cb1);
//		add(all);
		
		setVisible(true);
		setSize(600,500);
		setTitle("통계");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		setFont(new Font("맑은 고딕",0,13));
		getContentPane().setBackground(Color.white);
		
		cb1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
//				set();
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
	
//	Color[] col=new Color[5];
//	Random rand=new Random();
	
//	void set() {
//		all.removeAll();
//		all.setBackground(Color.white);
//		
//		if(cb1.getSelectedIndex()==0) {
//			// 첫번째 차트
//			mgr.sql="select c.cno, cname, count(*) from apply a join job j on a.jno=j.jno join brand b on j.bno=b.bno join category c on b.cno=c.cno where uno="+main.no+" group by c.cno order by count(*) desc, c.cno limit 5;";
////			mgr.sql="select c.cno, cname, count(*) from apply a join job j on a.jno=j.jno join brand b on j.bno=b.bno join category c on b.cno=c.cno where uno="+main.no+" group by c.cno order by 3 desc, 1 limit 5;";
//		} else {
//			// 두번째 차트
//			mgr.sql="select b.bno, bname, count(*) from apply a join job j on a.jno=j.jno join brand b on j.bno=b.bno group by b.bno order by count(*) desc, b.bno limit 5;";
////			mgr.sql="select b.bno, bname, count(*) from apply a join job j on a.jno=j.jno join brand b on j.bno=b.bno group by b.bno order by 3 desc, 1 limit 5;";
//		}
//		list=mgr.a3();
//		
//		double sum=0;
//		
//		JPanel a1[]=new JPanel[list.size()];
//		JLabel a2[]=new JLabel[list.size()];
//		
//		for (int i = 0; i < list.size(); i++) {
//			bean=list.get(i);
//			
//			v[i]=Integer.parseInt(bean.getC());
//			sum+=v[i];
//		
			// 색깔 랜덤
//			col[i]=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
//			
//			a1[i]=new JPanel(null);
//			a2[i]=new JLabel(bean.getB());
//			
//			a1[i].setBackground(col[i]);
//			a2[i].setFont(new Font("맑은 고딕", 1, 10));
//			
//			a1[i].setBounds(0+100*i, 0, 20, 20);
//			a2[i].setBounds(25+100*i, 0, 100, 20);
//			
//			all.add(a1[i]);
//			all.add(a2[i]);
//		}
//		
		// 비율에 따라 각도 구하기
//		int t=0;
//		for (int i = 0; i < ang.length; i++) {
//			if(i<4) {
//				ang[i]=(int)Math.round(v[i]/sum*360);
//				t+=ang[i];
//			} else {
//				ang[i]=360-t;
//			}
//		}
//		
//		pp= new JPanel(null) {
//			@Override
//			public void paint(Graphics g) {
//				// TODO Auto-generated method stub
//				super.paint(g);
//				int x=0;
//				int y=0;
//				//지름
//				int r=300;
//				//시작 지점(각도)
//				int t=0;
//				for (int i = 0; i < ang.length; i++) {
//					g.setColor(col[i]);
//					g.fillArc(x, y, r, r, t, ang[i]);
//					// 그리는 끝 지점(각도)
//					t+=ang[i];
//				}
//			}
//		};
//		pp.setBackground(Color.white);
//		pp.setBounds(140, 50, 300, 350);
//		add(pp);
//		
//		all.updateUI();
//		pp.repaint();
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new chart();
	}

}
