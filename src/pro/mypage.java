package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	
	//column 열
	String co[]= {"A","B","C","D","E"};
	//row 행
	String ro[][];
	
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
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		for (int i = 0; i < 10; i++) {
			model.addRow(new Object[] {(i+1),(i+1)+"-1",(i+1)+"-2",(i+1)+"-3",(i+1)+"-4"});
//			model.addRow(new Object[] {(i*5+1),(i*5+2),(i*5+3),(i*5+4),(i*5+5)});
		}
		
		table=new JTable(model);

		// 행 높이 조절
		table.setRowHeight(30);
		
		table.getTableHeader().setBackground(new Color(255,120,50));
		table.getTableHeader().setForeground(Color.white);
		table.setFont(new Font("맑은 고딕",0,13));
		
		DefaultTableCellRenderer dtc=new DefaultTableCellRenderer();
		dtc.setHorizontalAlignment(JLabel.CENTER);
		
		
		for (int i = 0; i < co.length; i++) {
			if(i==0||i==3||i==5) {
				table.getColumn(co[i]).setCellRenderer(dtc);
			}
		}
		
		jsp.setViewportView(table);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mypage();
	}

}
