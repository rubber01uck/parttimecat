package pro;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class login extends JFrame {

	// , JLabel.CENTER <- 글자 가운데 정렬 ≒ .setHorizontalAlignment(JLabel.CENTER)
	JLabel lb1=new JLabel("알바캣", JLabel.CENTER);
	JTextField tf1=new JTextField();
	JTextField tf2=new JTextField();
	JButton bt1=new JButton("로그인");
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public login() {
		setLayout(null);
		
		// 폰트 변경
		// new Font(폰트명, 폰트 스타일, 폰트 크기)
		// 폰트 스타일 => 0=기본, 1=볼드체(굵게), 2=이탤릭체(기울기), 3=볼드체+이탤릭체
		lb1.setFont(new Font("맑은 고딕",0,20));
		// 글자색 변경
		// new Color(R,G,B)
		lb1.setForeground(new Color(255,120,50));
		
		bt1.setFont(new Font("맑은 고딕",0,10));
		bt1.setForeground(Color.white);
		// 배경색 변경
		bt1.setBackground(new Color(255,120,50));
		
		// 배치 설정
		// .setBounds(x값, y값, 폭 값, 높이 값)
		lb1.setBounds(0,0,485,30);
		tf1.setBounds(20,60,220,30);
		tf2.setBounds(250,60,220,30);
		bt1.setBounds(20,100,450,30);
		
		// 위치만 조정하고 싶다면
		// .setLocation(x값, y값)

		// 크기만 조정하고 싶다면
		// .setSize(폭 값, 높이 값)
		
		add(lb1);
		add(tf1);
		add(tf2);
		add(bt1);
		
		setVisible(true);
		setSize(500,200);
		setTitle("로그인");
		// 폼 아이콘 변경
		// ./datafiles/icon/icon.png <- 상대 주소 (. ≒ 현재 파일 위치까지는 생략)
		// 상대 주소 <=> 절대 주소 (C:\Users\guest\Desktop\parttimecat\parttimecat\datafiles\icon\icon.png)
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		// 폼 배경색 변경
		getContentPane().setBackground(Color.white);
		
		// bt1이 동작할 때 (마우스, 키보드 둘 다 가능)
		bt1.addActionListener((e)->{
			mgr.sql="select uno, unick from user where uid='"+tf1.getText()+"' and upw='"+tf2.getText()+"';";
			// 결과값의 열이 2개 ≒ .a2()
			list=mgr.a2();
			// tf 둘 중 하나라도 빈칸이면
			if(tf1.getText().equals("")||tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다.","경고",JOptionPane.ERROR_MESSAGE);
			}else if(tf1.getText().equals("admin")&&tf2.getText().equals("1234")){
				JOptionPane.showMessageDialog(null, "관리자로 로그인하였습니다.","정보",JOptionPane.INFORMATION_MESSAGE);
				// 폼 닫기
				dispose();
				// 관리자 메인 폼 불러오기
				new amain();
			// sql 검색 결과가 없을 때 ≒ 해당 id, pw를 가진 회원이 없을 때
			}else if(list.size()==0) {
				JOptionPane.showMessageDialog(null, "일치하는 회원 정보가 없습니다.","경고",JOptionPane.ERROR_MESSAGE);
			}else {
				// 검색 결과가 하나라도 있어야만 가져올 수 있음
				bean=list.get(0);
				// bean.getB() ≒ unick
				JOptionPane.showMessageDialog(null, bean.getB()+" 회원님 환영합니다.","정보",JOptionPane.INFORMATION_MESSAGE);
				// main의 int형 변수 no를 bean.getA() ≒ uno로 변경함
				// Integer.parseInt() <- int형이 아닌 변수를 int형으로 변환할 때 사용
				// bean에 담긴 값은 항상 문자열임
				main.no=Integer.parseInt(bean.getA());
				dispose();
				new main();
			}
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 로그인 폼 불러오기 (실행)
		new login();
	}

}