package ex10;
import java.sql.*;
import java.util.*;

public class CourseDAO {
	Connection con=Database.connect();
	//강좌조회
	public CourseVO read(String ccode) {
		CourseVO vo=new CourseVO();
		try {
			String sql="SELECT * FROM Courses where ccode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, ccode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vo.setCcode(rs.getString("ccode"));
				vo.setCname(rs.getString("cname"));
			}
		}catch(Exception e) {
			System.out.println("강좌조회 오류:" + e.toString());
		}
		return vo;
	}
	
	//강좌등록
	public void insert(CourseVO vo) {
		try {
			String sql="INSERT INTO Courses(cCODE, cNAME) VALUES(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getCcode());
			ps.setString(2, vo.getCname());
			ps.execute();
		}catch(Exception e) {
			System.out.println("강좌등록 오류:" + e.toString());
		}
	}
	
	//새로운 강좌번호 구하기
	public String getCode() {
		String ccode="";
		try {
			String sql="SELECT MAX(cCODE)+1 NCODE FROM courses";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				ccode=rs.getString("ncode");
			}
		}catch(Exception e) {
			System.out.println("새로운 강좌번호 구하기 오류:" + e.toString());
		}
		return ccode;
	}
	
	//강좌목록
	public List<CourseVO> list(){
		List<CourseVO> array=new ArrayList<>();
		try {
			String sql="SELECT * FROM courses order by ccode desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CourseVO vo=new CourseVO();
				vo.setCcode(rs.getString("ccode"));
				vo.setCname(rs.getString("cname"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("강좌목록 오류:" + e.toString());
		}
		return array;
	}
}
