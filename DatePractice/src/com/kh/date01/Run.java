package com.kh.date01;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) throws Exception{
		// 새로운 맴버 등록
		// 등록된 맴버의 정보조회
		//id,pw,birth_date 모두 입력 받을것
		
		Scanner sc = new Scanner(System.in);
		MemberDAO dao = new MemberDAO();
		
		System.out.print("id 입력 >> ");
		String id = sc.nextLine();
		System.out.print("pw 입력 >> ");
		String pw = sc.nextLine();
		System.out.print("생일 입력(19800101 형식) >> ");
		String birth_date = sc.nextLine();
		
		MemberDTO dto = new MemberDTO(id, pw, birth_date);
		int rs = dao.insert(dto);
		if (rs>0) {
			System.out.println("데이터 insert success");
		}else {
			System.out.println("fail");
		}
		
		ArrayList<MemberDTO>list = dao.selectAll();
		if(list != null) {
			for(MemberDTO dto2 : list) {
				System.out.println(dto2.getId()
							+ " : " + dto2.getPw()
							+ " : " + dto2.getBirth_date());
			}
		}
	}

}
