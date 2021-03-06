package com.kh.std;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Run{

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StudentDAO dao = new StudentDAO();

		while(true) {
			System.out.println("=== 학생관리 프로그램 ===");
			System.out.println("1. 학생 정보 등록");
			System.out.println("2. 학생 정보 수정");
			System.out.println("3. 학생 정보 삭제");
			System.out.println("4. 학생 개별정보 조회");
			System.out.println("5. 학생 전체정보 조회");
			System.out.println("6. 프로그램 종료");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());
		
			if(menu == 1) {
				System.out.print("등록할 학생의 이름을 입력하시오 >> ");
				String name = sc.nextLine();
				System.out.print("등록할 학생의 번호를 입력하시오 >> ");
				String phone = sc.nextLine();
				System.out.print("등록할 학생의 생년원일을 입력하시오 >> ");
				
	            String birth_date = sc.nextLine();
	               
				StudentDTO dto = new StudentDTO(0,name, phone, birth_date);
				
				int rs = dao.insert(dto);
				if(rs>0)
					System.out.println("정보 등록!!");
				else
					System.out.println("정보 등록 실패~~");
			}else if(menu == 2) {
				System.out.print("수정할 학생의 no를 입력 하시오 >> ");
				int id_num = Integer.parseInt(sc.nextLine());
				System.out.print("수정할 학생의 이름을 입력하시오 >> ");
				String name = sc.nextLine();
				System.out.print("수정할 학생의 번호를 입력하시오 >> ");
				String phone = sc.nextLine();
				StudentDTO dto = new StudentDTO(id_num, name, phone, null);
				int rs =dao.update(dto);
				if(rs>0)
					System.out.println("정보 수정!!");
				else
					System.out.println("정보 수정 실패~~");
			}else if(menu == 3) {
				System.out.print("삭제할 학생의 no를 입력하시오 >> ");
				int id_num =Integer.parseInt(sc.nextLine());
				int rs = dao.delete(id_num);
				if(rs>0)
					System.out.println("정보 삭제!!");
				else
					System.out.println("정보 삭제 실패~~");
			}else if(menu == 4) {
				System.out.print("조회할 학생의 no을 입력하시오 >> ");
				int id_num = Integer.parseInt(sc.nextLine());
				StudentDTO rs = dao.select(id_num);
				if(rs != null)
					System.out.println(rs);
				else
					System.out.println("조회 실패~~!");
			}else if(menu == 5) {
				ArrayList<StudentDTO> list = dao.selectAll();
				if(list != null)
					for(StudentDTO dto : list) {
						System.out.println(dto.toString());
					}
				else
					System.out.println("조회 실패~~!");
			}else if(menu == 6) {
				System.out.println("Program Exit");
				break;
			}
			
		}
	}

}
