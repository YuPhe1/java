package ex01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		boolean run=true;

		while(run) {
			System.out.println("--------------------------------------------------");
			System.out.println("1.타입 | 2.연산자 | 3.조건문 | 4.배열 | 5.주소관리 |0.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("프로그램이 완전 종료됩니다.");
				break;
			case "1":
				TypeSample.run();
				break;
			case "2":
				Operation.run();
				break;
			case "5":
				Address1.run();
			}//switch
		}//while
	}//main
}//class
