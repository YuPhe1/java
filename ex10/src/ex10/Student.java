package ex10;

import java.util.*;
import java.text.*;
public class Student {
	public static void run() {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		StudentDAO sdao=new StudentDAO();
		EnrolDAO edao=new EnrolDAO();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		while(run) {
			System.out.println("\n\n**************** 학생관리 *************************");
			System.out.println("-------------------------------------------------");
			System.out.println("1.학생목록|2.학생조회|3.학생등록|0.메인메뉴");
			System.out.println("-------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("메인메뉴로 돌아갑니다!");
				break;
			case "1":
				List<StudentVO> array=sdao.list();
				System.out.println("-------------------------------------");
				for(StudentVO vo:array) {
					System.out.printf("%s\t%s\t%s\n",
							vo.getScode(), vo.getSname(), vo.getDept());
				}
				System.out.println("-------------------------------------");
				System.out.println(array.size() + "명 등록되었습니다.");
				break;
			case "2":
				while(true) {
					System.out.print("\n조회할학번>");
					String scode=s.nextLine();
					if(scode=="") {
						System.out.println("조회를 취소합니다.");
						break;
					}else {
						StudentVO vo=sdao.read(scode);
						if(vo.getScode()==null) {
							System.out.println("학생이 존재하지 않습니다.");
						}else {
							System.out.println("학생이름:" + vo.getSname());
							System.out.println("학생학과:" + vo.getDept());
							System.out.println("---------------------------------------------------");
							//수강신청한 강좌목록
							List<EnrolVO> carray=edao.clist(scode);
							if(carray.size()==0) {
								System.out.println("수강신청 내역이 없습니다.");
							}else {
								for(EnrolVO v:carray) {
									System.out.printf("%s\t%-20s\t%d\t%s\n",
											v.getCcode(),v.getCname(),
											v.getGrade(),sdf.format(v.getEdate()));
								}
							}
						}
					}
				}
				break;
			case"3":
				StudentVO vo=new StudentVO();
				vo.setScode(sdao.getCode());
				System.out.println("학번>" + vo.getScode());
				System.out.print("이름>");
				String sname=s.nextLine();
				if(sname=="") {
					System.out.println("등록을 취소합니다.");
				}else {
					System.out.print("학과>");
					String dept=s.nextLine();
					if(dept=="") {
						System.out.println("등록을 취소합니다.");
					}else {
						vo.setSname(sname);
						vo.setDept(dept);
						sdao.insert(vo);
						System.out.println("새로운 학생이 등록되었습니다.");
					}
				}
				break;
			default:
				System.out.println("메뉴를 다시선택하세요!");
			}//switch
		}//while
	}
}
