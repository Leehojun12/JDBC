package com.kh.std;

import java.sql.Date;

public class StudentDTO {
	private int no;
	private String name;
	private String phone;
	private String birth_date;
	
	
	public StudentDTO(int no, String name, String phone, String birth_date) {
		super();
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.birth_date = birth_date;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	
	@Override
	public String toString() {
		return no + " : " + name + " : " + phone +" : " + birth_date;
	}
	
}
