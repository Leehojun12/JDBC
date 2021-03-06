package com.kh.cafe;


import java.util.ArrayList;
import java.util.Scanner;

public class Run {
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		CafeDAO dao = new CafeDAO();
		// == 프로그램 ==
		// 1. 메뉴등록 2. 메뉴 수정 3. 메뉴 삭제 4. 메뉴 조회 5. 프로그램 종료
		while(true) {
		System.out.println("=== 카페 프로그램 ===");
		System.out.println("1. 메뉴 등록");
		System.out.println("2. 메뉴 수정");
		System.out.println("3. 메뉴 삭제");
		System.out.println("4. 메뉴 조회");
		System.out.println("5. 메뉴 전체 조회");
		System.out.println("6. 프로그램 종료");
		System.out.print(">> ");
		int menu = Integer.parseInt(sc.nextLine());
		
		if(menu == 1) {
			System.out.println("등록할 메뉴의 이름을 입력하시오");
			System.out.print(">> ");
			String name = sc.nextLine();
			System.out.println("등록할 메뉴의 가격을 입력하시오");
			System.out.print(">> ");
			int price = Integer.parseInt(sc.nextLine());
			
			CafeDTO dto = new CafeDTO(0,name,price,null);
			
			try{
				int rs= dao.insert(dto);
				if(rs > 0) {
					System.out.println("success");
				}else {
					System.out.println("fail");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(menu == 2) {
			System.out.print("수정할 제품 번호를 입력하시오 >> ");
			int id_num = Integer.parseInt(sc.nextLine());
			System.out.print("수정할 제품의 이름을 입력하세요 >> ");
			String name = sc.nextLine();
			System.out.print("수정할 제품의 가격을 입력하세요 >> ");
			int price = Integer.parseInt(sc.nextLine());
			
			CafeDTO dto = new CafeDTO(id_num, name, price, null);
			
			try{
				int rs= dao.update(dto);
				if(rs > 0) {
					System.out.println("success");
				}else {
					System.out.println("수정할 데이터가 없음");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(menu == 3) { // 고유값이 되는 product_id를 기준으로 삭제
			System.out.println("삭제할 메뉴의 번호 입력하시오");
			System.out.print(">> ");
			int id_num = Integer.parseInt(sc.nextLine());
			try{
				int rs= dao.delete(id_num);
				if(rs > 0) {
					System.out.println("success");
				}else {
					System.out.println("삭제할 데이터가 없음");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(menu == 4) {
			System.out.print("조회할 제품 번호를 입력하세요 >> ");
			int id_num = Integer.parseInt(sc.nextLine());
			try{
				CafeDTO rs= dao.select(id_num);
				if(rs != null) {
					System.out.println(rs);
				}else {
					System.out.println("조회 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(menu == 5) {
			try {
			ArrayList<CafeDTO> list = dao.selectAll();
			if(list != null) {
				for(CafeDTO dto : list) {
					System.out.println(dto.toString());
				}
			}else {
				System.out.println("조회 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		}else if(menu == 6) {
			
			System.out.println("프로그램을 종료합니다★★★");
			break;
			}
		}
	}
}
