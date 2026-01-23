package pro;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class main extends JFrame{

	static int no=1;
	
	// .getScaledInstance(이미지 폭 값, 이미지 높이 값, 이미지 스타일(gif 제외 = Image.SMOOTH)))
	// Image.SMOOTH = 4 (근데 0 해도 됨)
	JLabel logo=new JLabel(new ImageIcon(new ImageIcon("./datafiles/icon/cat.png").getImage().getScaledInstance(100, 50, 0)));
	JLabel lb=new JLabel("알바캣");

	// [A영역]
	// 레이블(JLabel) 배열
	JLabel a1[]=new JLabel[5];
	// 문자열(String) 배열
	String a2[]= {"채용", "브랜드", "찾기", "마이페이지", "통계"};
	// GridLayout() <- 격자 레이아웃, 안에 객체를 넣을 때, setBounds() 안 해도 됨. 크기 자동 변경
	// GridLayout(행 개수, 열 개수, 열 간격, 행 간격)
	JPanel p1=new JPanel(new GridLayout(1,5));
	
	
	// [B영역]
	// = new Panel(null) ≒ .setLayout(null)
	JPanel p2=new JPanel(null);
	JLabel top=new JLabel("인기 브랜드 TOP 5");
	JLabel top1[]=new JLabel[5];
	
	
	// [C영역]
	JLabel img=new JLabel();
	
	
	// [D영역]
	JPanel p3=new JPanel(null);
	JLabel b1=new JLabel("추천 알바");
	JLabel b2[]=new JLabel[6];
	
	ArrayList<Bean>list;
	Bean bean;
	DBMgr mgr=new DBMgr();
	
	public main() {
		setLayout(null);
		
		lb.setFont(new Font("맑은 고딕",1,20));
		lb.setForeground(new Color(255,120,50));
		
		for (int i = 0; i < 5; i++) {
			a1[i]=new JLabel(a2[i],JLabel.CENTER);
			a1[i].setFont(new Font("맑은 고딕",1,13));
			a1[i].setForeground(Color.white);
			p1.add(a1[i]);
			
			int n=i;
			a1[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					dispose();
					if(n==0) {
						new search();
					}else if(n==1) {
						new brand();
					}else if(n==2) {
						new find();
					}else if(n==3) {
						new mypage();
					}else {
						new chart();
					}
				}
			});
		}
		
		// [B영역]
		// 조건 4. 지원 건수 많은 순, 브랜드 번호 오름차순으로 브랜드 나열
		// 지원(apply) 테이블에는 브랜드 정보가 없으므로 브랜드 번호가 있고 같은 열이 있는 알바(job) 테이블 결합
		// 알바(job) 테이블에는 브랜드 이름이 없으므로 브랜드(brand) 테이블 결합
		// 브랜드 별로 지원 횟수를 알아야 하므로 브랜드 번호로 묶음(group by)
		// 지원 건수 많은 순으로 정렬해야 하므로 order by count(*) desc
		// 지원 건수 내림차순 다음 정렬 기준이 브랜드 번호 오름차순, 정렬 기준이 여러 개일 때는 콤마로 구분하여 순서대로 작성 => order by count(*) desc, b.bno (asc)
		// TOP 5만 필요하므로 가장 뒤에 'limit 가져올 수' => limit 5
		mgr.sql="select b.bno, bname, count(*) from apply as a join job as j on a.jno=j.jno join brand as b on j.bno=b.bno group by b.bno order by count(*) desc, b.bno limit 5;";
		
		// 브랜드 이름은 확인용으로 디자인 시 필요 없으므로 생략 가능 -> join brand 안 해도 됨.
		// mgr.sql="select bno, count(*) from apply as a join job as j on a.jno=j.jno group by bno order by count(*) desc, bno limit 5;";
		
		
		// mgr.sql에 작성한 구문을 mysql에 전송하여 실행하고 그 결과 값을 list에 저장
		// list 저장 시 결과 값의 열에 따라 가져오기, 실행할 mgr.sql은 출력할 열이 b.bno, bname, count(*) 세 개이므로 => list=mgr.a3();
		list=mgr.a3();
		for (int i = 0; i < 5; i++) {
			// bean은 list에서 행을 하나씩만 가져와서 저장할 수 있음
			// 실행할 mgr.sql의 결과값은 행이 5개
			// for문 안에서 각 행의 값을 불러와서 JLabel이 선언될 때마다 넣어줌
			bean=list.get(i);
			top1[i]=new JLabel(new ImageIcon(new ImageIcon("./datafiles/brand/"+bean.getA()+".png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH)));
			// 테두리 설정
			// new LineBorder(색, 선 굵기(숫자, 생략 가능))
			top1[i].setBorder(new LineBorder(Color.black));
			// for문이 돌 때마다 i값이 변경되어 x값이 80씩 늘어남
			top1[i].setBounds(10+80*i,30,70,70);
			p2.add(top1[i]);
		}
		
		// 로그인한 유저가 가장 최근에 지원한 알바의 카테고리 구하는 법
		mgr.sql="select cno from apply as a join job as j on a.jno=j.jno join brand as b on j.bno=b.bno where uno=1 order by apdate desc limit 1;";
		list=mgr.a1();
		// 로그인이 지원한 알바가 없을 경우 ≒ 위 sql의 결과 값이 나오지 않음
		if(list.size()==0) {
			// order by rand() <- 정렬 랜덤
			// 카테고리를 지정해주지 않고 랜덤으로 6개 추출
			mgr.sql="select jno, jname, jday, jtime, if(jwork=0,'정규직','계약직'), jmoney from job as j join brand as b on j.bno=b.bno order by rand() limit 6;";
		}else {
			// 가져온 카테고리 번호로 검색 후, 랜덤으로 6개 추출
			bean=list.get(0);
			mgr.sql="select jno, jname, jday, jtime, if(jwork=0,'정규직','계약직'), jmoney from job as j join brand as b on j.bno=b.bno where cno="+bean.getA()+" order by rand() limit 6;";
		}
		list=mgr.a6();
		
		int x=30;
		int y=30;
		for (int i = 0; i < 6; i++) {
			bean=list.get(i);
			// <html> <- JLabel의 Text 값이 JLabel의 폭 넓이를 넘겼을 때, 자동 줄바꿈 됨
			// <b></b> <- 사이의 Text 값은 볼드체(굵게) 처리됨
			// <br> 다음 줄로 이동됨
			// 문자열과 문자열 연결/결합 시 + 사용
			b2[i]=new JLabel("<html><b>"+bean.getB()+"</b><br><br>[주 "+bean.getC()+"일, "+bean.getD()+"시간, "+bean.getE()+"]<br>시급: "+bean.getF()+"원");
			b2[i].setName(bean.getA());
			b2[i].setBorder(new LineBorder(new Color(255,120,50)));
			b2[i].setBounds(x,y,180,130);
			// JLabel은 .setOpaque(false)가 기본 설정이라 true로 바꾸지 않고 배경색을 변경하지 않으면 적용이 되지 않음
			b2[i].setOpaque(true);
			b2[i].setBackground(Color.white);
			b2[i].setFont(new Font("맑은 고딕",0,13));
			
			// 폭 값(180) + 주고 싶은 간격(20)
			x+=200;
			// if문 보다 .setBounds()를 먼저 작성하였으므로 이 if문은 'JLable 3개를 배치하였다면'으로 해석할 수 있음
			if(i==2) {
				// 기본 간격
				x=30;
				// 높이 값(130) + 주고 싶은 간격(20)
				y+=150;
			}
			p3.add(b2[i]);
			
			int n=i;
			b2[n].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mouseClicked(e);
					dispose();
					info.no=Integer.parseInt(b2[n].getName());
					// h <- 이전 폼 구분
					// h=0은 메인에서 불러냄
					// h=1은 채용에서 불러냄
					info.h=0;
					new info();
				}
			});
		}
		p3.setBorder(new LineBorder(Color.black));
		p3.setBounds(20,230,645,320);
		
		logo.setBounds(20,0,100,50);
		lb.setBounds(120,0,100,50);
		
		p1.setBackground(new Color(255,120,50));
		p1.setBounds(0,60,685,40);
		
		top.setFont(new Font("맑은 고딕",1,10));
		top.setBounds(10,0,200,30);
		p2.add(top);
		
		p2.setBorder(new LineBorder(Color.black));
		p2.setBounds(20,110,410,110);
		
		img.setBorder(new LineBorder(Color.black));
		img.setBounds(440,110,220,110);
		
		b1.setFont(new Font("맑은 고딕",1,10));
		b1.setBounds(10,0,100,30);
		p3.add(b1);
		
		set();
		
		add(img);
		add(logo);
		add(lb);
		add(p1);
		add(p2);
		add(p3);
		
		setVisible(true);
		setSize(700,600);
		setTitle("메인");
		setIconImage(new ImageIcon("./datafiles/icon/icon.png").getImage());
		getContentPane().setBackground(Color.white);
		
		// 마우스로 동작했을 때
		img.addMouseListener(new MouseAdapter() {
			@Override
			// 마우스로 클릭했을 때
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				new advertise();
			}
		});
		
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
				new main();
			}
		});
		
		// 폼이 동작했을 때
		addWindowListener(new WindowAdapter() {
			@Override
			// 폼이 닫히는 중일 때
			// windowClosed와 혼용 주의
			// windowClosed 사용 시, 폼이 닫히고 나면 프로그램이 자동으로 종료되어 다른 폼을 불러올 수 없음
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				new login();
			}
		});
	}
	
	void set() {
		//랜덤
		Random r=new Random();
		
		Timer timer=new Timer();
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				// Random의 r.nextInt()는 0부터 시작하므로 원하는 마지막 값보다 하나 적게 값을 넣고 결과값에 1 더해주기
				// 이미지 클릭 시, 클릭한 브랜드 광고가 떠야함. r.nextInt(199)+1 을 두 번 쓰면 서로 값이 다르게 나오므로 먼저 저장함.
				advertise.no=r.nextInt(199)+1;
				img.setIcon(new ImageIcon(new ImageIcon("./datafiles/advertise/"+(advertise.no)+"-"+(r.nextInt(1)+1)+".jpg").getImage().getScaledInstance(220, 110, 0)));
			}
		};
		//task를, 0 밀리초 이후부터, 2000 밀리초 간격으로
		timer.schedule(task, 0, 2000);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();
	}

}