package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLInjection {
	public static void main(String[] args) {
		
		select2("abc123", "5555 or 1 = 1" );
	}
	
	// PreparedStatement -> 객체를 생성할 때 인자값으로 넘겨주는 sql문(쿼리문)을 미리 DBMS에 컴파일하여 올려둠.
	// ? -> 추후에 인자값을 세팅해줄 자리 표시
	// 추후에 ?자리에 set되는 값을 말그대로 값 그 자체로 들어가게 됨(문자열 인식)
	// 5555 or 1 = 1 -> 이러한 식을 넣어도 값으로만 인식이 되기 때문에 SQLInject에 대한 방지처리가 됨.
	// Statement 객체보다 PreparedStatement 객체를 활용했을 때 보다 가독성도 높아짐.
	public static void select2(String id, String pw) {
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		String sql = "select * from tbl_member where id = ? and pw = ?";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = con.prepareStatement(sql);){
			ResultSet rs = pstmt.executeQuery(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			if(rs.next()) {
				System.out.println(rs.getString(1) + " , " + rs.getString(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void select(String id, String pw) {
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		try(Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();){
			String sql = "select * from tbl_member where id = '"+id+"' and pw = '"+ pw +"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println(rs.getString(1) + " , " + rs.getString(2));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
