package ex10;

import java.util.*;

public class Enrol {
	public static void run() {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		StudentDAO sdao=new StudentDAO();
		EnrolDAO edao=new EnrolDAO();
		CourseDAO cdao=new CourseDAO();
		while(run) {
			System.out.println("\n\n**************** 수강관리 *************************");
			System.out.println("-------------------------------------------------");
			System.out.println("1.수강목록|2.수강신청|3.수강취소|4.성적등록|0.메인메뉴");
			System.out.println("-------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("메인메뉴로 돌아갑니다!");
				break;
			case "2":
				System.out.print("학번>");
				String scode=s.nextLine();
				if(scode=="") {
					System.out.println("수강신청을 취소합니다.");
				}else {
					StudentVO svo=sdao.read(scode);
					if(svo.getSname()==null) {
						System.out.println("해당학생이 존재하지 않습니다.");
					}else {
						System.out.printf("%s(%s)\n", 
								svo.getSname(),svo.getDept());
						while(true) {
							//수강신청한 목록
							System.out.println("\n-----------------------------------");
							List<EnrolVO> carray=edao.clist(scode);
							if(carray.size()==0) {
								System.out.println("수강신청 내역이 없습니다.");
							}else {
								for(EnrolVO v:carray) {
									System.out.printf("%s:%s|",
											v.getCcode(), v.getCname());
								}
							}
							System.out.println("\n-----------------------------------------------");
							
							//수강신청할 목록
							List<CourseVO> aarray=cdao.list();
							for(CourseVO v:aarray) {
								System.out.printf("%s:%s|",
										v.getCcode(), v.getCname());
							}
							System.out.println("\n----------------------------------------------");
							
							System.out.print("과목코드>");
							String ccode=s.nextLine();
							if(ccode=="") {
								System.out.println("수강신청을 취소합니다.");
								break;
							}
							
							//이미 수강신청했는지 체크
							EnrolVO vo=edao.read(scode, ccode);
							if(vo.getSname()!=null) {
								System.out.println("이미 수강신청을 했습니다.");
							}else {
								CourseVO cvo=cdao.read(ccode);
								if(cvo.getCname()==null) {
									System.out.println("해당 강좌가 존재하지 않습니다.");
								}else {
									//수정신청
									edao.insert(ccode, scode);
								}
							}
						}
					}
				}
				break;
			case "3":
				
				break;
			case "4":
				
				break;
			default:
				System.out.println("메뉴를 다시선택하세요!");
			}//switch
		}//while
	}
}
