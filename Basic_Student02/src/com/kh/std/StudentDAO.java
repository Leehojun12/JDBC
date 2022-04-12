package com.kh.std;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {

		private String url = "jdbc:oracle:thin:@localhost";
		private String username = "kh";
		private String password = "kh";
		
	    private ArrayList<StudentDTO> list = new ArrayList<>();
	    
		public int insert(StudentDTO dto) throws Exception{
			try(Connection con = DriverManager.getConnection(url,username, password);
				Statement stmt = con.createStatement();){
				
				String sql = "insert into tbl_student values(seq_cafe.nextval,'"+ dto.getName() +"','"+ dto.getPhone() +"',to_date('"+dto.getBirth_date()+"','yyyy/mm/dd'))";
				int rs = stmt.executeUpdate(sql);
				return rs;
			}
		}
		
		public int update(int id_num, String name, String phone) throws Exception{
			try(Connection con = DriverManager.getConnection(url,username, password);
					Statement stmt = con.createStatement();){
					
					String sql = "update tbl_student set name ='" +name +"',phone = '"+phone+ "' where no ="+id_num;
					int rs = stmt.executeUpdate(sql);
					return rs;
				}
		}
		
		public int delete(int id_num)	throws Exception{
			try(Connection con = DriverManager.getConnection(url,username, password);
					Statement stmt = con.createStatement();){
					
					String sql = "delete from tbl_student where no =" +id_num;
					int rs = stmt.executeUpdate(sql);
					return rs;
			}
		}
		
		public String select(int id_num) throws Exception{
			try(Connection con = DriverManager.getConnection(url,username, password);
					Statement stmt = con.createStatement();){
					
					String data = null;
					String sql = "select * from tbl_student where no ="+ id_num;
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						data = rs.getInt(1) + " : " + rs.getString(2) + " : " + rs.getString(3) + " : " + rs.getDate(4);
					}
					return data;
			}
		}
		public String Allselect() throws Exception{
			try(Connection con = DriverManager.getConnection(url,username, password);
					Statement stmt = con.createStatement();){
					
					String data = null;
					String sql = "select * from tbl_student";
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						data = rs.getInt("no") + " : " + rs.getString("name") + " : " + rs.getString("phone") + " : " + rs.getDate("birth_date");
					}
					return data;
			}
		}
}
