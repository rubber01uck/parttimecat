package pro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class amain extends JFrame{

	String a[]= {"최신순","오래된순"};
	JComboBox cb1=new JComboBox(a);
	
	JLabel add=new JLabel();
	JScrollPane jsp1=new JScrollPane();
	
	public amain() {
		setLayout(null);
		
		cb1.setFont(new Font("맑은 고딕",0,13));
		cb1.setBackground(Color.white);
		
		cb1.setBounds(20,50,150,30);
		add.setBounds(400,20,60,60);
		jsp1.setBounds(20,100,440,350);
		
		// 색 랜덤 시 아래 한 줄은 지워도 됨
		add.setIcon(new ImageIcon(new ImageIcon("./datafiles/icon/insert.png").getImage().getScaledInstance(60, 60, 0)));
		
//		set();
		
		add(cb1);
		add(add);
		add(jsp1);
		
		setVisible(true);
		setSize(500,500);
		setTitle("관리자 메인");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		setFont(new Font("맑은 고딕",0,13));
		getContentPane().setBackground(Color.white);
		
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				new insert();
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new login();
			}
		});
	}
	
//	void set() {
//		Timer timer=new Timer();
//		TimerTask task=new TimerTask() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				BufferedImage img;
//				try {
//					img = ImageIO.read(new File("./datafiles/icon/insert.png"));
//					int color;
//					
//					Random rand=new Random();
//					Color col=new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
	
					// 이미지 픽셀로 쪼개서 한 픽셀마다 색 바꾸기
//					for (int x = 0; x < img.getWidth(); x++) {
//						for (int y = 0; y < img.getHeight(); y++) {
//							int rgb=img.getRGB(x, y);
							// 투명부분이 아니면
//							if(rgb<-10) {
//								img.setRGB(x, y, col.getRGB());
//							}
//						}
//					}
//					add.setIcon(new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(60, 60, 0)));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//				}
//			}
//		};
//		timer.schedule(task, 0,1000);
//	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new amain();
	}

}
