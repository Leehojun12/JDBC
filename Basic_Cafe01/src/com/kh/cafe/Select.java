package com.kh.cafe;

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
			// 1번 메뉴의 이름을 '바닐라라떼', 사격을 4500원으로 변경
			
			/*String sql = "select * from cafe where product_id = 1";
			
			// executeQuery() 를 통해 반환하는 결과 -> ResultSet(질의 결과)
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs);// ResultSet의 주소값 반환
			// ResultSet이 반환될 때 커서라는 개념이 존재
			// 이 커서는 가장 첫 번째 행의 바로 윗쪽을 가리키고 있음.
			// 첫번 째 행을 가리고 싶으면 커서를 내려줘아함 -> rs.next();
//			System.out.println(rs.next());
//			System.out.println(rs.next());
			// 커서가 데이터를 가리키고 있는 상태에서 값을 얻어내는 방법
			// 1. 오라클의 인덱스를 사용하는 방법(product_id(1) product_name(2) price(3) register_date(4))
//			System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getInt(3) +" "+ rs.getDate(4));
			
			// 만약 커서를 내렸을 때 행이 존재한다면 값을 출력하겠다.
			if(rs.next()) {
				System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getInt(3) +" "+ rs.getDate(4));
			}
//			if(rs.next()) {
//				System.out.println(rs.getInt("product_id")
//						+ " : " + rs.getString("product_name")
//						+ " : " + rs.getInt("price")
//						+ " : " + rs.getDate("register_date"));
//			} */
			String sql = "select * from cafe";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getInt(1) +" "+ rs.getString(2) +" "+ rs.getInt(3) +" "+ rs.getDate(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
