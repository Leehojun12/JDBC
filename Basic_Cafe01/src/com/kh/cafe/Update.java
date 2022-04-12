package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Update {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		try (Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("success");
			// 1번 메뉴의 이름을 '바닐라라떼', 사격을 4500원으로 변경
			
			String sql = "update cafe set product_name = '바닐라라떼', price = 4500 where product_id = 1";
			
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
