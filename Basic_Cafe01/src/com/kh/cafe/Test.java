package com.kh.cafe;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
	public static void main(String[] args) {
		/*
		 *	자바 프로그램 - DB 연동
		 *	JDBC ( Java Database Connectivity)
		 *	: 자바에서 데이터베이스 연결해 데이터를 주고 받을 수 있게 해주는 라이브러리
		 *	-> sql 명령문을 자바에서 수행하면 -> DB전달
		 *	-> DBMS의 종류에 따라서 JDBC 종류도 달라짐
		 *	-> Oracle -> OJDBC
		 */
		// Connection 객체 생성 -> getConnection(url, username, userpw) 
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost","kh","kh")){
			System.out.println("DB 접속 성공~!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
