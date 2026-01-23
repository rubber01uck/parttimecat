package pro;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class binfo extends JFrame{

	static int h=0;
	static int no=0;
	
	public binfo() {
		setLayout(null);
		
		
		setVisible(true);
		setSize(500,400);
		setTitle("°ü¸®ÀÚ ¸ÞÀÎ");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		setFont(new Font("¸¼Àº °íµñ",0,13));
		getContentPane().setBackground(Color.white);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new binfo();
	}

}
