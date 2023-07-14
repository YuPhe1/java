package ex10;

import java.util.*;
import java.text.*;
public class Course {
	public static void run() {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		CourseDAO cdao=new CourseDAO();
		EnrolDAO edao=new EnrolDAO();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		while(run) {
			System.out.println("\n\n**************** 강좌관리 *************************");
			System.out.println("-------------------------------------------------");
			System.out.println("1.강좌목록|2.강좌조회|3.강좌등록|0.메인메뉴");
			System.out.println("-------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("메인메뉴로 돌아갑니다!");
				break;
			case "1":
				List<CourseVO> array=cdao.list();
				System.out.println("----------------------------------------------");
				for(CourseVO vo:array) {
					System.out.printf("%s\t%s\n", vo.getCcode(), vo.getCname());
				}
				System.out.println("----------------------------------------------");
				System.out.println(array.size() + "개 강좌가 등록되었습니다.");
				break;
			case "2":
				while(true) {
					System.out.print("\n조회할강좌번호>");
					String ccode=s.nextLine();
					if(ccode=="") {
						System.out.println("조회를 취소합니다.");
						break;
					}else {
						CourseVO vo=cdao.read(ccode);
						if(vo.getCcode()==null) {
							System.out.println("강좌가 존재하지 않습니다.");
						}else {
							System.out.println("강좌이름:" + vo.getCname());
							System.out.println("------------------------------------");
							//학생목록
							List<EnrolVO> sarray=edao.slist(ccode);
							if(sarray.size()==0) {
								System.out.println("수강신청한 학생이 없습니다.");
							}else {
								for(EnrolVO v:sarray) {
									System.out.printf("%s\t%s\t%d\t%s\n",
											v.getScode(), v.getSname(),
											v.getGrade(), sdf.format(v.getEdate()));
								}
							}
						}
					}
				}
				break;
			case "3":
				CourseVO vo=new CourseVO();
				vo.setCcode(cdao.getCode());
				System.out.println("강좌번호>" + vo.getCcode());
				System.out.print("강좌이름>");
				String cname=s.nextLine();
				if(cname=="") {
					System.out.println("등록을 취소합니다.");
				}else {
					vo.setCname(cname);
					cdao.insert(vo);
					System.out.println("새로운 강좌가 등록되었습니다.");
				}
				break;
			default:
				System.out.println("메뉴를 다시선택하세요!");
			}//switch
		}//while
	}
}
