package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("success");
			
			String sql = "update tbl_student set name = '김영수', phone = '010-1234-1234', birth_date = '1985/12/20' where no = 1";
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
