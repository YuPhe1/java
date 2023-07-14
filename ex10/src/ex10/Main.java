package ex10;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		while(run) {
			System.out.println("\n\n**************** 메인메뉴 *************************");
			System.out.println("-------------------------------------------------");
			System.out.println("1.학생관리|2.강좌관리|3.수강신청관리|0.프로그램종료");
			System.out.println("-------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("프로그램을 종료합니다!");
				break;
			case "1":
				Student.run();
				break;
			case "2":
				Course.run();
				break;
			case "3":
				Enrol.run();
				break;
			default:
				System.out.println("메뉴를 다시선택하세요!");
			}//switch
		}//while
	}//main
}//Main
