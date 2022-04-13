package com.kh.std;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

import com.kh.std.StudentDTO;

public class StudentDAO {
	
	BasicDataSource bds = new BasicDataSource();
	
	public StudentDAO() {
		bds.setUrl("jdbc:oracle:thin:@localhost");
		bds.setUsername("kh");
		bds.setPassword("kh");
		
		bds.setInitialSize(10);
	}
	
	public Connection getConnection() throws Exception{
		return bds.getConnection();
	}
	
	public int insert(StudentDTO dto) throws Exception{
		
		String sql = "insert into tbl_student values(seq_std.nextval, ?, ? , to_date(?))";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setString(3, dto.getBirth_date());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	public int update(StudentDTO dto) throws Exception{
		
		String sql = "update tbl_student set name = ?, phone = ? where no = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setString(1, dto.getName());
				pstmt.setString(2, dto.getPhone());
				pstmt.setInt(3, dto.getNo());
				
				int rs = pstmt.executeUpdate();
				return rs;
			}
	}
	public int delete(int id_num) throws Exception{
		
		String sql = "delete from tbl_student where no = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setInt(1, id_num);
				
				int rs = pstmt.executeUpdate();
				return rs;
			}
	}
	public StudentDTO select(int id_num) throws Exception{
		String sql = "select * from tbl_student where no = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
				
				pstmt.setInt(1, id_num);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int no = rs.getInt(1);
					String name = rs.getString(2);
					String phone = rs.getString(3);
					String birth_date = parseDate(rs.getDate(4));
					StudentDTO dto = new StudentDTO(no, name, phone, birth_date);
					return dto;
				}
				return null;
			}
	}
	public String parseDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		return sdf.format(date);
	}
	
	public ArrayList<StudentDTO> selectAll() throws Exception{
		String sql = "select * from tbl_student";
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
				ResultSet rs = pstmt.executeQuery();
				ArrayList<StudentDTO> list = new ArrayList<>();
				
				while(rs.next()) {
					int no = rs.getInt(1);
					String name = rs.getString(2);
					String phone = rs.getString(3);
					String birth_date = parseDate(rs.getDate(4));
					
					list.add(new StudentDTO(no, name, phone, birth_date));
				}
				return list;
			}
	}
}
