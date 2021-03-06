package com.kh.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("db접속 성공");
			String sql = "insert into tbl_student values(seq_std.nextval, 'tom', '010-5432-2321', to_date('1980/05/24','yyyy/mm/dd'))";
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
