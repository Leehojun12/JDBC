package com.kh.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class LoginDAO {

	private BasicDataSource bds = new BasicDataSource();
	private static LoginDAO instance = null;

	public LoginDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost");
		bds.setUsername("kh");
		bds.setPassword("kh");
		bds.setInitialSize(10);
	}

	public static LoginDAO getInstance() {
		if (instance == null) {
			instance = new LoginDAO();
		}
		return instance;
	}

	public String selectNick(String id) throws Exception {

		String sql = "select nickname from member_tbl where id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			return null;
		}
	}

	public LoginDTO selectID(String id) throws Exception {

		String sql = "select * from member_tbl where id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String login_id = rs.getString(1);
				LoginDTO dto = new LoginDTO(id, null, null);
				return dto;
			}
			return null;
		}
	}

	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}

	public int insert(LoginDTO dto) throws Exception {

		String sql = "insert into member_tbl values(?,?,?)";
		try (Connection con = this.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getNickname());

			int rs = pstmt.executeUpdate();
			return rs;
		}
	}

	public int delete(LoginDTO dto) throws Exception {

		String sql = "delete from member_tbl where id = ? and pw = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());

			int rs = pstmt.executeUpdate();
			return rs;

		}
	}

	public LoginDTO select(String id, String pw) throws Exception {
		String sql = "select * from member_tbl where id =? and pw = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String login_id = rs.getString(1);
				String login_pw = rs.getString(2);
				String login_nick = rs.getString(3);
				LoginDTO dto = new LoginDTO(id, pw, null);
				return dto;
			}
			return null;
		}
	}
}
