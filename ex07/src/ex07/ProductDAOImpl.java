package ex07;

import java.util.*;
import java.sql.*;

public class ProductDAOImpl implements ProductDAO{
	Connection con=Database.connect();
	
	@Override
	public void insert(ProductVO vo) throws Exception {
		String sql="INSERT INTO PRODUCTS(PCODE, PNAME, PPRICE) VALUES(SEQ_PCODE.NEXTVAL, ?, ?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, vo.getPname());
		ps.setInt(2, vo.getPprice());
		ps.execute();
	}

	@Override
	public void update(ProductVO vo) throws Exception {
		String sql="UPDATE PRODUCTS SET PNAME=?, PPRICE=? WHERE PCODE=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, vo.getPname());
		ps.setInt(2, vo.getPprice());
		ps.setInt(3, vo.getPcode());
		ps.execute();
	}
		

	@Override
	public void delete(int pcode) throws Exception {
		String sql="DELETE FROM PRODUCTS WHERE PCODE=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, pcode);
		ps.execute();
	}

	@Override
	public ProductVO read(int pcode) throws Exception {
		ProductVO vo=new ProductVO();
		String sql="SELECT * FROM PRODUCTS WHERE PCODE=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, pcode);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			vo.setPcode(rs.getInt("pcode"));
			vo.setPname(rs.getString("pname"));
			vo.setPprice(rs.getInt("pprice"));
		}
		return vo;
	}

	@Override
	public List<ProductVO> list() throws Exception {
		List<ProductVO> array=new ArrayList<>();
		String sql="SELECT * FROM PRODUCTS ORDER BY PCODE DESC";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ProductVO vo=new ProductVO();
			vo.setPcode(rs.getInt("pcode"));
			vo.setPname(rs.getString("pname"));
			vo.setPprice(rs.getInt("pprice"));
			array.add(vo);
		}
		return array;
	}
}
