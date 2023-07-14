package ex06;

import java.util.Scanner;
import java.text.*;

public class Main {
	public static void main(String[] args) {
		ProductDAO dao=new ProductDAO();
		SaleDAO sdao=new SaleDAO();
		DecimalFormat df=new DecimalFormat("#,###원");
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		Scanner s=new Scanner(System.in);
		boolean run=true;
		while(run) {
			System.out.println("\n\n******* 상품관리 *****************************");
			System.out.println("--------------------------------------------");
			System.out.println("1.입력 |2.조회 |3.목록 |4.수정 |5.삭제 |0.종료");
			System.out.println("6.판매정보조회");
			System.out.println("--------------------------------------------");
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "1":
				ProductVO pro1=new ProductVO();
				System.out.print("상품이름>");
				String pname=s.nextLine();
				if(pname=="") {
					System.out.println("입력을 종료합니다.");
					break;
				}else {
					pro1.setPname(pname);
					while(true) {
						System.out.print("상품가격>");
						String pprice=s.nextLine();
						if(isNumber(pprice)) {
							pro1.setPprice(Integer.parseInt(pprice));
							dao.insert(pro1);
							System.out.println("상품등록완료!");
							break;
						}
					}
				}
				break;
			case "2":
				while(true) {
					System.out.print("\n조회할상품코드>");
					String pcode=s.nextLine();
					if(pcode=="") break;
					if(isNumber(pcode)) {
						ProductVO pro2=dao.read(Integer.parseInt(pcode));
						if(pro2.getPname()==null) {
							System.out.println("해당 상품이 존재하지 않습니다.");
						}else {
							System.out.println("상품이름:" + pro2.getPname());
							System.out.println("상품가격:" + df.format(pro2.getPprice()));
						}
					}
				}
				break;
			case "3":
				for(ProductVO vo:dao.list()) {
					System.out.printf("%d\t%-35s\t%s\n",
							vo.getPcode(), vo.getPname(), df.format(vo.getPprice()));
				}
				System.out.println(dao.list().size() + "개 상품이 등록되었습니다.");
				break;
			case "5":
				int intPcode=number("상품코드>");
				//if(isNumber(pcode)) {
					ProductVO pro2=dao.read(intPcode);
					if(pro2.getPname()==null) {
						System.out.println("삭제할 상품이 존재하지않습니다.");
					}else {
						System.out.println("상품이름:" + pro2.getPname());
						System.out.println("상품가격:" + df.format(pro2.getPprice()));
						System.out.print("삭제하실래요(y)?");
						String sel=s.nextLine();
						if(sel.equals("Y") || sel.equals("y") || sel.equals("ㅛ")) {
							try {
								dao.delete(intPcode);
								System.out.println("상품삭제완료!");
							}catch(Exception e) {
								System.out.println("상품삭제실패!");
							}
						}
					}
				//}
				break;
			case "6":
				System.out.print("조회할코드>");
				String pcode=s.nextLine();
				if(pcode=="") break;
				if(isNumber(pcode)==false) break;
				ProductVO pro3=dao.read(Integer.parseInt(pcode));
				if(pro3.getPname()==null) {
					System.out.println("조회할 상품이 존재하지 않습니다.");
				}else {
					System.out.println("상품이름:" + pro3.getPname());
					System.out.println("상품가격:" + df.format(pro3.getPprice()));
					//판매내역
					System.out.println("------------------------------------------------------------");
					System.out.println("판매수량\t판매가격\t\t판매금액\t\t판매일");
					System.out.println("------------------------------------------------------------");
					for(SaleVO vo:sdao.list(Integer.parseInt(pcode))) {
						System.out.printf("%d\t%s\t%s\t%s\n",
								vo.getQnt(), 
								df.format(vo.getSprice()),
								df.format(vo.getQnt()*vo.getSprice()),
								sdf.format(vo.getSdate())
						);
					}
				}
				break;
			case "0":
				run=false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}//switch
		}//while	
	}//main
	
	//숫자인지 판별하는 메서드
	public static boolean isNumber(String str) {
		try {
			int number=Integer.parseInt(str);
			return true;
		}catch(Exception e) {
			System.out.println("숫자로 입력하세요!");
			return false;
		}
	}
	
	public static int number(String title) {
		Scanner s=new Scanner(System.in);
		int number=0;
		while(true) {
			try {
				System.out.printf("%s", title);
				String str=s.nextLine();
				if(str=="") return 0;
				number=Integer.parseInt(str);
				return number;
			}catch(Exception e) {
				System.out.println("숫자로 입력하세요!");
			}
		}
	}
}//Main





