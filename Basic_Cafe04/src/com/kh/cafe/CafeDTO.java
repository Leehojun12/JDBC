package com.kh.cafe;



import java.sql.Date;
import java.text.SimpleDateFormat;

public class CafeDTO {
	
	private int product_id;
	private String product_name;
	private int price;
	private String register_date;
	
	
	public CafeDTO(int product_id, String product_name, int price, String register_date) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.register_date = register_date;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	@Override
	public String toString() {
		return product_id + " : " + product_name+ " : "+ price+" : "+ register_date + " : "+ check_date();
	}
	
	// register_date 맴버필드를 이용해서 현재시간과 등록시간의 차이를 계산해 String값으로 반환해주는 메서드
	public String check_date() {
		// System.currentTimeMillis() -> 현재시간이 long형으로 반환됨
		
		// register_date 를 먼저 자바의 Date 타입으로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		// sdf의 형식처럼 생긴 register_date String 값을 자바의 Date형으로 변환하는 작업
		try {
			java.util.Date date = sdf.parse(this.register_date);
			// getTime : 자바의 date 타입 데이터를 long 형으로 변환해서 반환해주는 메서드
			long origin_date = date.getTime(); // 등록일을 long 형 변환
			long cur_date = System.currentTimeMillis(); // 현재 시간
			
			// 두 시간간의 갭 -> 초 단위로 계산
			long gap_time = (cur_date - origin_date) / 1000;
			
			int sec = 60;
			int min = 60;
			int hour = 24;
			int day = 30;
			int month = 12;
			
			// 1분 미만이라고 한다면 '~초 전'
			if(gap_time < sec) {
				return gap_time + "초 전";
			}else if((gap_time/=sec) < min) { // 1시간 미만이라고 한다면 '~분 전'
				return gap_time + "분 전";
			}else if((gap_time/= min) < hour) {// 1일 미만이라면 '~시간 전'
				// gap_time은 60이상의 값을 가지고 있음.
				// 60분 기준으로 분단위
				return gap_time + "시간 전";
			}else if((gap_time /= hour) < day) { // 30일 미만이라면 '~일 전'
				return gap_time + "일 전";
			}else if((gap_time/= day) < month) {
				return gap_time + "개월 전";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		// String 데이트 값을 가지고 있는 register_date -> 일단 long형으로 변환
		
	}
}
