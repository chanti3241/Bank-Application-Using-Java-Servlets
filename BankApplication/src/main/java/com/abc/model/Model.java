package com.abc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
	private String name;
	private String custmid;
	private int accno;
	private String pwd;
	private int balance;
	private String email;
	private int raccno;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	public ArrayList al = new ArrayList();
	public ArrayList sal = new ArrayList();
	public ArrayList ral = new ArrayList();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustmid() {
		return custmid;
	}
	public void setCustmid(String custmid) {
		this.custmid = custmid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Model() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication", "root", "Chanti3241");
		
	}
	public Boolean register() throws SQLException {
		String s = "insert into ABCBank values (?, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, name);
		pstmt.setString(2, custmid);
		pstmt.setInt(3, accno);
		pstmt.setString(4, pwd);
		pstmt.setInt(5, balance);
		pstmt.setString(6, email);
		
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public Boolean login() throws SQLException {
		String s = "select * from ABCBank where customer_id=? and password=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, custmid);
		pstmt.setString(2, pwd);
		
		ResultSet res = pstmt.executeQuery();
		while(res.next()==true){
			accno = res.getInt("account_no");
			return true;
		}
		return false;
	}
	public boolean checkBalance() throws SQLException {
		String s = "select balance from ABCBank where account_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		
		ResultSet res = pstmt.executeQuery();
		while(res.next()==true) {
			balance = res.getInt("balance");
			return true;
		}
		
		return false;
	}
	public boolean changePwd() throws SQLException {
		String s = "update ABCBank set password=? where account_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1, pwd);
		pstmt.setInt(2, accno);
		
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
		
	}
	public boolean transfer() throws SQLException {
		String s = "select * from ABCBank where account_no=?";
		PreparedStatement pstmt = con.prepareStatement(s);
		pstmt.setInt(1, raccno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
			String s2 = "update ABCBank set balance= balance-? where account_no=?";
			pstmt = con.prepareStatement(s2);
			pstmt.setInt(1, balance);
			pstmt.setInt(2, accno);
			int x = pstmt.executeUpdate();
			
			if(x > 0) {
				int x1 = res.getInt("BALANCE");
				if (x1 > 0) {
					String s3 = "update ABCBank set balance= balance+? where account_no=?";
					pstmt = con.prepareStatement(s3);
					pstmt.setInt(1, balance);
					pstmt.setInt(2, raccno);
					
					int x2 = pstmt.executeUpdate();
					if(x2 > 0) {
						String s4 = "insert into getStatement values(?,?,?)";
						pstmt = con.prepareStatement(s4);
						pstmt.setInt(1, accno);
						pstmt.setInt(2, raccno);
						pstmt.setInt(3, balance);
						
						int y = pstmt.executeUpdate();
						if(y>0) {
							return true;
						}else {
							return false;
						}
					}
				}else {
					return false;
				}
			}
		}
		return false;
	}
	public ArrayList getStatement() throws SQLException {
		String s = "select * from getStatement where account_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		res = pstmt.executeQuery();
		
		while(res.next()==true) {
			sal.add(res.getInt("account_no"));
			ral.add(res.getInt("raccno"));
			al.add(res.getInt("balance"));
			
		}
		return al;
	}
	public boolean applyLoan() throws SQLException {
		String s = "select * from ABCBank where account_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1, accno);
		ResultSet res = pstmt.executeQuery();
		
		while(res.next()==true) {
			name = res.getString("name");
			email = res.getString("email");
			return true;
		}
		return false;
	}
	
	
}
