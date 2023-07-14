package ex06;
import java.sql.*;
import java.util.*;

public class ProductDAO {
	Connection con=Database.connect();
	//상품삭제
	public void delete(int pcode)throws Exception {
		String sql = "delete from products where pcode=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, pcode);
		ps.execute();
	}
	
	//상품조회
	public ProductVO read(int pcode) {
		ProductVO vo=new ProductVO();
		try {
			String sql="select * from products where pcode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, pcode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vo.setPcode(rs.getInt("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setPprice(rs.getInt("pprice"));
			}
		}catch(Exception error) {
			System.out.println("상품조회:" + error.toString());
		}
		return vo;
	}
	
	//상품등록
	public void insert(ProductVO vo) {
		try {
			String sql = "INSERT INTO PRODUCTS(PCODE, PNAME, PPRICE) ";
				   sql +="VALUES(pcode_seq.nextval,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getPname());
			ps.setInt(2, vo.getPprice());
			ps.execute();
			
		}catch(Exception error) {
			System.out.println("상품등록:" + error.toString());
		}
	}
	
	//상품목록
	public List<ProductVO> list() {
		List<ProductVO> array=new ArrayList<>();
		try {
			String sql="select * from products order by pcode desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ProductVO vo=new ProductVO();
				vo.setPcode(rs.getInt("pcode"));
				vo.setPname(rs.getString("pname"));
				vo.setPprice(rs.getInt("pprice"));
				array.add(vo);
			}
		}catch(Exception error) {
			System.out.println("상품목록:" + error.toString());
		}
		return array;
	}
}
