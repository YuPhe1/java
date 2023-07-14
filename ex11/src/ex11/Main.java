package ex11;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		boolean run=true;
		CampDAO cdao=new CampDAO();
		while(run) {
			System.out.println("\n\n**************** 캠핑장관리 *****************************");
			System.out.println("------------------------------------------------------");
			System.out.println("1.캠핑장목록|2.캠핑장조회|3.캠핑장정보수정|0.종료|");
			System.out.println("4.시설물관리|5.유형관리|6.캠핑장등록");
			System.out.println("------------------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				run=false;
				System.out.println("프로그램을 종료합니다!");
				break;
			case "1":
				List<CampVO> array=cdao.list();
				for(CampVO vo:array) {
					System.out.printf("%s\t%-20s\t%s\t%s\n",
							vo.getCno(), vo.getCname(), vo.getJuso(), vo.getTel());
				}
				System.out.println(array.size() + "개 등록되었습니다.");
				break;
			case "2":
				while(true) {
					System.out.print("\n조회할번호>");
					String cno=s.nextLine();
					if(cno=="") {
						System.out.println("조회를 취소합니다.");
						break;
					}else {
						CampVO cvo=cdao.read(cno);
						if(cvo.getCname()==null) {
							System.out.println("해당이 존재하지 않습니다.");
						}else {
							System.out.println("캠핑장이름:" + cvo.getCname());
							System.out.println("캠핑장주소:" + cvo.getJuso());
							System.out.println("캠핑장전화:" + cvo.getTel());
						}
					}
				}
				break;
			case "6":
				CampVO cvo=new CampVO();
				cvo.setCno(cdao.getNo());
				System.out.println("새로운번호>" + cvo.getCno());
				System.out.print("캠핑장이름>");
				String cname=s.nextLine();
				if(cname.equals("")) {
					System.out.println("등록을 취소합니다.");
				}else {
					System.out.print("캠핑장주소>");
					String juso=s.nextLine();
					System.out.print("캠핑장전화>");
					String tel=s.nextLine();
					cvo.setCname(cname);
					cvo.setJuso(juso);
					cvo.setTel(tel);
					//System.out.println(cvo.toString());
					cdao.insert(cvo);
					System.out.println("새로운 캠핑장이 등록되었습니다.");
				}
				break;
			case "3":
				while(true) {
					System.out.print("수정할캠핑장번호>");
					String cno=s.nextLine();
					if(cno=="") {
						System.out.println("수정을 취소합니다.");
						break;
					}else {
						cvo=cdao.read(cno);
						if(cvo.getCname()==null) {
							System.out.println("캠핑장이 존재하지 않습니다.");
						}else { //수정할 캠핑장이 있으면
							System.out.println("캠핑장이름:" + cvo.getCname());
							System.out.print("새로운캠핑장이름>");
							String nname=s.nextLine();
							if(nname != "") cvo.setCname(nname);
							
							System.out.println("캠핑장주소:" + cvo.getJuso());
							System.out.print("새로운캠핑장주소>");
							String njuso=s.nextLine();
							if(njuso != "") cvo.setJuso(njuso);
							
							System.out.println("캠핑장전화:" + cvo.getTel());
							System.out.print("새로운캠핑장전화>");
							String ntel=s.nextLine();
							if(ntel != "") cvo.setTel(ntel);
							
							//System.out.println(cvo.toString());
							System.out.print("수정하실래요(Y)?");
							String sel=s.nextLine();
							if(sel.equals("Y")||sel.equals("y")||sel.equals("ㅛ")) {
								//수정작업
								cdao.update(cvo);
								System.out.println("수정이 완료되었습니다.");
							}else {
								System.out.println("수정이 취소되었습니다.");
							}
							break;
						}
					}
				}
				break;
			default:
				System.out.println("메뉴를 다시선택하세요!");
			}//switch
		}//while
	}//main
}//Main
