package ex09;
import java.util.*;
import java.text.*;
public class Messages {
	public static void run(UserVO uvo) {
		Scanner s=new Scanner(System.in);
		MessageDAO ddao=new MessageDAO();
		UserDAO udao=new UserDAO();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean run=true;
		while(run) {
			System.out.println("\n\n**메시지관리**");
			System.out.println("----------------------------------------------------");
			System.out.println("1.받은메시지|2.보낸메시지|3.메시지전송|4.메시지삭제|0.메인메뉴");
			System.out.println("----------------------------------------------------");
			uvo=udao.read(uvo.getId());
			System.out.printf("이름:%s(%s)\t 포인트:%d\n",
					uvo.getUname(), uvo.getId(), uvo.getPoint());
			System.out.print("선택>");
			String menu=s.nextLine();
			switch(menu) {
			case "0":
				System.out.println("메인으로 돌아갑니다.");
				run=false;
				break;
			case "1"://받은메시지
				for(MessageVO vo:ddao.receiveList(uvo.getId())) {
					System.out.printf("%d\t%s(%s)\t%s\t%s\n",
							vo.getMid(), vo.getSender(),
							vo.getUname(), sdf.format(vo.getSdate()), vo.getMessage());
					System.out.println("---------------------------------------------------------------------------------");
				}
				//받은 메시지삭제
				while(true) {
					System.out.print("삭제할 받은 메시지번호>");
					String mid=s.nextLine();
					if(mid=="") break;
					System.out.print("삭제하실래요(y)? ");
					String sel=s.nextLine();
					if(sel.equals("Y")||sel.equals("y")||sel.equals("ㅛ")) {
						ddao.receiveDel(Integer.parseInt(mid));
						System.out.println("받은 메시지가 삭제되었습니다.");
						break;
					}
				}
				break;
			case "2"://보낸메시지
				for(MessageVO vo:ddao.sendList(uvo.getId())) {
					System.out.printf("%d\t%s(%s)\t%s\t%s\n",
							vo.getMid(), vo.getReceiver(),
							vo.getUname(), sdf.format(vo.getSdate()), vo.getMessage());
					System.out.println("-------------------------------------------------------------------------");
				}
				//보낸 메시지삭제
				while(true) {
					System.out.print("삭제할 보낸 메시지번호>");
					String mid=s.nextLine();
					if(mid=="") break;
					System.out.print("삭제하실래요(y)? ");
					String sel=s.nextLine();
					if(sel.equals("Y")||sel.equals("y")||sel.equals("ㅛ")) {
						ddao.sendDel(Integer.parseInt(mid));
						System.out.println("보낸 메시지가 삭제되었습니다.");
						break;
					}else {
						System.out.println("삭제가 취소되었습니다.");
					}
				}
				break;	
			case "3":
				while(true) {
					System.out.print("받을아이디>");
					String receiver=s.nextLine();
					if(receiver=="") {
						System.out.println("전송을 취소합니다.");
						break;
					}else {
						UserVO vo=udao.read(receiver);
						if(vo.getUname()==null) {
							System.out.println("해당 아이디가 존재하지 않습니다.");
						}else {
							//메시지 입력받아서 전송
							MessageVO mvo=new MessageVO();
							mvo.setSender(uvo.getId());
							mvo.setReceiver(receiver);
							System.out.print("메시지>");
							String message=s.nextLine();
							if(message=="") {
								System.out.println("메시지 전송을 취소합니다.");
							}else {
								mvo.setMessage(message);
								ddao.insert(mvo);
								udao.updatePoint(uvo.getId());
								System.out.println("메시지가 전송되었습니다.");
							}
							break;
						}
					}
				}
				break;
			default:
				System.out.println("메뉴를 선택하세요!");
			}
		}
	}
}
