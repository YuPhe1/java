package ex04;

public class Main {

	public static void main(String[] args) {
		StudentDAO dao=new StudentDAO();
		//조회
		//System.out.println(dao.read(11));
		
		//입력
		//Student stu=new Student(20,"조향덕","인천","010-9646-9022");
		//dao.insert(stu);
		
		//수정
		Student stu=new Student(13,"강감찬","서울","010-0000-0000");
		dao.update(stu);
		
		//삭제
		//dao.delete(11);
		
		//목록출력
		for(Student vo:dao.list()) {
			System.out.println(vo.toString());
		}
	}
}
