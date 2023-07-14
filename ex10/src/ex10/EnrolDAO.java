package ex10;
import java.util.*;
import java.sql.*;

public class EnrolDAO {
	Connection con=Database.connect();
	//수정신청
	public void insert(String ccode, String scode) {
		try {
			String sql="INSERT INTO ENROLS(SCODE, CCODE) VALUES(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ps.setString(2, ccode);
			ps.execute();
		}catch(Exception e) {
			System.out.println("수강신청:" + e.toString());
		}
	}
	
	//특정학생이 특정강좌를 신청했는지 조회
	public EnrolVO read(String scode, String ccode) {
		EnrolVO vo=new EnrolVO();
		try {
			String sql="select * from view_enrols where scode=? and ccode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ps.setString(2, ccode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setCcode(rs.getString("ccode"));
				vo.setCname(rs.getString("cname"));
				vo.setEdate(rs.getTimestamp("edate"));
				vo.setGrade(rs.getInt("grade"));
			}
		}catch(Exception e) {
			System.out.println("특정학생이 특정강좌를 신청했는지 조회:" + e.toString());
		}
		return vo;
	}
	
	
	//특정 강좌를 신청한 학생목록
	public List<EnrolVO> slist(String ccode){
		List<EnrolVO> array=new ArrayList<>();
		try {
			String sql="select * from view_enrols where ccode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, ccode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EnrolVO vo=new EnrolVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setCname(rs.getString("cname"));
				vo.setEdate(rs.getTimestamp("edate"));
				vo.setGrade(rs.getInt("grade"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("특정 강좌를 신청한 학생목록:" + e.toString());
		}
		return array;
	}
		
	//특정 학생이 신청한 강좌목록
	public List<EnrolVO> clist(String scode){
		List<EnrolVO> array=new ArrayList<>();
		try {
			String sql="select * from view_enrols where scode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EnrolVO vo=new EnrolVO();
				vo.setCcode(rs.getString("ccode"));
				vo.setCname(rs.getString("cname"));
				vo.setEdate(rs.getTimestamp("edate"));
				vo.setGrade(rs.getInt("grade"));
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("특정 학생이 신청한 강좌목록:" + e.toString());
		}
		return array;
	}
}
