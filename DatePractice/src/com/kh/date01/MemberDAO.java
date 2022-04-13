package com.kh.date01;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MemberDAO {
	
	public int insert(MemberDTO dto) throws Exception{
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String pw = "kh";
		
		String sql = "insert into tbl_member values(?,?,to_date(?))";
		
		try(Connection con = DriverManager.getConnection(url, username, pw);
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getBirth_date());
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public ArrayList<MemberDTO> selectAll()	throws Exception{
		String url = "jdbc:oracle:thin:@localhost";
		String username = "kh";
		String password = "kh";
		
		String sql = "select * from tbl_member";
		
		try(Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			ArrayList<MemberDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String birth_date = toJavaString(rs.getDate("birth_date"));
				
				list.add(new MemberDTO(id, pw, birth_date));
			}
			return list;
		}
	}
	
	public String toJavaString(Date date) {
		// oracle date 타입의 데이터를 java의 String으로 변환 -> SimpleDateFormat
		// 생성자의 인자값을 String 으로 변환할때 어떤 형식으로 변환 할 것인지 format
		// oracle 월 (mm/MM), 분(mi)
		// java 월(MM) ,분(mm) 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date_str = sdf.format(date);
		return date_str;
	}
}
