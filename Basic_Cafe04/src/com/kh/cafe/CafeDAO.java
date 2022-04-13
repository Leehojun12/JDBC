package com.kh.cafe;



import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class CafeDAO {
	
	// BasicDataSource 클래스 인스턴스 생성 -> DBCP를 만들어주는 클래스
	private BasicDataSource bds = new BasicDataSource();
	
	// DBCP를 구성하기 위해 필요한 설정값 셋팅
	public CafeDAO() {
		// CafeDAO 생성자가 호출되면 자동으로 bds에 설정값을 부여하도록
		bds.setUrl("jdbc:oracle:thin:@localhost");
		bds.setUsername("kh");
		bds.setPassword("kh");
		
		// 커넥션 개수 셋팅
		bds.setInitialSize(10);
	}
	
	// 커넥션 풀에 있는 커넥션을 꺼내서 반환해주는 메서드
	public Connection getConnection() throws Exception {
		return bds.getConnection();
	}
	
	
	

	public int insert(CafeDTO dto) throws Exception{		
		
		String sql = "insert into cafe values(seq_cafe.nextval, ?,?,sysdate)";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, dto.getProduct_name());
			pstmt.setInt(2, dto.getPrice());
			
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	
	public int update(CafeDTO dto) throws Exception{
		
		String sql = "update cafe set product_name =?, price =? where product_id = ?";
		
		try(Connection con =this.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
				
				pstmt.setString(1, dto.getProduct_name());
				pstmt.setInt(2, dto.getPrice());
				pstmt.setInt(3, dto.getProduct_id());
				int rs = pstmt.executeUpdate();
				return rs;
		}
	}
	
	public int delete(int id_num) throws Exception{
		
		String sql = "delete from cafe where product_id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, id_num);
			int rs = pstmt.executeUpdate();
			return rs;
		}
	}
	public CafeDTO select(int id_num) throws Exception{		

		String sql = "select * from cafe where product_id = ?";
		
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, id_num);
			ResultSet rs =pstmt.executeQuery();
			if(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				String register_date = parseDate(rs.getDate(4));
				CafeDTO dto = new CafeDTO(product_id, product_name, price, register_date);
				return dto;
			}
			return null;
		}
	}
	
	public ArrayList<CafeDTO> selectAll() throws Exception{

		String sql = "select * from cafe";
	
		try(Connection con = this.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			// 인자값이 셋팅이 필요없는 경우에는 바로 쿼리문으로 실행하면 됨
			ResultSet rs = pstmt.executeQuery();
			ArrayList<CafeDTO> list = new ArrayList<>();
			
			while(rs.next()) {
				int product_id = rs.getInt(1);
				String product_name = rs.getString(2);
				int price = rs.getInt(3);
				String register_date = parseDate(rs.getDate(4));
				
				list.add(new CafeDTO(product_id, product_name, price, register_date));
			}
			return list;
		}
	}
	
	public String parseDate(Date date) {
		// 년/월/일 24시:분:초
		// 자바에서 hh -> default 12시간 기준으로 표기됨
		// HH -> 24시간 표기
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
}
