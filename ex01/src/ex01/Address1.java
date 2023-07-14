package ex01;

import java.util.*;

public class Address1 {
	public static void run() {
		List<Student> array=new ArrayList<>();
		
		Student stu=new Student("20231", "홍길동", "인천 서구 경서동");
		array.add(stu);
		stu=new Student("20232", "심청이", "인천 부평구 계산동");
		array.add(stu);
		stu=new Student("20233", "강감찬", "서울 강서구 화곡동");
		array.add(stu);
		
		Scanner s=new Scanner(System.in);
		boolean run=true;
		
		while(run) {
			System.out.println("---------------------------------------------------------------");
			System.out.println("1.주소등록 | 2.주소목록 | 3.주소검색 | 4.주소수정 | 5.주소삭제 |0.종료");
			System.out.println("---------------------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "5":
				System.out.print("삭제할이름>");
				String delete=s.nextLine();
				boolean find=false;
				for(Student st:array) {
					if(delete.equals(st.getSname())) {
						find=true;
						array.remove(st);
						System.out.println("삭제완료!");
						break;
					}
				}
				if(!find) System.out.println(delete + "학생이 없습니다!");
				break;
			case "4":
				System.out.print("수정할이름>");
				String update=s.nextLine();
				find=false;
				for(Student st:array) {
					if(update.equals(st.getSname())) {
						find=true;
						System.out.println("이름:" + st.getSname());
						System.out.println("주소:" + st.getAddress());
						System.out.print("새로운주소>");
						String newAdd=s.nextLine();
						if(newAdd != "") {
							st.setAddress(newAdd);
							System.out.println("새로운 주소로 수정되었습니다.");
						}
					}
				}
				if(!find) System.out.println(update + "학생이 없습니다!");
				break;
			case "3":
				System.out.print("검색할이름>");
				String search=s.nextLine();
				find=false;
				for(Student st:array) {
					if(search.equals(st.getSname())) { //찾았을때
						System.out.println(st.toString());
						find=true;
					}
				}
				if(!find) System.out.println(search + "학생이 없습니다!");
				break;
			case "2":
				for(Student st:array) {
					System.out.println(st.toString());
				}
				break;
			case "1":
				stu=new Student();
				stu.setSno("2023" + (array.size()+1));
				System.out.println("학번>" + stu.getSno());
				
				System.out.print("이름>");
				stu.setSname(s.nextLine());
				
				System.out.print("주소>");
				stu.setAddress(s.nextLine());
				array.add(stu);
				System.out.println(array.size() + "명 등록완료!");
				break;
			case "0":
				run=false;
				System.out.println("프로그램이 종료됩니다.");
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요!");
				break;
			}//switch
		}//while
	}//run
}//class
