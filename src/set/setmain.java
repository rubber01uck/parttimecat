package set;
import java.sql.*;

import javax.swing.JOptionPane;

public class setmain {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306?allowLoadLocalInfile=true&serverTimezone=UTC","root","1234");
			Statement stmt=con.createStatement();
			stmt.execute("set global local_infile='on';");
			
			stmt.execute("drop database if exists parttimecat;");
			stmt.execute("create database parttimecat default character set utf8");
			stmt.execute("use parttimecat");
			
			stmt.execute("create table user(uno int primary key auto_increment, uname varchar(10), uid varchar(10), upw varchar(10), unick varchar(15), ugender int, ubirth date, ugrade varchar(15), udetail varchar(500));");
			stmt.execute("create table category(cno int primary key auto_increment, cname varchar(20));");
			stmt.execute("create table brand(bno int primary key auto_increment, bname varchar(20), cno int, bxx int, byy int, foreign key(cno) references category(cno));");
			stmt.execute("create table job(jno int primary key auto_increment, jname varchar(500), bno int, jmoney int, jpeople int, jday int, jtime int, jwork int, jgrade int, foreign key(bno) references brand(bno));");
			stmt.execute("create table advertise(ano int primary key auto_increment, aname varchar(300), adetail varchar(500));");
			stmt.execute("create table likes(lno int primary key auto_increment, jno int, ldate date, uno int, foreign key(jno) references job(jno), foreign key(uno) references user(uno));");
			stmt.execute("create table apply(apno int primary key auto_increment, jno int, apdate date, uno int, apok int, foreign key(jno) references job(jno), foreign key(uno) references user(uno));");
			
			String a[]= {"user","category","brand","job","advertise","likes","apply"};
			for (int i = 0; i < a.length; i++) {
				stmt.execute("load data local infile './datafiles/"+a[i]+".txt' into table "+a[i]+" ignore 1 lines;");
			}
			
			stmt.execute("drop user if exists 'user'@'localhost';");
			stmt.execute("create user 'user'@'localhost' identified by '1234'");
			stmt.execute("grant select, insert, update, delete on clothingstore.* to 'user'@'localhost'");
			
			JOptionPane.showMessageDialog(null, "셋팅 성공", "정보",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
			
	}

}
