package pro;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class ta extends JFrame{

	JScrollPane jsp=new JScrollPane();
	JTextField tf1=new JTextField();
	JTextArea ta1=new JTextArea();
	JButton bt1=new JButton("¹öÆ°");
	
	public ta() {
		setLayout(null);
		
		tf1.setVisible(false);
		
		ta1.setBorder(new LineBorder(Color.black));
		
		tf1.setBounds(10,10,260,60);
		ta1.setBounds(10,100,260,60);
		bt1.setBounds(10,170,260,30);
		
		add(jsp);
		add(tf1);
		add(ta1);
		add(bt1);
		
		
		setVisible(true);
		setSize(300,300);
		setTitle("textarea");
		getContentPane().setBackground(Color.white);
		
		bt1.addActionListener((e)->{
			if(tf1.isVisible()){
				tf1.setVisible(false);
				ta1.setVisible(true);
			}else {
				tf1.setVisible(true);
				ta1.setVisible(false);
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ta();
	}

}
