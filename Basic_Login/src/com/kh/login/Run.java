package com.kh.login;

import java.util.Scanner;

public class Run {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		LoginDAO dao = LoginDAO.getInstance();
		int count = 0;

		while (true) {
			System.out.println("** 멤버 서비스 프로그램 **");
			System.out.println("1. 로그인");
			System.out.println("2. 회원 가입");
			System.out.println("3. 회원 탈퇴");
			System.out.println("4. 프로그램 종료");
			System.out.print(">> ");
			int menu = Integer.parseInt(sc.nextLine());

			if (menu == 1) {
				login: while (true) {
					System.out.print("아이디 입력 >> ");
					String id = sc.nextLine();
					System.out.print("비밀번호 입력 >> ");
					String pw = sc.nextLine();

					try {
						LoginDTO rs = dao.select(id, pw);
						if (rs != null) {
							System.out.println("로그인 성공");
							System.out.println(dao.selectNick(id)+ "님 환영합니다!\n 오늘의 날씨는 흐림, 평균온도는 12.5도입니다.");
							break login;
						} else {
							System.out.println("로그인 실패");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (menu == 2) {
				newID: while (true) {
					System.out.print("아이디 입력 >> ");
					String id = sc.nextLine();
					System.out.print("비밀번호 입력 >> ");
					String pw = sc.nextLine();
					System.out.print("닉네임 입력 >> ");
					String nickname = sc.nextLine();

					LoginDTO dto = new LoginDTO(id, pw, nickname);

					try {
						LoginDTO rs = dao.selectID(id);
						if (rs != null) {

							System.out.println("중복된 아이디가 있습니다.");

						} else {
							System.out.println("사용가능한 아이디입니다.");
							int ra = dao.insert(dto);
							if (ra > 0) {
								System.out.println("회원가입 완료");
								break newID;
							} else {
								System.out.println("회원가입 실패");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (menu == 3) {
				delete: while (true) {
					System.out.print("탈퇴할 회원의 아이디 입력 >>");
					String id = sc.nextLine();
					System.out.print("탈퇴할 회원의 비밀번호 입력 >>");
					String pw = sc.nextLine();

					LoginDTO dto = new LoginDTO(id, pw, null);

					try {
						System.out.println(dao.selectNick(id) + "님 정말 삭제를 하시겠습니까?");
						System.out.println("yes\tno");
						System.out.print(">> ");
						String anwser = sc.nextLine();
						if (anwser.equals("yes")) {
							int rs = dao.delete(dto);
							System.out.println("회원 삭제가 완료되었습니다");
							break delete;
						} else if (anwser.equals("no")) {
							break delete;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (menu == 4) {
				System.out.println("프로그램 종료***");
				break;
			}
		}
	}
}
