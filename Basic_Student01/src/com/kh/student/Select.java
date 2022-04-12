package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		try (Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("success");
			
			String sql = "select * from tbl_student where no = 1";
			String sql1 = "select * from tbl_student";
			ResultSet rs = stmt.executeQuery(sql);
//			if(rs.next()) {
//				System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getString(3) +" "+ rs.getDate(4));
//			}
			while(rs.next()) {
				System.out.println(rs.getInt("no") + " " + rs.getString("name") + " " + rs.getString("phone") + " " + rs.getDate("birth_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
