package pro;
import java.sql.*;
import java.util.ArrayList;

public class DBMgr {
	static String sql;
	
	static Connection con=null;
	static Statement stmt=null;
	static ResultSet rs=null;
	
	DBCon conn;
	
	public DBMgr () {
		conn=new DBCon();
	}
	
	public ArrayList<Bean> update() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a1() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a2() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a3() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a4() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a5() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a6() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a7() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a8() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a9() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				bean.setI(rs.getString(9));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public ArrayList<Bean> a10() {
		ArrayList<Bean>list=new ArrayList<>();
		Bean bean;
		
		try {
			con=conn.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()) {
				bean=new Bean();
				
				bean.setA(rs.getString(1));
				bean.setB(rs.getString(2));
				bean.setC(rs.getString(3));
				bean.setD(rs.getString(4));
				bean.setE(rs.getString(5));
				bean.setF(rs.getString(6));
				bean.setG(rs.getString(7));
				bean.setH(rs.getString(8));
				bean.setI(rs.getString(9));
				bean.setJ(rs.getString(10));
				
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
}
