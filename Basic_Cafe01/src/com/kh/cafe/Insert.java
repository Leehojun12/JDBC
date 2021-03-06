package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert {
	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		//Connection 객체 생성 -> url, username, password
		// Statement 객체 생성 -> 오라클에서 sql 문을 전송해 쿼리의 결과를 요청할 수 있게 해주는 객체
		// cafe 테이블 새로운 데이터 insert ( seq_cafe.nextval, '아이스 아메리카노', 3000, sysdate)
		try(Connection con = DriverManager.getConnection(url,username, password);
			Statement stmt = con.createStatement();){
			
			System.out.println("DB접속 성공");
			String sql = "insert into cafe values(seq_cafe.nextval, '아이스아메리카노', 2000, sysdate)";
			// 행에 영향을 줄 경우 (insert, update, delete) executeUpdate() -> 행에 변화가 생김.
			// excecuteUpdate() 메서드의 반환값 = 인자값으로 넘겨준 쿼리문을 통해서 영향을 받은 행의 개수
			int rs = stmt.executeUpdate(sql);
			
			if(rs > 0) {
				System.out.println("success");
			}else {
				System.out.println("fail");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
